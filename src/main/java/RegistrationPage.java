import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

@Getter
public class RegistrationPage extends Page {

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = ".//input[@id='id_gender1']")
    private WebElement male;

    protected String getMaleAttribute(){
        return male.getAttribute("checked");
    }

    @FindBy(xpath = ".//input[@id='id_gender2']")
    private WebElement female;

    protected String getFemaleAttribute(){
        return female.getAttribute("checked");
    }

    @FindBy(xpath = "//*[@id='customer_firstname']")
    private WebElement сustomerFirstName;

    @FindBy(xpath = "//*[@id='customer_lastname']")
    private WebElement сustomerLastName;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement email;

    protected String getEmailAttribute(){
        return email.getAttribute("value");
    }

    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement password;

    protected String getPasswordAttribute(){
        return password.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='days']")
    private WebElement days;

    protected String getDaysAttribute(){
        return days.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='months']")
    private WebElement months;

    protected String getMonthsAttribute(){
        return months.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='years']")
    private WebElement years;

    protected String getYearsAttribute(){
        return years.getAttribute("value");
    }

    @FindBy(xpath = "//input[@id='newsletter']")
    private WebElement newsletter;

    protected String getNewsletterAttribute(){
        return newsletter.getAttribute("checked");
    }

    @FindBy(xpath = "//input[@id='optin']")
    private WebElement specialOffers;

    protected String getSpecialOffersAttribute(){
        return specialOffers.getAttribute("checked");
    }

    @FindBy(xpath = "//*[@id='company']")
    private WebElement company;

    protected String getCompanyAttribute(){
        return company.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='address1']")
    private WebElement address1;

    protected String getAddress1Attribute(){
        return address1.getAttribute("value");
    }

    @FindBy(xpath = "//input[@id='firstname']")
    private WebElement firstname;

    protected String getFirstnameAttribute(){
        return firstname.getAttribute("value");
    }

    @FindBy(xpath = "//input[@id='lastname']")
    private WebElement lastname;

    protected String getLastnameAttribute(){
        return lastname.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='address2']")
    private WebElement address2;

    protected String getAddress2Attribute(){
        return address2.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='city']")
    private WebElement city;

    protected String getCityAttribute(){
        return city.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='id_state']")
    private WebElement state;

    protected String getStateAttribute(){
        return state.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='postcode']")
    private WebElement postcode;

    protected String getPostcodeAttribute(){
        return postcode.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='id_country']")
    private WebElement country;

    protected String getCountryAttribute(){
        return country.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='other']")
    private WebElement additionalInformation;

    protected String getAdditionalInformationAttribute(){
        return additionalInformation.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='phone']")
    private WebElement homePhone;

    protected String getHomePhoneAttribute(){
        return homePhone.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='phone_mobile']")
    private WebElement mobilePhone;

    protected String getMobilePhoneAttribute(){
        return mobilePhone.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='alias']")
    private WebElement alias;

    protected String getAliasAttribute(){
        return alias.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='submitAccount']/span")
    private WebElement register;

    @FindBy(xpath = "//*[@id='columns']/div[1]")
    private WebElement invalidData;

    private void clickGenderMale() {
        waitForWebElementToBeClickable(male);
        male.click();
    }

    private void clickGenderFemale() {
        waitForWebElementToBeClickable(female);
        female.click();
    }

    private void sendCustomerFirstName(String userFirstName) {
        waitForWebElementVisibility(сustomerFirstName);
        сustomerFirstName.clear();
        сustomerFirstName.sendKeys(userFirstName);
    }

    protected void sendLastname(String userLastName) {
        waitForWebElementVisibility(lastname);
        lastname.clear();
        lastname.sendKeys(userLastName);
    }

    protected void sendFirstname(String userFirstName) {
        waitForWebElementVisibility(firstname);
        firstname.clear();
        firstname.sendKeys(userFirstName);
    }

    private void sendCustomerLastName(String userLastName) {
        waitForWebElementVisibility(сustomerLastName);
        сustomerLastName.clear();
        сustomerLastName.sendKeys(userLastName);
    }

    protected void sendPassword(String userPassword) {
        waitForWebElementVisibility(password);
        password.clear();
        password.sendKeys(userPassword);
    }

    protected void selectDays(String userDate) {
        Select sDate = new Select(days);
        sDate.selectByValue(String.valueOf(userDate));
    }

    protected void selectMonths(String userMonth) {
        Select sMonth = new Select(months);
        sMonth.selectByValue(String.valueOf(userMonth));
    }

    protected void selectYears(String userYear) {
        Select sYear = new Select(years);
        sYear.selectByValue(String.valueOf(userYear));
    }

    private void clickNewsletter() {
        newsletter.click();
    }

    private void clickSpecialOffers() {
        specialOffers.click();
    }

    protected void sendCompany(String userCompany) {
        waitForWebElementVisibility(company);
        company.clear();
        company.sendKeys(userCompany);
    }

    protected void sendAddress1(String userAddress1) {
        waitForWebElementVisibility(address1);
        address1.clear();
        address1.sendKeys(userAddress1);
    }

    protected void sendAddress2(String userAddress2) {
        waitForWebElementVisibility(address2);
        address2.clear();
        address2.sendKeys(userAddress2);
    }

    protected void sendCity(String userCity) {
        waitForWebElementVisibility(city);
        city.clear();
        city.sendKeys(userCity);
    }

    protected void selectState(String userState) {
        Select dropdown = new Select(state);
        dropdown.selectByValue(String.valueOf(userState));
    }

    protected void sendPostcode(String userPostcode) {
        waitForWebElementVisibility(postcode);
        postcode.clear();
        postcode.sendKeys(userPostcode);
    }

    private void selectCountry(String userCountry) {
        Select dropdown = new Select(country);
        dropdown.selectByValue(String.valueOf(userCountry));
    }

    protected void sendAdditionalInformation(String userAdditionalInformation) {
        waitForWebElementVisibility(additionalInformation);
        additionalInformation.clear();
        additionalInformation.sendKeys(userAdditionalInformation);
    }

    protected void sendHomePhone(String userHomePhone) {
        waitForWebElementVisibility(homePhone);
        homePhone.clear();
        homePhone.sendKeys(userHomePhone);
    }

    protected void sendMobilePhone(String userMobilePhone) {
        waitForWebElementVisibility(mobilePhone);
        mobilePhone.clear();
        mobilePhone.sendKeys(userMobilePhone);
    }

    protected void sendAlias(String userAlias) {
        alias.clear();
        alias.sendKeys(userAlias);
    }

    public void clickRegister() {
        waitForWebElementVisibility(register);
        waitForWebElementToBeClickable(register);
        register.click();
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

    void createNewAccountWithOnlyRequiredFields(User user){
        sendCustomerFirstName(user.getFirstName());
        sendCustomerLastName(user.getLastName());
        sendPassword(user.getPassword());
        selectDays(user.getDate());
        selectMonths(user.getMonth());
        selectYears(user.getYear());
        sendAddress1(user.getAddress1());
        sendCity(user.getCity());
        selectState(user.getState());
        sendPostcode(user.getPostcode());
        selectCountry(user.getCountry());
        sendMobilePhone(user.getMobilePhone());
        sendAlias(user.getAlias());
    }

    void createNewAccountWithAllFields(User user){
        chooseGender(user);
        sendCustomerFirstName(user.getFirstName());
        sendCustomerLastName(user.getLastName());
        sendPassword(user.getPassword());
        selectDays(user.getDate());
        selectMonths(user.getMonth());
        selectYears(user.getYear());
        chooseNewsLetter(user);
        chooseSpecialOffers(user);
        sendCompany(user.getCompany());
        sendAddress1(user.getAddress1());
        sendAddress2(user.getAddress2());
        sendCity(user.getCity());
        selectState(user.getState());
        sendPostcode(user.getPostcode());
        selectCountry(user.getCountry());
        sendAdditionalInformation(user.getAdditionalInformation());
        sendHomePhone(user.getHomePhone());
        sendMobilePhone(user.getMobilePhone());
        sendAlias(user.getAlias());
    }

    public void registerAccount(){
        clickRegister();
    }
}