package com.fu.core.verify;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: yangbingwen
 * @date: 2022/10/28
 * @description:
 */

public class KaptchaUtils implements VerifyCodeUtils {
    public static DefaultKaptcha kaptcha;

    public KaptchaUtils() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "5");
        properties.put("kaptcha.textproducer.char.length", "6");
        properties.put("kaptcha.textproducer.font.size", "25");
        properties.put("kaptcha.textproducer.font.color", "gray");
        properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        properties.put("kaptcha.background.clear.from", "white");
        properties.put("kaptcha.background.clear.to", "white");
        properties.put("kaptcha.image.width", "150");
        properties.put("kaptcha.image.height", "35");
        Config config = new Config(properties);
        kaptcha = new DefaultKaptcha();
        kaptcha.setConfig(config);
    }

    public KaptchaUtils(Properties properties) {
        Config config = new Config(properties);
        kaptcha = new DefaultKaptcha();
        kaptcha.setConfig(config);
    }

    /**
     * 获取到配置信息的String列表
     *
     * @return
     */
    @Override
    public String createText() {
        String text = kaptcha.createText();
        return text;
    }

    /**
     * 创建为图片格式
     *
     * @param text
     * @return
     */
    public BufferedImage createImage(String text) {
        BufferedImage image = kaptcha.createImage(text);
        return image;
    }

    /**
     * 将图片个是类写入到流中,并转换为字节数组
     *
     * @param text
     * @return
     */
    @Override
    public byte[] getImage(String text) {
        BufferedImage image = this.createImage(text);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "jpg", outputStream);
            return outputStream.toByteArray();
        } catch (IOException var5) {
            var5.printStackTrace();
            return null;
        }
    }
}
