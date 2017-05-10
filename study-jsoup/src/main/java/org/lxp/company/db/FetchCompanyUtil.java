package org.lxp.company.db;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.lxp.company.model.Company;

public class FetchCompanyUtil {
  static List<Company> foshan() throws IOException {
    final String chartset = "gbk";
    final Set<String> linkSet = DbUtil.getInstance().readFoshanLinkSet();
    String url = "http://www.11467.com/foshan/";
    Document document = Jsoup.parse(new URL(url).openStream(), chartset, url);
    List<Element> links = document.select("#main #il .boxcontent").first().select("a");
    List<Company> rtn = new ArrayList<Company>(links.size());
    long now = System.currentTimeMillis();
    for (Element link : links) {
      String href = link.attr("href");
      if (linkSet.contains(href)) {
        continue;
      }
      Company company = new Company();
      company.setName(link.text());
      company.setLink(href);
      company.setUpdateTime(now);

      if (!StringUtil.isBlank(href)) {
        document = Jsoup.parse(new URL(href).openStream(), chartset, href);
        company.setContactInfo(document.select(":containsOwn(基本资料)").first().parent().select(".boxcontent").html());
      }
      rtn.add(company);
    }
    return rtn;
  }

  static List<Company> huicong() throws IOException {
    final String chartset = "gbk";
    final Set<String> linkSet = DbUtil.getInstance().readHuicongLinkSet();
    String url = "http://cn.hc360.com/";
    Document document = Jsoup.parse(new URL(url).openStream(), chartset, url);
    List<Element> links = document.select(".enterprise .newenter a");
    List<Company> rtn = new ArrayList<Company>(links.size());
    long now = System.currentTimeMillis();
    for (Element link : links) {
      String href = link.attr("href");
      if (linkSet.contains(href)) {
        continue;
      }
      Company company = new Company();
      company.setName(link.attr("title"));
      company.setLink(href);
      company.setUpdateTime(now);
      if (!StringUtil.isBlank(href)) {
        document = Jsoup.parse(new URL(href).openStream(), chartset, href);
        company.setContactInfo(document.select(":containsOwn(联系我们)").not("a").first().parent().parent().parent()
            .select(".contentbox").html());
      }
      rtn.add(company);
    }
    return rtn;
  }
}
