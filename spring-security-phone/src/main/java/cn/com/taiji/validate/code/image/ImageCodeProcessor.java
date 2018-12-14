package cn.com.taiji.validate.code.image;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import cn.com.taiji.validate.code.impl.AbstractValidateCodeProcessor;

@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    private static final String FORMAT_NAME = "JPEG";

    /**
     * 发送图形验证码，将其写到相应中
     *
     * @param request   ServletWebRequest实例对象
     * @param imageCode 验证码
     * @throws Exception 异常
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(), FORMAT_NAME, request.getResponse().getOutputStream());
    }
}