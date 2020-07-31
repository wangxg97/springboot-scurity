package com.security.springbootsecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//安全配置
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //定义用户信息服务(查询用户信息)
    //@Bean
    public UserDetailsService userDetailsService(){
        //暂时从内存中查询用户信息
        InMemoryUserDetailsManager manager=new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1","p2").build());
        manager.createUser(User.withUsername("lisi").password("123").authorities("p2").build());
        return manager;
    }

    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();//这是 比较字符串，没有任何加密算法的那种
    }


    //安全拦截机制
   @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1")//访问/r/r1 需要有p1的权限
                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated()// /r/** 请求必须认证通过
       .anyRequest().permitAll()//除了 /r/** 请求，其他的请求可以访问
       .and()
                .formLogin()//允许表单登录
       .successForwardUrl("/login-success");//自定义登录成功的页面地址
    }
}
