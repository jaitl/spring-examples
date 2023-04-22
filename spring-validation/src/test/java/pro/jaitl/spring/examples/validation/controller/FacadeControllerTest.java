package pro.jaitl.spring.examples.validation.controller;

import org.junit.jupiter.api.Test;
import pro.jaitl.spring.examples.validation.dto.FacadeDto;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FacadeControllerTest extends BaseApiTest {
    @Test
    public void testFacadeCreate_Valid() throws Exception {
        FacadeDto facadeDto = new FacadeDto();
        facadeDto.setDescription("test aaa");
        facadeDto.setName("test aaa");


        mockMvc.perform(doPost("/v1/facades", facadeDto))
                .andExpect(status().isOk());
    }

    @Test
    public void testFacadeCreate_Invalid() throws Exception {
        FacadeDto facadeDto = new FacadeDto();
        facadeDto.setName("test");
        facadeDto.setDescription("test");


        mockMvc.perform(doPost("/v1/facades", facadeDto))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", equalTo(400)))
                .andExpect(jsonPath("$.error", equalTo("facadeDto: Facade fields don't have the string pattern")));
    }
}