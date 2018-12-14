package cn.com.taiji.demo;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import cn.com.taiji.validate.code.ValidateCodeProcessor;

@RestController
public class ImageCodeController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    @GetMapping("/code/{type}")
    public void getCode(HttpServletRequest request, HttpServletResponse response,@PathVariable String type) throws Exception {
        validateCodeProcessors.get(type + ValidateCodeProcessor.CODE_PROCESSOR).create(new ServletWebRequest(request, response));
    }
}