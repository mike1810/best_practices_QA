import models.Account;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class SignInPageTest  extends BaseTest {

    private SignInPage signInPage;
    DataPoolNowInUpdate dp;

    @BeforeClass
    public void beforeClass(ITestContext testContext) throws IOException {
        super.beforeClass();
        dp = new DataPoolNowInUpdate("accFile", testContext, Account.class);
        driver.get(prop.getProperty("signInPageURL"));
        signInPage = new SignInPage(driver);
        signInPage = PageFactory.initElements(driver, SignInPage.class);
    }

    @Test(dataProvider = "dataProvider")
    public void goToRegistrationPage(Account acc){
        /*{
  "Email": {
    "email":"tester@tester.tester"
  },
  "Password": {
    "password":"12345"
  }
}*/
        signInPage.sendNewEmail(acc.getEmail().getEmail());
        signInPage.clickButtonToCreateAccount();
    }

    @DataProvider
    private Object[][] dataProvider(){
        return dp.getData();
    }
}