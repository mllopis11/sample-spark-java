package mike.samples.spark;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import mike.bootstrap.springboot.application.Application;

@SpringBootApplication
@ComponentScan(basePackages = { Application.BOOT_BASE_PACKAGE, "mike.samples.spark" })
public class JdbcSparkApplication {

    public static void main(String[] args) {
        Application.batch(JdbcSparkApplication.class, args);
    }
}
