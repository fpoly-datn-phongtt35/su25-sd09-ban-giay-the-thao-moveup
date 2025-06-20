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
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @PostMapping("/dang-nhap")
//    public ResponseEntity<?> login(@Valid @RequestBody DangNhapForm form, HttpServletResponse response) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(form.getEmail(), form.getMatKhau())
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            TaiKhoan taiKhoan = taiKhoanRepository.findByEmail(form.getEmail())
//                    .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));
//
//            String token = jwtTokenUtil.generateToken(
//                    taiKhoan.getEmail(),
//                    authentication.getAuthorities()
//            );
//
//            Cookie cookie = new Cookie("JWT_TOKEN", token);
//            cookie.setHttpOnly(true);
//            cookie.setMaxAge(Constant.MAX_AGE_COOKIE);
//            cookie.setPath("/");
//            response.addCookie(cookie);
//
//            Map<String, Object> responseBody = new HashMap<>();
//            responseBody.put("token", token);
//            responseBody.put("user", new DangNhapList(taiKhoan));
//
//            return ResponseEntity.ok(responseBody);
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body("Sai email hoặc mật khẩu");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Lỗi hệ thống: " + e.getMessage());
//        }
//    }

    @PostMapping("/dang-nhap")
    public ResponseEntity<?> login(@Valid @RequestBody DangNhapForm form, HttpServletResponse response) {
        try {
            // Xác thực thông tin đăng nhập
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(form.getEmail(), form.getMatKhau())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Lấy thông tin tài khoản
            TaiKhoan taiKhoan = taiKhoanRepository.findByEmail(form.getEmail())
                    .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));

            // Kiểm tra trạng thái tài khoản
            if (!Constant.ACTIVE.equalsIgnoreCase(taiKhoan.getTrangThai())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(Map.of(
                                "success", false,
                                "message", Constant.ACCOUNT_BLOCKED
                        ));
            }

            // Tạo JWT token
            String token = jwtTokenUtil.generateToken(
                    taiKhoan.getEmail(),
                    authentication.getAuthorities()
            );

            // Thiết lập cookie
            Cookie cookie = new Cookie("JWT_TOKEN", token);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(Constant.MAX_AGE_COOKIE);
            cookie.setPath("/");
            cookie.setSecure(false); // Đặt true nếu sử dụng HTTPS
            response.addCookie(cookie);

            // Tạo response data
            DangNhapList userResponse = new DangNhapList(taiKhoan);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("success", true);
            responseBody.put("message", Constant.LOGIN_SUCCESS);
            responseBody.put("token", token);
            responseBody.put("user", userResponse);

            return ResponseEntity.ok(responseBody);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "success", false,
                            "message", Constant.LOGIN_FAILED
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "success", false,
                            "message", Constant.SYSTEM_ERROR + ": " + e.getMessage()
                    ));
        }
    }

    @PostMapping("/dang-ky")
    public ResponseEntity<?> register(@Valid @RequestBody DangNhapForm form) {
        try {
            // Validate input
            if (form.getEmail() == null || form.getEmail().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of(
                                "success", false,
                                "message", "Email không được để trống"
                        ));
            }

            if (form.getMatKhau() == null || form.getMatKhau().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of(
                                "success", false,
                                "message", "Mật khẩu không được để trống"
                        ));
            }

            if (form.getMatKhau().length() < 4) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of(
                                "success", false,
                                "message", "Mật khẩu phải có ít nhất 4 ký tự"
                        ));
            }

            // Kiểm tra email đã tồn tại
            if (taiKhoanRepository.findByEmail(form.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of(
                                "success", false,
                                "message", Constant.EMAIL_EXISTED
                        ));
            }

            // Tạo tài khoản mới
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setEmail(form.getEmail().trim().toLowerCase());
            taiKhoan.setMatKhau(passwordEncoder.encode(form.getMatKhau()));
            taiKhoan.setVaiTro(form.getVaiTro() != null ? form.getVaiTro() : true); // Mặc định là khách hàng
            taiKhoan.setTrangThai(Constant.ACTIVE);

            // Lưu vào database
            taiKhoan = taiKhoanRepository.save(taiKhoan);

            // Tạo response data - trả về dữ liệu vừa tạo
            DangNhapList responseData = new DangNhapList(taiKhoan);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("success", true);
            responseBody.put("message", Constant.REGISTER_SUCCESS);
            responseBody.put("user", responseData);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "success", false,
                            "message", Constant.SYSTEM_ERROR + ": " + e.getMessage()
                    ));
        }
    }

}
