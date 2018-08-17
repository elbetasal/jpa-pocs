package org.elbetasal.developer.jpa.jpaexamples;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
public class UserService {

    private final UserRepository userRepository;

    private final EntityManager entityManager;

    public UserService(UserRepository userRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }


    @Transactional
    public void updateUser(Long id, String userName) {

        User user = entityManager.getReference(User.class, id);
        user.setUserName(userName);
        entityManager.merge(user);

    }
}
