/*
package notActualTests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignInPageTest  extends BaseTest {

    private SignInPage signInPage;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        driver.get(prop.getProperty("signInPageURL"));
        signInPage = new SignInPage(driver);
        signInPage = PageFactory.initElements(driver, SignInPage.class);
    }

    @Test(dataProvider = "DPForAccountCreating", dataProviderClass = User.class)
    public void goToRegistrationPage(User user){*/
/*
        signInPage.waitPageIsLoaded();
        signInPage.checkPageTitle();*//*

        signInPage.sendNewEmail(user.getEmail());
        signInPage.clickButtonToCreateAccount();
    }
}*/
