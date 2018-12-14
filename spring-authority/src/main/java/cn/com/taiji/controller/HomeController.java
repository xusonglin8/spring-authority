package cn.com.taiji.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
@GetMapping("/login")
public String login() {
	return "/login";
}
@GetMapping({"","/","/index"})
public String index(Model model) {
	//principal为
	Object principal =SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if("anonymousUser".equals(principal)) {
		model.addAttribute("name","anonymousUser");
	}else {
		User user=(User)principal;
		model.addAttribute("name",user.getUsername());
	}
	return "/index";
}

}
