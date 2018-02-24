package pers.jz.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author jemmyzhang on 2018/2/24.
 */
public class User implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private List<String> authorityValues;

    public User(String username,String password, List<String> authorityValues) {
        this.username = username;
        this.password = password;
        this.authorityValues=authorityValues;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list=new ArrayList<>();
        if(Objects.nonNull(authorityValues)){
            authorityValues.stream().forEach((var)->list.add(new SimpleGrantedAuthority(var)));
        }
        return list;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public List<String> getAuthorityValues() {
        return authorityValues;
    }

    public void setAuthorityValues(List<String> authorityValues) {
        this.authorityValues = authorityValues;
    }
}
