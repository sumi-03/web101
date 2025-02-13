package com.example.payload;

import com.example.payload.status.SuccessStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class CommonResponse<T> {

    @Getter(AccessLevel.NONE)
    @JsonProperty("isSuccess")
    private final boolean isSuccess;

    private final String code;

    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;

    @JsonIgnore
    private final HttpStatus httpStatus;

    // 성공시 응답 생성
    public static <T> CommonResponse<T> onSuccess(T result) {
        return new CommonResponse<>(true, SuccessStatus.OK.getCode(), SuccessStatus.OK.getMessage(), result, SuccessStatus.OK.getHttpStatus());
    }

//    public static <T> CommonResponse<T> of(BaseStatus status, T result) {
//        return new CommonResponse<>(true,
//                status.getReasonHttpStatus().getCode(),
//                status.getReasonHttpStatus().getMessage(),
//                result,
//                status.getReasonHttpStatus().getHttpStatus());
//    }

    // 실패시 응답 생성
    public static <T> CommonResponse<T> onFailure(String code, String message, T data) {
        return new CommonResponse<>(false, code, message, data, null);
    }
}