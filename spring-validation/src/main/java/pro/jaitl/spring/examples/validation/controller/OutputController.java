package pro.jaitl.spring.examples.validation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pro.jaitl.spring.examples.validation.dto.OutputDto;
import pro.jaitl.spring.examples.validation.dto.validation.OnCreateGroup;
import pro.jaitl.spring.examples.validation.dto.validation.OnUpdateGroup;

@Slf4j
@RestController
@RequestMapping("/v1/outputs")
public class OutputController {

    @PostMapping
    public void create(@Validated(OnCreateGroup.class) @RequestBody OutputDto outputDto) {
        log.info("create request: {}", outputDto);
    }

    @PutMapping
    public void update(@Validated(OnUpdateGroup.class) @RequestBody OutputDto outputDto) {
        log.info("update request: {}", outputDto);
    }
}
