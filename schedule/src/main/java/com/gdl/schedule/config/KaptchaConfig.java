package com.gdl.schedule.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no");                     //是否使用边框
        properties.setProperty("kaptcha.border.color", "105,179,90");       //边框颜色
        properties.setProperty("kaptcha.textproducer.font.color", "black");   //验证码字体颜色
        properties.setProperty("kaptcha.image.width", "110");               //验证码图片的宽度
        properties.setProperty("kaptcha.image.height", "40");               //验证码图片的高度
        properties.setProperty("kaptcha.textproducer.font.size", "30");     //验证码字体的大小
        properties.setProperty("kaptcha.session.key", "code");              //验证码保存在session的key
        properties.setProperty("kaptcha.textproducer.char.length", "4");    //验证码输出的字符长度
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑"); //验证码的字体设置
        //properties.setProperty("kaptcha.textproducer.char.string","0123456789ABCEFGHJKLMNPQRSTUVWXYabcdefghijkmnpqrstuvwxy"); //验证码的取值范围
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy"); //图片的样式
        properties.setProperty("kaptcha.noise.color", "red");              //干扰颜色，合法值： r,g,b 或者 white,black,blue.
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise"); //干扰实现类
        properties.setProperty("kaptcha.background.clear.from", "150,150,150");              //背景颜色渐变，开始颜色
        properties.setProperty("kaptcha.background.clear.to", "white");              //背景颜色渐变，结束颜色
        properties.setProperty("kaptcha.textproducer.char.space", "3");              //文字间隔
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
