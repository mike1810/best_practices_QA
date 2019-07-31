import models.DataIs;
import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;

public class VerifyPersonalInfoTest extends BaseTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private MyAccountPage myAccountPage;
    private MyPersonalInformationPage myPersonalInformationPage;

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
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
        myPersonalInformationPage = PageFactory.initElements(driver, MyPersonalInformationPage.class);
    }

    @Test(dataProvider = "dataProvider")
    public void VerifyPersonalInfo(User user) {
        signInPage.sendNewEmail(user.getPersonalInfo().getEmail());
        signInPage.openRegistrationPage();
        registrationPage.createNewAccount(user);
        myAccountPage.openMyPersonalInformation();
        verifyPersonalInformation(user);
    }

    private void verifyPersonalInformation(User user){
        Assert.assertEquals(
                myPersonalInformationPage.
                        getUserPersonalInfo().compareTo(user.getPersonalInfo()),
                0);
    }

    @DataProvider
    private Object[][] dataProvider(){
        return dataPool.getData(DataIs.USER_BEFORE_EDITING);
    }
}