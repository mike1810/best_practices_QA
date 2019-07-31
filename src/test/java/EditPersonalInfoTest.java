import models.DataIs;
import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import java.io.IOException;

public class EditPersonalInfoTest extends BaseTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private MyAccountPage myAccountPage;
    private MyPersonalInformationPage myPersonalInformationPage;

    @BeforeClass
    public void beforeClass( ITestContext testContext ) throws IOException {
        super.beforeClass();
        dataPool = new DataPool("dataFile", testContext, User.class, DataIs.USER_BEFORE_EDITING);
        dataPool.addNewDataPool("dataToReplaceFile", testContext, User.class, DataIs.USER_AFTER_EDITING);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(prop.getProperty("homePageURL")+prop.getProperty("signInPageURL"));
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
        myPersonalInformationPage = PageFactory.initElements(driver, MyPersonalInformationPage.class);
    }

    @Test
    public void passwordTest() {
    }

    @Test(dataProvider = "dataProvider")
    public void editPersonalInfo(User userBefore, User userAfter) {
        signInPage.sendNewEmail(userBefore.getPersonalInfo().getEmail());
        signInPage.openRegistrationPage();
        registrationPage.createNewAccount(userBefore);
        registrationPage.openMyAccount();

        myPersonalInformationPage.openMyAccount();
        myAccountPage.openMyPersonalInformation();
        verifyPersonalInformation(userBefore);

        myPersonalInformationPage.openMyAccount();
        myAccountPage.openMyPersonalInformation();
        myPersonalInformationPage.updatePersonalInformation(userBefore, userAfter);

        myPersonalInformationPage.openMyAccount();
        myAccountPage.openMyPersonalInformation();
        verifyPersonalInformation(userAfter);
    }

    private void verifyPersonalInformation(User user){
        Assert.assertEquals(
                myPersonalInformationPage.
                        getUserPersonalInfo().compareTo(user.getPersonalInfo()),
                0);
    }

    @DataProvider
    private Object[][] dataProvider(){
        User userBefore = (User) dataPool.getData(DataIs.USER_BEFORE_EDITING)[0][0];
        User userAfter = (User) dataPool.getData(DataIs.USER_AFTER_EDITING)[0][0];
        return new Object[][]{{userBefore,userAfter}};
    }
}
