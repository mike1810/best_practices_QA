package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Getter
public class PersonalIlfo {

    {}
    private boolean genderMale;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String email;
    private String date;
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
}
