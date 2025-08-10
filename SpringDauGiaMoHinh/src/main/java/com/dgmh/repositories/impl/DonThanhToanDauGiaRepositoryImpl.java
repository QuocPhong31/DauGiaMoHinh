/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories.impl;

import com.dgmh.pojo.DonThanhToanDauGia;
import com.dgmh.pojo.NguoiDung;
import com.dgmh.pojo.PhienDauGia;
import com.dgmh.repositories.DonThanhToanDauGiaRepository;
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
public class DonThanhToanDauGiaRepositoryImpl implements DonThanhToanDauGiaRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    private Session s() { return factory.getObject().getCurrentSession(); }

    @Override
    public DonThanhToanDauGia findByPhien(PhienDauGia p) {
        String hql = "FROM DonThanhToanDauGia d WHERE d.phienDauGia = :p";
        return s().createQuery(hql, DonThanhToanDauGia.class)
                  .setParameter("p", p)
                  .uniqueResult();
    }

    @Override
    public List<DonThanhToanDauGia> findByNguoiMua(NguoiDung u) {
        String hql = "FROM DonThanhToanDauGia d WHERE d.nguoiMua = :u ORDER BY d.ngayTao DESC";
        return s().createQuery(hql, DonThanhToanDauGia.class)
                  .setParameter("u", u)
                  .getResultList();
    }

    @Override
    public DonThanhToanDauGia add(DonThanhToanDauGia d) {
        s().persist(d);
        return d;
    }

    @Override
    public DonThanhToanDauGia update(DonThanhToanDauGia d) {
        s().merge(d);
        return d;
    }
}
