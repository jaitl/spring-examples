package pro.jaitl.spring.examples.validation.repository;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InputRepositoryTest {
    @Autowired
    private InputRepository inputRepository;

    @Test
    public void testSave_Success() {
        InputEntity entity = new InputEntity();
        entity.setInputId(1);
        entity.setName("test");

        assertDoesNotThrow(() -> inputRepository.save(entity));

        System.out.println(inputRepository.findById(entity.getId()));
    }

    @Test
    public void testSave_Failed() {
        InputEntity entity = new InputEntity();
        entity.setInputId(-1);
        entity.setName(null);

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class,
                () -> inputRepository.save(entity));

        Set<String> constraintViolations = e.getConstraintViolations().stream()
                .map(v -> String.format("%s: %s", v.getPropertyPath().toString(), v.getMessage()))
                .collect(Collectors.toSet());

        assertTrue(constraintViolations.contains("name: must not be null"));
        assertTrue(constraintViolations.contains("inputId: must be greater than or equal to 0"));
    }
}
