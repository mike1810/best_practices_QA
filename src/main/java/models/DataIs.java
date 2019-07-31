package models;

import lombok.Getter;

@Getter
public enum DataIs {
    NOT_NORMAL_USER_FOR_NEGATIVE,
    NORMAL_USER_FOR_NEGATIVE,
    USER_BEFORE_EDITING,
    USER_AFTER_EDITING
    /*REGISTERED("Registered"),NOT_REGISTERED("Not registered");
    private String key;
    DataIs(String key){
        this.key = key;
    }*/
}