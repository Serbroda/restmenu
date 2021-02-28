package de.rottstegge.restmenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RestmenuServerWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestmenuServerWebappApplication.class, args);
	}

}
