package pers.jz.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author jemmyzhang on 2018/2/23.
 */
public class User implements UserDetails {

    private String username;

    private String password;

    private List<String> authorityValues;

    public User(String username, String password, List<String> authorityValues) {
        this.username = username;
        this.password = password;
        this.authorityValues = authorityValues;
    }

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(authorityValues)) {
            authorityValues.stream().forEach((var)->list.add(new SimpleGrantedAuthority(var)));        }
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAuthorityValues() {
        return authorityValues;
    }

    public void setAuthorityValues(List<String> authorityValues) {
        this.authorityValues = authorityValues;
    }
}
