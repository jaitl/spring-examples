package pro.jaitl.spring.examples.validation.controller;

import org.junit.jupiter.api.Test;
import pro.jaitl.spring.examples.validation.dto.OutputDto;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OutputControllerTest extends BaseApiTest {
    @Test
    public void testOutput_Create_Valid() throws Exception {
        OutputDto outputDto = new OutputDto();
        outputDto.setName("test");
        outputDto.setOutputId(1);

        mockMvc.perform(doPost("/v1/outputs", outputDto))
                .andExpect(status().isOk());
    }

    @Test
    public void testOutput_Create_Invalid() throws Exception {
        OutputDto outputDto = new OutputDto();
        outputDto.setName(null);
        outputDto.setOutputId(null);

        mockMvc.perform(doPost("/v1/outputs", outputDto))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", equalTo(400)))
                .andExpect(jsonPath("$.error", containsString("outputId: must not be null")))
                .andExpect(jsonPath("$.error", containsString("name: must not be null")));
    }

    @Test
    public void testOutput_Update_Valid() throws Exception {
        OutputDto outputDto = new OutputDto();
        outputDto.setName(null);
        outputDto.setOutputId(null);

        mockMvc.perform(doPut("/v1/outputs", outputDto))
                .andExpect(status().isOk());
    }

    @Test
    public void testOutput_Update_Invalid() throws Exception {
        OutputDto outputDto = new OutputDto();
        outputDto.setName("it's longer than 20 characters");
        outputDto.setOutputId(-1);

        mockMvc.perform(doPut("/v1/outputs", outputDto))
                .andExpect(jsonPath("$.code", equalTo(400)))
                .andExpect(jsonPath("$.error", containsString("outputId: must be greater than or equal to 0")))
                .andExpect(jsonPath("$.error", containsString("name: size must be between 0 and 20")));
    }
}