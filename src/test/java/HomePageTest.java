import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        driver.get(prop.getProperty("homePageURL"));
        homePage = new HomePage(driver);
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @Test
    public void goToSignInFromHomePage() {
        homePage.waitPageIsLoaded();
        homePage.checkPageTitle();
        homePage.clickSignInButton();
    }
}