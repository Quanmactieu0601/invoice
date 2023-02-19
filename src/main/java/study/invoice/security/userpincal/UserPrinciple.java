package study.invoice.security.userpincal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import study.invoice.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {

    private Long id;
    private String login;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean activated = false;
    private String country;
    private String phone;
    private String personalID;
    private Long comID;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple() {
    }

    public UserPrinciple(Long id, String login, String password, String firstName, String lastName, String email, boolean activated, String country, String phone, String personalID, Long comID, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.country = country;
        this.phone = phone;
        this.personalID = personalID;
        this.comID = comID;
        this.authorities = authorities;
    }

    public static UserPrinciple build(User user){
        List<GrantedAuthority> authorities = user.getAuthorities().stream()
                                                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                                                .collect(Collectors.toList());
        return new UserPrinciple(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getActivated(),
                user.getCountry(),
                user.getPhone(),
                user.getPersonalID(),
                user.getComID(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
