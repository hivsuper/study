package org.lxp.multiple.thread;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 测试ThreadLocal
 * @author Super.Li
 * @date Jul 6, 2017
 */
public class ThreadLocalTest {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final ThreadLocal<DateFormat> DATE_FORMAT = new ThreadLocal<DateFormat>() {
        public DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static void main(String[] args) throws InterruptedException {
        String date = "2017-07-06";
        testDateFormat(date);
        testThreadLocal(date);
    }

    private static void testDateFormat(String date) throws InterruptedException {
        multipleThreadExecute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(df.parse(date));
                } catch (ParseException e) {
                }
            }
        });
    }

    private static void testThreadLocal(String date) throws InterruptedException {
        multipleThreadExecute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(DATE_FORMAT.get().parse(date));
                } catch (ParseException e) {
                }
            }
        });
    }

    private static void multipleThreadExecute(Runnable runnable) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(runnable);
        }
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }
}
