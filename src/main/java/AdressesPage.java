import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdressesPage extends Page{

    @FindBy(xpath = "//span[contains(text(),'Add a new address')]")
    private WebElement addANewAddress;

    @FindBy(xpath = "//span[contains(text(),'Update')]")
    private WebElement updateAddress;

    @FindBy(xpath = "//span[contains(text(),'Back to your account')]")
    private WebElement backToYourAccount;

    private void clickAddANewAddress(){
        waitForWebElementToBeClickable(addANewAddress);
        addANewAddress.click();
    }

    void addNewAddress(){
        clickAddANewAddress();
    }

    private void clickUpdateAddress(){
        waitForWebElementToBeClickable(updateAddress);
        updateAddress.click();
    }

    void updateAddress(){
        clickUpdateAddress();
    }

    private void clickBackToYourAccount(){
        waitForWebElementToBeClickable(backToYourAccount);
        backToYourAccount.click();
    }

    void goToMyAccountPage(){
        clickBackToYourAccount();
    }

    void checkPageTitle(){}
}