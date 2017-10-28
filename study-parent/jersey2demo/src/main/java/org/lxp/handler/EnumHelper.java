package org.lxp.handler;

import org.lxp.vo.StringEnum;

public abstract class EnumHelper {

  public static <E extends StringEnum<?>> E getEnum(Class<E> clazz, String text) {
    E[] es = clazz.getEnumConstants();
    E result = null;
    for (E e : es) {
      if (e.getText().equals(text)) {
        result = e;
        break;
      }
    }
    return result;
  }
}
