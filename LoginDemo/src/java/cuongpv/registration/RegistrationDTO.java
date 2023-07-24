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
public class RegistrationDTO implements Serializable {
        private String userName;
        private String password;
        private String lastName;
        private boolean role;

    public RegistrationDTO() {
        
    }

    public RegistrationDTO(String userName, String password, String lastName, boolean role) {
        this.userName = userName;
        this.password = password;
        this.lastName = lastName;
        this.role = role;
    }
        
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the role
     */
    public boolean isRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(boolean role) {
        this.role = role;
    }
        
        


       
}
