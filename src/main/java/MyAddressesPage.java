import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAddressesPage extends Page{
    public MyAddressesPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Add a new address')]")
    private WebElement addANewAddress;

    @FindBy(xpath = "//span[contains(text(),'Update')]")
    private WebElement updateAddress;

    @FindBy(xpath = "//span[contains(text(),'Delete')]")
    private WebElement deleteAddress;

    @FindBy(xpath = "//span[contains(text(),'Back to your account')]")
    private WebElement backToYourAccount;

    @FindBy(xpath = "//span[contains(text(),'Home')]")
    private WebElement Home;

    private void clickAddANewAddress(){
        waitForWebElementToBeClickable(addANewAddress);
        addANewAddress.click();
    }

    void addANewAddress(){
        clickAddANewAddress();
    }

    private void clickDeleteAddress(){
        waitForWebElementToBeClickable(deleteAddress);
        deleteAddress.click();
    }

    void deleteAnAddress(){
        clickDeleteAddress();
    }

    private void clickUpdateAddress(){
        waitForWebElementToBeClickable(updateAddress);
        updateAddress.click();
    }

    void openAddressUpdatePage(){
        clickUpdateAddress();
    }

    private void clickBackToYourAccount(){
        waitForWebElementToBeClickable(backToYourAccount);
        backToYourAccount.click();
    }

    void goToMyAccountPage(){
        clickBackToYourAccount();
    }

    private void clickHome(){
        waitForWebElementToBeClickable(Home);
        Home.click();
    }

    void openHome(){
        clickHome();
    }
}