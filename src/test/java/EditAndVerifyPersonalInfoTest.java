import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.HashMap;

public class EditAndVerifyPersonalInfoTest extends BaseTest {

    private SignInPage signInPage;
    private MyAccountPage myAccountPage;
    private MyAddressesPage myAddressesPage;
    private MyAddressesUpdatePage myAddressesUpdatePage;
    private MyPersonalInformationPage myPersonalInformationPage;
    private String newPassword, oldPassword;

    @BeforeSuite
    protected void beforeSuite( ITestContext testContext ) {
        userPool = new UserPool();
        HashMap<String,String> parameters = new HashMap<>( testContext.getCurrentXmlTest().getAllParameters());
        userPool.processDataFile( parameters.get( "dataFile" ) );
        userPoolNew = new UserPool();
        HashMap<String,String> parameters2 = new HashMap<>( testContext.getCurrentXmlTest().getAllParameters());
        userPoolNew.processDataFile( parameters2.get( "dataToReplaceFile" ) );
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
        driver.get(prop.getProperty("homePageURL")+prop.getProperty("signInPageURL"));

    }

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
        return userPool.getData();
    }

    @DataProvider
    private Object[][] dataProviderWithNewUser(){
        return userPoolNew.getData();
    }
}
