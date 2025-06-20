package com.project.muwbe.configs;

import com.project.muwbe.entities.TaiKhoan;
import com.project.muwbe.repositories.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SercurityConfig {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            TaiKhoan taiKhoan = taiKhoanRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Tài khoản không tồn tại: " + email));
            String role = taiKhoan.getVaiTro() ? "ROLE_CLIENT" : "ROLE_EMPLOYEE";
            return User.withUsername(taiKhoan.getEmail())
                    .password(taiKhoan.getMatKhau())
                    .roles(role.replace("ROLE_", ""))
                    .disabled(!"ACTIVE".equalsIgnoreCase(taiKhoan.getTrangThai()))
                    .build();
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/tai-khoan/dang-nhap").permitAll()
                        .anyRequest().authenticated()
                )
                .securityContext(context -> context
                        .securityContextRepository(new HttpSessionSecurityContextRepository())
                );

        return http.build();
    }

}
