package pro.jaitl.spring.examples.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pro.jaitl.spring.examples.validation.controller.request.CreateInputRequest;
import pro.jaitl.spring.examples.validation.dto.AttachmentDto;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ValidationTest {
    @Autowired
    private Validator validator;

    @Test
    public void testValidate_Success() {
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setContent("some_bite_content");

        CreateInputRequest request = new CreateInputRequest();
        request.setInputId(1);
        request.setName("newInputValidTest");
        request.setAttachment(attachmentDto);

        Set<ConstraintViolation<CreateInputRequest>> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testValidate_Invalid_InputId() {
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setContent("some_bite_content");

        CreateInputRequest request = new CreateInputRequest();
        request.setInputId(-1);
        request.setName("newInputValidTest");
        request.setAttachment(attachmentDto);

        List<ConstraintViolation<CreateInputRequest>> result = List.copyOf(validator.validate(request));
        assertEquals(1, result.size());

        ConstraintViolation<CreateInputRequest> validationResult = result.get(0);

        assertEquals("inputId", validationResult.getPropertyPath().toString());
        assertEquals("must be greater than or equal to 0", validationResult.getMessage());
    }
}