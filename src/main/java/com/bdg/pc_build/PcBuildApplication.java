package com.bdg.pc_build;

import com.bdg.pc_build.authentication.service.AuthenticationService;
import com.bdg.pc_build.config.JwtService;
import com.bdg.pc_build.token.repository.TokenDAO;
import com.bdg.pc_build.user.enumerations.Role;
import com.bdg.pc_build.user.model.entity.User;
import com.bdg.pc_build.user.repository.UserDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PcBuildApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PcBuildApplication.class, args);

        EntityManager entityManager = context.getBean(EntityManager.class);
        Query query = entityManager.createQuery("select p from User p where p.email = 'admin@example.com'");
        if (query.getResultList().isEmpty()) {
            BCryptPasswordEncoder passwordEncoder = context.getBean(BCryptPasswordEncoder.class);

            User defaultAdmin = new User(
                    "pc_build",
                    "admin",
                    "admin@example.com",
                    passwordEncoder.encode("pcbuildadmin"),
                    Role.ADMIN
            );

            UserDAO userDAO = context.getBean(UserDAO.class);
            TokenDAO tokenDAO = context.getBean(TokenDAO.class);

            tokenDAO.deleteById(1L);
            userDAO.deleteById(1L);
            userDAO.save(defaultAdmin);

            JwtService jwtService = context.getBean(JwtService.class);
            String generatedToken = jwtService.generateToken(defaultAdmin);

            AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
            authenticationService.saveUserToken(defaultAdmin, generatedToken);
        }
    }
}