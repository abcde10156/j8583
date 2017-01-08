package com.protocol.utils;

import org.slf4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

/**

 */
public class ByteUtil {
    private static Logger logger = Log.makeLogger(ByteUtil.class);
    /**
     * 内存复制体
     */
    public static byte[] ZEROS = new byte[1024 * 16];

    public static int read(byte[] data, int off) {
        return data[off] & 0xff;
    }

    public static int write(byte[] data, int off, int val) {
        data[off] = (byte) (val & 0xff);
        return 1;
    }

    public static int readInt(byte[] data, int off) {
        int ch1 = (data[off + 0] & 0xff) << 24;
        int ch2 = (data[off + 1] & 0xff) << 16;
        int ch3 = (data[off + 2] & 0xff) << 8;
        int ch4 = (data[off + 3] & 0xff) << 0;
        return ch1 + ch2 + ch3 + ch4;
    }

    public static int writeInt(byte[] data, int off, int val) {
        data[off + 0] = (byte) (val >>> 24);
        data[off + 1] = (byte) (val >>> 16);
        data[off + 2] = (byte) (val >>> 8);
        data[off + 3] = (byte) (val >>> 0);
        return 4;
    }

    public static int readInt(byte[] data, int off, int len) {
        int value = 0;
        for (int idx = 0; idx < len; idx++) {
            value += (data[off + idx] & 0xff) << (8 * (len - idx - 1));
        }
        return value;
    }

    public static int writeInt(byte[] data, int off, int val, int len) {
        for (int idx = 0; idx < len; idx++) {
            data[off + idx] = (byte) (val >>> (8 * (len - idx - 1)));
        }
        return len;
    }

    public static int readShort(byte[] data, int off) {
        int ch1 = (data[off + 0] & 0xff) << 8;
        int ch2 = (data[off + 1] & 0xff) << 0;
        return ch1 + ch2;
    }

    public static int writeShort(byte[] data, int off, int val) {
        data[off + 0] = (byte) (val >>> 8);
        data[off + 1] = (byte) (val >>> 0);
        return 2;
    }

    public static boolean readBool(byte[] data, int off) {
        return data[off] == (byte) 'T';
    }

    public static int writeBool(byte[] data, int off, boolean val) {
        data[off] = (byte) (val ? 'T' : 'F');
        return 1;
    }

    public static int readBits(byte[] data, int off, int start, int end) {
        return (0xff & (data[off] << (8 - end))) >>> (8 - end + start);
    }

    public static int writeBits(byte[] data, int off, int start, int end, int bits) {
        byte ptmp = (byte) ~(((0xff & (0xff << (8 - end))) >>> (start + 8 - end)) << start);
        byte ntmp = (byte) (((0xff & (bits << (8 - (end - start)))) >>> (8 - (end - start))) << start);
        data[start] = (byte) (data[start] & ptmp | ntmp);
        return 0;
    }

    public static Number readNumber(byte[] data, int off) {
        Long ch1 = (long) (data[off + 0] << 56);
        ch1 = ch1 | (data[off + 1] & 0xff) << 48;
        ch1 = ch1 | (long) (data[off + 2] & 0xff) << 40;
        ch1 = ch1 | (long) (data[off + 3] & 0xff) << 32;
        ch1 = ch1 | (long) (data[off + 4] & 0xff) << 24;
        ch1 = ch1 | (data[off + 5] & 0xff) << 16;
        ch1 = ch1 | (data[off + 6] & 0xff) << 8;
        ch1 = ch1 | (data[off + 7] & 0xff) << 0;
        return ch1;
    }

    public static int writeNumber(byte[] data, int off, Number val) {
        long xval = val.longValue();
        data[off + 0] = (byte) (xval >>> 56);
        data[off + 1] = (byte) (xval >>> 48);
        data[off + 2] = (byte) (xval >>> 40);
        data[off + 3] = (byte) (xval >>> 32);
        data[off + 4] = (byte) (xval >>> 24);
        data[off + 5] = (byte) (xval >>> 16);
        data[off + 6] = (byte) (xval >>> 8);
        data[off + 7] = (byte) (xval >>> 0);
        return 8;
    }

    public static long readLong(byte[] data, int off) {
        long ch1 = (long) data[off + 0] << 56;
        long ch2 = (long) (data[off + 1] & 0xff) << 48;
        long ch3 = (long) (data[off + 2] & 0xff) << 40;
        long ch4 = (long) (data[off + 3] & 0xff) << 32;
        long ch5 = (long) (data[off + 4] & 0xff) << 24;
        int ch6 = (data[off + 5] & 0xff) << 16;
        int ch7 = (data[off + 6] & 0xff) << 8;
        int ch8 = (data[off + 7] & 0xff) << 0;
        return ch1 + ch2 + ch3 + ch4 + ch5 + ch6 + ch7 + ch8;
    }

    public static int writeLong(byte[] data, int off, long val) {
        data[off + 0] = (byte) (val >>> 56);
        data[off + 1] = (byte) (val >>> 48);
        data[off + 2] = (byte) (val >>> 40);
        data[off + 3] = (byte) (val >>> 32);
        data[off + 4] = (byte) (val >>> 24);
        data[off + 5] = (byte) (val >>> 16);
        data[off + 6] = (byte) (val >>> 8);
        data[off + 7] = (byte) (val >>> 0);
        return 8;
    }

    public static int writeCLong(byte[] data, int off, long val) {
        data[off + 0] = (byte) (val >>> 0);
        data[off + 1] = (byte) (val >>> 8);
        data[off + 2] = (byte) (val >>> 16);
        data[off + 3] = (byte) (val >>> 24);
        data[off + 4] = (byte) (val >>> 32);
        data[off + 5] = (byte) (val >>> 40);
        data[off + 6] = (byte) (val >>> 48);
        data[off + 7] = (byte) (val >>> 56);
        return 8;
    }

    public static double readDouble(byte[] data, int off) {
        return Double.longBitsToDouble(readLong(data, off));
    }

    public static int writeDouble(byte[] data, int off, double val) {
        return writeLong(data, off, Double.doubleToLongBits(val));
    }

    public static String readString(byte[] data, int off) {
        int realen = zeroRange(data, off);
        return new String(data, off, realen);
    }

    public static int writeString(byte[] data, int off, String str) {
        return writeString(data, off, str, "gbk");
    }

    public static int writeString(byte[] data, int off, String str, String encoding) {
        try {
            byte[] strbits = str.getBytes(encoding);
            int realen = Math.min(strbits.length, data.length - off);
            data[off + realen] = 0;
            System.arraycopy(strbits, 0, data, off, realen);
            return realen + 1;
        } catch (Throwable th) {
            return 0;
        }
    }

    public static String readString(byte[] data, int off, int len) {
        int realen = Math.min(len, zeroRange(data, off));
        return new String(data, off, realen);
    }

    public static String readFixString(byte[] data, int off, int len) {
        int rlen = Math.min(data.length - off, len);
        byte[] val = new byte[rlen];
        System.arraycopy(data, off, val, 0, rlen);
        rlen = Math.min(rlen, zeroRange(data, off));
        return new String(val, 0, rlen);
    }

    public static int writeString(byte[] data, int off, String str, int len, String encoding) {
        try {
            byte[] strbits = str.getBytes(encoding);
            int realen = Math.min(strbits.length, len);
            realen = Math.min(realen, data.length - off);
            System.arraycopy(strbits, 0, data, off, realen);
            return realen + 1;
        } catch (Throwable th) {
            return 0;
        }
    }

    public static int writeString(byte[] data, int off, String str, int len) {
        return writeString(data, off, str, len, "gbk");
    }

    /**
     * len为固定长度
     */
    public static int writeFixString(byte[] data, int off, String str, int len, String encoding) {
        try {
            byte[] strbits = str.getBytes();
            int realen = Math.min(strbits.length, len);
            realen = Math.min(realen, data.length - off);
            System.arraycopy(strbits, 0, data, off, realen);
            return len;
        } catch (Throwable th) {
            return 0;
        }
    }

    public static int writeFixString(byte[] data, int off, String str, int len) {
        byte[] strbits = str.getBytes();
        int realen = Math.min(strbits.length, len);
        realen = Math.min(realen, data.length - off);
        System.arraycopy(strbits, 0, data, off, realen);
        return len;
    }

    /**
     * 计算从offset开始，非0字符的个数
     */
    public static int zeroRange(byte[] data, int off) {
        for (int idx = off; idx < data.length; idx++) {
            if (data[idx] == 0) {
                return idx - off;
            }
        }
        return data.length - off;
    }

    public static String makeBinaryStr(int bs) {
        String ZERO = "00000000";
        String s = "";
        s = Integer.toBinaryString(bs);
        if (s.length() > 8) {
            s = s.substring(s.length() - 8);
        } else if (s.length() < 8) {
            s = ZERO.substring(s.length()) + s;
        }
        return s;
    }

    public static byte makeIntFromBinary(String str) {
        Integer ii = Integer.parseInt(str, 2);
        return ii.byteValue();
    }

    /**
     * List转数组
     *
     * @param list
     * @return
     */
    public static byte[] list2ByteArray(List<Byte> list) {
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 数组转List
     *
     * @param bytes
     * @return
     */
    public static List<Byte> byteArray2List(byte[] bytes) {
        List<Byte> list = new LinkedList<Byte>();
        for (int i = 0; i < bytes.length; i++) {
            list.add(bytes[i]);
        }
        return list;
    }

    public static String showByteStr(byte[] bb) {
        if (bb == null) return "";
        StringBuilder builder = new StringBuilder(bb.length);
        for (byte b : bb) {
            builder.append(",").append(b);
        }
        builder.delete(0, 1);
        return builder.toString();
    }

    public static void printByte(byte[] bb) {
        logger.info(showByteStr(bb));
    }

    public static String getEncodingString(byte[] data, String encoding) {
        try {
            return new String(data, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(data);
        }
    }

    public static byte[] getEncodingByte(String str, String encoding) {
        try {
            return str.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }
}
