package io.microsamples.secrets.vault;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VaultApplication implements CommandLineRunner {

	@Value("${username}")
	String username;

	@Value("${password}")
	String password;

	public static void main(String[] args) {
		SpringApplication.run(VaultApplication.class, args);
	}

	@Override
	public void run(String... strings) {
		System.out.println("username from vault ----> " + username);
		System.out.println("password from vault ----> " + password);
	}
}
