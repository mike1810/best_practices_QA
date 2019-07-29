import models.DataIs;
import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class EditAddressesTest extends BaseTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private MyAccountPage myAccountPage;
    private MyAddressesPage myAddressesPage;
    private MyAddressesUpdatePage myAddressesUpdatePage;

    @BeforeClass
    public void beforeClass( ITestContext testContext ) throws IOException {
        super.beforeClass();
        driver.get(prop.getProperty("homePageURL")+prop.getProperty("signInPageURL"));
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
        myAddressesPage = PageFactory.initElements(driver, MyAddressesPage.class);
        myAddressesUpdatePage = PageFactory.initElements(driver, MyAddressesUpdatePage.class);
        dataPool = new DataPool("dataFile", testContext, User.class, DataIs.USER_BEFORE_EDITING);
        dataPool.addNewDataPool("dataToReplaceFile", testContext, User.class, DataIs.USER_AFTER_EDITING);
    }

    @Test(dataProvider = "dataProvider")
    public void editAccountAddress(User userBefore, User userAfter) {
        signInPage.sendNewEmail(userBefore.getPersonalInfo().getEmail());
        signInPage.clickButtonToCreateAccount();
        registrationPage.createNewAccountWithAllFields(userBefore);
        registrationPage.registerAccount();
        Assert.assertTrue(registrationPage.accountWasRegistered());

        myAccountPage.openMyAddresses();
        myAddressesPage.openAddressUpdatePage();
        myAddressesUpdatePage.updateAddress(userAfter);
        myAddressesUpdatePage.saveUpdates();
        myAddressesPage.openAddressUpdatePage();
        verifyAddressUpdated(userBefore);
        verifyAddress(userAfter);
    }

    private void verifyAddressUpdated(User user){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getFirstnameAttribute(),
                user.getAddress().getFirstName());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getLastnameAttribute(),
                user.getAddress().getLastName());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getAddress1Attribute(),
                user.getAddress().getAddress1());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getAddress2Attribute(),
                user.getAddress().getAddress2());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getCompanyAttribute(),
                user.getAddress().getCompany());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getCityAttribute(),
                user.getAddress().getCity());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getStateAttribute(),
                user.getAddress().getState());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getPostcodeAttribute(),
                user.getAddress().getPostcode());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getHomePhoneAttribute(),
                user.getAddress().getHomePhone());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getMobilePhoneAttribute(),
                user.getAddress().getMobilePhone());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getAdditionalInformationAttribute(),
                user.getAddress().getAdditionalInformation());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getAliasAttribute(),
                user.getAddress().getAlias());
        softAssert.assertAll();
    }

    private void verifyAddress(User user){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                myAddressesUpdatePage.getFirstnameAttribute(),
                user.getAddress().getFirstName());
        softAssert.assertEquals(
                myAddressesUpdatePage.getLastnameAttribute(),
                user.getAddress().getLastName());
        softAssert.assertEquals(
                myAddressesUpdatePage.getAddress1Attribute(),
                user.getAddress().getAddress1());
        softAssert.assertEquals(
                myAddressesUpdatePage.getAddress2Attribute(),
                user.getAddress().getAddress2());
        softAssert.assertEquals(
                myAddressesUpdatePage.getCompanyAttribute(),
                user.getAddress().getCompany());
        softAssert.assertEquals(
                myAddressesUpdatePage.getCityAttribute(),
                user.getAddress().getCity());
        softAssert.assertEquals(
                myAddressesUpdatePage.getStateAttribute(),
                user.getAddress().getState());
        softAssert.assertEquals(
                myAddressesUpdatePage.getPostcodeAttribute(),
                user.getAddress().getPostcode());
        softAssert.assertEquals(
                myAddressesUpdatePage.getHomePhoneAttribute(),
                user.getAddress().getHomePhone());
        softAssert.assertEquals(
                myAddressesUpdatePage.getMobilePhoneAttribute(),
                user.getAddress().getMobilePhone());
        softAssert.assertEquals(
                myAddressesUpdatePage.getAdditionalInformationAttribute(),
                user.getAddress().getAdditionalInformation());
        softAssert.assertEquals(
                myAddressesUpdatePage.getAliasAttribute(),
                user.getAddress().getAlias());
        softAssert.assertAll();
    }

    @DataProvider
    private Object[][] dataProvider(){
        User userBefore = (User) dataPool.getData(DataIs.USER_BEFORE_EDITING)[0][0];
        User userAfter = (User) dataPool.getData(DataIs.USER_AFTER_EDITING)[0][0];
        return new Object[][]{{userBefore,userAfter}};
    }
}
