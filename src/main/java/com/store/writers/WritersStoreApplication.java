package com.store.writers;

import com.store.writers.model.Role;
import com.store.writers.model.entity.User;
import com.store.writers.repository.UserRepository;
import com.store.writers.service.UserService;
import com.store.writers.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@SpringBootApplication
public class WritersStoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WritersStoreApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {

		/** Adding a user for prototyping */
		User user1 = new User();
		user1.setFirstname("-");
		user1.setLastname("-");
		user1.setUsername("user1");
		user1.setPassword(SecurityUtil.passwordEncoder().encode("user1"));
		user1.setEmail("rosmacias@gmail.com");

		userService.createUser(user1, Role.ADMIN);

		User user2 = new User();
		user2.setFirstname("-");
		user2.setLastname("-");
		user2.setUsername("user2");
		user2.setPassword(SecurityUtil.passwordEncoder().encode("user2"));
		user2.setEmail("inkwritersstore@gmail.com");
	}
}
