package selenium;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeTest {
  
  private WebDriver webDriver;
  
  @Before
  public void createDriver() {
    System.setProperty("webdriver.chrome.driver", "selenium/chromedriver/2.40/chromedriver");
    webDriver = new ChromeDriver();
  }
  
  @After
  public void quitDriver() {
    webDriver.close();
  }
  
  @Test
  public void testGoogle() {
    webDriver.get("https://www.google.com");
    assertThat(webDriver.getTitle(), is("Google"));
  }
  
  @Test
  public void testGoogleSearch() {
    webDriver.get("https://www.google.com");
    
    WebElement searchElement = webDriver.findElement(By.name("q"));
    searchElement.sendKeys("selenium");
    searchElement.submit();
    
    // ページが更新するまで待つ（Timeoutは10秒)
    new WebDriverWait(webDriver, 10)
        .until((ExpectedCondition<Boolean>) webDriver -> webDriver.getTitle().toLowerCase().startsWith("selenium"));
    
    assertThat(webDriver.getTitle(), is("selenium - Google 検索"));
  }
}
