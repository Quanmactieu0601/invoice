package study.invoice.dto.respone;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtRespone {
    String token;
    private String type = "Bearer";
    private String login;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtRespone() {
    }

    public JwtRespone(String token, String type, String login, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.type = type;
        this.login = login;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
