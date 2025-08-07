/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories.impl;

import com.dgmh.pojo.NguoiDung;
import com.dgmh.pojo.PhienDauGia;
import com.dgmh.pojo.TheoDoiSanPham;
import com.dgmh.repositories.TheoDoiSanPhamRepository;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
public class TheoDoiSanPhamRepositoryImpl implements TheoDoiSanPhamRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean kiemTraTheoDoi(int nguoiDungId, int phienDauGiaId) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM TheoDoiSanPham WHERE nguoiDung.id = :uid AND phienDauGia.id = :pid");
        q.setParameter("uid", nguoiDungId);
        q.setParameter("pid", phienDauGiaId);
        return ((Long) q.getSingleResult()) > 0;
    }

    @Override
    public void themTheoDoi(int nguoiDungId, int phienDauGiaId) {
        Session s = factory.getObject().getCurrentSession();
        TheoDoiSanPham td = new TheoDoiSanPham();

        NguoiDung nd = s.get(NguoiDung.class, nguoiDungId);
        PhienDauGia pdg = s.get(PhienDauGia.class, phienDauGiaId);

        td.setNguoiDung(nd);
        td.setPhienDauGia(pdg);
        td.setNgayTheoDoi(new Date());
        s.save(td);
    }

    @Override
    public void xoaTheoDoi(int nguoiDungId, int phienDauGiaId) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("DELETE FROM TheoDoiSanPham WHERE nguoiDung.id = :uid AND phienDauGia.id = :pid");
        q.setParameter("uid", nguoiDungId);
        q.setParameter("pid", phienDauGiaId);
        q.executeUpdate();
    }

    @Override
    public List<TheoDoiSanPham> layTheoDoiTheoNguoiDung(int nguoiDungId) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM TheoDoiSanPham WHERE nguoiDung.id = :uid");
        q.setParameter("uid", nguoiDungId);
        return q.getResultList();
    }
}
