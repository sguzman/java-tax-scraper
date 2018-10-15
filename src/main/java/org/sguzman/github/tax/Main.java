package org.sguzman.github.tax;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class Main {
  public final static String[] barNumbers = {"24041980", "00792098"};

  private final static String username = "dhoffpauir@hoffpauirlaw.com";
  private final static String password = "maverick2010";

  private final static String user_id = "ctl00_ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder2_ContentPlaceHolder2_txtUserName";
  private final static String pass_id = "ctl00_ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder2_ContentPlaceHolder2_txtPassword";

  private final static String button_id = "ctl00_ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder2_ContentPlaceHolder2_btnLoginImageButton";
  private final static String online_services  = "ctl00_lnkOnlineServices";
  private final static String search_records  = "ctl00_hlSearch";

  private final static String mobile_id = "ctl00_ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder2_ContentPlaceHolder2_lnkMobileApp";

  public static void main(String[] args) throws IOException {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
    //chromeOptions.addArguments("--headless");

    WebDriver Driver = new ChromeDriver(chromeOptions);
    Driver.navigate().to("https://www.hcdistrictclerk.com/eDocs/Secure/Login.aspx?ReturnUrl=%2fCommon%2fDefault.aspx%3fShowFF%3d1");
    System.out.println(Driver.getTitle());

    WebDriverWait waitForUsername = new WebDriverWait(Driver, 5);
    waitForUsername.until(ExpectedConditions.visibilityOfElementLocated(By.id(user_id)));

    Driver.findElement(By.id(user_id)).sendKeys(username);
    Driver.findElement(By.id(pass_id)).sendKeys(password);
    Driver.findElement(By.id(button_id)).click();

    WebDriverWait waitForError = new WebDriverWait(Driver, 5);
    waitForError.until(ExpectedConditions.visibilityOfElementLocated(By.id(online_services)));

    Actions action = new Actions(Driver);
    action.moveToElement(Driver.findElement(By.id(online_services))).build().perform();

    WebDriverWait waitSearch = new WebDriverWait(Driver, 5);
    waitSearch.until(ExpectedConditions.visibilityOfElementLocated(By.id(search_records)));
    Driver.findElement(By.id(search_records)).click();

    WebDriverWait waitMobile = new WebDriverWait(Driver, 5);
    waitMobile.until(ExpectedConditions.visibilityOfElementLocated(By.id(mobile_id)));
    Driver.findElement(By.id(mobile_id)).click();

    //Driver.quit();
  }
}
