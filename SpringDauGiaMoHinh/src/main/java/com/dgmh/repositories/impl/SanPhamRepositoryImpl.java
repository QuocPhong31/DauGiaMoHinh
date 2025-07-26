/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories.impl;

import com.dgmh.pojo.SanPham;
import com.dgmh.repositories.SanPhamRepository;
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
public class SanPhamRepositoryImpl implements SanPhamRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public SanPham add(SanPham sanPham) {
        Session session = this.factory.getObject().getCurrentSession();
        session.persist(sanPham);
        return sanPham;
    }

    @Override
    public List<SanPham> getAll() {
        Session session = this.factory.getObject().getCurrentSession();
        Query<SanPham> q = session.createQuery("FROM SanPham", SanPham.class);
        return q.getResultList();
    }

    @Override
    public SanPham getById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(SanPham.class, id);
    }

    @Override
    public boolean delete(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        SanPham sp = session.get(SanPham.class, id);
        if (sp != null) {
            session.delete(sp);
            return true;
        }
        return false;
    }
}
