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

    private void backToYourAddresses() {
        clickAfterWaiting(backToYourAddressesButton);
    }

    void updateAddress(User user) {
        send(getFirstname(), user.getMainAddress().getFirstName());
        send(getLastname(), user.getMainAddress().getLastName());
        send(getCompany(), user.getMainAddress().getCompany());
        send(getAddress1(), user.getMainAddress().getAddress1());
        send(getAddress2(), user.getMainAddress().getAddress2());
        send(getCity(), user.getMainAddress().getCity());
        selectByVisibleText(getState(), user.getMainAddress().getState());
        send(getPostcode(), user.getMainAddress().getPostcode());
        send(getHomePhone(), user.getMainAddress().getHomePhone());
        send(getMobilePhone(), user.getMainAddress().getMobilePhone());
        send(getAdditionalInformation(), user.getMainAddress().getAdditionalInformation());
        send(getAlias(), user.getMainAddress().getAlias());
    }

    public Address getUserAddress(){
        Address pageUserAddress = new Address();
        pageUserAddress.setFirstName(getValueAttribute(getFirstname()));
        pageUserAddress.setLastName(getValueAttribute(getLastname()));
        pageUserAddress.setCompany(getValueAttribute(getCompany()));
        pageUserAddress.setAddress1(getValueAttribute(getAddress1()));
        pageUserAddress.setAddress2(getValueAttribute(getAddress2()));
        pageUserAddress.setCity(getValueAttribute(getCity()));
        pageUserAddress.setCountry(getTextAttribute(getCountry()));
        pageUserAddress.setState(getTextAttribute(getState()));
        pageUserAddress.setPostcode(getValueAttribute(getPostcode()));
        pageUserAddress.setHomePhone(getValueAttribute(getHomePhone()));
        pageUserAddress.setMobilePhone(getValueAttribute(getMobilePhone()));
        pageUserAddress.setAdditionalInformation(getValueAttribute(getAdditionalInformation()));
        pageUserAddress.setAlias(getValueAttribute(getAlias()));
        return pageUserAddress;
    }
}