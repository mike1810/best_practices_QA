package models;

import lombok.Getter;

@Getter
public enum DataIs {
    USER_FOR_NEGATIVE_AFTER,
    USER_FOR_NEGATIVE_BEFORE,
    USER_BEFORE_EDITING,
    USER_AFTER_EDITING
    /*REGISTERED("Registered"),NOT_REGISTERED("Not registered");
    private String key;
    DataIs(String key){
        this.key = key;
    }*/
}