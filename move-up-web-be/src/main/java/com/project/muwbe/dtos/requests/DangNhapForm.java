package com.project.muwbe.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DangNhapForm {
    private Long id;
    private String email;
    private String matKhau;
    private Boolean vaiTro;
}
