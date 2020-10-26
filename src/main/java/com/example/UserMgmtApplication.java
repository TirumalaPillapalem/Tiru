package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class UserMgmtApplication {


	public static void main(String[] args) {
		SpringApplication.run(UserMgmtApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.example"))
				.build();
	}

	/* @Override
	public void run(String... args) throws Exception {

		UserRequest user = new UserRequest();
		user.setFirstName("Tirumala");
		user.setLastName("Pillapalem");

		Set<AddressRequest> addressDetails = new HashSet<>();
		AddressRequest address = new AddressRequest();
		address.setId(1L);
		address.setCity("Bangalore");
		address.setPostalCode("560037");
		address.setState("Karnataka");
		address.setStreet("TechPark");
		address.setUserRequest(user);

		AddressRequest newAddress = new AddressRequest();
		newAddress.setId(2L);
		newAddress.setCity("Bangalore_1");
		newAddress.setPostalCode("560037_1");
		newAddress.setState("Karnataka_1");
		newAddress.setStreet("TechPark_1");
		address.setUserRequest(user);

		addressDetails.add(address);
		addressDetails.add(newAddress);

		user.setAddress(addressDetails);
		userService.saveUser(user);
	} */

}
