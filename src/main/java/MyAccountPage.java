import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends Page{

    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Order history and details')]")
    private WebElement orderHistoryAndDetails;

    @FindBy(xpath = "//span[contains(text(),'My credit slips')]")
    private WebElement myCreditSlips;

    @FindBy(xpath = "//span[contains(text(),'My addresses')]")
    private WebElement myAdresses;

    @FindBy(xpath = "//span[contains(text(),'My personal information')]")
    private WebElement myPersonalInformation;

    @FindBy(xpath = "//span[contains(text(),'My wishlists')]")
    private WebElement myWishlists;

    @FindBy(xpath = "//span[contains(text(),'Home')]")
    private WebElement home;

    void clickOrderHistoryAndDetails(){
        waitForWebElementToBeClickable(orderHistoryAndDetails);
        orderHistoryAndDetails.click();
    }

    void clickMyCreditSlips(){
        waitForWebElementToBeClickable(myCreditSlips);
        myCreditSlips.click();
    }

    void clickMyAdresses(){
        waitForWebElementToBeClickable(myAdresses);
        myAdresses.click();
    }

    void clickMyPersonalInformation(){
        waitForWebElementToBeClickable(myPersonalInformation);
        myPersonalInformation.click();
    }

    void clickMyWishlists(){
        waitForWebElementToBeClickable(myWishlists);
        myWishlists.click();
    }

    void clickHome(){
        waitForWebElementToBeClickable(home);
        home.click();
    }

    void clickAllButtons(){
        clickOrderHistoryAndDetails();
        clickMyAccountButton();
        clickMyAccountButton();
        clickMyAccountButton();
        clickMyCreditSlips();
        clickMyAccountButton();
        clickMyAdresses();
        clickMyAccountButton();
        clickMyPersonalInformation();
        clickMyAccountButton();
        clickMyWishlists();
        clickMyAccountButton();
        clickHome();
    }

    void checkPageTitle(){}
}
