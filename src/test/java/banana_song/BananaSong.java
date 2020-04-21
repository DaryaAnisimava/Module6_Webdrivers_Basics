package banana_song;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class BananaSong {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver;
        System.setProperty("webdriver.firefox.driver", "src/test/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://google.com");
        driver.findElement(By.name("q")).click();

        Actions make  = new Actions(driver);
        Action kbEvents = make.sendKeys("Banana song", Keys.ENTER).build();
        kbEvents.perform();
        WebElement searchInput = waitForElementLocatedBy(driver,By.xpath("//*[@href='https://www.youtube.com/watch?v=wCkerYMffMo']"));
        searchInput.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        if (driver.findElement(By.xpath("//*[@id='logo']")).isDisplayed()!=true){
            throw new RuntimeException("You are not on Youtube page!");
        }

        String times=driver.findElement(By.xpath("//*[@id='count']/yt-view-count-renderer/span[1]")).getText();
        String s=times.replaceAll("\\D","");

        if (Integer.parseInt(s)<50000000){
            throw new RuntimeException("This is not valid video!");
        }

        String timeWatch=driver.findElement(By.xpath("//*[@id='movie_player']/div[23]/div[2]/div[1]/div/span[3]")).getText();
        System.out.println(timeWatch);

        Assert.assertEquals(timeWatch, "0:49", "This is not valid video!");

        driver.close();
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
