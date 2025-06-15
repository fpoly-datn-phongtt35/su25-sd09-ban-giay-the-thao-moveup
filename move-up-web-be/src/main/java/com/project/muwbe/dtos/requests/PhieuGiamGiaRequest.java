package com.project.muwbe.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhieuGiamGiaRequest {

    @NotNull(message = "Mã giảm giá không được để trống!")
    private String maGiamGia;

    @NotNull(message = "Têm giảm giá không được để trống!")
    private String tenGiamGia;

    @NotNull(message = "Phần trăm giảm không được để trống!")
    private Integer phanTramGiam;

    @NotNull(message = "Giá tiền giảm không được để trống!")
    private Long giaTienGiam;

    @NotNull(message = "Giá trị tối thiểu không được để trống!")
    private Long giaTriToiThieu;

    @NotNull(message = "Giá trị tối đa không được để trống!")
    private Long giaTriToiDa;

    @NotNull(message = "Số lượng không được để trống!")
    private Integer soLuong;

    @NotNull(message = "Ngày bắt đầu không được để trống!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayBatDau;

    @NotNull(message = "Ngày kết thúc không được để trống!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayKetThuc;

    @NotNull(message = "Trạng thái không được để trống!")
    private Boolean trangThai;

    @NotNull(message = "Mô tả không được để trống!")
    private String moTa;

    private Long ngayTao;

    private Long nguoiTao;

    private Long ngayCapNhat;

    private Long nguoiCapNhat;
}
