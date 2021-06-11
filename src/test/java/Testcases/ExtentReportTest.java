package Testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import config.Testbase;

public class ExtentReportTest extends Testbase {


    @Test
    public void passTest() {
        System.out.println("Pass");
        extentTest.get().info("Some info about pass test");

    }

    @Test
    public void skipTest() {
        System.out.println("Skip");
        extentTest.get().info("Some info about skip test");
        throw new SkipException("Skipped test");
    }

    @Test
    public void GoogleTittleValidation() {
        Assert.assertEquals(driver.getTitle(), "Google");
        extentTest.get().info("Some info about Google validation test");
    }

    @Test
    public void GoogleLogoValidation() {
        boolean img = driver.findElement(By.xpath("//img[@id='hplogo']")).isDisplayed();
        Assert.assertTrue(img);
        Assert.assertFalse(img);
    }


}
