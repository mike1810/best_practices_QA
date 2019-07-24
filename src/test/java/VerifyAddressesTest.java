import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.HashMap;

public class VerifyAddressesTest extends BaseTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private MyAccountPage myAccountPage;
    private MyAddressesPage myAddressesPage;
    private MyAddressesUpdatePage myAddressesUpdatePage;

    @BeforeSuite
    protected void beforeSuite( ITestContext testContext ) {
        dataPool = new DataPool("dataFile", testContext, User.class);
        dataPool.fillNewDataPool("dataToReplaceFile", testContext, User.class);
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
    public void verifyAddressTest(User user1, User user2) {
        signInPage.sendNewEmail(user1.getEmail());
        signInPage.clickButtonToCreateAccount();
        registrationPage.createNewAccountWithAllFields(user1);
        registrationPage.registerAccount();
        Assert.assertTrue(registrationPage.accountWasRegistered());

        myAccountPage.openMyAddresses();
        myAddressesPage.openAddressUpdatePage();
        verifyAddress(user2);
    }

    private void verifyAddress(User user){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                myAddressesUpdatePage.getFirstnameAttribute(),
                user.getFirstName());
        softAssert.assertEquals(
                myAddressesUpdatePage.getLastnameAttribute(),
                user.getLastName());
        softAssert.assertEquals(
                myAddressesUpdatePage.getAddress1Attribute(),
                user.getAddress1());
        softAssert.assertEquals(
                myAddressesUpdatePage.getAddress2Attribute(),
                user.getAddress2());
        softAssert.assertEquals(
                myAddressesUpdatePage.getCompanyAttribute(),
                user.getCompany());
        softAssert.assertEquals(
                myAddressesUpdatePage.getCityAttribute(),
                user.getCity());
        softAssert.assertEquals(
                myAddressesUpdatePage.getStateAttribute(),
                user.getState());
        softAssert.assertEquals(
                myAddressesUpdatePage.getPostcodeAttribute(),
                user.getPostcode());
        softAssert.assertEquals(
                myAddressesUpdatePage.getCountryAttribute(),
                user.getCountry());
        softAssert.assertEquals(
                myAddressesUpdatePage.getHomePhoneAttribute(),
                user.getHomePhone());
        softAssert.assertEquals(
                myAddressesUpdatePage.getMobilePhoneAttribute(),
                user.getMobilePhone());
        softAssert.assertEquals(
                myAddressesUpdatePage.getAdditionalInformationAttribute(),
                user.getAdditionalInformation());
        softAssert.assertEquals(
                myAddressesUpdatePage.getAliasAttribute(),
                user.getAlias());
        softAssert.assertAll();
    }

    @DataProvider
    private Object[][] dataProvider(){
        return dataPool.getData();
    }
}
