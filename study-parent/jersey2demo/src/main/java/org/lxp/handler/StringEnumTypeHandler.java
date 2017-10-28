package org.lxp.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.lxp.vo.StringEnum;

@SuppressWarnings("rawtypes")
public class StringEnumTypeHandler<E extends StringEnum> extends BaseTypeHandler<E> {
  private Class<E> type;

  public StringEnumTypeHandler(Class<E> type) {
    if (type == null)
      throw new IllegalArgumentException("Type argument cannot be null");
    this.type = type;
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter.getText());
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    E e = EnumHelper.getEnum(type, rs.getString(columnName));
    return e;
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    E e = EnumHelper.getEnum(type, rs.getString(columnIndex));
    return e;
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    E e = EnumHelper.getEnum(type, cs.getString(columnIndex));
    return e;
  }

}
