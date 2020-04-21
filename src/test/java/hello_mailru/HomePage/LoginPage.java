package hello_mailru.HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class LoginPage {
    private final WebDriver driver;
    WebDriverWait wait;



    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);

    }

    @FindBy(id = "mailbox:login")
    WebElement loginField;

    @FindBy(id = "mailbox:password")
    WebElement passwordField;

    @FindBy(id = "mailbox:submit")
    WebElement submitButton;

    public MailPage login(String user, String password) {
        loginField.sendKeys(user);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        submitButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        passwordField.sendKeys(password);
        submitButton.click();
        wait.until(ExpectedConditions.urlContains("e.mail.ru"));
        return new MailPage(driver);

    }



}

