package cn.com.taiji.validate.code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import cn.com.taiji.validate.code.image.ImageCodeGenerator;
import cn.com.taiji.validate.code.sms.DefaultSmsCodeSender;
import cn.com.taiji.validate.code.sms.SmsCodeSender;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator") // 如果容器中已经存在名字为imageCodeGenerator的Bean,则不需要实例化Bean
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setDefaultKaptcha(defaultKaptcha);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        DefaultSmsCodeSender defaultSmsCodeSender = new DefaultSmsCodeSender();
        return defaultSmsCodeSender;
    }
}