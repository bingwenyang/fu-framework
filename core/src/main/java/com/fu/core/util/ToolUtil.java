package com.fu.core.util;

import org.apache.commons.lang3.RandomUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * UUID工具类
 */
public class ToolUtil {


    public static final String[] RANDOM_POOL = new String[]{
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L",
            "M",
            "N",
            "O",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "U",
            "V",
            "W",
            "X",
            "Y",
            "Z",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "i",
            "j",
            "k",
            "l",
            "m",
            "n",
            "o",
            "p",
            "q",
            "r",
            "s",
            "t",
            "u",
            "v",
            "w",
            "x",
            "y",
            "z"};
    /**
     * 生成随机数字和字母
     *
     * @param length
     * @return
     */
    public static String getStringRandom(int length) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {
            val.append(RANDOM_POOL[random.nextInt(42)]);
        }
        return val.toString();
    }
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");

    }
    public static void main(String[] args) throws UnknownHostException, InterruptedException {


    }
}
