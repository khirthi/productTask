package com.task.products.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.products.DTO.GetOrderInfoDto;
import com.task.products.DTO.OrderItemDto;
import com.task.products.Service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Order Controller Tests")
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    private MockMvc mockMvc;
    private GetOrderInfoDto orderInfo;
    String orderInfoJson;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        MockitoAnnotations.openMocks(this);
        orderInfo = new GetOrderInfoDto();
        orderInfo.setOrderPlacedByCustomer(1);
        orderInfo.setSalesPersonIncharge(1);
        orderInfo.setOrderItems(List.of(new OrderItemDto(1, 2)));
        orderInfo.setStatus("PLACED");

        orderInfoJson = objectMapper.writeValueAsString(orderInfo);

        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Nested
    @DisplayName("Add Order Tests")
    class AddOrderTests {

        @Test
        @DisplayName("Should Add Order Successfully")
        void shouldAddOrderSuccessfully() throws Exception {

            mockMvc.perform(post("/orders")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(orderInfoJson))
                    .andExpect(status().isNoContent());

            verify(orderService).addOrder(any(GetOrderInfoDto.class));
        }

        @Test
        @DisplayName("Should Fail to Add Order When Service Throws Bad Request Exception")
        void shouldFailToAddOrderWhenServiceThrowsBadRequestException() throws Exception {

            String orderInfo = objectMapper.writeValueAsString(null);

            mockMvc.perform(post("/orders")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(orderInfo))
                    .andExpect(status().isBadRequest());
        }
    }
}
