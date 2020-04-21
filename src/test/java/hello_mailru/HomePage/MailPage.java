package hello_mailru.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MailPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public MailPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    public String getUserNAme() {
        return driver.findElement(By.id("PH_user-email")).getText();
    }

    public String createMail(String to, String theme, String body) {
        driver.findElement(By.className("compose-button__wrapper")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='button2__wrapper'][@tabindex='570']")));
        driver.findElement(By.xpath("//input[@class='container--H9L5q size_s_compressed--2c-eV'][@tabindex='100']")).sendKeys(to);
        driver.findElement(By.xpath("//input[@class='container--H9L5q size_s_compressed--2c-eV'][@tabindex='400']")).sendKeys(theme);
        driver.findElement(By.xpath("/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[5]/div/div/div[2]/div[1]")).sendKeys(body);
        //driver.switchTo().frame(driver.findElement(By.xpath("//*[@class='editable-container-opee']")));
        //driver.switchTo().activeElement().sendKeys(body);
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//span[@class='button2__wrapper'][@tabindex='580']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='compose-windows__notify']")));
        return driver.findElement(By.xpath("//*[@class='compose-windows__notify']")).getText();

    }

    public String verifyAddressee() {
        return driver.findElement(By.xpath("//span[@class='text--1tHKB']")).getText();
    }


    public String verifySubject() {
        return driver.findElement(By.xpath("//*[@class='container--H9L5q size_s_compressed--2c-eV'][@tabindex='400']")).getText();
    }

    public String verifyBody() {
        return driver.findElement(By.xpath("/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[5]/div/div/div[2]/div[1]")).getText();
    }

    public String sendMail() {
        driver.findElement(By.xpath("//span[@class='button2__wrapper'][@tabindex='570']")).click();
        return driver.findElement(By.xpath("//div[@class='layer__header']")).getText();
    }

    public String checkDraft() {
        driver.findElement(By.xpath("//*[@id='ph_mail']/span")).click();
        driver.findElement(By.xpath("//*[@class='ico ico_16-categories:drafts_s ico_categories:drafts_s ico_size_s']")).click();
        return driver.findElement(By.xpath("//span[@class='octopus__title']")).getText();
    }

    public String checkSent() {
        driver.findElement(By.xpath("//*[@id='ph_mail']/span")).click();
        driver.findElement(By.xpath("//*[@class='ico ico_16-actions:reply_s ico_actions:reply_s ico_size_s']")).click();
        return driver.findElement(By.xpath("//*[@class='ll-sj__normal']")).getText();
    }

    public String logOut() {
        driver.findElement(By.id("PH_logoutLink")).click();
        return driver.findElement(By.id("PH_authLink")).getText();
    }
}
