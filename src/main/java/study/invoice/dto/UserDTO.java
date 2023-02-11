package study.invoice.dto;

import java.util.Set;

public class UserDTO {
    private String login;
    private String password;

    private String remindPassword;
    private String firstName;
    private String lastName;
    private String email;
    private boolean activated = true;
    private String country;
    private String phone;
    private String personalID;
    private Set<String> authorities;

    public UserDTO() {
    }

    public UserDTO(String login, String password, String firstName, String lastName, String email, boolean activated, String country, String phone, String personalID, Set<String> authorities) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.country = country;
        this.phone = phone;
        this.personalID = personalID;
        this.authorities = authorities;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public String getRemindPassword() {
        return remindPassword;
    }

    public void setRemindPassword(String remindPassword) {
        this.remindPassword = remindPassword;
    }
}
