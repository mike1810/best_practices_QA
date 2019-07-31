import models.DataIs;
import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;

public class InvalidErrorsTest extends BaseTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    //private Logger LOGGER;

    @BeforeSuite
    protected void beforeSuite(ITestContext testContext) {

        dataPool = new DataPool("normalUserForNegative", testContext, User.class, DataIs.NORMAL_USER_FOR_NEGATIVE);
        dataPool.addNewDataPool("invalidUser", testContext, User.class, DataIs.NOT_NORMAL_USER_FOR_NEGATIVE);
    }

    @Override
    @BeforeClass
    public void beforeClass() throws IOException {
        super.beforeClass();
        //LOGGER = LogManager.getLogger(RegistrationTest.class);
        driver.get(prop.getProperty("homePageURL") + prop.getProperty("registrationPageURL"));
    }

    @BeforeMethod
    public void beforeMethod() {
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        driver.get(prop.getProperty("homePageURL") + prop.getProperty("registrationPageURL"));
    }

    @Test(dataProvider = "dataProvider")
    public void checkInvalidErrors(User testUser, User negativeUser) {
        testUser.getPersonalInfo().setCustomerFirstName(negativeUser.getPersonalInfo().getCustomerFirstName());

        signInPage.sendNewEmail(testUser.getPersonalInfo().getEmail());
        signInPage.openRegistrationPage();

        registrationPage.createNewAccount(testUser);

        LOGGER.info("we wait error in: " + testUser.fieldsWillBeNegative(negativeUser));
        LOGGER.info("real error in: " + registrationPage.findInvalid());
        Assert.assertEquals(
                testUser.fieldsWillBeNegative(negativeUser).
                        compareTo(registrationPage.findInvalid()),
                0);
    }

    @Test
    public void testLogger() {
        //LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));
        //LOGGER.debug("Debug Message Logged !!!");
        //LOGGER.info("Info Message Logged !!!");
    }

    @DataProvider
    private Object[][] dataProvider(){
        User testUser = (User) dataPool.getData(DataIs.NORMAL_USER_FOR_NEGATIVE)[0][0];
        User negativeUser = (User) dataPool.getData(DataIs.NOT_NORMAL_USER_FOR_NEGATIVE)[0][0];
        return new Object[][]{{testUser,negativeUser}};
    }
}

