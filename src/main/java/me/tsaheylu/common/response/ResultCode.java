package me.tsaheylu.common.response;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ResultCode {
  SUCCESS(200, "Sucess"), // 成功
  BAD_REQUEST(400, "Bad Request"),
  UNAUTHORIZED(401, "Unauthorized"), // 未认证
  NOT_FOUND(404, "Not Found"), // 接口不存在
  INTERNAL_SERVER_ERROR(500, "Internal Server Error"), // 服务器内部错误
  METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

  /*参数错误:1001-1999*/
  PARAMS_IS_INVALID(1001, "Params Is Invalid"),
  PARAMS_IS_BLANK(1002, "Params Is Blank");
  /*用户错误2001-2999*/

  private Integer code;
  private String message;

  ResultCode(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
