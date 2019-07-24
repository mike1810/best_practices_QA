import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.HashMap;

public class VerifyPersonalInfoTest extends BaseTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private MyAccountPage myAccountPage;
    private MyPersonalInformationPage myPersonalInformationPage;

    @BeforeSuite
    protected void beforeSuite( ITestContext testContext ) {
        dataPool = new DataPool("dataFile", testContext, User.class);
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
        signInPage.sendNewEmail(user.getEmail());
        signInPage.clickButtonToCreateAccount();
        registrationPage.createNewAccountWithAllFields(user);
        registrationPage.registerAccount();

        myAccountPage.openMyPersonalInformation();
        verifyPersonalInformation(user);
    }

    private void verifyPersonalInformation(User user){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                myPersonalInformationPage.getMaleAttribute(),
                user.isGenderMale());
        softAssert.assertEquals(
                myPersonalInformationPage.getFirstnameAttribute(),
                user.getFirstName());
        softAssert.assertEquals(
                myPersonalInformationPage.getLastnameAttribute(),
                user.getLastName());
        softAssert.assertEquals(
                myPersonalInformationPage.getDaysAttribute(),
                user.getDate());
        softAssert.assertEquals(
                myPersonalInformationPage.getMonthsAttribute(),
                user.getMonth());
        softAssert.assertEquals(
                myPersonalInformationPage.getYearsAttribute(),
                user.getYear());
        softAssert.assertEquals(
                myPersonalInformationPage.getNewsletterAttribute(),
                user.isNewsLetter());
        softAssert.assertEquals(
                myPersonalInformationPage.getSpecialOffersAttribute(),
                user.isSpecialOffers());
        softAssert.assertAll();
    }

    @DataProvider
    private Object[][] dataProvider(){
        return dataPool.getData();
    }
}
