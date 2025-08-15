/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services;

/**
 *
 * @author Tran Quoc Phong
 */
import com.dgmh.dto.ThongKeDTO;
import java.time.LocalDate;
import java.util.List;

public interface ThongKeService {
    List<ThongKeDTO> thongKeTheoNgay(LocalDate tuNgay, LocalDate denNgay);
}

