package org.example.validator;

import org.apache.commons.io.FileUtils;
import org.example.exception.InvalidFileFormatException;
import org.example.exception.InvalidOutputPathException;
import org.example.exception.InvalidPrefixException;
import org.example.exception.NoAccessException;
import org.example.service.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ArgsValidator {

    public void validateOutputPath(String outputPath) {
        Path path = Paths.get(outputPath);
        if (Files.exists(path) && !Files.isDirectory(path)) {
            throw new InvalidOutputPathException("Output path is not a directory: " + path);
        }
        if(!Files.exists(path)){
            try {
                FileUtils.forceMkdir(path.toFile());
            } catch (IOException e) {
                throw new InvalidOutputPathException("Couldn't create a directory: " + e.getMessage());
            }
        }
        if(!FileService.isReadableAndWritable(path))
            throw new NoAccessException("There is no access to the path: " + path);
    }

    public void validatePrefix(String prefix) {
        if (prefix.isBlank()) {
            throw new InvalidPrefixException("Prefix cannot be empty or blank");
        }
        if (!prefix.matches("^[^\\\\/:*?\"<>|]+$")) {
            throw new InvalidPrefixException("Prefix contains invalid characters: " + prefix);
        }
    }

    public void validateFiles(List<String> fileNames){
        for(String fileName : fileNames){
            if(!fileName.toLowerCase().endsWith(".txt"))
                throw new InvalidFileFormatException("The file format should be .txt: " + fileName);
            if(!FileService.isFileAccessibleForRead(Paths.get(fileName))){
                throw new NoAccessException("Couldn't access the file: " + fileName);
            }
        }
    }
}
