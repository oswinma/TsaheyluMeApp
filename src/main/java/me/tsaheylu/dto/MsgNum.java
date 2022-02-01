package me.tsaheylu.dto;

public class MsgNum {

  private Long toid;

  private Long num = 0l;

  public Long getToid() {
    return toid;
  }

  public void setToid(Long toid) {
    this.toid = toid;
  }

  public Long getNum() {
    return num;
  }

  public void setNum(Long num) {
    this.num = num;
  }

  public MsgNum(Long toid, long num) {
    this.toid = toid;
    this.num = num;
  }

  public MsgNum(Long toid) {
    this.toid = toid;
  }
}
