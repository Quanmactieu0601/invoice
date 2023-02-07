package study.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import study.invoice.dto.UserDTO;
import study.invoice.enm.RoleName;
import study.invoice.service.implement.RoleServiceImpl;
import study.invoice.service.implement.UserServiceImpl;
import study.invoice.web.rest.AuthController;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class InvoiceApplication implements CommandLineRunner {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;

    public static void main(String[] args) {
        SpringApplication.run(InvoiceApplication.class, args);
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
//                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
//                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
//        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
//                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter(urlBasedCorsConfigurationSource);
//    }

    @Override
    public void run(String... args) throws Exception {
        Set<RoleName> roleNames = new HashSet<>();
        Arrays.stream(RoleName.values()).forEach( role -> {
            if(!roleService.existsByName(role)) {
                roleNames.add(role);
            }
        });
        if(roleNames.stream().count() > 0){
            roleService.create(roleNames);
        }

        if(!userService.existsByLogin("root")){
            UserDTO admin = new UserDTO();
            admin.setLogin("root");
            admin.setFirstName("root");
            admin.setPassword("pass!@#$%");
            admin.setAuthorities(new HashSet<String>(Arrays.asList("ROOT")));

            userService.createUser(admin);
        }
    }
}
