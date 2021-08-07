package healthygym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import healthygym.model.User;

@Controller
public class UserController {
	
	@GetMapping("/user")
    @ResponseBody
    public User getUser(@RequestParam(name="id", required=false) int id) {
        
		User user = new User();
		user.setId(id);
		
		return user;
    }

}
