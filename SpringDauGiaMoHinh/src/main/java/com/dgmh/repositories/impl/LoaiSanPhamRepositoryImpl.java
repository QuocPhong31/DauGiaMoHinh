/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories.impl;

import com.dgmh.pojo.LoaiSanPham;
import com.dgmh.repositories.LoaiSanPhamRepository;
import org.hibernate.query.Query;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tran Quoc Phong
 */
@Repository
@Transactional
public class LoaiSanPhamRepositoryImpl implements LoaiSanPhamRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public LoaiSanPham add(LoaiSanPham loaiSanPham) {
        Session session = this.factory.getObject().getCurrentSession();
        session.persist(loaiSanPham);
        return loaiSanPham;
    }

    @Override
    public List<LoaiSanPham> getLoaiSanPhamHoatDong() {
        Session session = this.factory.getObject().getCurrentSession();
        String hql = "FROM LoaiSanPham WHERE trangThai IN ('HOAT_DONG', 'BI_KHOA')";
        Query<LoaiSanPham> query = session.createQuery(hql, LoaiSanPham.class);
        return query.getResultList();
    }

    @Override
    public boolean deleteLoaiSanPham(int id) {
        try {
            Session session = this.factory.getObject().getCurrentSession();
            LoaiSanPham loai = session.get(LoaiSanPham.class, id);
            if (loai != null) {
                session.delete(loai);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean khoaLoaiSanPham(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        LoaiSanPham loai = session.get(LoaiSanPham.class, id);
        if (loai != null) {
            loai.setTrangThai("BI_KHOA");
            session.update(loai);
            return true;
        }
        return false;
    }

    @Override
    public boolean moKhoaLoaiSanPham(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        LoaiSanPham loai = session.get(LoaiSanPham.class, id);
        if (loai != null) {
            loai.setTrangThai("HOAT_DONG");
            session.update(loai);
            return true;
        }
        return false;
    }
}
