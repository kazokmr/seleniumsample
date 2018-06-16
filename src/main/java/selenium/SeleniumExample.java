package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumExample {
  public static void main(String[] args) {
    
    // ChromeDriverまでのパスを設定する
    System.setProperty("webdriver.chrome.driver", "selenium/chromedriver/2.40/chromedriver");
    WebDriver driver = new ChromeDriver();
    
    driver.get("https://www.google.com");
    
    WebElement element = driver.findElement(By.name("q"));
    
    element.sendKeys("Cheese!");
    
    element.submit();
    
    System.out.println("Page title is: " + driver.getTitle());
    
    (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver webDriver) {
        return webDriver.getTitle().toLowerCase().startsWith("cheese!");
      }
    });
    
    System.out.println("Page title is: " + driver.getTitle());
    
    driver.quit();
  }
}
