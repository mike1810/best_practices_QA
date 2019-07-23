import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.HashMap;

public class VerifyAddressesTest extends BaseTest {

    private SignInPage signInPage;
    private MyAccountPage myAccountPage;
    private MyAddressesPage myAddressesPage;
    private MyAddressesUpdatePage myAddressesUpdatePage;
    private MyPersonalInformationPage myPersonalInformationPage;

    @BeforeSuite
    protected void beforeSuite( ITestContext testContext ) {
        dataPool = new dataPool();
        HashMap<String,String> parameters = new HashMap<>( testContext.getCurrentXmlTest().getAllParameters());
        dataPool.processDataFile( parameters.get( "dataFile" ), User.class );
        dataPoolNew = new dataPool();
        HashMap<String,String> parameters2 = new HashMap<>( testContext.getCurrentXmlTest().getAllParameters());
        dataPoolNew.processDataFile( parameters2.get( "dataToReplaceFile" ), User.class );
    }

    @BeforeClass
    @Parameters({"oldPassword", "email"})
    public void beforeClass(String oldPassword, String email) throws IOException {
        super.beforeClass();
        driver.get(prop.getProperty("homePageURL")+prop.getProperty("signInPageURL"));
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
        myAddressesPage = PageFactory.initElements(driver, MyAddressesPage.class);
        myAddressesUpdatePage = PageFactory.initElements(driver, MyAddressesUpdatePage.class);
        myPersonalInformationPage = PageFactory.initElements(driver, MyPersonalInformationPage.class);
        //signInPage.signInWith("tester@tester.tester", "12345");
        signInPage.signInWith(email, oldPassword);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(prop.getProperty("homePageURL")+prop.getProperty("signInPageURL"));
    }

    @Test(dataProvider = "dataProvider")
    public void testVerifyOldAddress(User oldUser){
        myAccountPage.openMyAddresses();
        myAddressesPage.openAddressUpdatePage();
        verifyAddress(oldUser);
    }

    @Test(dataProvider = "dataProviderWithNewUser")
    public void testEditOldAddress(User newUser){
        myAccountPage.openMyAddresses();
        myAddressesPage.openAddressUpdatePage();
        myAddressesUpdatePage.updateAddress(newUser);
        myAddressesUpdatePage.saveUpdates();
    }

    @Test(dataProvider = "dataProviderWithNewUser")
    public void testVerifyNewAddress(User newUser){
        myAccountPage.openMyAddresses();
        myAddressesPage.openAddressUpdatePage();
        verifyAddress(newUser);
    }

    @Test(dataProvider = "dataProvider")
    public void testOldAddressReturn(User oldUser){
        myAccountPage.openMyAddresses();
        myAddressesPage.openAddressUpdatePage();
        myAddressesUpdatePage.updateAddress(oldUser);
        myAddressesUpdatePage.saveUpdates();
    }

    /*@Test(dataProvider = "dataProvider")
    @Parameters({ "oldPassword", "newPassword" })
    public void testVerifyInfo(String oldPassword, String newPassword, models.User oldUser ){
        myAccountPage.openMyPersonalInformation();
        System.out.println(myPersonalInformationPage.getMaleAttribute());
        System.out.println(myPersonalInformationPage.getFirstnameAttribute());
        System.out.println(myPersonalInformationPage.getLastnameAttribute());
        System.out.println(myPersonalInformationPage.getEmailAttribute());
        System.out.println(myPersonalInformationPage.getDaysAttribute());
        System.out.println(myPersonalInformationPage.getMonthsAttribute());
        System.out.println(myPersonalInformationPage.getYearsAttribute());
        System.out.println(myPersonalInformationPage.getOldPasswordAttribute());
        System.out.println(myPersonalInformationPage.getNewsletterAttribute());
        System.out.println(myPersonalInformationPage.getSpecialOffersAttribute());
        System.out.println(myPersonalInformationPage.getPasswordAttribute());
        System.out.println(myPersonalInformationPage.getConfirmationPasswordAttribute());
    }*/

    private void verifyAddressUpdated(User user){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getFirstnameAttribute(),
                user.getFirstName());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getLastnameAttribute(),
                user.getLastName());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getAddress1Attribute(),
                user.getAddress1());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getAddress2Attribute(),
                user.getAddress2());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getCompanyAttribute(),
                user.getCompany());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getCityAttribute(),
                user.getCity());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getStateAttribute(),
                user.getState());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getPostcodeAttribute(),
                user.getPostcode());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getCountryAttribute(),
                user.getCountry());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getHomePhoneAttribute(),
                user.getHomePhone());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getMobilePhoneAttribute(),
                user.getMobilePhone());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getAdditionalInformationAttribute(),
                user.getAdditionalInformation());
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getAliasAttribute(),
                user.getAlias());
        softAssert.assertAll();
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

    @DataProvider
    private Object[][] dataProviderWithNewUser(){
        return dataPoolNew.getData();
    }
}
