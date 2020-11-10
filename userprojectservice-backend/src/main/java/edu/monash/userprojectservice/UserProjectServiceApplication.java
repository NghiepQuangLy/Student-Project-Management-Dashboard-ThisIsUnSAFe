package edu.monash.userprojectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableTransactionManagement
@SpringBootApplication
public class UserProjectServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProjectServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://localhost:3000",
                                "http://localhost:3001",
                                "http://spmd-git-frontend.s3-website-ap-southeast-2.amazonaws.com",
                                "http://spmd-admin-frontend.s3-website-ap-southeast-2.amazonaws.com",
                                "http://spmdgitbackend-env.eba-dyda2zrz.ap-southeast-2.elasticbeanstalk.com",
                                "http://spmdgitbackend-env.eba-dyda2zrz.ap-southeast-2.elasticbeanstalk.com/",
                                "http://localhost:3002",
                                "http://ancient-springs-72265.herokuapp.com",
                                "http://ancient-springs-72265.herokuapp.com/",
                                "http://trello-frontend-1.herokuapp.com",
                                "http://trello-backend-1.herokuapp.com",
                                "http://reminders-frontend-1.herokuapp.com",
                                "http://reminders-backend-1.herokuapp.com",
                                "http://localhost:3003",
                                "http://spmd-export.s3-website-ap-southeast-2.amazonaws.com",
                                "http://spmd-export.s3-website-ap-southeast-2.amazonaws.com/",
                                "http://spmd-gdoc.s3-website-ap-southeast-2.amazonaws.com",
                                "http://spmd-gdoc.s3-website-ap-southeast-2.amazonaws.com/",
                                "http://d21emc0xlr7tto.cloudfront.net"
                        );
            }
        };
    }
}
