import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.HashMap;

public class EditPersonalInfoTest extends BaseTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private MyAccountPage myAccountPage;
    private MyAddressesPage myAddressesPage;
    private MyAddressesUpdatePage myAddressesUpdatePage;
    private MyPersonalInformationPage myPersonalInformationPage;
    private String newPassword, oldPassword;

    @BeforeClass
    public void beforeClass( ITestContext testContext ) throws IOException {
        super.beforeClass();
        driver.get(prop.getProperty("homePageURL")+prop.getProperty("signInPageURL"));
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
        myAddressesPage = PageFactory.initElements(driver, MyAddressesPage.class);
        myAddressesUpdatePage = PageFactory.initElements(driver, MyAddressesUpdatePage.class);
        myPersonalInformationPage = PageFactory.initElements(driver, MyPersonalInformationPage.class);
        dataPool = new DataPool("dataFile", testContext, User.class);
        dataPool.fillNewDataPool("dataToReplaceFile", testContext, User.class);
    }

    @Test
    public void passwordTest() {
        Object[][] obj = dataPool.getData();
        newPassword = ((User)obj[0][0]).getPassword();
        oldPassword = ((User)obj[0][1]).getPassword();
        System.out.println(newPassword);
        System.out.println(oldPassword);
    }

    @Test(dataProvider = "dataProvider")
    public void editPersonalInfo(User user1, User user2) {
        signInPage.sendNewEmail(user1.getEmail());
        signInPage.clickButtonToCreateAccount();
        registrationPage.createNewAccountWithAllFields(user1);
        registrationPage.registerAccount();

        myAccountPage.openMyPersonalInformation();
        verifyPersonalInformation(user1);

        myPersonalInformationPage.openMyAccount();
        myAccountPage.openMyPersonalInformation();
        myPersonalInformationPage.updatePersonalInformation(user2,user1.getPassword(),user2.getPassword());
        myPersonalInformationPage.saveUpdates();

        myPersonalInformationPage.openMyAccount();
        myAccountPage.openMyPersonalInformation();
        verifyPersonalInformation(user2);

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
                //myPersonalInformationPage.getDropBoxValueAttribute(myPersonalInformationPage.getDays()),
                //myPersonalInformationPage.getDays().getAttribute("value"),
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
