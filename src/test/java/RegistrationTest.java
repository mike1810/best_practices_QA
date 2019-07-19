import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest{

    private RegistrationPage registrationPage;
    private SignInPage signInPage;
    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        LOGGER  = LogManager.getLogger(RegistrationTest.class);
        //PropertyConfigurator.configure( "C:\\Users\\kalib\\Documents\\GitHub\\main\\src\\test\\resources\\log4j.properties");
        PropertyConfigurator.configure( "C:\\Users\\mikhail.kaliberdin\\Documents\\GitHub\\automationQA\\src\\test\\resources\\log4j.properties");

        driver.get(prop.getProperty("registrationPageURL"));
    }

    @BeforeMethod
    public void beforeMethod() {
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        driver.get(prop.getProperty("registrationPageURL"));
    }

    @Test(dataProvider = "dataProvider")
    public void registerNewAccount(User user) {

        signInPage.sendNewEmail(user.getEmail());
        signInPage.clickButtonToCreateAccount();

        registrationPage.createNewAccountWithAllFields(user);
        registrationPage.registerAccount();

        Assert.assertTrue(registrationPage.accountWasRegistered());
        registrationPage.clickSignOutButton();
    }

    @Test(dataProvider = "dataProvider")
    public void registerNewAccountOnlyRequired(User user) {

        signInPage.sendNewEmail(user.getEmail());
        signInPage.clickButtonToCreateAccount();

        registrationPage.createNewAccountWithOnlyRequiredFields(user);
        registrationPage.registerAccount();

        Assert.assertTrue(registrationPage.accountWasRegistered());
        registrationPage.clickSignOutButton();
    }

    @Test(dataProvider = "dataProvider")
    public void registerNewAccountNegative(User user) {

        signInPage.sendNewEmail(user.getEmail());
        signInPage.clickButtonToCreateAccount();

        registrationPage.registerAccount();
        Assert.assertTrue(registrationPage.accountWasNotRegistered());
    }

    @Test
    public void testLogger(){
        LOGGER.error( "Error Message Logged !!!", new NullPointerException(
                "NullError" ) );
        LOGGER.debug( "Debug Message Logged !!!" );
        LOGGER.info( "Info Message Logged !!!" );
    }

    @DataProvider
    private Object[][] dataProvider(){
        return dataPool.getData();
    }
}

