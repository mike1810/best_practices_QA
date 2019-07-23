import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAddressesUpdatePage extends RegistrationPage{

    public MyAddressesUpdatePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Save')]")
    private WebElement saveButton;

    @FindBy(xpath = "//span[contains(text(),'Back to your addresses')]")
    private WebElement backToYourAddressesButton;

    private void clickSaveButton(){
        waitForWebElementToBeClickable(saveButton);
        saveButton.click();
    }

    void saveUpdates(){
        clickSaveButton();
    }

    private void backToYourAddresses(){
        waitForWebElementToBeClickable(backToYourAddressesButton);
        backToYourAddressesButton.click();
    }

    void updateAddress(User user){
        sendFirstname(user.getFirstName());
        sendLastname(user.getLastName());
        sendCompany(user.getCompany());
        sendAddress1(user.getAddress1());
        sendAddress2(user.getAddress2());
        sendCity(user.getCity());
        selectState(user.getState());
        sendPostcode(user.getPostcode());
        sendHomePhone(user.getHomePhone());
        sendMobilePhone(user.getMobilePhone());
        sendAdditionalInformation(user.getAdditionalInformation());
        sendAlias(user.getAlias());
    }

}
