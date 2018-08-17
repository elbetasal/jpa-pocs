package org.elbetasal.developer.jpa.jpaexamples;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaExamplesApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Long userToTest;

    @Before
    public void setup(){
        userToTest = userRepository.save(User
                .builder()
                .userName("other")
                .build())
                .getId();
        testEntityManager.getEntityManager().flush();
    }

    @Test
    @Transactional
    public void update_user_using_find_method() {

        User userToUpdate = testEntityManager.find(User.class , userToTest);
        userToUpdate.setUserName("change");

        testEntityManager.persistAndFlush(userToUpdate);

    }

    @Test
    public void update_user_using_get_reference() {
        User userToUpdate = testEntityManager.getEntityManager().getReference(User.class , userToTest);
        userToUpdate.setUserName("change");



    }

}
