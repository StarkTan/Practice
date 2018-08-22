package com.stark.asyn;

import com.stark.asyn.task.AsyncTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 测试异步任务
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class ApplicationTest {
    private static final Logger log = LoggerFactory.getLogger(ApplicationTest.class);
    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void testAsync() throws InterruptedException, ExecutionException {
        asyncTask.dealNoReturnTask();
        Future<String> f = asyncTask.dealHaveReturnTask(5);
        log.info("主线程执行finished");
        log.info(f.get());
    }
}