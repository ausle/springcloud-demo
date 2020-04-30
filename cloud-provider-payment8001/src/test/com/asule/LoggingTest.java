package com.asule;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * created by asule on 2020-04-28 14:28
 */
public class LoggingTest {

    Logger logger= LoggerFactory.getLogger(LoggingTest.class);

    @Test
    public void test(){
        logger.debug("xxxx");
        logger.info("xxxx");
        logger.warn("xxxx");
        logger.error("xxxx");
    }

}
