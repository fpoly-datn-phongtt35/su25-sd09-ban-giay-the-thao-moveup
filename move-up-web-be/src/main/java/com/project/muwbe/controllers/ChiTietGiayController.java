package com.project.muwbe.controllers;

import com.project.muwbe.dtos.responses.AdminChiTietGiay;
import com.project.muwbe.dtos.responses.AdminGiay;
import com.project.muwbe.entities.ChiTietGiay;
import com.project.muwbe.entities.Giay;
import com.project.muwbe.repositories.ChiTietGiayRepository;
import com.project.muwbe.repositories.GiayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chi-tiet-giay")
@CrossOrigin(origins = "*")
public class ChiTietGiayController {
    @Autowired
    private ChiTietGiayRepository chiTietGiayRepository;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "50") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(direction, sortBy));
        Page<ChiTietGiay> list = chiTietGiayRepository.findAll(pageable);
        Page<AdminChiTietGiay> results = list.map(AdminChiTietGiay::new);
        return ResponseEntity.ok(results);
    }
}
