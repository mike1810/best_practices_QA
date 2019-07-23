
package hmmmm;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class Addresses {


    /*sendFirstname(user.getFirstName());
    sendLastname(user.getLastName());



    sendCompany(user.getCompany());*/
    @FindBy(xpath = "//*[@id='company']")
    private WebElement company;

    public String getCompanyAttribute() {
        return company.getAttribute("value");
    }

/*
    sendAddress1(user.getAddress1());
    @FindBy(xpath = "//*[@id='address1']")
    private WebElement address1;

    protected String getAddress1Attribute() {
        return address1.getAttribute("value");
    }


    sendAddress2(user.getAddress2());
    @FindBy(xpath = "//*[@id='address2']")
    private WebElement address2;

    protected String getAddress2Attribute() {
        return address2.getAttribute("value");
    }


    sendCity(user.getCity());
    selectState(user.getState());
    sendPostcode(user.getPostcode());
    sendHomePhone(user.getHomePhone());
    sendMobilePhone(user.getMobilePhone());
    sendAdditionalInformation(user.getAdditionalInformation());
    sendAlias(user.getAlias());*/
}

