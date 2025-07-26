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
    public TheoDoiSanPham themTheoDoi(TheoDoiSanPham t) {
        Session session = this.factory.getObject().getCurrentSession();
        session.persist(t);
        return t;
    }

    @Override
    public boolean xoaTheoDoi(int nguoiDungId, int sanPhamId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<?> q = session.createQuery(
            "DELETE FROM TheoDoiSanPham WHERE nguoiDung.id = :uid AND sanPham.id = :sid");
        q.setParameter("uid", nguoiDungId);
        q.setParameter("sid", sanPhamId);
        return q.executeUpdate() > 0;
    }

    @Override
    public List<TheoDoiSanPham> layTheoDoiTheoNguoiDung(int nguoiDungId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<TheoDoiSanPham> q = session.createQuery(
            "FROM TheoDoiSanPham WHERE nguoiDung.id = :uid ORDER BY ngayTheoDoi DESC", TheoDoiSanPham.class);
        q.setParameter("uid", nguoiDungId);
        return q.getResultList();
    }

    @Override
    public boolean kiemTraDangTheoDoi(int nguoiDungId, int sanPhamId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<Long> q = session.createQuery(
            "SELECT COUNT(*) FROM TheoDoiSanPham WHERE nguoiDung.id = :uid AND sanPham.id = :sid", Long.class);
        q.setParameter("uid", nguoiDungId);
        q.setParameter("sid", sanPhamId);
        Long count = q.uniqueResult();
        return count != null && count > 0;
    }
}
