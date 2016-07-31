package org.lxp.company.model;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Company implements Comparable<Company> {
  private String name;
  private String link;
  private String phone;
  private String linkMan;
  private long updateTime;
  private String contactInfo;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getLinkMan() {
    return linkMan;
  }

  public void setLinkMan(String linkMan) {
    this.linkMan = linkMan;
  }

  public long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(long updateTime) {
    this.updateTime = updateTime;
  }

  public String getContactInfo() {
    return contactInfo;
  }

  public void setContactInfo(String contactInfo) {
    this.contactInfo = contactInfo;
  }

  @JsonIgnore
  public Date getUpdateDate() {
    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(this.updateTime);
    return c.getTime();
  }

  @Override
  public String toString() {
    return "Company [name=" + name + ", link=" + link + ", phone=" + phone + ", linkMan=" + linkMan + ", updateTime="
        + updateTime + ", contactInfo=" + contactInfo + "]";
  }

  public int compareTo(Company o) {
    long rtn = o.getUpdateTime() - this.getUpdateTime();
    return rtn == 0 ? 0 : (rtn > 0 ? 1 : -1);
  }
}