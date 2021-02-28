package de.rottstegge.restmenu.repository;

import de.rottstegge.restmenu.model.User;
import de.rottstegge.restmenu.util.TransactionalProfileSpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TransactionalProfileSpringBootTest
public class UserRepositoryTest extends AbstractRepositoryTest<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    protected User getCreateEntity() {
        User user = new User();
        user.setUsername("maxmustermann");
        user.setPassword("$2y$12$Gb5Fr5zErUPJB8h6595TM.9Hzhb1qNTdv6qdBk0N.PX0ZkVjq6wri");
        return user;
    }

    @Override
    protected void modifyUpdateEntity(User entity) {
        entity.setUsername("admin");
    }

    @Override
    protected Example<User> getExample() {
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        User user = new User();
        user.setUsername("maxmustermann");
        return Example.of(user, matcher);
    }
}
