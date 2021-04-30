package com.lisy.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;

/**
 * @Author: lisy
 * @Date: 2021/4/9
 * @version: 1.0
 * @Description: "google验证配置,详细配置可看 https://blog.csdn.net/yhflyl/article/details/102535776"
 */
@Configuration
public class CaptchaConfig {
    @Bean
    public DefaultKaptcha producer() {
        // 配置
        Properties properties = new Properties();
        // 图形验证码相关配置
        // 图片边框，合法值：yes , no
        properties.put("kaptcha.border","yes");
        // 边框厚度，合法值：>0
        properties.put("kaptcha.border.thickness",1);
        // 设置边框颜色 合法值： r,g,b (and optional alpha) 或者 white,black,blue
        properties.put("kaptcha.border.color","3,169,244");
        // 验证码文本字体颜色，默认黑色 ，合法值： r,g,b 或者 white,black,blue.
        properties.put("kaptcha.textproducer.font.color","black");
        // 字体大小
        properties.put("kaptcha.textproducer.font.size", "30");
        // 字体
        properties.put("kaptcha.textproducer.font.mames","Arial, Courier");
        // 验证码长度
        properties.put("kaptcha.textproducer.char.length", "4");
        // 文字间隔 默认值: 2
        properties.put("kaptcha.textproducer.char.space", "4");
        // 描述: 图片宽默认值: 200 合法值
        properties.put("kaptcha.image.width","100");
        // 描述: 图片高 默认值: 50 合法值:
        properties.put("kaptcha.image.height","40");
        // 干扰颜色，合法值： r,g,b 或者 white,black,blue.
        properties.put("kaptcha.noise.color","blue");
        // 干扰实现类
        properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        /**
         * 描述: 图片样式
         * 默认值: 水纹 com.google.code.kaptcha.impl.WaterRipple
         * 合法值: 水纹 com.google.code.kaptcha.impl.WaterRipple
         *        鱼眼 com.google.code.kaptcha.impl.FishEyeGimpy
         *        阴影 com.google.code.kaptcha.impl.ShadowGimpy
         */
        properties.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        // 验证码
        properties.put("kaptcha.session.key", "kaptcha.code");
        properties.put("kaptcha.session.date", "kaptcha.date");
        // 文本集合，验证码值从此集合中获取
        properties.put("kaptcha.textproducer.char.string","abcde23456789gfynmnpwx");
        // 背景颜色渐变，开始颜色light grey
        // properties.put("kaptcha.background.clear.from","light,grey");
        // 背景颜色渐变，结束颜色
        // properties.put("kaptcha.background.clear.to", "white");
        // 文字渲染器
        properties.put("kaptcha.word.impl", "com.google.code.kaptcha.text.impl.DefaultWordRenderer");
        Config config = new Config(properties);
        // 验证码生成器
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
