package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
         public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("amazon");
        searchBox.submit();

        // Try to find an element with the text "Amazon.in"
        WebElement amazonInElement = driver.findElement(By.xpath("//h3[text()='Amazon.in']"));

        // If the element exists and is displayed, print a message saying that the Amazon page was found
        if (amazonInElement != null && amazonInElement.isDisplayed()) {
            System.out.println("Amazon page found");
        } else {
            System.out.println("Amazon page not found");
        }

        System.out.println("end Test case: testCase01");
    }
}
