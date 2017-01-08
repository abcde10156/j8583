package com.protocol.utils;

import java.io.UnsupportedEncodingException;

/**
 * User:
 * Date: 12-4-19
 * Time: 12:48:00
 */
public class StringUtils {

    public static final String STR_SPACE = "";

    public static final String STR_ZERO = "0";

    public static final String STR_ONE = "1";

    public static final String STR_SQL_MARK = "?";

    public static final String STR_QUOT1 = "'";

    public static final String STR_TRUE = "true";

    public static final String STR_FALSE = "false";

    public static final String STR_EQUAL = "=";

    public static final String STR_LEFT_SPLIT1 = "[";

    public static final String STR_RIGHT_SPLIT1 = "]";

    public static final String ENCODING_UTF8 = "UTF-8";

    public static final String ENCODING_GBK = "GBK";

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String fillZero(String src, int length) {
        StringBuffer buffer = new StringBuffer(src);
        int j = length - src.length();
        for (int i = 0; i < j; i++) {
            buffer.insert(0, STR_ZERO);
        }
        return buffer.toString();
    }

    public static String fillZero(Object src, int length) {
        return fillZero(String.valueOf(src), length);
    }

    public static String showSqlValues(String sql, Object... values) {
        if (values.length == 0) {
            return sql;
        }
        sql = sql.trim();
        String[] splt_sql = sql.split("\\" + STR_SQL_MARK);
        if (splt_sql.length != values.length) {
            throw new RuntimeException("the number of values not equals parameter of sql");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splt_sql.length; i++) {
            Object value = values[i];
            if (value instanceof String) {
                sb.append(splt_sql[i]).append(STR_QUOT1).append(value).append(STR_QUOT1);
            } else {
                sb.append(splt_sql[i]).append(value);
            }

        }
        return sb.toString();
    }

    /**
     * 把字符串右填充成固定长度(长度按字节数计算)
     *
     * @param str      待填充的字符串
     * @param size     填充后的长度
     * @param padChar  用来填充的字符(因为要按字节填充，只能是char)
     * @param encoding 计算原字符串长度的编码方式
     * @return
     */
    public static String rightPadWithBytes(String str, int size, char padChar, String encoding) {
        return padWithBytes(str, size, padChar, encoding, false);
    }

    /**
     * 把字符串左填充成固定长度(长度按字节数计算)
     *
     * @param str      待填充的字符串
     * @param size     填充后的长度
     * @param padChar  用来填充的字符(因为要按字节填充，只能是char)
     * @param encoding 计算原字符串长度的编码方式
     * @return
     */
    public static String leftPadWithBytes(String str, int size, char padChar, String encoding) {
        return padWithBytes(str, size, padChar, encoding, true);
    }

    private static String padWithBytes(String str, int size, char padChar, String encoding, boolean isLeft) {
        if (str == null) {
            return null;
        }
        int strLen;
        try {
            strLen = str.getBytes(encoding).length;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncoding:" + encoding, e);
        }

        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }

        char[] padChars = new char[pads];
        for (int i = 0; i < padChars.length; i++) {
            padChars[i] = padChar;
        }
        if (isLeft) {
            return new String(padChars) + str;
        } else {
            return str + new String(padChars);
        }
    }

    public static void checkLenUTF8(String str, int len, String errMsg, Object... value) {
        byte[] bb = new byte[0];
        try {
            bb = str.getBytes(StringUtils.ENCODING_UTF8);
        } catch (UnsupportedEncodingException e) {

        }
        if (bb.length > len) {
            ExceptionUtils.throwEx(new RuntimeException(errMsg));
        }
    }

    public static void checkLenGBK(String str, int len, String errMsg, Object... value) {
        byte[] bb = new byte[0];
        try {
            bb = str.getBytes(StringUtils.ENCODING_GBK);
        } catch (UnsupportedEncodingException e) {

        }
        if (bb.length > len) {
            ExceptionUtils.throwEx(new RuntimeException(errMsg));
        }
    }

    public static String encodeStrGBK(byte[] bb) {
        try {
            return new String(bb, ENCODING_GBK);
        } catch (UnsupportedEncodingException e) {
            return new String(bb);
        }
    }

    public static String encodeStrUTF8(byte[] bb) {
        try {
            return new String(bb, ENCODING_UTF8);
        } catch (UnsupportedEncodingException e) {
            return new String(bb);
        }
    }

    public static byte[] encodeBytesGBK(String str) {
        try {
            return str.getBytes(ENCODING_GBK);
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    public static byte[] encodeBytesUTF8(String str) {
        try {
            return str.getBytes(ENCODING_UTF8);
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }
}
