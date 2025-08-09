/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories.impl;

import com.dgmh.pojo.PhienDauGiaNguoiDung;
import com.dgmh.repositories.PhienDauGiaNguoiDungRepository;
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
public class PhienDauGiaNguoiDungRepositoryImpl implements PhienDauGiaNguoiDungRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public PhienDauGiaNguoiDung datGia(PhienDauGiaNguoiDung p) {
        Session session = this.factory.getObject().getCurrentSession();
        session.persist(p);
        return p;
    }

    @Override
    public List<PhienDauGiaNguoiDung> getLichSuByPhien(int phienId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<PhienDauGiaNguoiDung> q = session.createQuery(
            "FROM PhienDauGiaNguoiDung WHERE phienDauGia.id = :phienId ORDER BY thoiGianDauGia DESC",
            PhienDauGiaNguoiDung.class);
        q.setParameter("phienId", phienId);
        return q.getResultList();
    }

    @Override
    public List<PhienDauGiaNguoiDung> getLichSuByNguoiDung(int nguoiDungId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<PhienDauGiaNguoiDung> q = session.createQuery(
            "FROM PhienDauGiaNguoiDung WHERE nguoiDung.id = :nguoiDungId ORDER BY thoiGianDauGia DESC",
            PhienDauGiaNguoiDung.class);
        q.setParameter("nguoiDungId", nguoiDungId);
        return q.getResultList();
    }

    @Override
    public PhienDauGiaNguoiDung getGiaCaoNhat(int phienId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<PhienDauGiaNguoiDung> q = session.createQuery(
            "FROM PhienDauGiaNguoiDung WHERE phienDauGia.id = :phienId ORDER BY giaDau DESC",
            PhienDauGiaNguoiDung.class);
        q.setParameter("phienId", phienId);
        q.setMaxResults(1);
        return q.uniqueResult();
    }
    
    @Override
    public List<PhienDauGiaNguoiDung> getByPhien(int phienId) {
        Session s = factory.getObject().getCurrentSession();
        String hql = """
            FROM PhienDauGiaNguoiDung pd
            WHERE pd.phienDauGia.id = :pid
            ORDER BY pd.giaDau DESC, pd.thoiGianDauGia ASC
        """;
        return s.createQuery(hql, PhienDauGiaNguoiDung.class)
                .setParameter("pid", phienId)
                .getResultList();
    }
}
