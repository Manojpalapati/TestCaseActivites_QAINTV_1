package demo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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
       // driver.close();
    }

    public void testCase02() {
        System.out.println("Start Test case: testCase02");
    
        // Visit the link
        driver.get("https://in.bookmyshow.com/explore/home/chennai");
    
        // Get all the hyperlinks present in the page
        List<WebElement> links = driver.findElements(By.tagName("a"));
    
        // Count the number of hyperlinks
        int linkCount = links.size();
    
        // Print the link count
        System.out.println("The total number of hyperlinks is: " + linkCount);
    
        System.out.println("end Test case: testCase02");
        //endTest();
    
    
    }

    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
    
        // Visit LinkedIn
        driver.get("https://in.linkedin.com/");
    
        // Sign in to LinkedIn account
        // TODO: Replace the following username and password with your LinkedIn credentials
        String username = "manojpalapati@gmail.com";
        String password = "a!s@d#f$g%@3333";
    
        WebElement signInButton = driver.findElement(By.xpath("//a[contains(text(),'Sign in')]"));
        signInButton.click();
    
        WebElement emailInput = driver.findElement(By.id("username"));
        emailInput.sendKeys(username);
    
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
    
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();
    
        // Wait for the page to load
        Thread.sleep(3000);
    
        // Print the count of `Who's viewed your profile` and `Impressions of your post`
        WebElement whoViewedYourProfileCountElement = driver.findElement(By.xpath("(//span[@class='feed-identity-widget-item__stat']//strong)[1]"));
        String whoViewedYourProfileCount = whoViewedYourProfileCountElement.getText();
        System.out.println("Count of Who's viewed your profile: " + whoViewedYourProfileCount);
    
        // Wait for the page to load
        Thread.sleep(5000);
    
    
        WebElement impressionsOfYourPostCountElement = driver.findElement(By.xpath("(//span[@class='feed-identity-widget-item__stat']//strong)[2]"));
        String impressionsOfYourPostCount = impressionsOfYourPostCountElement.getText();
        System.out.println("Impressions of your post: " + impressionsOfYourPostCount);
        // Wait for the page to load
        Thread.sleep(5000);
    
        // Create a post, sharing with `Connections only` and confirm if it gets posted
    
        // TODO: Replace the following post text with your own post text
        String postText = "This is a test post.";
    
        WebElement newPostButton = driver.findElement(By.xpath("//span[text()[normalize-space()='Start a post']]"));
        newPostButton.click();
        Thread.sleep(3000);
    
        WebElement postTextArea = driver.findElement(By.xpath("//div[@aria-label='Text editor for creating content']"));
        postTextArea.sendKeys(postText);
        Thread.sleep(3000);
    
        WebElement shareButton = driver.findElement(By.xpath("//div[contains(@class,'share-unified-settings-entry-button__lockup-content artdeco-entity-lockup__content')]//div[1]"));
        //System.out.println("share button found");
        shareButton.click();
         Thread.sleep(2000);
        WebElement connectionsonly = driver.findElement(By.xpath("(//button[@role='radio'])[2]"));
        connectionsonly.click();
        Thread.sleep(3000);
        WebElement done = driver.findElement(By.xpath("//button[contains(@class,'share-box-footer__primary-btn artdeco-button')]"));
        done.click();
        Thread.sleep(3000);
        WebElement post = driver.findElement(By.xpath("//div[@class='share-box_actions']//button[1]"));
        post.click();
    
    
        // Wait for the post to be posted
        Thread.sleep(5000);
    
        // Confirm if the post was posted
        WebElement postElement = driver.findElement(By.xpath("(//div[contains(@class,'update-components-text relative')])[1]"));
        if (postElement != null) {
          System.out.println("Post was posted successfully!");
        } else {
          System.out.println("Post was not posted successfully!");
        }
    
        System.out.println("end Test case: testCase03");
      }


      public void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");
        // Visit the page: https://the-internet.herokuapp.com/nested_frames
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        
        // Switch to the top frame
        driver.switchTo().frame("frame-top");

        // Print the text of the top frame
        

        // Switch to the left frame inside the top frame
        driver.switchTo().frame(driver.findElement(By.name("frame-left")));

        // Print the text of the left frame inside the top frame
       String text = driver.findElement(By.tagName("body")).getText();
        System.out.println(text);

        // Switch back to the top frame
        driver.switchTo().defaultContent();
        // Switch to the top frame
        driver.switchTo().frame("frame-top");

        // Switch to the middle frame inside the top frame
        driver.switchTo().frame(driver.findElement(By.name("frame-middle")));

        // Print the text of the middle frame inside the top frame
        text = driver.findElement(By.tagName("body")).getText();
        System.out.println(text);

        // Switch back to the top frame
        driver.switchTo().defaultContent();
          // Switch to the top frame
        driver.switchTo().frame("frame-top");

        // Switch to the right frame inside the top frame
        driver.switchTo().frame(driver.findElement(By.name("frame-right")));
        // Print the text of the right frame inside the top frame
        text = driver.findElement(By.tagName("body")).getText();
        System.out.println(text);

        // Switch back to the top frame
        driver.switchTo().defaultContent();

        // Switch to the bottom frame
        driver.switchTo().frame(driver.findElement(By.name("frame-bottom")));

        // Print the text of the bottom frame
        text = driver.findElement(By.tagName("body")).getText();
        System.out.println(text);

        // Switch back to the default frame
        driver.switchTo().defaultContent();
        System.out.println("End Test case: testCase04");
    }
    
     public void testCase05(){
        System.out.println("Start Test case: testCase05");
    // Visit the site: https://www.imdb.com/chart/top
    driver.get("https://www.imdb.com/chart/top");
    // Scroll down to visibility of sort by option
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebElement sortBy = driver.findElement(By.id("sort-by-selector"));
    js.executeScript("arguments[0].scrollIntoView();", sortBy);


    // Find count of movies with class name cli-parent
    List<WebElement> movieCount = driver.findElements(By.className("cli-parent"));
    System.out.println("Count of movies: " + movieCount.size());

    // Click on sort by option and select 'IMDb rating'
    Select sortByOption = new Select(driver.findElement(By.id("sort-by-selector")));
    sortByOption.selectByValue("USER_RATING");

//     // Find highest rated movie
    WebElement highestRatedMovie = driver.findElement(By.xpath("//h3[text()='1. The Shawshank Redemption']"));
    System.out.println("Highest rated movie: " + highestRatedMovie.getText());

       sortByOption.selectByValue("RELEASE_DATE");

//     // Find the most recent movie on the list
    WebElement mostRecentMovie = driver.findElement(By.xpath("//h3[text()='47. Oppenheimer']"));
    System.out.println("Most recent movie on the list: " + mostRecentMovie.getText());

//     // Scroll down to the oldest movie on the list
    WebElement oldestMovie = driver.findElement(By.xpath("//h3[text()='131. The Kid']"));
    js.executeScript("arguments[0].scrollIntoView();", oldestMovie);
    System.out.println("Oldest movie on the list: " + oldestMovie.getText());


     js.executeScript("arguments[0].scrollIntoView();", sortByOption);

//     // Click on sort by option and select 'Popularity'
       sortByOption.selectByValue("POPULARITY");

//     // Find the movie with the most user ratings
    WebElement mostUserRatings = driver.findElement(By.xpath("//h3[text()='47. Oppenheimer']"));
    System.out.println("Movie with the most user ratings: " + mostUserRatings.getText());
    System.out.println("Start Test case: testCase05");
    
    }

    public void testCase06() throws IOException {
        System.out.println("Start Test case: testCase06");
        // Open the browser and navigate to the URL
        driver.get(" https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");

        // Switch to the frame with the id "iframeResult"
        WebElement iframe = driver.findElement(By.id("iframeResult"));
        driver.switchTo().frame(iframe);

        // Find the "Try it" button at the top of the page and click on it
        WebElement tryItButton = driver.findElement(By.tagName("button"));
        tryItButton.click();

        // Get the current window handle of the original window
        String originalWindowHandle = driver.getWindowHandle();

        // Switch to the new window that was opened
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Get the URL and Title of the new window
        String newWindowUrl = driver.getCurrentUrl();
        String newWindowTitle = driver.getTitle();

        // Take a screenshot of the new window
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;
        File screenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);

        screenshot.renameTo(new File("new_window_screenshot.png"));

        // Close the new window
        driver.close();

        // Switch back to the original window using the window handle that you obtained in step 3
        driver.switchTo().window(originalWindowHandle);

        // Print the URL and Title of the original window
        System.out.println("Original window URL: " + driver.getCurrentUrl());
        System.out.println("Original window Title: " + driver.getTitle());
        System.out.println("Start Test case: testCase06");
        endTest();
    }

      
}

