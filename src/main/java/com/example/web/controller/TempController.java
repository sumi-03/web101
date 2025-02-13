package com.example.web.controller;
import com.example.converter.TempConverter;
import com.example.payload.CommonResponse;
import com.example.web.dto.TempResponse;
import com.example.service.temp.TempQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempController {

    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    public CommonResponse<TempResponse.TempTestDto> testAPI() {
        return CommonResponse.onSuccess(TempConverter.toDto("This is test string!"));
    }

    @GetMapping("/exception")
    public CommonResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag) {
        tempQueryService.checkFlag(flag);
        return CommonResponse.onSuccess(TempConverter.toTempExceptionDto(flag));
    }

}