import models.DataIs;
import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class VerifyAddressesTest extends BaseTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private MyAccountPage myAccountPage;
    private MyAddressesPage myAddressesPage;
    private MyAddressesUpdatePage myAddressesUpdatePage;

    @BeforeSuite
    protected void beforeSuite( ITestContext testContext ) {
        dataPool = new DataPool("dataFile", testContext, User.class, DataIs.USER_BEFORE_EDITING);
    }

    @BeforeClass
    public void beforeClass() throws IOException {
        super.beforeClass();
        driver.get(prop.getProperty("homePageURL")+prop.getProperty("signInPageURL"));
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
        myAddressesPage = PageFactory.initElements(driver, MyAddressesPage.class);
        myAddressesUpdatePage = PageFactory.initElements(driver, MyAddressesUpdatePage.class);
    }

    @Test(dataProvider = "dataProvider")
    public void verifyAddressTest(User user) {
        signInPage.sendNewEmail(user.getPersonalInfo().getEmail());
        signInPage.openRegistrationPage();
        registrationPage.createNewAccount(user);
        Assert.assertTrue(registrationPage.accountWasRegistered());

        myAccountPage.openMyAddresses();
        myAddressesPage.verifyAddresses(user);
        Assert.assertEquals(myAddressesPage.verifyAddresses(user),0);
    }

    /*private void verifyAddress(User user){
        Assert.assertEquals(
                myAddressesUpdatePage.
                        getUserAddress().compareTo(user.getMainAddress()),
                0);
    }*/

    @DataProvider
    private Object[][] dataProvider(){
        return dataPool.getData(DataIs.USER_BEFORE_EDITING);
    }
}
