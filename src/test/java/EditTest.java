import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class EditTest extends BaseTest{

    private SignInPage signInPage;
    private MyAccountPage myAccountPage;
    private MyAddressesPage myAddressesPage;
    private MyAddressesUpdatePage myAddressesUpdatePage;
    private MyPersonalInformationPage myPersonalInformationPage;
    private String newPassword, oldPassword;

    @Override
    @BeforeClass
    public void beforeClass(){
        super.beforeClass();
        driver.get(prop.getProperty("signInPageURL"));
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
        myAddressesPage = PageFactory.initElements(driver, MyAddressesPage.class);
        myAddressesUpdatePage = PageFactory.initElements(driver, MyAddressesUpdatePage.class);
        myPersonalInformationPage = PageFactory.initElements(driver, MyPersonalInformationPage.class);
        signInPage.signInWith("tester@tester.tester", "12345");

    }

    @Test(dataProvider = "dataProvider")
    public void getOldPasswordParameter(User user){
        this.oldPassword = user.getPassword();
    }

    @Test(dataProvider = "dataProviderWithNewUser")
    public void getNewPasswordParameter(User user){
        this.newPassword = user.getPassword();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(prop.getProperty("signInPageURL"));
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
////////////////////////////////////////////////////////////
    @Test(dataProvider = "dataProvider")//WithNewUser")
    public void verifyOldPersonalInformation(User oldUser){
        myAccountPage.openMyPersonalInformation();
        verifyPersonalInformation(oldUser);
    }

    @Test(dataProvider = "dataProviderWithNewUser")//WithNewUser")
    public void editOldPersonalInformation(User newUser){
        myAccountPage.openMyPersonalInformation();
        myPersonalInformationPage.updatePersonalInformation(newUser,oldPassword,newPassword);
        myPersonalInformationPage.saveUpdates();
    }

    @Test(dataProvider = "dataProviderWithNewUser")//WithNewUser")
    public void verifyNewPersonalInformation(User newUser){
        myAccountPage.openMyPersonalInformation();
        verifyPersonalInformation(newUser);
    }

    @Test(dataProvider = "dataProvider")//WithNewUser")
    public void editNewPersonalInformation(User oldUser){
        myAccountPage.openMyPersonalInformation();
        myPersonalInformationPage.updatePersonalInformation(oldUser,newPassword,oldPassword);
        myPersonalInformationPage.saveUpdates();
    }

    @Test(dataProvider = "dataProvider")//WithNewUser")
    public void testo(User oldUser){
        myAccountPage.openMyPersonalInformation();
        verifyPersonalInformation(oldUser);
        myPersonalInformationPage.updatePersonalInformation(oldUser,
                oldUser.getPassword(),
                oldUser.getPassword().replace("12345","54321"));
        myPersonalInformationPage.saveUpdates();
    }
////////////////////////////////////////////////////////////



    @Test(dataProvider = "dataProvider")
    @Parameters({ "oldPassword", "newPassword" })
    public void testVerifyInfo(String oldPassword, String newPassword, User oldUser ){
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
//        /*
//
//        */
//        verifyPersonalInfo(oldUser);
//        myPersonalInformationPage.updatePersonalInformation(oldUser, oldPassword, newPassword);
//        myPersonalInformationPage.saveUpdates();
//        myAccountPage.openMyPersonalInformation();
//        verifyPersonalInfo();
//        myPersonalInformationPage.updatePersonalInformation(oldUser, newPassword, oldPassword);
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

    private void verifyAddressUpdated(User user){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getFirstnameAttribute(),
                user.getFirstName()
        );
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getLastnameAttribute(),
                user.getLastName()
        );
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getAddress1Attribute(),
                user.getAddress1()
        );
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getAddress2Attribute(),
                user.getAddress2()
        );
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getCompanyAttribute(),
                user.getCompany()
        );
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getCityAttribute(),
                user.getCity()
        );
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getStateAttribute(),
                user.getState()
        );
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getPostcodeAttribute(),
                user.getPostcode()
        );
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getCountryAttribute(),
                user.getCountry()
        );
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getHomePhoneAttribute(),
                user.getHomePhone()
        );
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getMobilePhoneAttribute(),
                user.getMobilePhone()
        );
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getAdditionalInformationAttribute(),
                user.getAdditionalInformation()
        );
        softAssert.assertNotEquals(
                myAddressesUpdatePage.getAliasAttribute(),
                user.getAlias()
        );
        softAssert.assertAll();
    }

    private void verifyAddress(User user){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                myAddressesUpdatePage.getFirstnameAttribute(),
                user.getFirstName()
        );
        softAssert.assertEquals(
                myAddressesUpdatePage.getLastnameAttribute(),
                user.getLastName()
        );
        softAssert.assertEquals(
                myAddressesUpdatePage.getAddress1Attribute(),
                user.getAddress1()
        );
        softAssert.assertEquals(
                myAddressesUpdatePage.getAddress2Attribute(),
                user.getAddress2()
        );
        softAssert.assertEquals(
                myAddressesUpdatePage.getCompanyAttribute(),
                user.getCompany()
        );
        softAssert.assertEquals(
                myAddressesUpdatePage.getCityAttribute(),
                user.getCity()
        );
        softAssert.assertEquals(
                myAddressesUpdatePage.getStateAttribute(),
                user.getState()
        );
        softAssert.assertEquals(
                myAddressesUpdatePage.getPostcodeAttribute(),
                user.getPostcode()
        );
        softAssert.assertEquals(
                myAddressesUpdatePage.getCountryAttribute(),
                user.getCountry()
        );
        softAssert.assertEquals(
                myAddressesUpdatePage.getHomePhoneAttribute(),
                user.getHomePhone()
        );
        softAssert.assertEquals(
                myAddressesUpdatePage.getMobilePhoneAttribute(),
                user.getMobilePhone()
        );
        softAssert.assertEquals(
                myAddressesUpdatePage.getAdditionalInformationAttribute(),
                user.getAdditionalInformation()
        );
        softAssert.assertEquals(
                myAddressesUpdatePage.getAliasAttribute(),
                user.getAlias()
        );
        softAssert.assertAll();
    }

    @DataProvider
    private Object[][] dataProvider(){
        return userPool.getData();
    }

    @DataProvider
    private Object[][] dataProviderWithNewUser(){
        return userPoolNew.getData();
    }
}
