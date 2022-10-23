package com.taf.utils;

import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.bo.UserAccount;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static com.taf.utils.DateProvider.getRandomDateOfBirth;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@UtilityClass
public class UserProvider {

    public UserAccount getUserAccount() {
        String firstName = randomAlphanumeric(8, 14);
        String lastName = randomAlphanumeric(8, 14);
        String dateOfBirth = getRandomDateOfBirth();
        List<Product> products = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart(products);
        return new UserAccount(firstName, lastName, dateOfBirth, shoppingCart);
    }

    public UserAccount getUserAccount(String firstName, String lastName,String dateOfBirth) {
        List<Product> products = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart(products);
        return new UserAccount(firstName, lastName, dateOfBirth, shoppingCart);
    }
}
