import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class MyAccountPage extends Page {

    public MyAccountPage(WebDriver driver) {
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

    void openOrderHistoryAndDetails() {
        clickAfterWaiting(orderHistoryAndDetails);
    }

    void openMyCreditSlips() {
        clickAfterWaiting(myCreditSlips);
    }

    void openMyAddresses() {
        click(myAddresses);
        //clickAfterWaiting(myAddresses);
    }

    void openMyPersonalInformation() {
        clickAfterWaiting(myPersonalInformation);
    }

    void openMyWishlists() {
        clickAfterWaiting(myWishlists);
    }

    void goHomePage() {
        clickAfterWaiting(home);
    }
}
