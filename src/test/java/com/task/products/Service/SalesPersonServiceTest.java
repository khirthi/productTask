package com.task.products.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SalesPersonServiceTest {

    @InjectMocks
    SalesPersonService salesPersonServiceTest = new SalesPersonServiceImpl();

}
