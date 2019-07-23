import lombok.Getter;
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

    protected String getOldPasswordAttribute(){
        return oldPassword.getAttribute("value");
    }

    @FindBy(xpath = "//input[@id='confirmation']")
    private WebElement confirmationPassword;

    protected String getConfirmationPasswordAttribute(){
        return confirmationPassword.getAttribute("value");
    }

    @FindBy(xpath = "//span[contains(text(),'Home')]")
    private WebElement home;

    @FindBy(xpath = "//span[contains(text(),'Back to your account')]")
    private WebElement backToYourAccount;

    @FindBy(xpath = "//span[contains(text(),'Save')]")
    private WebElement saveButton;

    private void chooseGender(User user) {
        click(user.isGenderMale() ? getMale() : getFemale());
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

    void updatePersonalInformation(User user, String oldPassword, String newPassword){
        chooseGender(user);
        send(getFirstname(), user.getFirstName());
        send(getLastname(), user.getLastName());
        //Email
        select(getDays(), user.getDate());
        select(getMonths(), user.getMonth());
        select(getYears(), user.getYear());
        send(getOldPassword(), oldPassword);
        chooseNewsLetter(user);
        chooseSpecialOffers(user);
        send(getPassword(), newPassword);
        send(getConfirmationPassword(), newPassword);
    }

}
