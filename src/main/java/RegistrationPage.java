import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class RegistrationPage extends Page {

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    //@FindBy(xpath = "//*[@id='id_gender1']")
    @FindBy(xpath = ".//input[@id='id_gender1']")
    private WebElement male;

    //@FindBy(xpath = "//*[@id='id_gender2']")
    @FindBy(xpath = ".//input[@id='id_gender2']")
    private WebElement female;

    @FindBy(xpath = "//*[@id='customer_firstname']")
    private WebElement firstName;

    @FindBy(xpath = "//*[@id='customer_lastname']")
    private WebElement lastName;

    @FindBy(xpath = "//*[@id='email']")
    private WebElement emailSecondPage;

    @FindBy(xpath = "//*[@id='passwd']")
    private WebElement password;

    @FindBy(xpath = "//*[@id='days']")
    private WebElement date;

    @FindBy(xpath = "//*[@id='months']")
    private WebElement month;

    @FindBy(xpath = "//*[@id='years']")
    private WebElement year;

    @FindBy(xpath = "//input[@id='newsletter']")
    private WebElement newsletter;

    @FindBy(xpath = "//input[@id='optin']")
    private WebElement specialOffers;

    @FindBy(xpath = "//*[@id='customer_firstname']")
    private WebElement firstNameInAdressForm;

    @FindBy(xpath = "//*[@id='customer_lastname']")
    private WebElement lastNameInAdressForm;

    @FindBy(xpath = "//*[@id='company']")
    private WebElement company;

    @FindBy(xpath = "//*[@id='address1']")
    private WebElement address;

    @FindBy(xpath = "//*[@id='address2']")
    private WebElement addressLine2;

    @FindBy(xpath = "//*[@id='city']")
    private WebElement city;

    @FindBy(xpath = "//*[@id='id_state']")
    private WebElement state;

    @FindBy(xpath = "//*[@id='postcode']")
    private WebElement postcode;

    @FindBy(xpath = "//*[@id='id_country']")
    private WebElement country;

    @FindBy(xpath = "//*[@id='other']")
    private WebElement additionalInformation;

    @FindBy(xpath = "//*[@id='phone']")
    private WebElement homePhone;

    @FindBy(xpath = "//*[@id='phone_mobile']")
    private WebElement mobilePhone;

    @FindBy(xpath = "//*[@id='alias']")
    private WebElement anAdressAlias;

    @FindBy(xpath = "//*[@id='submitAccount']/span")
    private WebElement register;

    @FindBy(xpath = "//*[@id='columns']/div[1]")
    private WebElement invalidData;

    void checkPageTitle(){
        Assert.assertEquals(driver.getTitle(), "Login - My Store", "not equals");
    }

    void clickGenderMale() {
        waitForWebElementToBeClickable(male);
        male.click();
    }

    void clickGenderFemale() {
        waitForWebElementToBeClickable(female);
        female.click();
    }

    void sendFirstName(String userFirstName) {
        waitForWebElementVisibility(firstName);
        firstName.sendKeys(userFirstName);
    }

    void sendLastName(String userLastName) {
        waitForWebElementVisibility(lastName);
        lastName.sendKeys(userLastName);
    }

    void sendEmailSecondPage(String userEmail) {
        waitForWebElementVisibility(emailSecondPage);
        emailSecondPage.clear();
        emailSecondPage.sendKeys(userEmail);
    }

    void sendPassword(String userPassword) {
        waitForWebElementVisibility(password);
        password.sendKeys(userPassword);
    }

    void selectDate(String userDate) {
        //waitForWebElementVisibility(date);
        //waitForWebElementToBeClickable(date);
        Select sDate = new Select(date);
        sDate.selectByValue(String.valueOf(userDate));
    }

    void selectMonth(String userMonth) {
       // waitForWebElementVisibility(month);
        //waitForWebElementToBeClickable(month);
        Select sMonth = new Select(month);
        sMonth.selectByValue(String.valueOf(userMonth));
    }

    void selectYear(String userYear) {
       // waitForWebElementVisibility(year);
       // waitForWebElementToBeClickable(year);
        Select sYear = new Select(year);
        sYear.selectByValue(String.valueOf(userYear));
    }

    void clickNewsletter() {
        //waitForWebElementToBeClickable(newsletter);
        newsletter.click();
    }

    void clickSpecialOffers() {
        //waitForWebElementToBeClickable(specialOffers);
        specialOffers.click();
    }

    void firstNameInAddress(String userFirstName) {
        firstNameInAdressForm.clear();
        firstNameInAdressForm.sendKeys(userFirstName);
    }

    void lastNameInAddress(String userLastName) {
        lastNameInAdressForm.clear();
        lastNameInAdressForm.sendKeys(userLastName);
    }

    void sendCompany(String userCompany) {
        waitForWebElementVisibility(company);
        company.sendKeys(userCompany);
    }

    void sendAddress(String userAdress) {
        waitForWebElementVisibility(address);
        address.sendKeys(userAdress);
    }

    void sendAddressLine2(String userAdressLine2) {
        waitForWebElementVisibility(addressLine2);
        addressLine2.sendKeys(userAdressLine2);
    }

    void sendCity(String userCity) {
        waitForWebElementVisibility(city);
        city.sendKeys(userCity);
    }

    void selectState(String userState) {
       // waitForWebElementVisibility(state);
       // waitForWebElementToBeClickable(state);
        Select dropdown = new Select(state);
        dropdown.selectByValue(String.valueOf(userState));
    }

    void sendPostcode(String userPostcode) {
        waitForWebElementVisibility(postcode);
        postcode.sendKeys(userPostcode);
    }

    void selectCountry(String userCountry) {
    //    waitForWebElementVisibility(country);
    //    waitForWebElementToBeClickable(country);
        Select dropdown = new Select(country);
        dropdown.selectByValue(String.valueOf(userCountry));
    }

    void sendAdditionalInformation(String userAdditionalInformation) {
        waitForWebElementVisibility(additionalInformation);
        additionalInformation.sendKeys(userAdditionalInformation);
    }

    void sendHomePhone(String userHomePhone) {
        waitForWebElementVisibility(homePhone);
        homePhone.sendKeys(userHomePhone);
    }

    void sendMobilePhone(String userMobilePhone) {
        waitForWebElementVisibility(mobilePhone);
        mobilePhone.sendKeys(userMobilePhone);
    }

    void sendAnAddressAlias(String alias) {
        anAdressAlias.clear();
        anAdressAlias.sendKeys(alias);
    }

    public void clickRegister() {
        waitForWebElementVisibility(register);
        waitForWebElementToBeClickable(register);
        register.click();
    }

    public String getInvalidData() {
        return invalidData.getText();
    }
}
