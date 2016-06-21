package org.lxp.company.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxp.company.db.DbUtil;
import org.lxp.company.model.Company;

public class ShowServlet extends HttpServlet {
  private static final ThreadLocal<DateFormat> DF = new ThreadLocal<DateFormat>() {
    @Override
    public DateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
  };
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    DbUtil.flushFile();

    response.setCharacterEncoding("UTF-8");
    response.setHeader("content-type", "text/html;charset=UTF-8");
    List<Company> companies = DbUtil.readFile();
    StringBuilder sb = new StringBuilder();
    sb.append(
        "<style>table{border-collapse:collapse;border-spacing:0;}th,td{border:1px solid #666;padding:0;}</style><table><tr><th>序号</th><th>名称</th><th>联系人</th><th>电话</th><th>入库时间</th><th>来源</th></tr>");
    int index = 0;
    for (Company company : companies) {
      sb.append("<tr><td>").append(++index).append("</td>");
      sb.append("<td>").append(company.getName()).append("</td>");
      sb.append("<td>").append(company.getLinkMan() == null ? "" : company.getLinkMan()).append("</td>");
      sb.append("<td>").append(company.getPhone() == null ? "" : company.getPhone()).append("</td>");
      sb.append("<td>").append(DF.get().format(company.getUpdateDate())).append("</td>");
      sb.append("<td><a href='").append(company.getLink()).append("'>").append(company.getLink())
          .append("</a></td></tr>");
    }
    sb.append("</table>");
    response.getWriter().write(sb.toString());
  }
}
