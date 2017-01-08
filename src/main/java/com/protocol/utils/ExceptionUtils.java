package com.protocol.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User:
 * Date: 13-10-22
 * Time: 15:40
 */
public class ExceptionUtils {

    private static Logger log = LoggerFactory.getLogger(ExceptionUtils.class);

    public static void printException(Throwable throwable) {
        printException(throwable, -1);
    }

    public static void printException10(Throwable throwable) {
        printException(throwable, 10);
    }

    public static void printException(Throwable throwable, int errorStackDeep) {
        log.error(throwable.toString());
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        if (errorStackDeep == -1) {
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                log.error(stackTraceElement.toString());
            }
        } else if (errorStackDeep > 0) {
            for (int i = 0; i < errorStackDeep; i++) {
                log.error(stackTraceElements[i].toString());
            }
        }

    }

    public static void throwEx(String errMsg) {
        RuntimeException exception = new RuntimeException(errMsg);
        ExceptionUtils.printException(exception);
        throw exception;
    }

    public static void throwEx(RuntimeException e) {
        ExceptionUtils.printException(e);
        throw e;
    }
}
