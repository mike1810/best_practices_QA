import models.DataIs;
import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class VerifyAddressesTest extends BaseTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private MyAccountPage myAccountPage;
    private MyAddressesPage myAddressesPage;
    private MyAddressesUpdatePage myAddressesUpdatePage;

    @BeforeSuite
    protected void beforeSuite( ITestContext testContext ) {
        dataPool = new DataPool("dataFile", testContext, User.class, DataIs.USER_BEFORE_EDITING);
    }

    @BeforeClass
    public void beforeClass() throws IOException {
        super.beforeClass();
        driver.get(prop.getProperty("homePageURL")+prop.getProperty("signInPageURL"));
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
        myAddressesPage = PageFactory.initElements(driver, MyAddressesPage.class);
        myAddressesUpdatePage = PageFactory.initElements(driver, MyAddressesUpdatePage.class);
    }

    @Test(dataProvider = "dataProvider")
    public void verifyAddressTest(User user) {
        signInPage.sendNewEmail(user.getPersonalInfo().getEmail());
        signInPage.clickButtonToCreateAccount();
        registrationPage.createNewAccountWithAllFields(user);
        registrationPage.registerAccount();
        Assert.assertTrue(registrationPage.accountWasRegistered());

        myAccountPage.openMyAddresses();
        myAddressesPage.openAddressUpdatePage();
        verifyAddress(user);
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
                myAddressesUpdatePage.getCountryAttribute(),
                user.getAddress().getCountry());
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
        return dataPool.getData(DataIs.USER_BEFORE_EDITING);
    }
}
