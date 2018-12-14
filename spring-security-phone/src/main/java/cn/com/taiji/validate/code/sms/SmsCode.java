package cn.com.taiji.validate.code.sms;
import java.time.LocalDateTime;

import cn.com.taiji.validate.code.ValidateCode;

public class SmsCode extends ValidateCode {
    private String mobile;

    public SmsCode(String mobile, String code, int expireTime) {
        super(code, expireTime);
        this.mobile = mobile;
    }

    public SmsCode(String mobile, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
