package pro.jaitl.spring.examples.validation.service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pro.jaitl.spring.examples.validation.repository.InputEntity;

@Slf4j
@Service
@Validated
public class InputService {
    public void handle(@Valid InputEntity inputEntity) {
        log.info("entity: {}", inputEntity);
    }
}
