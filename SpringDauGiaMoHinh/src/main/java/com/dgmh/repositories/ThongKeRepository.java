/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dgmh.repositories;

/**
 *
 * @author ADMIN
 */
import com.dgmh.dto.ThongKeDTO;
import java.time.LocalDate;
import java.util.List;

public interface ThongKeRepository {
    List<ThongKeDTO> thongKeTheoNgay(LocalDate tuNgay, LocalDate denNgay);
    List<ThongKeDTO> thongKePhienDauGiaNgay(LocalDate tuNgay, LocalDate denNgay);
}
