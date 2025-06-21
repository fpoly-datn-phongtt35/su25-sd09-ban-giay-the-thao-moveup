package com.project.muwbe.dtos.responses;

import com.project.muwbe.entities.Anh;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonChiTietGiay {
    private Long id;
    private HoaDonGiay giay;
    private String sku;
    private Long giaNhap;
    private Long giaBan;
    private String mauSac;
    private Integer size;
    private Anh anh;
    private Long soLuong;
    private Boolean trangThai;
}
