package com.suinautant.myhome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suinautant.myhome.model.User;
import com.suinautant.myhome.repository.UserRepository;

@RestController
@RequestMapping("/api")
class UserApiController {

	@Autowired
	private UserRepository repository;

	@GetMapping("/users")
	List<User> all() {
		return repository.findAll();
	}
	// end::get-aggregate-root[]

	@PostMapping("/users")
	User newUser(@RequestBody User newUser) {
		return repository.save(newUser);
	}

	// Single item

	@GetMapping("/users/{id}")
	User one(@PathVariable Long id) {

		return repository.findById(id).orElse(null);
	}

	@PutMapping("/users/{id}")
	User replaceUser(@RequestBody User newUser, @PathVariable Long id) {


		return repository.findById(id).map(User -> {
//			User.setTitle(newUser.getTitle());
//			User.setContent(newUser.getContent());
			return repository.save(User);
		}).orElseGet(() -> {
			newUser.setId(id);
			return repository.save(newUser);
		});
	}

	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
