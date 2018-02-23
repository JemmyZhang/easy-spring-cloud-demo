package pers.jz.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author jemmyzhang on 2018/2/23.
 */
@Deprecated
public class DemoSecurityConfig {

    //@EnableWebSecurity
    //@Order(Ordered.HIGHEST_PRECEDENCE)
    public static class LoginAppSecurityConfigurer extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().antMatcher("/user/**").formLogin().and()
                    .authorizeRequests().antMatchers("/user/**").authenticated();
        }
    }

    //@EnableWebSecurity
    public static class WebAppSecurityConfigurer extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().httpBasic().and().authorizeRequests()
                    .antMatchers("/movie/**").authenticated()
                    .antMatchers("/login").permitAll()
                    .and().authorizeRequests().anyRequest().authenticated();
        }
    }
}
