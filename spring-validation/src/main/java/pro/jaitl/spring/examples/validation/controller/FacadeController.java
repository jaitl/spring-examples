package pro.jaitl.spring.examples.validation.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.jaitl.spring.examples.validation.dto.FacadeDto;

@Slf4j
@RestController
@RequestMapping("/v1/facades")
public class FacadeController {
    @PostMapping
    public void create(@Valid @RequestBody FacadeDto facadeDto) {
        log.info("create request: {}", facadeDto);
    }
}
