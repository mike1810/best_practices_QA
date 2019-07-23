package hmmmm;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
class CustomPage extends AbstractPage implements IGetAttribute{
    Address address = new Address();
}

@Getter
@Setter
class Address implements IGetAttribute{
    @FindBy(xpath = "//*[@id='company']")
    private WebElement company;

    String getCompanyAttribute() {
        return company.getAttribute("value");
    }

    String getCompanyAttribute3() {
        return getTextBoxValueAttributeStatic(company);
    }
}

class Test {
    CustomPage customPage = new CustomPage();
    void qwer() {
        customPage.getAddress().getCompany().getAttribute("value");
        customPage.getAddress().getCompanyAttribute();
        customPage.getAddress().getCompanyAttribute3();                                   // 6
        customPage.getTextBoxValueAttributeStatic(customPage.getAddress().getCompany());  // 7
    }
}