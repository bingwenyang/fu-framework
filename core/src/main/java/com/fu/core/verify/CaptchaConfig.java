package com.fu.core.verify;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


@Configuration
public class CaptchaConfig {

    @Bean
    @ConditionalOnMissingBean
    public VerifyCodeUtils verifyCode() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.char.space", "5");
        properties.put("kaptcha.textproducer.char.length", "4");
        properties.put("kaptcha.textproducer.font.size", "25");
        properties.put("kaptcha.textproducer.font.color", "blue");
        properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        properties.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        properties.put("kaptcha.background.clear.from", "white");
        properties.put("kaptcha.background.clear.to", "white");
        properties.put("kaptcha.image.width", "150");
        properties.put("kaptcha.image.height", "35");
        return new KaptchaUtils(properties);
    }
}
