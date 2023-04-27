package pro.jaitl.spring.examples.validation.custom;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pro.jaitl.spring.examples.validation.dto.UserDto;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RusPhoneNumberTest {
    @Autowired
    private Validator validator;

    @Test
    public void test_Success() {
        UserDto userDto = new UserDto();
        userDto.setName("test");
        userDto.setPhone("+7(222)333-22-11");

        Set<ConstraintViolation<UserDto>> result = validator.validate(userDto);
        assertTrue(result.isEmpty());
    }

    @Test
    public void test_Failed() {
        UserDto userDto = new UserDto();
        userDto.setName("test");
        userDto.setPhone("not phone");

        List<ConstraintViolation<UserDto>> result = List.copyOf(validator.validate(userDto));
        assertEquals(1, result.size());

        ConstraintViolation<UserDto> validationResult = result.get(0);

        assertEquals("phone", validationResult.getPropertyPath().toString());
        assertEquals("Wrong phone number", validationResult.getMessage());
    }
}
