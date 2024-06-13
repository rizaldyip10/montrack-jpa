package com.purwadhika.montrackv2;

import com.purwadhika.montrackv2.config.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class Montrackv2Application {

	public static void main(String[] args) {
		SpringApplication.run(Montrackv2Application.class, args);
	}

}
