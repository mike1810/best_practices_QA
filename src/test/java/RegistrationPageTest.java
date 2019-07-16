import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationPageTest extends BaseTest{

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
        LOGGER  = LogManager.getLogger(RegistrationPageTest.class);
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
        if (user.isGenderMale()) {
            registrationPage.clickGenderMale();
        } else {
            registrationPage.clickGenderFemale();
        }
        registrationPage.sendFirstName(user.getFirstName());
        registrationPage.sendLastName(user.getLastName());
        registrationPage.sendPassword(user.getPassword());
        registrationPage.selectDate(user.getDate());
        registrationPage.selectMonth(user.getMonth());
        registrationPage.selectDate(user.getDate());
        if (user.isNewsLetter()) {
            registrationPage.clickNewsletter();
        }
        if(user.isSpecialOffers()){
            registrationPage.clickSpecialOffers();
        }
        registrationPage.selectYear(user.getYear());
        registrationPage.sendCompany(user.getCompany());
        registrationPage.sendAddress(user.getAddress1());
        registrationPage.sendAddressLine2(user.getAddress2());
        registrationPage.sendCity(user.getCity());
        registrationPage.selectState(user.getState());
        registrationPage.sendPostcode(user.getPostcode());
        registrationPage.selectCountry(user.getCountry());
        registrationPage.sendAdditionalInformation(user.getAdditionalInformation());
        registrationPage.sendHomePhone(user.getHomePhone());
        registrationPage.sendMobilePhone(user.getMobilePhone());
        registrationPage.sendAnAddressAlias(user.getAddressAlias());
        registrationPage.clickRegister();
        registrationPage.clickSignOutButton();
    }

    @Test(dataProvider = "DPForAccountCreating", dataProviderClass = User.class)
    public void registerNewAccountOnlyRequired(User user) {

        signInPage.sendNewEmail(user.getEmail());
        signInPage.clickButtonToCreateAccount();

        registrationPage = new RegistrationPage(driver);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        registrationPage.waitPageIsLoaded();
        registrationPage.checkPageTitle();
        registrationPage.sendFirstName(user.getFirstName());
        registrationPage.sendLastName(user.getLastName());
        registrationPage.sendPassword(user.getPassword());
        registrationPage.selectDate(user.getDate());
        registrationPage.selectMonth(user.getMonth());
        registrationPage.selectDate(user.getDate());
        registrationPage.selectYear(user.getYear());
        registrationPage.sendAddress(user.getAddress1());
        registrationPage.sendCity(user.getCity());
        registrationPage.selectState(user.getState());
        registrationPage.sendPostcode(user.getPostcode());
        registrationPage.selectCountry(user.getCountry());
        registrationPage.sendMobilePhone(user.getMobilePhone());
        registrationPage.sendAnAddressAlias(user.getAddressAlias());
        registrationPage.clickRegister();
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

        registrationPage.clickRegister();
        try {
            Assert.assertNotEquals(driver.getTitle(), "My account - My Store");
            LOGGER.info("Account not created");
        }catch(Error error){
            LOGGER.error("Account created");
        }
    }
}

