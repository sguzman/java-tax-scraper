package org.sguzman.github.tax;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class Main {
  public final static String[] barNumbers = {"24041980", "00792098"};

  public static void main(String[] args) throws IOException {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
    chromeOptions.addArguments("--headless");

    WebDriver Driver = new ChromeDriver(chromeOptions);
    Driver.navigate().to("https://the-internet.herokuapp.com/login");

    WebDriverWait waitForUsername = new WebDriverWait(Driver, 5000);
    waitForUsername.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

    Driver.findElement(By.id("username")).sendKeys("tomsmith");
    Driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

    Driver.findElement(By.cssSelector("button.radius")).click();
    System.out.println(Driver.getPageSource());

    WebDriverWait waitForError = new WebDriverWait(Driver, 5000);
    waitForError.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
    Driver.quit();
  }
}
