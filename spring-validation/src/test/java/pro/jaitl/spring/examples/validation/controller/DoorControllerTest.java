package pro.jaitl.spring.examples.validation.controller;

import org.junit.jupiter.api.Test;
import pro.jaitl.spring.examples.validation.dto.DoorDto;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DoorControllerTest extends BaseApiTest {
    @Test
    public void testFacadeCreate_Valid() throws Exception {
        DoorDto doorDto = new DoorDto();
        doorDto.setMaterialType("steel");
        doorDto.setLockType("digital");


        mockMvc.perform(doPost("/v1/doors", doorDto))
                .andExpect(status().isOk());
    }

    @Test
    public void testFacadeCreate_Invalid() throws Exception {
        DoorDto doorDto = new DoorDto();
        doorDto.setMaterialType("wood");
        doorDto.setLockType("key");

        mockMvc.perform(doPost("/v1/doors", doorDto))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", equalTo(400)))
                .andExpect(jsonPath("$.error", equalTo("validDoor: Unreliable door")));
    }
}