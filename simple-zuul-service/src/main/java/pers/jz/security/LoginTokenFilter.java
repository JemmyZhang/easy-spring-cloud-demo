package pers.jz.security;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.CollectionUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author jemmyzhang on 2018/2/23.
 */
public class LoginTokenFilter extends UsernamePasswordAuthenticationFilter {

    public LoginTokenFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        pers.jz.entity.User userDetail = convertToUserDetail(user);
        String subject = new Gson().toJson(userDetail);
        String token = Jwts
                .builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(SignatureAlgorithm.HS512, "SimpleJWT")
                .compact();
        response.addHeader("Authorization", "Bearer " + token);
        super.successfulAuthentication(request, response, chain, authResult);
    }

    private pers.jz.entity.User convertToUserDetail(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        List<String> authorityValues = new ArrayList<>();
        if (!CollectionUtils.isEmpty(authorities)) {
            authorities.stream().forEach((var) -> authorityValues.add(var.getAuthority()));
        }
        return new pers.jz.entity.User(username, password, authorityValues);
    }
}
