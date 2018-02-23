package pers.jz.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author jemmyzhang on 2017/12/3.
 */

@SpringBootApplication
@EnableEurekaServer
public class HaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HaServerApplication.class,args);
    }
}
