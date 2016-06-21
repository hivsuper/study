package org.lxp.company.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.lxp.company.model.Company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DbUtil {
  private static final String DB_BASE_PATH = "D:";
  private static final String FOSHAN_PATH = DB_BASE_PATH + "/foshan.txt";
  private static final String HUICONG_PATH = DB_BASE_PATH + "/huicong.txt";

  public static Set<String> readFoshanLinkSet() throws IOException {
    return readSet2Cache(FOSHAN_PATH);
  }

  public static Set<String> readHuicongLinkSet() throws IOException {
    return readSet2Cache(HUICONG_PATH);
  }

  public static List<Company> readFile() throws IOException {
    List<Company> companies = readFile2Cache(FOSHAN_PATH);
    companies.addAll(readFile2Cache(HUICONG_PATH));
    Collections.sort(companies);
    return companies;
  }

  public static void flushFile() throws IOException {
    List<Company> companies = FetchCompanyUtil.foshan();
    if (!companies.isEmpty()) {
      companies.addAll(readFile2Cache(FOSHAN_PATH));
      flushCache2File(companies, FOSHAN_PATH);
    }

    companies.clear();

    companies = FetchCompanyUtil.huicong();
    if (!companies.isEmpty()) {
      companies.addAll(readFile2Cache(HUICONG_PATH));
      flushCache2File(companies, HUICONG_PATH);
    }
  }

  private static Set<String> readSet2Cache(String path) throws IOException {
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

  private static List<Company> readFile2Cache(String path) throws IOException {
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

  private static void flushCache2File(List<Company> companies, String path) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
    writer.write(new ObjectMapper().writeValueAsString(companies));
    writer.close();
  }

  public static void main(String[] args) throws IOException {
    flushFile();
  }
}
