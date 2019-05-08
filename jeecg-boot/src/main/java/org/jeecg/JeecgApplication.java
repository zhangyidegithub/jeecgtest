//package org.jeecg;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@SpringBootApplication(scanBasePackages= {"org.jeecg.*","com.aisino.*"})
//@EnableSwagger2
//public class JeecgApplication {
//
//    public static void main(String[] args) {
//    	System.setProperty("spring.devtools.restart.enabled", "false");
//    	SpringApplication.run(JeecgApplication.class, args);
//    }
//}

package org.jeecg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages= {"org.jeecg.*","com.aisino.*"})
@EnableSwagger2
public class JeecgApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JeecgApplication.class);
    }


    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "true");
        SpringApplication.run(JeecgApplication.class, args);
    }
}