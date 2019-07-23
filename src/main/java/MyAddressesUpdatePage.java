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
        send(getFirstname(), user.getFirstName());//sendFirstname(user.getFirstName());
        send(getLastname(), user.getLastName());//sendLastname(user.getLastName());
        send(getCompany(), user.getCompany());//sendCompany(user.getCompany());
        send(getAddress1(), user.getAddress1());//sendAddress1(user.getAddress1());
        send(getAddress2(), user.getAddress2());//sendAddress2(user.getAddress2());
        send(getCity(), user.getCity());//sendCity(user.getCity());
        select(getState(), user.getState());
        send(getPostcode(), user.getPostcode());//sendPostcode(user.getPostcode());
        send(getHomePhone(), user.getHomePhone());//sendHomePhone(user.getHomePhone());
        send(getMobilePhone(), user.getMobilePhone());//sendMobilePhone(user.getMobilePhone());
        send(getAdditionalInformation(), user.getAdditionalInformation());//sendAdditionalInformation(user.getAdditionalInformation());
        send(getAlias(), user.getAlias());//sendAlias(user.getAlias());
    }
}