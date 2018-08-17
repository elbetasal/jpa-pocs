package org.elbetasal.developer.jpa.jpaexamples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootApplication
@Configuration
public class JpaExamplesApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(JpaExamplesApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {
            userRepository.save(User
                    .builder()
                    .userName("pleymo")
                    .build());
        };

    }

    @RestController
    @RequestMapping("/")
    class UserController {

        @GetMapping("/users/")
        @Transactional
        Iterable<User> getUsers(){
            return userRepository.findAll();
        }

        @PostMapping("/user/{userName}")
        User saveUser(@PathVariable String userName){
            return userRepository.save(User
                    .builder()
                    .userName(userName)
                    .build());
        }

        @PatchMapping("/user/{id}")
        void updateUser(@PathVariable Long id , @RequestBody User user){
            userService.updateUser(id , user.getUserName());
        }
    }

}
