import models.Account;
import models.DataIs;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;

public class SignInPageTest extends BaseTest {

    private SignInPage signInPage;
    private MyAccountPage myAccountPage;
    private DataPoolNowInUpdate dp;

    //@BeforeMethod
    @BeforeClass
    public void beforeClass(ITestContext testContext) throws IOException {
        dp = new DataPoolNowInUpdate("registered", testContext, Account.class, DataIs.USER_BEFORE_EDITING);
        dp.addNewDataPool("notRegistered", testContext, Account.class, DataIs.USER_AFTER_EDITING);
        driver.get(prop.getProperty("homePageURL") + prop.getProperty("signInPageURL"));
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    }

    @Test(dataProvider = "dataProvider")
    public void enterAccount(Account acc) {
        System.out.println(
                "Login" + acc.getEmail().getEmail() +
                        "\nPassword" + acc.getPassword().getPassword());

        signInPage.signInWith(
                acc.getEmail().getEmail(),
                acc.getPassword().getPassword());
        Assert.assertTrue(myAccountPage.getMyWishlists().isEnabled());
        myAccountPage.signOut();
    }

    @Test(dataProvider = "dataProviderNegative")
    public void enterAccountNegative(Account acc) {
        System.out.println(
                "Login" + acc.getEmail().getEmail() +
                        "\nPassword" + acc.getPassword().getPassword());

        signInPage.signInWith(
                acc.getEmail().getEmail(),
                acc.getPassword().getPassword());
        Assert.assertTrue(signInPage.getSubmit().isEnabled());
    }

    @Test(dataProvider = "dataProviderTest")
    public void enterAccountTest(Account acc, Account acc2) {
        System.out.println(
                "Login" + acc.getEmail().getEmail() +
                        "\nPassword" + acc.getPassword().getPassword());
        System.out.println(
                "Login" + acc2.getEmail().getEmail() +
                        "\nPassword" + acc2.getPassword().getPassword());

        signInPage.signInWith(
                acc.getEmail().getEmail(),
                acc.getPassword().getPassword());
        Assert.assertTrue(signInPage.getSignOutButton().isEnabled());
    }

    @DataProvider
    private Object[][] dataProvider() {
        return dp.getData(DataIs.USER_BEFORE_EDITING);
    }

    @DataProvider
    private Object[][] dataProviderNegative() {
        return dp.getData(DataIs.USER_AFTER_EDITING);
    }

    @DataProvider
    private Object[][] dataProviderTest() {
        Account acc1 = (Account) dp.getData(DataIs.USER_BEFORE_EDITING)[0][0];
        Account acc2 = (Account) dp.getData(DataIs.USER_AFTER_EDITING)[0][0];
        return new Object[][]{{acc1,acc2}};
    }
}