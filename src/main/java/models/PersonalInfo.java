package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Data
public class PersonalInfo {

    private boolean genderMale;
    private String customerFirstName;
    private String customerLastName;
    @JsonIgnore
    private String email;
    private String day;
    private String month;
    private String year;
    private String password;
    private boolean newsLetter;
    private boolean specialOffers;

    {
        this.email = giveNameToNewAccount() + "@gmail.com";
    }

    private static String giveNameToNewAccount() {
        DateFormat dateFormat = new SimpleDateFormat("yMdHms");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    @Override
    public String toString(){
        return  "genderMale: " + genderMale +
                "; customerFirstName: " + customerFirstName +
                "; customerLastName: " + customerLastName +
                "; email: " + email +
                "; day: " + day +
                "; month: " + month +
                "; year: " + year +
                "; newsLetter: " + newsLetter +
                "; specialOffers: " + specialOffers;
    }

    public int compareTo(PersonalInfo comparedWith){
        return this.toString().compareTo(comparedWith.toString());
    }
}

//h3[contains(text(),'My address')]
//h3[contains(text(),'My address2')]