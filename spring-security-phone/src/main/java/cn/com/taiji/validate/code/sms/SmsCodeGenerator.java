package cn.com.taiji.validate.code.sms;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import cn.com.taiji.validate.code.ValidateCode;
import cn.com.taiji.validate.code.ValidateCodeGenerator;


@Component
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ValidateCode generator(ServletWebRequest httpServletRequest) {
        String code = RandomStringUtils.randomNumeric(6);
        return new SmsCode(httpServletRequest.getRequest().getParameter("mobile"),code, 60*5);
    }
}
