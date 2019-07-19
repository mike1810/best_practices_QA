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
    private WebElement myAddresses;

    @FindBy(xpath = "//span[contains(text(),'My personal information')]")
    private WebElement myPersonalInformation;

    @FindBy(xpath = "//span[contains(text(),'My wishlists')]")
    private WebElement myWishlists;

    @FindBy(xpath = "//span[contains(text(),'Home')]")
    private WebElement home;

    private void clickOrderHistoryAndDetails(){
        waitForWebElementToBeClickable(orderHistoryAndDetails);
        orderHistoryAndDetails.click();
    }

    void openOrderHistoryAndDetails()
    {clickOrderHistoryAndDetails();
    }

    private void clickMyCreditSlips(){
        waitForWebElementToBeClickable(myCreditSlips);
        myCreditSlips.click();
    }

    void openMyCreditSlips(){
        clickMyCreditSlips();
    }

    private void clickMyAddresses(){
        waitForWebElementToBeClickable(myAddresses);
        myAddresses.click();
    }

    void openMyAddresses(){
        clickMyAddresses();
    }

    private void clickMyPersonalInformation(){
        waitForWebElementToBeClickable(myPersonalInformation);
        myPersonalInformation.click();
    }

    void openMyPersonalInformation(){
        clickMyPersonalInformation();
    }

    private void clickMyWishlists(){
        waitForWebElementToBeClickable(myWishlists);
        myWishlists.click();
    }

    void openMyWishlists(){
        clickMyWishlists();
    }

    private void clickHome(){
        waitForWebElementToBeClickable(home);
        home.click();
    }

    void openHome(){
        clickHome();
    }

    void clickAllButtons(){
        openOrderHistoryAndDetails();
        openMyAccount();
        openMyCreditSlips();
        openMyAccount();
        openMyAddresses();
        openMyAccount();
        openMyPersonalInformation();
        openMyAccount();
        openMyWishlists();
        openMyAccount();
        openHome();
    }

    void checkPageTitle(){}
}
