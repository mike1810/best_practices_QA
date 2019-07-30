import models.DataIs;
import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;

public class VerifyAddressesTest extends BaseTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private MyAccountPage myAccountPage;
    private MyAddressesPage myAddressesPage;
    private MyAddressesUpdatePage myAddressesUpdatePage;

    @BeforeSuite
    protected void beforeSuite( ITestContext testContext ) {
        //dataPool = new DataPool("dataFile", testContext, User.class, DataIs.USER_BEFORE_EDITING);
    }

    @BeforeClass
    public void beforeClass() throws IOException {
    }

    @Test(dataProvider = "dataProvider")
    public void verifyAddressTest(User user) {
        signInPage.sendNewEmail(user.getPersonalInfo().getEmail());
        signInPage.clickButtonToCreateAccount();
        registrationPage.createNewAccountWithAllFields(user);
        Assert.assertTrue(registrationPage.accountWasRegistered());

        myAccountPage.openMyAddresses();



        myAddressesPage.openAddressUpdatePage();
        //verifyAddress(user);
    }

    private void verifyAddress(User user){
        /*Assert.assertEquals(
                myAddressesUpdatePage.
                        getUserAddress().compareTo(user.getMainAddress()),
                0);*/
    }

    @DataProvider
    private Object[][] dataProvider(){
        return dataPool.getData(DataIs.USER_BEFORE_EDITING);
    }
}
