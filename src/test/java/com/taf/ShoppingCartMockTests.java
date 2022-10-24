package com.taf;

import com.epam.reportportal.junit5.ReportPortalExtension;
import com.epam.tamentoring.bo.DiscountUtility;
import com.epam.tamentoring.bo.OrderService;
import com.epam.tamentoring.bo.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static com.taf.utils.UserProvider.getUserAccount;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = TestApplication.class)
@ExtendWith(ReportPortalExtension.class)
public class ShoppingCartMockTests {

    @Mock
    DiscountUtility discountUtility;

    @InjectMocks
    OrderService orderService = new OrderService();

    @Test
    public void injectMock() {
        double expectedDiscount = 3;
        UserAccount account = getUserAccount("John", "Smith", "1990/10/10");
        Mockito.when(orderService.getDiscountUtility().calculateDiscount(account)).thenReturn(expectedDiscount);
        Assertions.assertEquals(expectedDiscount, discountUtility.calculateDiscount(account));
        verify(discountUtility, Mockito.times(1)).calculateDiscount(account);
        verifyNoMoreInteractions(discountUtility);
    }
}
