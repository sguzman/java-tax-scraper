package org.sguzman.github.tax;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

  private final static int timeoutOutInSeconds = 10;

  private final static String barcode_id = "tabSearch_tabCivil_txtBarNumber";

  private static ChromeDriver initDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
    //chromeOptions.addArguments("--headless");

    return new ChromeDriver(chromeOptions);
  }

  private static void mobile(WebDriver Driver) {
    Driver.navigate().to("https://www.hcdistrictclerk.com/eDocs/Secure/Login.aspx?ReturnUrl=%2fCommon%2fDefault.aspx%3fShowFF%3d1");
    System.out.println(Driver.getTitle());

    WebDriverWait waitForUsername = new WebDriverWait(Driver, timeoutOutInSeconds);
    waitForUsername.until(ExpectedConditions.visibilityOfElementLocated(By.id(user_id)));

    Driver.findElement(By.id(user_id)).sendKeys(username);
    Driver.findElement(By.id(pass_id)).sendKeys(password);
    Driver.findElement(By.id(button_id)).click();

    WebDriverWait waitForError = new WebDriverWait(Driver, timeoutOutInSeconds);
    waitForError.until(ExpectedConditions.visibilityOfElementLocated(By.id(online_services)));

    Actions action = new Actions(Driver);
    action.moveToElement(Driver.findElement(By.id(online_services))).build().perform();

    WebDriverWait waitSearch = new WebDriverWait(Driver, timeoutOutInSeconds);
    waitSearch.until(ExpectedConditions.visibilityOfElementLocated(By.id(search_records)));
    Driver.findElement(By.id(search_records)).click();

    WebDriverWait waitMobile = new WebDriverWait(Driver, timeoutOutInSeconds);
    waitMobile.until(ExpectedConditions.visibilityOfElementLocated(By.id(mobile_id)));
    Driver.findElement(By.id(mobile_id)).click();
  }

  public static void main(String[] args) throws IOException {
    WebDriver Driver = initDriver();
    mobile(Driver);

    ArrayList<CourtCase> list = new ArrayList<>();
    for (String barcode : barNumbers) {
      WebDriverWait waitForBarNumberInput = new WebDriverWait(Driver, timeoutOutInSeconds);
      waitForBarNumberInput.until(ExpectedConditions.visibilityOfElementLocated(By.id(barcode_id)));
      Driver.findElement(By.id(barcode_id)).sendKeys(barcode);
      Driver.findElement(By.id("btnSearch")).click();

      WebDriverWait waitResults = new WebDriverWait(Driver, timeoutOutInSeconds * 4);
      waitResults.until(ExpectedConditions.visibilityOfElementLocated(By.id("tblResults")));
      Document doc = Jsoup.parse(Driver.getPageSource());
      Elements rows = doc.select("tr[style]");
      for (int i = 0; i < rows.size(); ++i) {
        Element r = rows.get(i);
        Elements cells = r.select("td");

        String cause = cells.get(0).text().trim().replace("\n", "");
        String style = cells.get(1).text().trim().replace("\n", "");
        String fileDate = cells.get(2).text().trim().replace("\n", "");
        String court = cells.get(3).text().trim().replace("\n", "");
        String region = cells.get(4).text().trim().replace("\n", "");
        String type = cells.get(5).text().trim().replace("\n", "");

        System.out.print(cause);
        System.out.print(',');
        System.out.print(style);
        System.out.print(',');
        System.out.print(fileDate);
        System.out.print(',');
        System.out.print(court);
        System.out.print(',');
        System.out.print(region);
        System.out.print(',');
        System.out.print(type);
        System.out.println();

        list.add(new CourtCase(cause, style, fileDate, court, region, type));
      }

      break;
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    reader.readLine();

    Driver.quit();
  }
}
