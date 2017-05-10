package org.lxp.company.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxp.company.db.DbUtil;
import org.lxp.company.job.RefreshJob;
import org.lxp.company.model.Company;

public class ShowServlet extends HttpServlet {
  private static final ThreadLocal<DateFormat> DF = new ThreadLocal<DateFormat>() {
    @Override
    public DateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd");
    }
  };
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    DbUtil.initialize(request.getSession().getServletContext().getRealPath("/").replace(request.getContextPath(), ""));
    Date lastRunTime;
    if (RefreshJob.getLastRunTime() == null) {
      DbUtil.getInstance().flushFile();
      lastRunTime = new Date();
      RefreshJob.setLastRunTime(lastRunTime);
    } else {
      lastRunTime = RefreshJob.getLastRunTime();
    }
    response.setCharacterEncoding("UTF-8");
    response.setHeader("content-type", "text/html;charset=UTF-8");
    List<Company> companies = DbUtil.getInstance().readFile();
    StringBuilder sb = new StringBuilder();
    sb.append("<link rel='stylesheet' type='text/css' href='http://style.11467.com/www/css/b2b.css' />");
    sb.append("上一次更新时间").append(DF.get().format(lastRunTime)).append("</br>");
    sb.append(
        "<style>table{border-collapse:collapse;border-spacing:0;}th,td{border:1px solid #666;padding:0;}</style><table><tr><th nowrap='nowrap'>序号</th><th>名称</th><th>联系信息</th><th>入库时间</th></tr>");
    int index = 0;
    for (Company company : companies) {
      sb.append("<tr><td nowrap='nowrap'>").append(++index).append("</td>");
      sb.append("<td nowrap='nowrap'><a href='").append(company.getLink()).append("' target='_blank'>")
          .append(company.getName()).append("</a></td>");
      sb.append("<td>").append(company.getContactInfo() == null ? "" : company.getContactInfo()).append("</td>");
      sb.append("<td nowrap='nowrap'>").append(DF.get().format(company.getUpdateDate())).append("</td></tr>");
    }
    sb.append("</table>");
    response.getWriter().write(sb.toString());
  }
}