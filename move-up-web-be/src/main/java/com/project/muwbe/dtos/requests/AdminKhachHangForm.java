package com.project.muwbe.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class AdminKhachHangForm {

    @NotNull(message = "Mã khách hàng không được để trống!")
    private String maKhachHang;

    private Long idTaiKhoan;

    @NotNull(message = "Họ tên không được để trống!")
    private String hoTen;

    @NotNull(message = "Số điện thoại không được để trống!")
    @Pattern(regexp = "^0[0-9]{9}$", message = "Số điện thoại không đúng định dạng!")
    private String soDienThoai;

    @NotNull(message = "Giới tính không được để trống!")
    private Boolean gioiTinh;

    @NotNull(message = "Ngày sinh không được để trống!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;

    private Long nguoiTao;
    private Long nguoiCapNhat;

    // Optional: dùng cho filter hoặc tìm kiếm nâng cao
    private String keyword;
}
