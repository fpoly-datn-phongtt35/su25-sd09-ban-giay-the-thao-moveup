package com.project.muwbe.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminDanhMucConForm {
    private Long idDanhMuc;
    private String tenDanhMucCon;
    private Boolean trangThai;

    private Date ngayTao;
    private Long nguoiTao;
    private Date ngayCapNhat;
    private Long nguoiCapNhat;
}
