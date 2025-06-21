package com.project.muwbe.dtos.requests;

import com.project.muwbe.entities.TaiKhoan;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminDanhMucForm {
    private String tenDanhMuc;
    private Boolean trangThai;

    private Date ngayTao;
    private Long nguoiTao;
    private Date ngayCapNhat;
    private Long nguoiCapNhat;
}
