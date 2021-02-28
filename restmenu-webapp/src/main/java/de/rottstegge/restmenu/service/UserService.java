package de.rottstegge.restmenu.service;

import de.rottstegge.restmenu.model.User;

public interface UserService {

    User getUserByUsername(String username);

    User getUserById(long id);

}
