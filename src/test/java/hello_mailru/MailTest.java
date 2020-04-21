package hello_mailru;

import hello_mailru.HomePage.LoginPage;
import hello_mailru.HomePage.MailPage;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MailTest extends WebdriverSetting{

    private String userName = "dashenka.anisimova.2000";                            //Почтовый адрес для авторизации
    private String userPassword = "19Mart2020";                                     //Пароль для авторизации
    private String toUserName = "adel2425@tut.by";                                  //Почтовый адрес получателя
    private String themeOfMail = "Test";                                            //Тема письма
    private String bodyOfMail = "I sent you the test mail";                         //Содержание письма
    private MailPage mailPage;
    private LoginPage loginPage;


    @Test(priority = 1)
    public void loginTest(){
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mailPage = loginPage.login(userName, userPassword);
        Assert.assertEquals("dashenka.anisimova.2000@mail.ru", mailPage.getUserNAme());

    }

    @Test(priority = 2)
    public void createMail(){
        String result = mailPage.createMail(toUserName, themeOfMail, bodyOfMail);
        Assert.assertTrue(result.contains("Сохранено в черновиках"));
    }
    @Test(priority = 3)
    public void verifyAddressee(){
        String receiver = mailPage.verifyAddressee();
        Assert.assertTrue(receiver.contains("adel2425@tut.by"), "Addressee mismatching!");
        System.out.println("Addressee is the same");
    }

    @Test(priority = 3)
    public void verifySubject(){
        String subject = mailPage.verifySubject();
        Assert.assertTrue(subject.contains("Test"), "Subject mismatching!");
        //Assert.assertThat(subject, CoreMatchers.containsString("Test"));
        //Assert.assertEquals(subject, "Test Mail");
        System.out.println("Subject is the same");
    }

    @Test(priority = 3)
    public void verifyBody(){
        String body = mailPage.verifyBody();
        Assert.assertTrue(body.contains("I sent you the test mail"),"Body mismatching!");
        System.out.println("Body is the same");
    }

    @Test(priority =4)
    public void sendMail(){
        String result = mailPage.sendMail();
        Assert.assertTrue(result.contains("Письмо отправлено"));

    }

    @Test(priority = 5)
    public void checkDraft(){
        String result = mailPage.checkDraft();
        //Assert.assertEquals(result, "У вас нет незаконченных или неотправленных писем", "The draft folder is not empty!");
        Assert.assertTrue(result.contains("У вас нет незаконченных"));
    }
    @Test(priority = 6)
    public void checkSent(){
        String result = mailPage.checkSent();
        Assert.assertTrue(result.contains("Test"));
    }
    @Test(priority = 7)
    public void logOut(){
        String logLink = mailPage.logOut();
        Assert.assertTrue(logLink.contains("Вход"));
    }

    }



