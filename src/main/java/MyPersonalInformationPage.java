import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    private void clickHome(){
        waitForWebElementToBeClickable(home);
        home.click();
    }

    void openHome(){
        clickHome();
    }

    private void clickBackToYourAccount(){
        waitForWebElementToBeClickable(backToYourAccount);
        backToYourAccount.click();
    }

    void goToMyAccountPage(){
        clickBackToYourAccount();
    }

    private void clickSaveButton(){
        waitForWebElementToBeClickable(saveButton);
        saveButton.click();
    }

    void saveUpdates(){
        clickSaveButton();
    }

    void sendOldPassword(String userOldPassword){
        waitForWebElementVisibility(oldPassword);
        oldPassword.clear();
        oldPassword.sendKeys(userOldPassword);
    }

    void sendConfirmationPassword(String userPassword){
        waitForWebElementVisibility(confirmationPassword);
        confirmationPassword.clear();
        confirmationPassword.sendKeys(userPassword);
    }

}
