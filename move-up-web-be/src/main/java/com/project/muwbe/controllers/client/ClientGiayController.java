package com.project.muwbe.controllers.client;
import com.project.muwbe.dtos.responses.GiayList;
import com.project.muwbe.entities.*;
import com.project.muwbe.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/client/giay")
@CrossOrigin(origins = "*")
public class ClientGiayController {
    @Autowired
    private GiayRepository giayRepository;

    @GetMapping("/productPage")
    public ResponseEntity<?> productPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "30") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(direction, sortBy));
        Page<Giay> list = giayRepository.findAll(pageable);
        Page<GiayList> results = list.map(GiayList::new);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/dashboardProduct")
    public ResponseEntity<?> dashboardProduct(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(Sort.Order.desc("uuTien"), new Sort.Order(direction, sortBy));

        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<Giay> list = giayRepository.findAll(pageable);
        Page<GiayList> results = list.map(GiayList::new);

        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Giay giay = giayRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy giày với ID: " + id));
            GiayList result = new GiayList(giay);
            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + e.getMessage());
        }
    }
}
