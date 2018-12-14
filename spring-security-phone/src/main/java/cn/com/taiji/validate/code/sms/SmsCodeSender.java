package cn.com.taiji.validate.code.sms;

public interface SmsCodeSender {
    void send(String mobile, String code);
}
