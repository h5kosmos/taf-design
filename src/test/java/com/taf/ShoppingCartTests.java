package com.taf;

import com.epam.reportportal.junit5.ReportPortalExtension;
import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.UserAccount;
import com.taf.utils.ProductProvider;
import com.taf.utils.UserProvider;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.collections.CollectionUtils;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(classes = TestApplication.class)
@ExtendWith(ReportPortalExtension.class)

public class ShoppingCartTests {

    private final static int ONE_ITEM = 1;
    private final static int TWO_ITEMS = 2;

    @Test
    void userAddProductToShoppingCartAndVerifyThatProductInCart() {
        Product expectedProduct = ProductProvider.getRandomProduct();
        UserAccount user = UserProvider.getUserAccount();
        user.getShoppingCart().addProductToCart(expectedProduct);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(user.getShoppingCart().getProducts().size()).isEqualTo(ONE_ITEM);
        softAssertions.assertThat(user.getShoppingCart().getProducts().get(0)).isEqualTo(expectedProduct);
    }

    @Test
    void userWithoutProductVerifyTotalPrice() {
        UserAccount user = UserProvider.getUserAccount();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(user.getShoppingCart().getProducts().size()).isEqualTo(0);
        softAssertions.assertThat(user.getShoppingCart().getCartTotalPrice()).isEqualTo(0);
    }

    @Test
    void userRemoveProductFromShoppingCartAndVerifyThatProductNotInCart() {
        Product expectedProduct = ProductProvider.getRandomProduct();
        UserAccount user = UserProvider.getUserAccount();
        user.getShoppingCart().addProductToCart(expectedProduct);
        user.getShoppingCart().removeProductFromCart(expectedProduct);

        assertFalse(CollectionUtils.hasElements(user.getShoppingCart().getProducts()));
    }

    @Test
    void userAddProductTwoTimesAndVerifyTotalQuantity() {
        Product expectedProduct = ProductProvider.getRandomProduct();
        UserAccount user = UserProvider.getUserAccount();
        user.getShoppingCart().addProductToCart(expectedProduct);
        user.getShoppingCart().addProductToCart(expectedProduct);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(user.getShoppingCart().getProducts().size()).isEqualTo(ONE_ITEM);
        softAssertions.assertThat(user.getShoppingCart().getProducts().get(0).getQuantity()).isEqualTo(expectedProduct.getQuantity() * 2);
    }

    @Test
    void userAddTwoProductsAndVerifyTotalPrice() {
        Product productOne = ProductProvider.getRandomProduct();
        Product productTwo = ProductProvider.getRandomProduct();
        UserAccount user = UserProvider.getUserAccount();
        user.getShoppingCart().addProductToCart(productOne);
        user.getShoppingCart().addProductToCart(productTwo);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(user.getShoppingCart().getProducts().size()).isEqualTo(TWO_ITEMS);
        softAssertions.assertThat(user.getShoppingCart().getCartTotalPrice()).isEqualTo(productOne.getPrice() + productTwo.getPrice());
    }
}
