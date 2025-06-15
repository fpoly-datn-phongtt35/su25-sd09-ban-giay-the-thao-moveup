package com.project.muwbe.controllers;

import com.project.muwbe.dtos.responses.AdminGiayList;
import com.project.muwbe.entities.Giay;
import com.project.muwbe.repositories.GiayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/giay")
@CrossOrigin(origins = "*")
public class GiayController {
    @Autowired
    private GiayRepository giayRepository;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "50") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(direction, sortBy));
        Page<Giay> list = giayRepository.findAll(pageable);
        Page<AdminGiayList> results = list.map(AdminGiayList::new);
        return ResponseEntity.ok(results);
    }
}
