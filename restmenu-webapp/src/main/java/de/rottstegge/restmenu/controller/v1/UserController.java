package de.rottstegge.restmenu.controller.v1;

import de.rottstegge.restmenu.model.User;
import de.rottstegge.restmenu.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);
        if(user == null) {
            throw new EntityNotFoundException("User with username '" + username + "' not found");
        }
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }
}
