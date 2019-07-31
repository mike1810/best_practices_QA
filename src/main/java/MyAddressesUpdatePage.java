import models.Address;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAddressesUpdatePage extends RegistrationPage {

    public MyAddressesUpdatePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Save')]")
    private WebElement saveButton;

    @FindBy(xpath = "//span[contains(text(),'Back to your addresses')]")
    private WebElement backToYourAddressesButton;

    void saveUpdates() {
        clickAfterWaiting(saveButton);
    }

    void backToYourAddresses() {
        clickAfterWaiting(backToYourAddressesButton);
    }

}