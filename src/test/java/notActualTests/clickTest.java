/*
package notActualTests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class clickTest extends BaseTest {
    private MyAccountPage myAccountPage;

    private RegistrationPage registrationPage;
    private SignInPage signInPage;


    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();

    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(prop.getProperty("registrationPageURL"));
        signInPage = PageFactory.initElements(driver, SignInPage.class);
    }

    @Test(dataProvider = "DPForAccountCreating", dataProviderClass = User.class)
    public void clickAllButtonsOfMyAccountPage(User user) {

        signInPage.waitPageIsLoaded();
        signInPage.checkPageTitle();


        signInPage.sendNewEmail(user.getEmail());
        signInPage.clickButtonToCreateAccount();

        registrationPage = new RegistrationPage(driver);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        registrationPage.waitPageIsLoaded();
        registrationPage.checkPageTitle();
        registrationPage.createNewAccountWithOnlyRequiredFields(user);
        registrationPage.registerAccount();

        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
        myAccountPage.clickAllButtons();

}

    @DataProvider
    private Object[][] dataProvider(){
        return dataPool.getData();
    }

    @Test( dataProvider = "dataProvider" )
    public void testGetAccountFromDataFile( User user ) {
        System.out.println( user );
    }
}*/