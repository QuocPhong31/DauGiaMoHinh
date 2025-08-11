/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories.impl;

import com.dgmh.pojo.PhienDauGia;
import com.dgmh.pojo.PhienDauGiaNguoiDung;
import com.dgmh.repositories.PhienDauGiaRepository;
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
public class PhienDauGiaRepositoryImpl implements PhienDauGiaRepository {

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

    @Override
    public PhienDauGia capNhatPhien(PhienDauGia p) {
        Session session = this.factory.getObject().getCurrentSession();
        session.update(p); // hoặc session.merge(p);
        return p;
    }

    @Override
    public boolean capNhatKetQuaPhien(int phienId) {
        Session session = this.factory.getObject().getCurrentSession();

        // Lấy phiên đấu giá
        PhienDauGia phien = session.get(PhienDauGia.class, phienId);
        if (phien == null) {
            return false;
        }

        // Kiểm tra nếu đã kết thúc và chưa có giá chốt
        Date now = new Date();
        if (phien.getThoiGianKetThuc().before(now) && phien.getGiaChot() == null) {
            // Lấy người có giá cao nhất
            String hql = "FROM PhienDauGiaNguoiDung WHERE phienDauGia.id = :phienId ORDER BY giaDau DESC";
            Query<PhienDauGiaNguoiDung> q = session.createQuery(hql, PhienDauGiaNguoiDung.class);
            q.setParameter("phienId", phienId);
            q.setMaxResults(1);
            List<PhienDauGiaNguoiDung> result = q.getResultList();

            if (!result.isEmpty()) {
                PhienDauGiaNguoiDung topBid = result.get(0);

                phien.setGiaChot(topBid.getGiaDau());
                phien.setNguoiThangDauGia(topBid.getNguoiDung());
            }
            // Cập nhật trạng thái kết thúc dù có người đặt hay không
            phien.setTrangThai("da_ket_thuc");
            session.update(phien);
            return true;
        }

        return false;
    }

    @Override
    public List<com.dgmh.dto.QuanLyBaiDauDTO> getBaiDauCuaNguoiBan(int sellerId) {
        Session s = this.factory.getObject().getCurrentSession();

        String hql = """
        select new com.dgmh.dto.QuanLyBaiDauDTO(
            p.id,
            sp.id,
            sp.tenSanPham,
            sp.hinhAnh,
            sp.giaKhoiDiem,
            coalesce(
                (case
                    when p.trangThai = 'da_ket_thuc' and p.giaChot is not null then p.giaChot
                    else (select max(x.giaDau) from PhienDauGiaNguoiDung x where x.phienDauGia = p)
                 end),
                sp.giaKhoiDiem
            ),
            p.thoiGianBatDau,
            p.thoiGianKetThuc,
            sp.trangThai,
            p.trangThai,
            p.giaChot,
            win.username,
            pay.trangThai
        )
        from PhienDauGia p
            join p.sanPham sp
            left join p.nguoiThangDauGia win
            left join DonThanhToanDauGia pay on pay.phienDauGia = p
        where sp.nguoiDung.id = :sellerId
        order by p.thoiGianBatDau desc
    """;

        Query<com.dgmh.dto.QuanLyBaiDauDTO> q
                = s.createQuery(hql, com.dgmh.dto.QuanLyBaiDauDTO.class);
        q.setParameter("sellerId", sellerId);
        return q.getResultList();
    }

}
