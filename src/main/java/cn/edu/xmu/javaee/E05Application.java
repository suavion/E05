package cn.edu.xmu.javaee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "cn.edu.xmu.javaee")
@EntityScan(basePackages = "cn.edu.xmu.javaee.mapper.po")
@EnableJpaRepositories(basePackages = "cn.edu.xmu.javaee.mapper")
public class E05Application {

    public static void main(String[] args) {
        SpringApplication.run(E05Application.class, args);
    }

}
