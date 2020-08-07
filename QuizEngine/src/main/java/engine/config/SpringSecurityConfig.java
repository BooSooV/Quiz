package engine.config;

import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;




@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select email, password, active from user where email=?")
                .authoritiesByUsernameQuery("select email, roles from user where email=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/quizzes").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/quizzes/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/quizzes").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/quizzes/**/solve").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/quizzes/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/quizzes/completed").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .logout().permitAll();
    }
}

