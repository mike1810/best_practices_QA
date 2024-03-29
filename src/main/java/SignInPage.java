import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class SignInPage extends Page {
    public SignInPage(){}
    public SignInPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@id='email_create']")
    private WebElement newEmail;

    @FindBy(xpath = "//*[@id='SubmitCreate']/span")
    private WebElement buttonToCreateAccount;

    @FindBy(xpath = "//*[@id='email']")
    private WebElement registeredEmail;

    @FindBy(xpath = "//*[@id='passwd']")
    private WebElement registeredEmailsPasswd;

    void checkPageTitle(){
        Assert.assertEquals(driver.getTitle(), "Login - My Store", "not equals");
    }

    void sendNewEmail(String email){
        waitForWebElementVisibility(newEmail);
        newEmail.sendKeys(email);
    }

    void clickButtonToCreateAccount(){
        waitForWebElementToBeClickable(buttonToCreateAccount);
        buttonToCreateAccount.click();
    }

    @Override
    public String toString(){
        return "SignInPage";
    }
}
