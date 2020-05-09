package kr.or.connect.reservationManagement.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "kr.or.connect.reservationManagement.dao", "kr.or.connect.reservationManagement.service"})
@Import(DBConfig.class)
public class ApplicationConfig {

}
