package com.dass.LearnMorseCode.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.*;



// Need getters and setters for the model
// need tests for those getters and setters
//
@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToMany(mappedBy = "user")
    List<Message> inbox;


    String username;
    String password;

    public ApplicationUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public List<Message> getInbox() {
        return inbox;
    }

    public ApplicationUser() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
