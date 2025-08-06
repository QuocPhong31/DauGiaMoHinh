/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories.impl;

import com.dgmh.pojo.TheoDoiSanPham;
import com.dgmh.repositories.TheoDoiSanPhamRepository;
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
    public TheoDoiSanPham theoDoi(int nguoiDungId, int phienId) {
        Session session = factory.getObject().getCurrentSession();

        // Kiểm tra đã theo dõi chưa
        Query<Long> q = session.createQuery(
            "SELECT COUNT(*) FROM TheoDoiSanPham WHERE nguoiDung.id = :uid AND phienDauGia.id = :pid", Long.class);
        q.setParameter("uid", nguoiDungId);
        q.setParameter("pid", phienId);

        Long count = q.uniqueResult();
        if (count != null && count > 0)
            return null;

        TheoDoiSanPham t = new TheoDoiSanPham();

        // Lấy các entity liên quan
        t.setNguoiDung(session.get(com.dgmh.pojo.NguoiDung.class, nguoiDungId));
        t.setPhienDauGia(session.get(com.dgmh.pojo.PhienDauGia.class, phienId));
        t.setNgayTheoDoi(new java.util.Date());

        session.persist(t);
        return t;
    }

    @Override
    public boolean boTheoDoi(int nguoiDungId, int phienId) {
        Session session = factory.getObject().getCurrentSession();
        Query<?> q = session.createQuery(
            "DELETE FROM TheoDoiSanPham WHERE nguoiDung.id = :uid AND phienDauGia.id = :pid");
        q.setParameter("uid", nguoiDungId);
        q.setParameter("pid", phienId);
        return q.executeUpdate() > 0;
    }

    @Override
    public List<TheoDoiSanPham> getTheoDoiByNguoiDung(int nguoiDungId) {
        Session session = factory.getObject().getCurrentSession();
        Query<TheoDoiSanPham> q = session.createQuery(
            "FROM TheoDoiSanPham WHERE nguoiDung.id = :uid ORDER BY ngayTheoDoi DESC", TheoDoiSanPham.class);
        q.setParameter("uid", nguoiDungId);
        return q.getResultList();
    }

    @Override
    public boolean isDangTheoDoi(int nguoiDungId, int phienId) {
        Session session = factory.getObject().getCurrentSession();
        Query<Long> q = session.createQuery(
            "SELECT COUNT(*) FROM TheoDoiSanPham WHERE nguoiDung.id = :uid AND phienDauGia.id = :pid", Long.class);
        q.setParameter("uid", nguoiDungId);
        q.setParameter("pid", phienId);
        Long count = q.uniqueResult();
        return count != null && count > 0;
    }
}
