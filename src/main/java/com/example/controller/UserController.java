package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.UserRequest;
import com.example.bean.UserResponse;
import com.example.service.UserService;

@RestController
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param userRequest
	 * @return
	 */
	@PostMapping(value = "/saveUser", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> saveUser(UserRequest userRequest) {
		try {
			/*
			 * if (StringUtils.isEmpty(userRequest)) { return new
			 * ResponseEntity<>(HttpStatus.BAD_REQUEST); }
			 */
			userService.saveUser(userRequest);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception exception) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param userRequest
	 * @param userId
	 * @return
	 */
	@PutMapping("updateUser/{userId}")
	public ResponseEntity<?> updateStudent(UserRequest userRequest, @PathVariable String userId) {
		try {
			if (StringUtils.isEmpty(userId)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			userService.updateUser(userRequest, userId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception.." + e.getCause());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("getUser/{userId}")
	public UserResponse getUser(@PathVariable String userId) {
		UserResponse userResponse = null;
		try {
			userResponse = userService.getUser(userId);
		} catch (Exception e) {
			logger.error("Exception.." + e.getCause());
		}
		return userResponse;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("deleteUser/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId) {
		try {
			if (StringUtils.isEmpty(userId)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			userService.deleteUser(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception.." + e.getCause());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
