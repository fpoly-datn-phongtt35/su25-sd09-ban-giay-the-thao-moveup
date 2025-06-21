package com.project.muwbe.dtos.responses;

import com.project.muwbe.entities.DanhMuc;
import com.project.muwbe.entities.DanhMucCon;
import com.project.muwbe.entities.TaiKhoan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonGiay {
    private Long id;
    private String tenGiay;
    private String moTaGiay;
    private Boolean trangThai;
    private DanhMuc danhMuc;
    private DanhMucCon danhMucCon;
    private String thuongHieu;
    private String chatLieu;
    private String xuatXu;
    private String kieuDang;
    private String tuKhoa;
    private Integer uuTien;
    private Timestamp ngayTao;
    private TaiKhoan nguoiTao;
    private Timestamp ngayCapNhat;
    private TaiKhoan nguoiCapNhat;
    private List<AnhGiayList> anhGiay;
}
