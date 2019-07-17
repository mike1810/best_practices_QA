import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest{

    private RegistrationPage registrationPage;
    private SignInPage signInPage;

    @Test
    public void testLogger(){
        LOGGER.error( "Error Message Logged !!!", new NullPointerException(
                "NullError" ) );
        LOGGER.debug( "Debug Message Logged !!!" );
        LOGGER.info( "Info Message Logged !!!" );
    }

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        LOGGER  = LogManager.getLogger(RegistrationTest.class);
        PropertyConfigurator.configure( "C:\\Users\\mikhail.kaliberdin\\Documents\\GitHub\\automationQA\\src\\test\\resources\\log4j.properties");
    }

    @BeforeMethod
    public void beforeMethod() {

        driver.get(prop.getProperty("registrationPageURL"));
        signInPage = PageFactory.initElements(driver, SignInPage.class);

        signInPage.waitPageIsLoaded();
        signInPage.checkPageTitle();
    }

    @Test(dataProvider = "DPForAccountCreating", dataProviderClass = User.class)
    public void registerNewAccount(User user) {

        signInPage.sendNewEmail(user.getEmail());
        signInPage.clickButtonToCreateAccount();

        registrationPage = new RegistrationPage(driver);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);

        registrationPage.waitPageIsLoaded();
        registrationPage.checkPageTitle();
        registrationPage.createNewAccountWithAllFields(user);
        registrationPage.clickRegister();
        //registrationPage.clickSignOutButton();
    }

    @Test(dataProvider = "DPForAccountCreating", dataProviderClass = User.class)
    public void registerNewAccountOnlyRequired(User user) {

        signInPage.sendNewEmail(user.getEmail());
        signInPage.clickButtonToCreateAccount();

        registrationPage = new RegistrationPage(driver);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        registrationPage.waitPageIsLoaded();
        registrationPage.checkPageTitle();
        registrationPage.createNewAccountWithOnlyRequiredFields(user);
        registrationPage.registerAccount();
        registrationPage.clickSignOutButton();
    }

    @Test(dataProvider = "DPForAccountCreating", dataProviderClass = User.class)
    public void registerNewAccountNegative(User user) {

        signInPage.sendNewEmail(user.getEmail());
        signInPage.clickButtonToCreateAccount();

        registrationPage = new RegistrationPage(driver);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        registrationPage.waitPageIsLoaded();
        registrationPage.checkPageTitle();

        registrationPage.registerAccount();
        Assert.assertTrue(registrationPage.accountWasNotRegistered());

    }
}

