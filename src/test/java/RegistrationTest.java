import models.Address;
import models.DataIs;
import models.User;
import org.apache.log4j.PropertyConfigurator;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import readResource.ReadResourceFile;

import java.io.IOException;

public class RegistrationTest extends BaseTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    //private Logger LOGGER;

    @BeforeSuite
    protected void beforeSuite(ITestContext testContext) {
        dataPool = new DataPool("dataFile", testContext, User.class, DataIs.USER_BEFORE_EDITING);
    }

    @Override
    @BeforeClass
    public void beforeClass() throws IOException {
        super.beforeClass();
        //LOGGER = LogManager.getLogger(RegistrationTest.class);
        PropertyConfigurator.configure(ReadResourceFile.read("log4jProperties.txt"));
        driver.get(prop.getProperty("homePageURL") + prop.getProperty("registrationPageURL"));
    }

    @BeforeMethod
    public void beforeMethod() {
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        driver.get(prop.getProperty("homePageURL") + prop.getProperty("registrationPageURL"));
    }

    @Test(dataProvider = "dataProvider")
    public void registerNewAccount(User user) {

        signInPage.sendNewEmail(user.getPersonalInfo().getEmail());
        signInPage.clickButtonToCreateAccount();

        registrationPage.createNewAccountWithAllFields(user);
        //registrationPage.registerAccount();

        Assert.assertTrue(registrationPage.accountWasRegistered());
        //registrationPage.signOut();
    }

    @Test(dataProvider = "dataProvider")
    public void registerNewAccountNegative(User user) {

        signInPage.sendNewEmail(user.getPersonalInfo().getEmail());
        signInPage.clickButtonToCreateAccount();

        registrationPage.registerAccount();
        Assert.assertTrue(registrationPage.accountWasNotRegistered());
    }

    @Test(dataProvider = "dataProvider")
    public void Collection(User user) {

        signInPage.sendNewEmail(user.getPersonalInfo().getEmail());
        signInPage.clickButtonToCreateAccount();

        registrationPage.createNewAccountWithAllFields(user);
        registrationPage.registerAccount();

        Assert.assertTrue(registrationPage.accountWasRegistered());

    }

    @Test
    public void testLogger() {
        //LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));
        //LOGGER.debug("Debug Message Logged !!!");
        //LOGGER.info("Info Message Logged !!!");
    }

    @DataProvider
    private Object[][] dataProvider() {
        return dataPool.getData(DataIs.USER_BEFORE_EDITING);
    }
}

