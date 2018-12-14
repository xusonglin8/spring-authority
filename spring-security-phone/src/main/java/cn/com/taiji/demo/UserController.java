	package cn.com.taiji.demo;
	import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

	@RestController
	@RequestMapping("/user")
	public class UserController {

	    @GetMapping("/index")
	    public String index() {
	        return "index";
	    }

	    @GetMapping("/info")
	    public Object info(Principal principal) {
	        return principal;
	    }
	}
