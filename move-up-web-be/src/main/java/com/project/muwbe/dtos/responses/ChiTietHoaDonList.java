package com.project.muwbe.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChiTietHoaDonList {
    private Long id;
    private HoaDonChiTietGiay chiTietGiay;
    private Integer soLuong;
    private Long thanhTien;
}

