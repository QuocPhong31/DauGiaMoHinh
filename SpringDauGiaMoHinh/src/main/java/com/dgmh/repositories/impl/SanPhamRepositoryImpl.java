/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories.impl;

import com.cloudinary.utils.ObjectUtils;
import com.dgmh.pojo.SanPham;
import com.cloudinary.Cloudinary;
import com.dgmh.pojo.LoaiSanPham;
import com.dgmh.pojo.NguoiDung;
import com.dgmh.repositories.SanPhamRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tran Quoc Phong
 */
@Repository
@Transactional
public class SanPhamRepositoryImpl implements SanPhamRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public SanPham addSanPham(String tenSanPham, String moTa, BigDecimal giaKhoiDiem, BigDecimal buocNhay, BigDecimal giaBua, int loaiSanPhamId, String username, MultipartFile avatar) {
        Session session = this.factory.getObject().getCurrentSession();
        // Tạo đối tượng SanPham từ thông tin trong params
        SanPham sanPham = new SanPham();
        sanPham.setTenSanPham(tenSanPham);
        sanPham.setMoTa(moTa);
        sanPham.setGiaKhoiDiem(giaKhoiDiem);
        sanPham.setBuocNhay(buocNhay);
        sanPham.setGiaBua(giaBua);
        sanPham.setNgayDang(new java.util.Date());
        sanPham.setTrangThai("CHO_DUYET");

        // Lấy đối tượng LoaiSanPham từ ID
        LoaiSanPham loaiSanPham = session.get(LoaiSanPham.class, loaiSanPhamId);
        sanPham.setLoaiSanPham(loaiSanPham);

        // Lấy đối tượng NguoiDung từ username
        Query<NguoiDung> query = session.createQuery("FROM NguoiDung WHERE username = :un", NguoiDung.class);
        query.setParameter("un", username);
        NguoiDung nguoiDung = query.uniqueResult(); 
        sanPham.setNguoiDung(nguoiDung);
        
        System.out.println("SanPham debug:");
        System.out.println("Ten: " + tenSanPham);
        System.out.println("NguoiDung: " + (nguoiDung != null ? nguoiDung.getUsername() : "NULL"));
        System.out.println("LoaiSanPham: " + (loaiSanPham != null ? loaiSanPham.getTenLoai() : "NULL"));

        // Upload ảnh nếu có
        if (avatar != null && !avatar.isEmpty()) {
            try {
                Map uploadResult = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                sanPham.setHinhAnh(uploadResult.get("secure_url").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Lưu sản phẩm vào DB
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
    public boolean updateTrangThai(int id, String trangThai) {
        Session session = this.factory.getObject().getCurrentSession();
        SanPham sanPham = session.get(SanPham.class, id);
        if (sanPham != null) {
            sanPham.setTrangThai(trangThai);  
            session.update(sanPham);
            return true;
        }
        return false;
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
