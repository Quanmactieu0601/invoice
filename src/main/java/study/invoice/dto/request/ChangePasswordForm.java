package study.invoice.dto.request;

import javax.validation.constraints.NotNull;

public class ChangePasswordForm {
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private String newPassword;
    @NotNull
    private String remindPassword;

    public ChangePasswordForm(@NotNull String login, @NotNull String password, @NotNull String newPassword, @NotNull String remindPassword) {
        this.login = login;
        this.password = password;
        this.newPassword = newPassword;
        this.remindPassword = remindPassword;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRemindPassword() {
        return remindPassword;
    }

    public void setRemindPassword(String remindPassword) {
        this.remindPassword = remindPassword;
    }
}
