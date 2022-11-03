package com.doctorkernel.user.cmd.api;

import com.doctorkernel.user.core.infra.axon.config.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AxonConfig.class })
public class UserCommandApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserCommandApplication.class, args);
	}
}
