package org.lxp.vo;

public enum CommentStateEnum implements StringEnum<CommentStateEnum> {
  NOMARL("正常"), ABNORMAL("异常");
  private String code;

  private CommentStateEnum(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  @Override
  public String getText() {
    return code.toLowerCase();
  }

  @Override
  public String formText(String value) {
    for (CommentStateEnum e : values()) {
      if (e.getText().equals(value)) {
        return e.getText();
      }
    }
    return null;
  }
}
