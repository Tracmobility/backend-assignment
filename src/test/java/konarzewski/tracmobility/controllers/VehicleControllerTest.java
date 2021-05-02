package konarzewski.tracmobility.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class that implements Vehicle integration tests.
 *
 * @author Pawel Konarzewski
 */

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should trigger 404 status code if can not find QR-code")
    void shouldTrigger404StatusCodeIfCanNotFindQrCode() throws Exception {
        //given
        var qrCode = "QR_0010";
        //when
        var mockResult = mockMvc.perform(patch("/vehicles/borrow/" + qrCode))
                .andExpect(status().isNotFound());
    }


}