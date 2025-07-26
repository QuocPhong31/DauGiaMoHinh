/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories.impl;

import com.dgmh.pojo.PhienDauGia;
import com.dgmh.repositories.PhienDauGiaRepository;
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
public class PhienDauGiaRepositoryImpl implements PhienDauGiaRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public PhienDauGia themPhienDauGia(PhienDauGia p) {
        Session session = this.factory.getObject().getCurrentSession();
        session.persist(p);
        return p;
    }

    @Override
    public List<PhienDauGia> getLayTatCaPhien() {
        Session session = this.factory.getObject().getCurrentSession();
        Query<PhienDauGia> q = session.createQuery("FROM PhienDauGia", PhienDauGia.class);
        return q.getResultList();
    }

    @Override
    public PhienDauGia getLayPhienTheoId(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(PhienDauGia.class, id);
    }

    @Override
    public boolean capNhatTrangThai(int id, String trangThai) {
        Session session = this.factory.getObject().getCurrentSession();
        PhienDauGia p = session.get(PhienDauGia.class, id);
        if (p != null) {
            p.setTrangThai(trangThai);
            session.update(p);
            return true;
        }
        return false;
    }
}
