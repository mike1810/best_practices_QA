import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class RegistrationPage extends Page {

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = ".//input[@id='id_gender1']")
    private WebElement male;

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

    private void clickGenderMale() {
        waitForWebElementToBeClickable(male);
        male.click();
    }

    private void clickGenderFemale() {
        waitForWebElementToBeClickable(female);
        female.click();
    }

    private void sendFirstName(String userFirstName) {
        waitForWebElementVisibility(firstName);
        firstName.sendKeys(userFirstName);
    }

    private void sendLastName(String userLastName) {
        waitForWebElementVisibility(lastName);
        lastName.sendKeys(userLastName);
    }

    void sendEmailSecondPage(String userEmail) {
        waitForWebElementVisibility(emailSecondPage);
        emailSecondPage.clear();
        emailSecondPage.sendKeys(userEmail);
    }

    private void sendPassword(String userPassword) {
        waitForWebElementVisibility(password);
        password.sendKeys(userPassword);
    }

    private void selectDate(String userDate) {
        Select sDate = new Select(date);
        sDate.selectByValue(String.valueOf(userDate));
    }

    private void selectMonth(String userMonth) {
        Select sMonth = new Select(month);
        sMonth.selectByValue(String.valueOf(userMonth));
    }

    private void selectYear(String userYear) {
        Select sYear = new Select(year);
        sYear.selectByValue(String.valueOf(userYear));
    }

    private void clickNewsletter() {
        newsletter.click();
    }

    private void clickSpecialOffers() {
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

    private void sendCompany(String userCompany) {
        waitForWebElementVisibility(company);
        company.sendKeys(userCompany);
    }

    private void sendAddress(String userAdress) {
        waitForWebElementVisibility(address);
        address.sendKeys(userAdress);
    }

    private void sendAddressLine2(String userAdressLine2) {
        waitForWebElementVisibility(addressLine2);
        addressLine2.sendKeys(userAdressLine2);
    }

    private void sendCity(String userCity) {
        waitForWebElementVisibility(city);
        city.sendKeys(userCity);
    }

    private void selectState(String userState) {
        Select dropdown = new Select(state);
        dropdown.selectByValue(String.valueOf(userState));
    }

    private void sendPostcode(String userPostcode) {
        waitForWebElementVisibility(postcode);
        postcode.sendKeys(userPostcode);
    }

    private void selectCountry(String userCountry) {
        Select dropdown = new Select(country);
        dropdown.selectByValue(String.valueOf(userCountry));
    }

    private void sendAdditionalInformation(String userAdditionalInformation) {
        waitForWebElementVisibility(additionalInformation);
        additionalInformation.sendKeys(userAdditionalInformation);
    }

    private void sendHomePhone(String userHomePhone) {
        waitForWebElementVisibility(homePhone);
        homePhone.sendKeys(userHomePhone);
    }

    private void sendMobilePhone(String userMobilePhone) {
        waitForWebElementVisibility(mobilePhone);
        mobilePhone.sendKeys(userMobilePhone);
    }

    private void sendAnAddressAlias(String alias) {
        anAdressAlias.clear();
        anAdressAlias.sendKeys(alias);
    }

    public void clickRegister() {
        waitForWebElementVisibility(register);
        waitForWebElementToBeClickable(register);
        register.click();
    }

    void createNewAccountWithOnlyRequiredFields(User user){
        sendFirstName(user.getFirstName());
        sendLastName(user.getLastName());
        sendPassword(user.getPassword());
        selectDate(user.getDate());
        selectMonth(user.getMonth());
        selectDate(user.getDate());
        selectYear(user.getYear());
        sendAddress(user.getAddress1());
        sendCity(user.getCity());
        selectState(user.getState());
        sendPostcode(user.getPostcode());
        selectCountry(user.getCountry());
        sendMobilePhone(user.getMobilePhone());
        sendAnAddressAlias(user.getAddressAlias());
    }

    private void chooseGender(User user){
        if (user.isGenderMale()) {
            clickGenderMale();
        } else {
            clickGenderFemale();
        }
    }

    private void chooseNewsLetter(User user){
        if (user.isNewsLetter()) {
            clickNewsletter();
        }
    }

    private void chooseSpecialOffers(User user){
        if(user.isSpecialOffers()){
            clickSpecialOffers();
        }
    }

    void createNewAccountWithAllFields(User user){
        chooseGender(user);
        sendFirstName(user.getFirstName());
        sendLastName(user.getLastName());
        sendPassword(user.getPassword());
        selectDate(user.getDate());
        selectMonth(user.getMonth());
        selectYear(user.getYear());
        chooseNewsLetter(user);
        chooseSpecialOffers(user);
        sendCompany(user.getCompany());
        sendAddress(user.getAddress1());
        sendAddressLine2(user.getAddress2());
        sendCity(user.getCity());
        selectState(user.getState());
        sendPostcode(user.getPostcode());
        selectCountry(user.getCountry());
        sendAdditionalInformation(user.getAdditionalInformation());
        sendHomePhone(user.getHomePhone());
        sendMobilePhone(user.getMobilePhone());
        sendAnAddressAlias(user.getAddressAlias());
    }

    public void registerAccount(){
        clickRegister();
    }

    public String getInvalidData() {
        return invalidData.getText();
    }
}