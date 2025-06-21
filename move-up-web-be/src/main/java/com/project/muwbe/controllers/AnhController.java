package com.project.muwbe.controllers;

import com.project.muwbe.entities.Anh;
import com.project.muwbe.repositories.AnhRepository;
import org.hibernate.dialect.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/anh")
@CrossOrigin("*")
public class AnhController {

    @Value("${upload.dir:${user.dir}/media}")
    private String duongDanThuMuc;

    @Autowired
    private AnhRepository anhRepository;

    @PostMapping("/tai")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Không có file nào được tải lên.");
            }

            // Dùng biến cấu hình
            Path uploadPath = Paths.get(duongDanThuMuc);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Tạo tên file duy nhất
            String originalFileName = Paths.get(Objects.requireNonNull(file.getOriginalFilename())).getFileName().toString();
            String uniqueFileName = UUID.randomUUID() + "_" + originalFileName;
            Path filePath = uploadPath.resolve(uniqueFileName);

            // Ghi file lên đĩa
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Tạo bản ghi ảnh trong database
            Anh anh = new Anh();
            anh.setTenAnh(originalFileName);
            anh.setDuongDan("/media/" + uniqueFileName); // public access URL
            anh.setNgayTao(new Timestamp(System.currentTimeMillis()));

            Anh saved = anhRepository.save(anh);

            return ResponseEntity.ok(saved);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Tải ảnh thất bại: " + e.getMessage());
        }
    }
}
