package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.example.enums.DataTypeEnum;
import org.example.exception.FileFilterException;
import org.example.model.Params;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class FileService {

    private final StatisticService statisticService;

    public static boolean isFileAccessibleForRead(Path filePath) {
        return Files.exists(filePath) && Files.isReadable(filePath);
    }

    public static boolean isReadableAndWritable(Path filePath){
        return Files.isWritable(filePath) && Files.isReadable(filePath);
    }

    public static boolean isRegularFile(Path filePath){
        return Files.exists(filePath) && Files.isRegularFile(filePath);
    }

    public void filesFilter(Params params) {
        List<LineIterator> iterators = new ArrayList<>();
        try {
            for (Path filePath : params.filePaths()) {
                if (isRegularFile(filePath)) {
                    iterators.add(FileUtils.lineIterator(filePath.toFile(), StandardCharsets.UTF_8.name()));
                }
            }
            boolean isAllFilesWritten = false;
            while (!isAllFilesWritten) {
                isAllFilesWritten = true;
                for (LineIterator iterator : iterators) {
                    if (iterator.hasNext()) {
                        writeData(params, iterator);
                        isAllFilesWritten = false;
                    }
                }
            }
            statisticService.countAvg();
            log.info("Filtering has been completed successfully");
        } catch (IOException e) {
            throw new FileFilterException("Couldn't filter the file: " + e.getMessage());
        }
    }

    private void writeData(Params params, LineIterator iterator) throws IOException {
        String data = iterator.next();
        DataTypeEnum dataTypeEnum = extractOutputFileType(data);
        Path outputPath = params.getOutputPathWithPrefix(dataTypeEnum);
        if(!params.aEnabled())
            clearFile(outputPath, dataTypeEnum);
        Files.writeString(
                outputPath,
                data + System.lineSeparator(),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }

    private void clearFile(Path path, DataTypeEnum dataTypeEnum) throws IOException {
        if (Files.exists(path) && !dataTypeEnum.isCleared()) {
            Files.write(path, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
            dataTypeEnum.setCleared(true);
        }
    }

    private DataTypeEnum extractOutputFileType(String data) {
        try {
            Long value = Long.parseLong(data);
            statisticService.addInteger(value);
            return DataTypeEnum.INTEGERS;
        } catch (NumberFormatException exception) {
            try {
                Double value = Double.parseDouble(data);
                statisticService.addFloat(value);
                return DataTypeEnum.FLOATS;
            } catch (NumberFormatException exception1) {
                statisticService.addString(data);
                return DataTypeEnum.STRING;
            }
        }
    }
}
