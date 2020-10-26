package com.example.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.AddressRequest;
import com.example.bean.UserRequest;
import com.example.bean.UserResponse;
import com.example.entity.Address;
import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public void saveUser(UserRequest userRequest) throws Exception {
		User user = new User();
		try {
			Set<Address> address = new HashSet<Address>();
			user.setFirstName(userRequest.getFirstName());
			user.setLastName(userRequest.getLastName());

			Set<AddressRequest> addressRequest = userRequest.getAddress();

			addressRequest.forEach((addressReq) -> {
				Address addEntity = new Address();
				addEntity.setCity(addressReq.getCity());
				addEntity.setPostalCode(addressReq.getPostalCode());
				addEntity.setState(addressReq.getState());
				addEntity.setStreet(addressReq.getStreet());
				addEntity.setUser(user);
				address.add(addEntity);
			});

			user.setAddress(address);
			// BeanUtils.copyProperties(userRequest.getAddress(), user.getAddress());

			User actualUser = userRepository.save(user);
			logger.info("After Saving.." + actualUser.getUserId());

		} catch (Exception exception) {
			StringWriter sw = new StringWriter();
			exception.printStackTrace(new PrintWriter(sw));
			logger.error("Excption while Saving......" + sw.toString());
		}

	}

	@Override
	public void updateUser(UserRequest userRequest, String userId) throws Exception {
		try {
			Optional<User> user = userRepository.findById(Long.parseLong(userId));
			if (user.isPresent()) {
				User actualUser = user.get();
				actualUser.setFirstName(userRequest.getFirstName());
				actualUser.setFirstName(userRequest.getLastName());

				Set<Address> address = new HashSet<Address>();
				actualUser.setFirstName(userRequest.getFirstName());
				actualUser.setLastName(userRequest.getLastName());

				Set<AddressRequest> addressRequest = userRequest.getAddress();

				addressRequest.forEach((addressReq) -> {
					Address addEntity = new Address();
					addEntity.setCity(addressReq.getCity());
					addEntity.setPostalCode(addressReq.getPostalCode());
					addEntity.setState(addressReq.getState());
					addEntity.setStreet(addressReq.getStreet());
					addEntity.setUser(actualUser);
					address.add(addEntity);
				});

				actualUser.setAddress(address);
				// BeanUtils.copyProperties(userRequest.getAddress(), actualUser.getAddress());
				userRepository.save(actualUser);
			}

		} catch (Exception exception) {
			StringWriter sw = new StringWriter();
			exception.printStackTrace(new PrintWriter(sw));
			logger.error("Excption while updating......" + sw.toString());

		}

	}

	@Override
	public void deleteUser(String userId) throws Exception {
		try {
			userRepository.deleteById(Long.parseLong(userId));
		} catch (Exception exception) {
			logger.error("exception while delete.." + exception.getCause());
		}

	}

	/**
	 * 
	 */
	@Override
	public List<UserResponse> getAllUser() throws Exception {
		List<UserResponse> userList = new ArrayList<UserResponse>();
		try {
			Iterable<User> allUsers = userRepository.findAll();
			allUsers.forEach((user) -> {
				UserResponse userResponse = new UserResponse();
				userResponse.setFirstName(user.getFirstName());
				userResponse.setLastName(user.getLastName());
				BeanUtils.copyProperties(user.getAddress(), userResponse.getAddress());
				userList.add(userResponse);
			});
		} catch (Exception exception) {
			logger.error("exception while delete.." + exception.getCause());
		}
		return userList;
	}

	@Override
	public UserResponse getUser(String userId) throws Exception {
		UserResponse userResponse = new UserResponse();
		try {
			Optional<User> user = userRepository.findById(Long.parseLong(userId));
			User actualUser = user.get();
			userResponse.setFirstName(actualUser.getFirstName());
			userResponse.setLastName(actualUser.getLastName());
			BeanUtils.copyProperties(actualUser.getAddress(), userResponse.getAddress());

		} catch (Exception exception) {
			logger.error("exception while delete.." + exception.getCause());
		}
		return userResponse;
	}
}
