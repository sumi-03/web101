package com.example.converter;

import com.example.web.dto.TempResponse;

public class TempConverter {

    public static TempResponse.TempTestDto toDto(String testString) {
        return TempResponse.TempTestDto.builder()
                .testString(testString)
                .build();
    }

    public static TempResponse.TempExceptionDTO toTempExceptionDto(Integer flag) {
        return TempResponse.TempExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}