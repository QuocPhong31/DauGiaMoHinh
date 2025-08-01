/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories.impl;

import com.cloudinary.utils.ObjectUtils;
import com.dgmh.pojo.SanPham;
import com.cloudinary.Cloudinary;
import com.dgmh.pojo.LoaiSanPham;
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
    public SanPham addSanPham(Map<String, String> params, MultipartFile avatar) {
        // Tạo đối tượng SanPham từ thông tin trong params
        SanPham sanPham = new SanPham();
        sanPham.setTenSanPham(params.get("tenSanPham"));
        sanPham.setMoTa(params.get("moTa"));
        sanPham.setGiaKhoiDiem(new BigDecimal(params.get("giaKhoiDiem")));
        sanPham.setBuocNhay(new BigDecimal(params.getOrDefault("buocNhay", "0")));  // Mặc định 0 nếu không có
        sanPham.setTrangThai(params.get("trangThai"));
        
        // Lấy ID của loại sản phẩm từ params và truy vấn đối tượng LoaiSanPham
        String loaiSanPhamId = params.get("loaiSanPham");
        if (loaiSanPhamId != null) {
            // Truy vấn LoaiSanPham theo ID
            Session session = this.factory.getObject().getCurrentSession();
            LoaiSanPham loaiSanPham = session.get(LoaiSanPham.class, Integer.parseInt(loaiSanPhamId));
            sanPham.setLoaiSanPham(loaiSanPham);  // Gán LoaiSanPham vào SanPham
        }
        
        // Nếu có avatar, xử lý tải ảnh lên Cloudinary
        if (avatar != null && !avatar.isEmpty()) {
            try {
                // Tải ảnh lên Cloudinary
                Map uploadResult = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                // Lấy URL ảnh và gán vào đối tượng sanPham
                sanPham.setHinhAnh(uploadResult.get("secure_url").toString());
            } catch (Exception e) {
                e.printStackTrace();
                // Bạn có thể xử lý lỗi ở đây (ví dụ: bỏ qua ảnh nếu có lỗi)
            }
        }

        // Lưu sản phẩm vào cơ sở dữ liệu
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
