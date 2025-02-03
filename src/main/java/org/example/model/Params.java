package org.example.model;

import org.example.enums.DataTypeEnum;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public record Params(
        String outputPath,
        String prefix,
        boolean aEnabled,
        boolean sEnabled,
        boolean fEnabled,
        List<Path> filePaths
) {

    public Path getOutputPathWithPrefix(DataTypeEnum dataTypeEnum){
        return Paths.get(outputPath, prefix() + dataTypeEnum.getFileName());
    }
}
