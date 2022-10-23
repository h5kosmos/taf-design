package com.taf.suites;

import com.taf.ShoppingCartMockTests;
import com.taf.ShoppingCartTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ShoppingCartTests.class, ShoppingCartMockTests.class})
public class ShoppingCartSuite {
}
