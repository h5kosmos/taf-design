package com.taf.utils;

import com.epam.tamentoring.bo.Product;
import lombok.experimental.UtilityClass;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@UtilityClass
public class ProductProvider {

    public static Product getRandomProduct() {
        int id = current().nextInt(1, 1000);
        String name = randomAlphanumeric(8, 14);
        double price = current().nextDouble(0.1, 100);
        double quantity = current().nextDouble(0.1, 5);
        return new Product(id, name, price, quantity);
    }
}
