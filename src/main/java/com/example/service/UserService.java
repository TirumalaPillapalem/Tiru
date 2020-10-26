package com.example.service;

import java.util.List;

import com.example.bean.UserRequest;
import com.example.bean.UserResponse;

public interface UserService {

	public void saveUser(UserRequest userRequest) throws Exception;

	public void updateUser(UserRequest userRequest, String userId) throws Exception;

	public void deleteUser(String userId) throws Exception;

	public List<UserResponse> getAllUser() throws Exception;

	public UserResponse getUser(String userId) throws Exception;

}
