package healthygym.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import healthygym.model.User;
import healthygym.repository.UserRepository;

@RestController
public class UserController {
	
    private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
		return userRepository.findById(id)
			      .orElseThrow(() -> new EntityNotFoundException());
    }
	
	@GetMapping("/users")
	public List<User> allUsers() {
	    return userRepository.findAll();
	}
	
	@PostMapping("/user")
	public User newUser(@RequestBody User user) {
	    return userRepository.save(user);
	}
	
	@PutMapping("/user/{id}")
	public User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
	    
	    return userRepository.findById(id)
	      .map(user -> {
	        user.setFirstName(newUser.getFirstName());
	        user.setLastName(newUser.getLastName());
	        return userRepository.save(user);
	      })
	      .orElseGet(() -> {
	    	newUser.setId(id);
	        return userRepository.save(newUser);
	      });
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
	}
	

}
