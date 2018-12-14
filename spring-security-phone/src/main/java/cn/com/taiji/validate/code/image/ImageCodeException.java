package cn.com.taiji.validate.code.image;
import org.springframework.security.core.AuthenticationException;

public class ImageCodeException extends AuthenticationException {
    public ImageCodeException(String msg) {
        super(msg);
    }
}