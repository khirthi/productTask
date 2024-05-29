package com.task.products.Controller;

import com.task.products.DTO.GetBillReceiptsForOrderIdDto;
import com.task.products.Service.BillReceiptsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Bill Receipts Controller Tests")
@ExtendWith(MockitoExtension.class)
class BillReceiptsControllerTest {

    @Mock
    private BillReceiptsService billReceiptsService;

    @InjectMocks
    private BillReceiptsController billReceiptsController;

    private MockMvc mockMvc;
    private GetBillReceiptsForOrderIdDto dto;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        dto = new GetBillReceiptsForOrderIdDto();
        dto.setOrderId(1);
        dto.setCustomerName("John Doe");
        dto.setSalesPersonName("Jane Smith");
        dto.setOrderValue(BigDecimal.valueOf(100.00));


        mockMvc = MockMvcBuilders.standaloneSetup(billReceiptsController).build();
    }

    @Nested
    @DisplayName("Get Bill Receipt of Order")
    class GetBillReceiptOfOrderTests {

        @Test
        @DisplayName("Should return bill receipt for valid order ID")
        void shouldReturnBillReceiptForValidOrderId() throws Exception {

            when(billReceiptsService.getBillReceiptOfOrder(1)).thenReturn(Optional.of(dto));

            mockMvc.perform(get("/bills/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Should return 404 when order ID is not found")
        void shouldReturn404WhenOrderIdNotFound() throws Exception {

            when(billReceiptsService.getBillReceiptOfOrder(1)).thenReturn(Optional.empty());

            mockMvc.perform(get("/bills/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }
    }
}
