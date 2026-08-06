package div.ahmed.springsecuritye11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// http://localhost:8080/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=https://springone.io/authorized
@SpringBootApplication
public class SpringSecurityE11Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityE11Application.class, args);
    }

}
