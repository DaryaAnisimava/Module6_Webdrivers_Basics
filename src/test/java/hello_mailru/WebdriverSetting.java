package hello_mailru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;



public class WebdriverSetting {
    WebDriver driver;
    private String browserDriver = "webdriver.chrome.driver";   //Вид драйвера
    private String driverPath = "C:\\Users\\Darya_Anisimava\\webdriver\\chromedriver_win32\\chromedriver.exe";         //Путь до драйвера



    @BeforeClass
    public void SetUp() {
        System.setProperty(browserDriver, driverPath);
        driver = new ChromeDriver();
        driver.get("https://mail.ru/");

    }



    @AfterClass
    public void tearDown() {
        driver.close();

    }

}