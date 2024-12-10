package org.example;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {
    @DataProvider(name = "LoginDataProvider")
    public Object[][] getData() {
        return new Object[][]{
                {"student", "Password123"}, // Valid credentials
                {"invalidUser", "Password143"}, // Invalid credentials
                {"testUser", "wrongPassword123"} // Invalid credentials
        };
    }
}
