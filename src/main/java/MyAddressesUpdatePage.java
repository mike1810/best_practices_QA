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

    private void backToYourAddresses() {
        clickAfterWaiting(backToYourAddressesButton);
    }

    void updateAddress(User user) {
        send(getFirstname(), user.getAddress().getFirstName());
        send(getLastname(), user.getAddress().getLastName());
        send(getCompany(), user.getAddress().getCompany());
        send(getAddress1(), user.getAddress().getAddress1());
        send(getAddress2(), user.getAddress().getAddress2());
        send(getCity(), user.getAddress().getCity());
        select(getState(), user.getAddress().getState());
        send(getPostcode(), user.getAddress().getPostcode());
        send(getHomePhone(), user.getAddress().getHomePhone());
        send(getMobilePhone(), user.getAddress().getMobilePhone());
        send(getAdditionalInformation(), user.getAddress().getAdditionalInformation());
        send(getAlias(), user.getAddress().getAlias());
    }
}