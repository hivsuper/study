package com.lxp.notes.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lxp.notes.exception.BaseException;

@Service
public class ExceptionService {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionService.class);

    /**
     * 1. 禁止捕获RuntimeException <br/>
     * 2. 禁止使用异常做分支处理(处理效率比条件分支低)
     */
    public String _二_1_no(List<String> list, int index) {
        String rtn = null;
        try {
            rtn = list.get(index);
        } catch (IndexOutOfBoundsException e) {
            LOG.error("list={} index={}", list, index, e);
            rtn = list.get(list.size() - 1);
        }
        return rtn;
    }

    public String _二_1_yes(List<String> list, int index) {
        return (list.size() < index - 1) ? list.get(index) : list.get(list.size() - 1);
    }

    public String _二_4_yes() {
        String rtn = "This is 2.4";
        try {
            throw new BaseException("测试一下");
        } catch (BaseException e) {
            rtn = "出错了";
        }
        return rtn;
    }

    @SuppressWarnings("finally")
    public String _二_7_no(int flag) {
        String rtn = "This is 2.7";
        try {
            if (flag > 0) {
                throw new BaseException("测试一下");
            }
            return rtn;
        } catch (Exception e) {
            return rtn;
        } finally {
            return "ops!!!";
        }
    }

    public void _二_6_yes() throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File("1.txt")));
            writer.write("hello world");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public void _二_8_no() throws BaseException, IOException {
        throw new BaseException("测试一下");
    }
}
