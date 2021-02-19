package de.rottstegge.restmenu.controller;

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

    @Override
    public ResponseEntity<GreetingDto> getGreeting(@NotNull @Valid  @RequestParam(value = "name") String name) throws Exception {
        GreetingDto greetingDto = new GreetingDto();
        greetingDto.setName(name);
        return new ResponseEntity<>(greetingDto, HttpStatus.OK);
    }
}
