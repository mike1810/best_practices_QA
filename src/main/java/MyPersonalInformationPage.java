import lombok.Getter;
import models.PersonalInfo;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class MyPersonalInformationPage extends RegistrationPage{

    public MyPersonalInformationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@id='old_passwd']")
    private WebElement oldPassword;

    /*protected String getOldPasswordAttribute(){
        return oldPassword.getAttribute("value");
    }*/

    @FindBy(xpath = "//input[@id='confirmation']")
    private WebElement confirmationPassword;

    /*protected String getConfirmationPasswordAttribute(){
        return confirmationPassword.getAttribute("value");
    }*/

    @FindBy(xpath = "//span[contains(text(),'Home')]")
    private WebElement home;

    @FindBy(xpath = "//span[contains(text(),'Back to your account')]")
    private WebElement backToYourAccount;

    @FindBy(xpath = "//span[contains(text(),'Save')]")
    private WebElement saveButton;

    private void chooseGender(User user) {
        click(user.getPersonalInfo().isGenderMale() ? getMale() : getFemale());
    }

    void goHomePage(){
        clickAfterWaiting(home);
    }

    private void clickBackToYourAccount(){
        clickAfterWaiting(backToYourAccount);
    }

    void goToMyAccountPage(){
        clickBackToYourAccount();
    }

    void saveUpdates(){
        clickAfterWaiting(saveButton);
    }

    void updatePersonalInformation(User userBefore, User userAfter){
        chooseGender(userAfter);
        send(getFirstname(), userAfter.getPersonalInfo().getCustomerFirstName());
        send(getLastname(), userAfter.getPersonalInfo().getCustomerLastName());
        send(getEmail(), userAfter.getPersonalInfo().getEmail());
        selectByValue(getDays(), userAfter.getPersonalInfo().getDay());
        selectByValue(getMonths(), userAfter.getPersonalInfo().getMonth());
        selectByValue(getYears(), userAfter.getPersonalInfo().getYear());
        chooseCheckBox(getNewsletter(), userAfter);
        chooseCheckBox(getSpecialOffers(), userAfter);
        send(getOldPassword(), userBefore.getPersonalInfo().getPassword());
        send(getPassword(), userAfter.getPersonalInfo().getPassword());
        send(getConfirmationPassword(), userAfter.getPersonalInfo().getPassword());
    }

    public PersonalInfo getUserPersonalInfo(){
        PersonalInfo pageUserPersonalInfo = new PersonalInfo();
        pageUserPersonalInfo.setCustomerFirstName(getValueAttribute(getFirstname()));
        pageUserPersonalInfo.setCustomerLastName(getValueAttribute(getLastname()));
        pageUserPersonalInfo.setEmail(getValueAttribute(getEmail()));
        pageUserPersonalInfo.setDay(getValueAttribute(getDays()));
        pageUserPersonalInfo.setMonth(getValueAttribute(getMonths()));
        pageUserPersonalInfo.setYear(getValueAttribute(getYears()));
        pageUserPersonalInfo.setGenderMale(getMale().isSelected());
        pageUserPersonalInfo.setNewsLetter(getNewsletter().isSelected());
        pageUserPersonalInfo.setSpecialOffers(getSpecialOffers().isSelected());
        return pageUserPersonalInfo;
    }

}
