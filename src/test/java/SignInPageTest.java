import models.Account;
import models.AccountIs;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;

public class SignInPageTest  extends BaseTest {

    private SignInPage signInPage;
    private MyAccountPage myAccountPage;
    private DataPoolNowInUpdate dp;

    //@BeforeMethod
    @BeforeClass
    public void beforeClass(ITestContext testContext) throws IOException {
        dp = new DataPoolNowInUpdate("registered", testContext, Account.class, AccountIs.REGISTERED);
        dp.addNewDataPool("notRegistered", testContext, Account.class, AccountIs.NOT_REGISTERED);
        driver.get(prop.getProperty("homePageURL")+prop.getProperty("signInPageURL"));
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    }

    @Test(dataProvider = "dataProvider")
    public void enterAccount(Account acc){
        System.out.println(
                "Login" +acc.getEmail().getEmail() +
                "\nPassword" + acc.getPassword().getPassword());

        signInPage.signInWith(
                acc.getEmail().getEmail(),
                acc.getPassword().getPassword());
        Assert.assertTrue(myAccountPage.getMyWishlists().isEnabled());
        myAccountPage.signOut();
    }

    @Test(dataProvider = "dataProviderNegative")
    public void enterAccountNegative(Account acc){
        System.out.println(
                "Login" +acc.getEmail().getEmail() +
                "\nPassword" + acc.getPassword().getPassword());

        signInPage.signInWith(
                acc.getEmail().getEmail(),
                acc.getPassword().getPassword());
        Assert.assertTrue(signInPage.getSubmit().isEnabled());
    }

    @DataProvider
    private Object[][] dataProvider(){
        return dp.getData(AccountIs.REGISTERED);
    }

    @DataProvider
    private Object[][] dataProviderNegative(){
        return dp.getData(AccountIs.NOT_REGISTERED);
    }
}