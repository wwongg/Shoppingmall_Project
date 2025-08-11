package Shopping.Book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth)->{
            //모든 이용자가 접근가능
            auth.requestMatchers("/").permitAll();
            auth.requestMatchers("/salad").permitAll();
            auth.requestMatchers("/mypage").hasRole("USER");
            auth.requestMatchers("/member/insert").permitAll();
            auth.requestMatchers("/member/list").hasRole("ADMIN");
            auth.requestMatchers("/member/detail").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/member/insert").permitAll();
            auth.requestMatchers("/member/update").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/member/delete").hasAnyRole("USER", "ADMIN");
            //auth.requestMatchers("/cart/**").permitAll();
            auth.requestMatchers("/cart/list").permitAll();
            auth.requestMatchers("/cart/insert/**").permitAll();
            auth.requestMatchers("/cart/update").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/cart/delete").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/order/insert/**").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/order/list").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/order/detail").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/order/delete").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/board/**").permitAll();
            auth.requestMatchers("/board/list").permitAll();
            auth.requestMatchers("/board/detail").permitAll();
            auth.requestMatchers("/board/insert").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/board/update").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/board/delete").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/boardcmt/insert").permitAll();
            auth.requestMatchers("/boardcmt/update").permitAll();
            auth.requestMatchers("/boardcmt/delete").permitAll();
            auth.requestMatchers("/info/**").permitAll();
            auth.requestMatchers("/notice/list").permitAll();
            auth.requestMatchers("/notice/detail").permitAll();
            auth.requestMatchers("/notice/insert").hasRole("ADMIN");
            auth.requestMatchers("/notice/update").hasRole("ADMIN");
            auth.requestMatchers("/notice/delete").hasRole("ADMIN");
            auth.requestMatchers("/salad/list").permitAll();
            auth.requestMatchers("/product/list").permitAll();
            auth.requestMatchers("/product/detail").permitAll();
            auth.requestMatchers("/product/insert").permitAll();
            auth.requestMatchers("/product/update").hasRole("ADMIN");
            auth.requestMatchers("/product/delete").hasRole("ADMIN");
            auth.requestMatchers("/product/admin").hasRole("ADMIN");
            auth.requestMatchers("/review/list").permitAll();
            auth.requestMatchers("/review/detail").permitAll();
            auth.requestMatchers("/review/insert").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/review/update").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/review/delete").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/reviewcmt/insert").permitAll();
            auth.requestMatchers("/reviewcmt/update").permitAll();
            auth.requestMatchers("/reviewcmt/delete").permitAll();
            auth.requestMatchers("/login","/logout","/find/email","/find/password").permitAll();
            auth.requestMatchers("/css/**", "/js/**", "/image/**", "/images/**").permitAll();
        });

        http.formLogin(login-> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("memberEmail")
                .passwordParameter("memberPassword")
                //로그인 성공 시 index.html 로 이동
                .defaultSuccessUrl("/", true)
                //로그인 실패
                .failureUrl("/login?error=true")
                .permitAll()
        );

        http.logout(logout->logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                //로그아웃 시 사용자 세션 삭제
                .invalidateHttpSession(true)
        );

        //사용자 인증처리 컴포넌트 서비스 등록
        //http.userDetailsService(loginService);
        //csrf 사용X
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
