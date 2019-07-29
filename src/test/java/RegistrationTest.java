import models.Address;
import models.DataIs;
import models.User;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import readResource.ReadResourceFile;

import java.io.IOException;

public class RegistrationTest extends BaseTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private MyAccountPage myAccountPage;
    private MyAddressesPage myAddressesPage;
    private MyAddressesUpdatePage myAddressesUpdatePage;
    private MyAddressesAddPage myAddressesAddPage;

    @BeforeSuite
    protected void beforeSuite(ITestContext testContext) {
        dataPool = new DataPool("dataFile", testContext, User.class, DataIs.USER_BEFORE_EDITING);
    }

    @Override
    @BeforeClass
    public void beforeClass() throws IOException {
        super.beforeClass();
        LOGGER = LogManager.getLogger(RegistrationTest.class);
        PropertyConfigurator.configure(ReadResourceFile.read("log4jProperties.txt"));
        driver.get(prop.getProperty("homePageURL") + prop.getProperty("registrationPageURL"));
    }

    @BeforeMethod
    public void beforeMethod() {
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
        myAddressesPage = PageFactory.initElements(driver, MyAddressesPage.class);
        myAddressesUpdatePage = PageFactory.initElements(driver, MyAddressesUpdatePage.class);
        myAddressesAddPage = PageFactory.initElements(driver, MyAddressesAddPage.class);
        driver.get(prop.getProperty("homePageURL") + prop.getProperty("registrationPageURL"));
    }

    @Test(dataProvider = "dataProvider")
    public void registerNewAccount(User user) {

        signInPage.sendNewEmail(user.getPersonalInfo().getEmail());
        signInPage.clickButtonToCreateAccount();

        registrationPage.createNewAccountWithAllFields(user);
        registrationPage.registerAccount();

        Assert.assertTrue(registrationPage.accountWasRegistered());
        registrationPage.signOut();
    }

    @Test(dataProvider = "dataProvider")
    public void registerNewAccountOnlyRequired(User user) {

        signInPage.sendNewEmail(user.getPersonalInfo().getEmail());
        signInPage.clickButtonToCreateAccount();

        registrationPage.createNewAccountWithOnlyRequiredFields(user);
        registrationPage.registerAccount();

        Assert.assertTrue(registrationPage.accountWasRegistered());
        registrationPage.signOut();
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


        System.out.println("--------------------------------------");
        for (Address address : user.getAnyAddresses()) {
            System.out.println(address);
        }
        user.getAnyAddresses().add(user.getMainAddress());
        System.out.println("--------------------------------------");
        for (Address address : user.getAnyAddresses()) {
            System.out.println(address);
        }
        System.out.println("--------------------------------------");


        if(user.getAnyAddresses().size() > 0) {
            for (Address address : user.getAnyAddresses()) {
                myAccountPage.openMyAddresses();
                myAddressesPage.addANewAddress();
                myAddressesAddPage.addNewAddress(address);
                myAddressesUpdatePage.saveUpdates();
            }
        }
        /*
        if(user.getAnyAddresses().size() > 0) {

            for (Address address : user.getAnyAddresses()) {
                for(WebElement webElement: webArr){
                    if(!webElement.equals(address.getAlias()))

                        myAccountPage.openMyAddresses();
                    myAddressesPage.addANewAddress();
                    myAddressesAddPage.addNewAddress(address);
                    myAddressesUpdatePage.saveUpdates();
                }
            }
        }*/
    }

    @Test
    public void testLogger() {
        LOGGER.error("Error Message Logged !!!", new NullPointerException(
                "NullError"));
        LOGGER.debug("Debug Message Logged !!!");
        LOGGER.info("Info Message Logged !!!");
    }

    @DataProvider
    private Object[][] dataProvider() {
        return dataPool.getData(DataIs.USER_BEFORE_EDITING);
    }
}

