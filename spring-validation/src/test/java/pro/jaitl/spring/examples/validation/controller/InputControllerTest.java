package pro.jaitl.spring.examples.validation.controller;

import org.junit.jupiter.api.Test;
import pro.jaitl.spring.examples.validation.controller.request.CreateInputRequest;
import pro.jaitl.spring.examples.validation.dto.AttachmentDto;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class InputControllerTest extends BaseApiTest {

    @Test
    public void testInput_Valid() throws Exception {
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setContent("some_bite_content");

        CreateInputRequest request = new CreateInputRequest();
        request.setInputId(1);
        request.setName("newInputValidTest");
        request.setAttachment(attachmentDto);


        mockMvc.perform(doPost("/v1/inputs", request))
                .andExpect(status().isOk());
    }

    @Test
    public void testInput_Invalid_InputId() throws Exception {
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setContent("some_bite_content");

        CreateInputRequest request = new CreateInputRequest();
        request.setInputId(-1);
        request.setName("newInputValidTest");
        request.setAttachment(attachmentDto);


        mockMvc.perform(doPost("/v1/inputs", request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", equalTo(400)))
                .andExpect(jsonPath("$.error", equalTo("inputId: must be greater than or equal to 0")));
    }

    @Test
    public void testInput_Invalid_NullAttachment() throws Exception {
        CreateInputRequest request = new CreateInputRequest();
        request.setInputId(1);
        request.setName("newInputValidTest");
        request.setAttachment(null);

        mockMvc.perform(doPost("/v1/inputs", request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", equalTo(400)))
                .andExpect(jsonPath("$.error", equalTo("attachment: must not be null")));
    }

    @Test
    public void testInput_Invalid_Attachment() throws Exception {
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setContent(null);

        CreateInputRequest request = new CreateInputRequest();
        request.setInputId(1);
        request.setName("newInputValidTest");
        request.setAttachment(attachmentDto);

        mockMvc.perform(doPost("/v1/inputs", request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", equalTo(400)))
                .andExpect(jsonPath("$.error", equalTo("attachment.content: must not be null")));
    }
}