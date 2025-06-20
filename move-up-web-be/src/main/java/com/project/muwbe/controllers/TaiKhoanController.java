package com.project.muwbe.controllers;

import com.project.muwbe.dtos.requests.DangNhapForm;
import com.project.muwbe.dtos.responses.DangNhapList;
import com.project.muwbe.entities.TaiKhoan;
import com.project.muwbe.repositories.TaiKhoanRepository;
import com.project.muwbe.utils.Constant;
import com.project.muwbe.utils.JwtTokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/tai-khoan")
@CrossOrigin(origins = "*")
public class TaiKhoanController {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/dang-nhap")
    public ResponseEntity<?> login(@Valid @RequestBody DangNhapForm form, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(form.getEmail(), form.getMatKhau())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            TaiKhoan taiKhoan = taiKhoanRepository.findByEmail(form.getEmail())
                    .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));

            String token = jwtTokenUtil.generateToken(
                    taiKhoan.getEmail(),
                    authentication.getAuthorities()
            );

            Cookie cookie = new Cookie("JWT_TOKEN", token);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(Constant.MAX_AGE_COOKIE);
            cookie.setPath("/");
            response.addCookie(cookie);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("token", token);
            responseBody.put("user", new DangNhapList(taiKhoan));

            return ResponseEntity.ok(responseBody);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sai email hoặc mật khẩu");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi hệ thống: " + e.getMessage());
        }
    }
}
