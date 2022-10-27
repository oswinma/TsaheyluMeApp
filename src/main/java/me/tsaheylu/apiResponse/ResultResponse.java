package me.tsaheylu.apiResponse;

public class ResultResponse {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result success() {
        return new Result()
                .setResult(ResultCode.SUCCESS);
    }

    public static Result success(Object data) {
        return new Result()
                .setResult(ResultCode.SUCCESS, data);


    }

    public static Result failure(ResultCode resultCode) {
        return new Result()
                .setResult(resultCode);
    }

    public static Result failure(ResultCode resultCode, Object data) {
        return new Result()
                .setResult(resultCode, data);
    }


}