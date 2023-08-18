package com.likelion.babel;

import com.likelion.babel.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CorsConfig.class)
public class BabelApplication {

	public static void main(String[] args) {
		SpringApplication.run(BabelApplication.class, args);
	}

}
