package com.project.muwbe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "chi_tiet_hoa_don")
public class ChiTietHoaDon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_hoa_don", referencedColumnName = "id")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "id_chi_tiet_giay", referencedColumnName = "id")
    private ChiTietGiay chiTietGiay;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "thanh_tien")
    private Long thanhTien;
}
