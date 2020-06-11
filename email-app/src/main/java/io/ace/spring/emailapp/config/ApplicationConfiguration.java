package io.ace.spring.emailapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MailConfiguration.class)
@ComponentScan("io.ace.spring.emailapp")
public class ApplicationConfiguration {
}
