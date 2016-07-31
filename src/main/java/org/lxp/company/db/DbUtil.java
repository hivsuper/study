package org.lxp.company.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.lxp.company.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DbUtil {
  private static final Logger LOG = LoggerFactory.getLogger(DbUtil.class);
  private static DbUtil dbUtil;
  private static String FOSHAN_PATH;
  private static String HUICONG_PATH;

  private DbUtil(String path) {
    FOSHAN_PATH = path + "/foshan.txt";
    HUICONG_PATH = path + "/huicong.txt";
  }

  public static synchronized DbUtil initialize(String path) {
    if (dbUtil == null) {
      dbUtil = new DbUtil(path);
      LOG.info("FYI: {} and {}", FOSHAN_PATH, HUICONG_PATH);
    }
    return dbUtil;
  }

  public static DbUtil getInstance() {
    if (dbUtil == null) {
      throw new IllegalArgumentException("DbUtil has not been initialized");
    }
    return dbUtil;
  }

  public Set<String> readFoshanLinkSet() throws IOException {
    return readSet2Cache(FOSHAN_PATH);
  }

  public Set<String> readHuicongLinkSet() throws IOException {
    return readSet2Cache(HUICONG_PATH);
  }

  public List<Company> readFile() throws IOException {
    List<Company> companies = readFile2Cache(FOSHAN_PATH);
    companies.addAll(readFile2Cache(HUICONG_PATH));
    Collections.sort(companies);
    return companies;
  }

  public void flushFile() throws IOException {
    List<Company> companies = FetchCompanyUtil.foshan();
    if (!companies.isEmpty()) {
      companies.addAll(readFile2Cache(FOSHAN_PATH));
      flushCache2File(clearOldCompanies(companies), FOSHAN_PATH);
    }

    companies.clear();

    companies = FetchCompanyUtil.huicong();
    if (!companies.isEmpty()) {
      companies.addAll(readFile2Cache(HUICONG_PATH));
      flushCache2File(clearOldCompanies(companies), HUICONG_PATH);
    }
  }

  private Set<String> readSet2Cache(String path) throws IOException {
    File file = new File(path);
    if (!file.exists()) {
      file.createNewFile();
    }
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line = null;
    final Set<String> linkSet = new HashSet<String>();
    while ((line = reader.readLine()) != null) {
      List<Company> companies = new ObjectMapper().readValue(line, new TypeReference<List<Company>>() {
      });
      for (Company company : companies) {
        linkSet.add(company.getLink());
      }
    }
    reader.close();
    return linkSet;
  }

  private List<Company> readFile2Cache(String path) throws IOException {
    File file = new File(path);
    if (!file.exists()) {
      file.createNewFile();
    }
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line = null;
    List<Company> companies = Collections.emptyList();
    while ((line = reader.readLine()) != null) {
      companies = new ObjectMapper().readValue(line, new TypeReference<List<Company>>() {
      });
    }
    reader.close();
    return companies;
  }

  private void flushCache2File(List<Company> companies, String path) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
    writer.write(new ObjectMapper().writeValueAsString(companies));
    writer.close();
  }

  private List<Company> clearOldCompanies(List<Company> companies) {
    Iterator<Company> it = companies.iterator();
    while (it.hasNext()) {
      Company company = it.next();
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.MONTH, -1);
      if (company.getUpdateDate().before(calendar.getTime())) {// 清除一个月前的记录
        it.remove();
      }
    }
    return companies;
  }

  public static void main(String[] args) throws IOException {
    DbUtil dbUtil = initialize("D:");
    dbUtil.flushFile();
  }
}
