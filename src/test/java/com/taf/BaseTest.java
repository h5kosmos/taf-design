package com.taf;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseTest {

    @Test(description = "Failed test")
    public void failedTest() {
        Assert.assertTrue(false);
    }

    @Test(description = "Success test")
    public void successTest() {

    }
}
