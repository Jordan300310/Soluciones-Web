package pe.fyj.fyj_erp_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FyjErpApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(FyjErpApiApplication.class, args);
  }

  @Bean
  CommandLineRunner printBcrypt(PasswordEncoder encoder) {
    return args -> {
      String raw = "admin123";
      System.out.println("BCrypt: " + encoder.encode(raw));
    };
  }
}
