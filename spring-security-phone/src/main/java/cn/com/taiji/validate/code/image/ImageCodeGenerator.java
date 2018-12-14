package cn.com.taiji.validate.code.image;
import java.awt.image.BufferedImage;

import org.springframework.web.context.request.ServletWebRequest;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import cn.com.taiji.validate.code.ValidateCode;
import cn.com.taiji.validate.code.ValidateCodeGenerator;

public class ImageCodeGenerator implements ValidateCodeGenerator {

    private DefaultKaptcha defaultKaptcha;

    @Override
    public ValidateCode generator(ServletWebRequest request) {
        //生产验证码字符串
        String code = defaultKaptcha.createText();
        //使用生产的验证码字符串返回一个BufferedImage对象
        BufferedImage image = defaultKaptcha.createImage(code);
        return new ImageCode(image, code, 60*5); // 过期时间设置5分钟
    }

    public DefaultKaptcha getDefaultKaptcha() {
        return defaultKaptcha;
    }

    public void setDefaultKaptcha(DefaultKaptcha defaultKaptcha) {
        this.defaultKaptcha = defaultKaptcha;
    }

}
