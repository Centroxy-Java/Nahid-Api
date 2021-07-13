package com.example.demo.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/add")
	public User add(@RequestBody User user) {
		System.out.println(user.getName());
		return userRepository.save(user);	
	}
	
	@GetMapping("/getallusers")
	public List<User> users(){
		return userRepository.findAll();
	}
	@GetMapping("get/{id}")
	public User user(@PathVariable(value="id") int id,User p){
		Optional<User> o= userRepository.findById(id);
		if(o.isPresent()) {
			p=o.get();
		}
		
		return p;
	}
	
	
	//deleting a specific record by using the method deleteById() of CrudRepository 
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable("id") int id)   
	{  
		 userRepository.deleteById(id);  
	}  
	//updating a record  
	
	@PutMapping("/update/{id}")
	public void update(@RequestBody User  user, @PathVariable("id")int id)   
	{  
		userRepository.save(user);  
	}  
}
