import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


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
        waitForWebElementVisibility(newEmail);
        newEmail.sendKeys(email);
    }

    void clickButtonToCreateAccount(){
        waitForWebElementToBeClickable(buttonToCreateAccount);
        buttonToCreateAccount.click();
    }

    void sendEmail(String regEmail){
        waitForWebElementVisibility(registeredEmail);
        registeredEmail.sendKeys(regEmail);
    }

    void sendPassword(String regPassword){
        waitForWebElementVisibility(registeredEmailsPasswd);
        registeredEmailsPasswd.sendKeys(regPassword);
    }
    void clickSubmit(){
        waitForWebElementToBeClickable(submit);
        submit.click();
    }

    void signIn(){
        clickSubmit();
    }

    void signInWith(String login, String password){
        sendEmail("tester@tester.tester");
        sendPassword("12345");
        clickSubmit();
    }

    @Override
    public String toString(){
        return "SignInPage";
    }
}
