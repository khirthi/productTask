package com.task.products.Service;

import com.task.products.DTO.GetBillReceiptsForOrderIdDto;
import com.task.products.Repository.BillReceiptsRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@DisplayName("Bill Receipts ServiceImpl Tests")
@ExtendWith(MockitoExtension.class)
class BillReceiptsServiceImplTest {

    @Mock
    private BillReceiptsRepo billReceiptsRepo;

    @InjectMocks
    private BillReceiptsServiceImpl billReceiptsService;

    @Nested
    @DisplayName("Get Bill Receipt of Order")
    class GetBillReceiptOfOrderTests {

        @Test
        @DisplayName("Should return bill receipt for valid order ID")
        void shouldReturnBillReceiptForValidOrderId() {
            GetBillReceiptsForOrderIdDto dto = new GetBillReceiptsForOrderIdDto();
            dto.setOrderId(1);
            dto.setCustomerName("John Doe");
            dto.setSalesPersonName("Jane Smith");
            dto.setOrderValue(BigDecimal.valueOf(100.00));

            when(billReceiptsRepo.getBillReceiptOfOrder(anyInt())).thenReturn(Optional.of(dto));

            Optional<GetBillReceiptsForOrderIdDto> result = billReceiptsService.getBillReceiptOfOrder(1);

            assertEquals(Optional.of(dto), result);
        }

        @Test
        @DisplayName("Should return empty when order ID is not found")
        void shouldReturnEmptyWhenOrderIdNotFound() {
            when(billReceiptsRepo.getBillReceiptOfOrder(anyInt())).thenReturn(Optional.empty());

            Optional<GetBillReceiptsForOrderIdDto> result = billReceiptsService.getBillReceiptOfOrder(999);

            assertEquals(Optional.empty(), result);
        }
    }
}
