package pro.jaitl.spring.examples.validation.service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pro.jaitl.spring.examples.validation.dto.OutputDto;
import pro.jaitl.spring.examples.validation.dto.validation.OnCreateGroup;
import pro.jaitl.spring.examples.validation.dto.validation.OnUpdateGroup;

@Validated
@Slf4j
@Service
public class OutputService {
    @Validated(OnCreateGroup.class)
    public void handleCreate(@Valid OutputDto outputDto) {
        log.info("create request: {}", outputDto);
    }

    @Validated(OnUpdateGroup.class)
    public void handleUpdate(@Valid OutputDto outputDto) {
        log.info("update request: {}", outputDto);
    }
}
