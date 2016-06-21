package org.lxp.company.job;

import java.io.IOException;

import org.lxp.company.db.DbUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RefreshJob implements Job {
  private static final Logger LOG = LoggerFactory.getLogger(RefreshJob.class);

  public void execute(JobExecutionContext arg0) throws JobExecutionException {
    try {
      LOG.info("...开始抓取任务...");
      DbUtil.flushFile();
    } catch (IOException e) {
      LOG.error(e.getMessage(), e);
    }
  }

}
