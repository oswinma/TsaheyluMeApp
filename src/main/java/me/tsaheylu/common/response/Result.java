package me.tsaheylu.common.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

  private Integer code;
  private String message;
  private T data;

  public Result setResult(ResultCode resultCode) {
    this.code = resultCode.getCode();
    this.message = resultCode.getMessage();
    return this;
  }

  public Result setResult(ResultCode resultCode, T data) {
    this.code = resultCode.getCode();
    this.message = resultCode.getMessage();
    this.setData(data);
    return this;
  }
}
