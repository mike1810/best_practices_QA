import models.DataIs;
import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

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
        dataPool = new DataPool("dataFile", testContext, User.class, DataIs.USER_BEFORE_EDITING);
        dataPool.addNewDataPool("dataToReplaceFile", testContext, User.class, DataIs.USER_AFTER_EDITING);
    }

    @Test
    public void passwordTest() {
        /*Object[][] obj = dataPool.getData();
        newPassword = ((User)obj[0][0]).getPersonalInfo().getPassword();
        oldPassword = ((User)obj[0][1]).getPersonalInfo().getPassword();
        System.out.println(newPassword);
        System.out.println(oldPassword);*/
    }

    @Test(dataProvider = "dataProvider")
    public void editPersonalInfo(User userBefore, User userAfter) {
        signInPage.sendNewEmail(userBefore.getPersonalInfo().getEmail());
        signInPage.clickButtonToCreateAccount();
        registrationPage.createNewAccountWithAllFields(userBefore);
        registrationPage.registerAccount();

        myAccountPage.openMyPersonalInformation();
        verifyPersonalInformation(userBefore);

        myPersonalInformationPage.openMyAccount();
        myAccountPage.openMyPersonalInformation();
        myPersonalInformationPage.updatePersonalInformation(userBefore, userAfter);
        myPersonalInformationPage.saveUpdates();

        myPersonalInformationPage.openMyAccount();
        myAccountPage.openMyPersonalInformation();
        verifyPersonalInformation(userAfter);
    }

    private void verifyPersonalInformation(User user){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                myPersonalInformationPage.getMaleAttribute(),
                user.getPersonalInfo().isGenderMale());
        softAssert.assertEquals(
                myPersonalInformationPage.getFirstnameAttribute(),
                user.getPersonalInfo().getCustomerFirstName());
        softAssert.assertEquals(
                myPersonalInformationPage.getLastnameAttribute(),
                user.getPersonalInfo().getCustomerLastName());
        softAssert.assertEquals(
                myPersonalInformationPage.getDaysAttribute(),
                //myPersonalInformationPage.getDropBoxValueAttribute(myPersonalInformationPage.getDays()),
                //myPersonalInformationPage.getDays().getAttribute("value"),
                user.getPersonalInfo().getDay());
        softAssert.assertEquals(
                myPersonalInformationPage.getMonthsAttribute(),
                user.getPersonalInfo().getMonth());
        softAssert.assertEquals(
                myPersonalInformationPage.getYearsAttribute(),
                user.getPersonalInfo().getYear());
        softAssert.assertEquals(
                myPersonalInformationPage.getNewsletterAttribute(),
                user.getPersonalInfo().isNewsLetter());
        softAssert.assertEquals(
                myPersonalInformationPage.getSpecialOffersAttribute(),
                user.getPersonalInfo().isSpecialOffers());
        softAssert.assertAll();
    }

    @DataProvider
    private Object[][] dataProvider(){
        User userBefore = (User) dataPool.getData(DataIs.USER_BEFORE_EDITING)[0][0];
        User userAfter = (User) dataPool.getData(DataIs.USER_AFTER_EDITING)[0][0];
        return new Object[][]{{userBefore,userAfter}};
    }
}
