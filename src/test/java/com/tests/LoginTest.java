package com.tests;

import base.BaseTest;
import listener.TestListener;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pojo.Login;
import utils.LogHelper;
import utils.TestDataProvider;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void preconditon() {
        loginPage = PageFactory.initElements(BaseTest.getDriver(), LoginPage.class);

    }


    @Test(dataProvider = "loginuser", dataProviderClass = TestDataProvider.class)
    public void testcase01(Login login) {
        LogHelper.getLogger().info("===== Starting Test =====");
        Assert.assertTrue(loginPage.login(login.getUsername(), login.getPassword()), "The Login successfully");

    }
}
