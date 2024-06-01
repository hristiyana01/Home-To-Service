package Backend.hometoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"Backend.hometoservice.service.implementation", "Backend.hometoservice.repository", "Backend.hometoservice.authorization", "Backend.hometoservice"})
public class HomeToServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeToServiceApplication.class, args);
    }
}
