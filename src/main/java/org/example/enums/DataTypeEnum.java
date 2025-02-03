package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.constant.FileNameConstant;

@Getter
@AllArgsConstructor
public enum DataTypeEnum {
    INTEGERS(FileNameConstant.INTEGERS, false),
    FLOATS(FileNameConstant.FLOATS, false),
    STRING(FileNameConstant.STRINGS, false);

    private final String fileName;
    @Setter
    private boolean isCleared;
}
