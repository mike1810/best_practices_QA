package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
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