/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services.impl;

import com.dgmh.pojo.PhienDauGiaNguoiDung;
import com.dgmh.repositories.PhienDauGiaNguoiDungRepository;
import com.dgmh.services.PhienDauGiaNguoiDungService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tran Quoc Phong
 */
@Service
public class PhienDauGiaNguoiDungServiceImpl implements PhienDauGiaNguoiDungService{
    @Autowired
    private PhienDauGiaNguoiDungRepository phienDauGiaNguoiDungRepository;

    @Override
    public PhienDauGiaNguoiDung datGia(PhienDauGiaNguoiDung p) {
        return phienDauGiaNguoiDungRepository.datGia(p);
    }

    @Override
    public List<PhienDauGiaNguoiDung> getLichSuByPhien(int phienId) {
        return phienDauGiaNguoiDungRepository.getLichSuByPhien(phienId);
    }

    @Override
    public List<PhienDauGiaNguoiDung> getLichSuByNguoiDung(int nguoiDungId) {
        return phienDauGiaNguoiDungRepository.getLichSuByNguoiDung(nguoiDungId);
    }

    @Override
    public PhienDauGiaNguoiDung getGiaCaoNhat(int phienId) {
        return phienDauGiaNguoiDungRepository.getGiaCaoNhat(phienId);
    }
    
    @Override
    public List<PhienDauGiaNguoiDung> getByPhien(int phienId) {
        return phienDauGiaNguoiDungRepository.getByPhien(phienId);
    }
}
