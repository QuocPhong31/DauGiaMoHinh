/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.controllers;

import com.dgmh.pojo.NguoiDung;
import com.dgmh.services.NguoiDungService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tran Quoc Phong
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class ApiAdminController {
    @Autowired
    private NguoiDungService nguoiDungService;
    
    @GetMapping("/")
    public ResponseEntity<List<NguoiDung>> getAllUsers() {
        return ResponseEntity.ok(nguoiDungService.getAllUsers());
    }

    @PostMapping(path ="/",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NguoiDung> addUser(@RequestParam Map<String, String> params, 
                                             @RequestParam("avatar") MultipartFile avatar) {
        return new ResponseEntity<>(nguoiDungService.addUser(params, avatar), HttpStatus.CREATED);
    }
}
