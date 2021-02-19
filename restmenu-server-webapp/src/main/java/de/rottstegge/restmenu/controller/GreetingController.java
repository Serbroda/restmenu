package de.rottstegge.restmenu.controller;

import de.rottstegge.restmenu.mapper.GreetingMapper;
import de.rottstegge.restmenu.model.Greeting;
import de.rottstegge.restmenu.repository.GreetingRepository;
import de.rottstegge.v1.model.GreetingDto;
import de.rottstegge.v1.server.GreetingApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class GreetingController implements GreetingApi {

    private final GreetingRepository greetingRepository;

    public GreetingController(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public ResponseEntity<GreetingDto> getGreeting(@NotNull @Valid @RequestParam(value = "name") String name) throws Exception {
        Greeting greeting = new Greeting();
        greeting.setName(name);
        greeting = greetingRepository.save(greeting);

        return new ResponseEntity<>(GreetingMapper.INSTANCE.map(greeting), HttpStatus.OK);
    }
}
