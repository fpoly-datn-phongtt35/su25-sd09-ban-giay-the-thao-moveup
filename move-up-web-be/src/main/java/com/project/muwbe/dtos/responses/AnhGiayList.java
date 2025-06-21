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
public class AnhGiayList {
    private Long id;
    private Anh anh;
}
