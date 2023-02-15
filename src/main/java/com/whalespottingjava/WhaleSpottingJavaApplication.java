package com.whalespottingjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }) // TODO 409: get rid of exclude once debugged
public class WhaleSpottingJavaApplication {
    public static void main(String[] args) {

        SpringApplication.run(WhaleSpottingJavaApplication.class, args);
    }

}
