import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class SignInPage extends Page {
    public SignInPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@id='email_create']")
    private WebElement newEmail;

    @FindBy(xpath = "//form[@id='create-account_form']//span[1]")
    private WebElement buttonToCreateAccount;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement registeredEmail;

    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement registeredEmailsPasswd;

    @FindBy(xpath = "//p[@class='submit']//span[1]")
    private WebElement submit;

    void sendNewEmail(String email){
        send(newEmail, email);
    }

    void clickButtonToCreateAccount(){
        clickAfterWaiting(buttonToCreateAccount);
    }

    void signIn(){
        clickAfterWaiting(submit);
    }

    void signInWith(String login, String password){
        send(registeredEmail, login);
        send(registeredEmailsPasswd, password);
        clickAfterWaiting(submit);
    }

    @Override
    public String toString(){
        return "SignInPage";
    }
}
