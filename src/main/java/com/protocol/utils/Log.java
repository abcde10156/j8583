package com.protocol.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User:
 * Date: 13-11-15
 * Time: 14:05
 */
public class Log {
    public static Logger makeLogger(Class clz) {
        return LoggerFactory.getLogger(clz);
    }
}
