package cn.com.taiji.demo;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public static final String RETURN_TYPE = "html"; // 需要登录时，用来判断是返回json数据还是跳转html页面

    // 如果用户访问的界面需要登录则会跳转到该路径，在这里判断是返回json格式的数据还是返回html页面
    @GetMapping("/authentication/login")
    @ResponseBody
    public Object authenticationLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (RETURN_TYPE.equals("html")) {
            redirectStrategy.sendRedirect(request,response,"/login/index");
        }
        // 如果是需要返回json数据，则返回需要登录的信息提示
        Map<String, Object> map = new HashMap<>();
        //String code = UUID.randomUUID().toString();
        map.put("code",1001);
        map.put("msg","需要登录");
        return map;
    }


    // 登录页面
    @GetMapping("/login/index")
    public String loginIndex() throws IOException {
        return "login";
    }
}