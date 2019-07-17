import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdressesPage extends Page{

    @FindBy(xpath = "//span[contains(text(),'Add a new address')]")
    private WebElement addANewAddress;

    @FindBy(xpath = "//span[contains(text(),'Update')]")
    private WebElement updateAddress;

    @FindBy(xpath = "//span[contains(text(),'Back to your account')]")
    private WebElement backToYourAccount;

    void addNewAddress(){
        waitForWebElementToBeClickable(addANewAddress);
        addANewAddress.click();
    }

    void checkPageTitle(){}
}