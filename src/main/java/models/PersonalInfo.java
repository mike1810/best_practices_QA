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
        if(this.genderMale != comparedWith.genderMale){
            return -1;
        }
        if(this.customerFirstName.compareTo(comparedWith.customerFirstName) != 0){
            return -1;
        }
        if(this.customerLastName.compareTo(comparedWith.customerLastName) != 0){
            return -1;
        }
        if(this.email.compareTo(comparedWith.email) != 0){
            return -1;
        }
        if(this.day.compareTo(comparedWith.day) != 0){
            return -1;
        }
        if(this.month.compareTo(comparedWith.month) != 0){
            return -1;
        }
        if(this.year.compareTo(comparedWith.year) != 0){
            return -1;
        }
        if(this.newsLetter != comparedWith.newsLetter){
            return -1;
        }
        if(this.specialOffers != comparedWith.specialOffers){
            return -1;
        }
        return 0;
    }
}

//h3[contains(text(),'My address')]
//h3[contains(text(),'My address2')]