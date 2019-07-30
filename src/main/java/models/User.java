package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Address mainAddress;
    private PersonalInfo personalInfo;

    private ArrayList<Address> addresses;

    @Override
    public String toString(){
        return personalInfo.getCustomerFirstName() + " " + personalInfo.getCustomerLastName();
    }
}