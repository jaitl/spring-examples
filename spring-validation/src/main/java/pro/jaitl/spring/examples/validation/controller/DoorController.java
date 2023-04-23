package pro.jaitl.spring.examples.validation.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.jaitl.spring.examples.validation.dto.DoorDto;

@Slf4j
@RestController
@RequestMapping("/v1/doors")
public class DoorController {
    @PostMapping
    public void create(@Valid @RequestBody DoorDto doorDto) {
        log.info("create request: {}", doorDto);
    }
}
