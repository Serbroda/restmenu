package de.rottstegge.restmenu.service.impl;

import de.rottstegge.restmenu.model.User;
import de.rottstegge.restmenu.repository.UserRepository;
import de.rottstegge.restmenu.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getOne(id);
    }
}
