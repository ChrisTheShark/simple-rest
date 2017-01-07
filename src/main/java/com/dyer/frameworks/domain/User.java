package com.dyer.frameworks.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import java.util.Arrays;

/**
 * Domain <code>class</code> represents a User.
 */
@Entity
public class User extends BaseEntity {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private String username;
    private String firstname;
    private String lastname;

    @JsonIgnore
    private String password;
    @JsonIgnore
    private String[] roles;

    protected User() {
        super();
    }

    public User(String username, String firstname, String lastname, String password, String[] roles) {
        this();
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        setPassword(password);
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
