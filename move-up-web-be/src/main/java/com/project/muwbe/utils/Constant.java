package com.project.muwbe.utils;

public class Constant {

    // Thời gian sống của cookie (5 giờ)
    public static final int MAX_AGE_COOKIE = 5 * 60 * 60;

    // Trạng thái tài khoản
    public static final String ACTIVE = "ACTIVE";
    public static final String INACTIVE = "INACTIVE";
    public static final String PENDING = "PENDING";
    public static final String BLOCKED = "BLOCKED";

    // Vai trò
    public static final String ROLE_CLIENT = "ROLE_CLIENT";
    public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    // Thông báo
    public static final String LOGIN_SUCCESS = "Đăng nhập thành công";
    public static final String LOGIN_FAILED = "Email hoặc mật khẩu không chính xác";
    public static final String REGISTER_SUCCESS = "Đăng ký thành công";
    public static final String EMAIL_EXISTED = "Email đã được sử dụng";
    public static final String ACCOUNT_BLOCKED = "Tài khoản đã bị khóa hoặc chưa được kích hoạt";
    public static final String LOGOUT_SUCCESS = "Đăng xuất thành công";
    public static final String UNAUTHORIZED = "Chưa đăng nhập";
    public static final String SYSTEM_ERROR = "Lỗi hệ thống";
}
