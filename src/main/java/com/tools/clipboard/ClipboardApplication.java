package com.tools.clipboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClipboardApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ClipboardApplication.class);

        application.run(args);
    }

}
