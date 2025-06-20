package com.project.muwbe.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.muwbe.entities.TaiKhoan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DangNhapList {
    private Long id;
    private String email;
    @JsonIgnore
    private String matKhau;
    private Boolean vaiTro; // true = khách hàng, false = nhân viên
    private String trangThai;

    public DangNhapList(TaiKhoan entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.matKhau = entity.getMatKhau();
        this.vaiTro = entity.getVaiTro();
        this.trangThai = entity.getTrangThai();
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = null;
    }
}
