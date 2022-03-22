package me.tsaheylu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("me.tsaheylu.dao.mapper")
public class TsaheyluMeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TsaheyluMeAppApplication.class, args);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry
//                        .addMapping("/**")
//                        .allowedOriginPatterns("http://localhost:4200")
//                        .allowedMethods("*")
//                        .allowedHeaders("*")
//                        .allowCredentials(true)
//                        .exposedHeaders("*")
//                        .maxAge(3600);
//            }
//        };
//    }
}
