package com.project.kcart;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class KcartApplication {
	public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().directory("./").ignoreIfMalformed().ignoreIfMissing().load();

        if(dotenv.get("DB_URL") != null ) {
            System.setProperty("DB_URL", dotenv.get("DB_URL"));
        }
        if(dotenv.get("DB_USERNAME") != null ) {
            System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        }
        if(dotenv.get("DB_PASSWORD") != null ) {
            System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        }
		SpringApplication.run(KcartApplication.class, args);
	}

}
