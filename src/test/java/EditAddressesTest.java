import models.DataIs;
import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;

public class EditAddressesTest extends BaseTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private MyAccountPage myAccountPage;
    private MyAddressesPage myAddressesPage;
    private MyAddressesUpdatePage myAddressesUpdatePage;

    @BeforeClass
    public void beforeClass( ITestContext testContext ) throws IOException {
        super.beforeClass();
        driver.get(prop.getProperty("homePageURL")+prop.getProperty("signInPageURL"));
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
        myAddressesPage = PageFactory.initElements(driver, MyAddressesPage.class);
        myAddressesUpdatePage = PageFactory.initElements(driver, MyAddressesUpdatePage.class);
        dataPool = new DataPool("dataFile", testContext, User.class, DataIs.USER_BEFORE_EDITING);
        dataPool.addNewDataPool("dataToReplaceFile", testContext, User.class, DataIs.USER_AFTER_EDITING);
    }

    @Test(dataProvider = "dataProvider")
    public void editAccountAddress(User userBefore, User userAfter) {

        signInPage.sendNewEmail(userBefore.getPersonalInfo().getEmail());
        signInPage.openRegistrationPage();
        registrationPage.createNewAccount(userBefore);

        Assert.assertTrue(registrationPage.accountWasRegistered());

        myAccountPage.openMyAddresses();
        myAddressesPage.updateAddress(userAfter);

        Assert.assertEquals(myAddressesPage.verifyAddresses(userAfter),0);
    }

    @DataProvider
    private Object[][] dataProvider(){
        dataPool.getData(DataIs.USER_BEFORE_EDITING);
        User userBefore = (User) dataPool.getData(DataIs.USER_BEFORE_EDITING)[0][0];
        User userAfter = (User) dataPool.getData(DataIs.USER_AFTER_EDITING)[0][0];
        return new Object[][]{{userBefore,userAfter}};
    }
}
