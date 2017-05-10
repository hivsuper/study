package org.lxp.company.job;

import java.io.IOException;
import java.util.Date;

import org.lxp.company.db.DbUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RefreshJob implements Job {
  private static Date lastRunTime;
  private static final Logger LOG = LoggerFactory.getLogger(RefreshJob.class);

  public void execute(JobExecutionContext arg0) throws JobExecutionException {
    try {
      LOG.info("...开始抓取任务...");
      lastRunTime = new Date();
      DbUtil.getInstance().flushFile();
    } catch (IOException e) {
      LOG.error(e.getMessage(), e);
    }
  }

  public static void setLastRunTime(Date lastRunTime) {
    RefreshJob.lastRunTime = lastRunTime;
  }

  public static Date getLastRunTime() {
    return lastRunTime;
  }
}
