import models.DataIs;
import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;

public class NegativePageElementsTest extends BaseTest {

    private SignInPage signInPage;
    private RegistrationPage registrationPage;

    @BeforeSuite
    protected void beforeSuite(ITestContext testContext) {
        dataPool = new DataPool(
                "normalUserForNegative",
                testContext,
                User.class,
                DataIs.NORMAL_USER_FOR_NEGATIVE);
    }

    @Override
    @BeforeClass
    public void beforeClass() throws IOException {
        super.beforeClass();
        driver.get(prop.getProperty("homePageURL") + prop.getProperty("registrationPageURL"));
    }

    @BeforeMethod
    public void beforeMethod() {
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        driver.get(prop.getProperty("homePageURL") + prop.getProperty("registrationPageURL"));
    }

    @Test(dataProvider = "dataProvider")
    public void checkPageElementsToBeVisibleAndDisplayed(User user) {
        signInPage.sendNewEmail(user.getPersonalInfo().getEmail());
        signInPage.openRegistrationPage();
        Assert.assertTrue(registrationPage.correctPageElementsAreShown());
    }

    @DataProvider
    private Object[][] dataProvider() {
        return dataPool.getData(DataIs.USER_BEFORE_EDITING);
    }
}

