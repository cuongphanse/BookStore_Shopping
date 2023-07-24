/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.registration;

import java.io.Serializable;

/**
 *
 * @author datnt
 */
public class RegistrationCreateError implements Serializable{
    private String userNameLengthError;
    private String passwordLengthError;
    private String fullNameLengthError;
    private String confirmNotMatched;
    private String userNameIsExisted;

    public RegistrationCreateError() {
    }

    
    public void setUserNameLengthError(String userNameLengthError) {
        this.userNameLengthError = userNameLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public void setFullNameLengthError(String fullNameLengthError) {
        this.fullNameLengthError = fullNameLengthError;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public void setUserNameIsExisted(String userNameIsExisted) {
        this.userNameIsExisted = userNameIsExisted;
    }

    public String getUserNameLengthError() {
        return userNameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public String getFullNameLengthError() {
        return fullNameLengthError;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public String getUserNameIsExisted() {
        return userNameIsExisted;
    }
    
    
}
