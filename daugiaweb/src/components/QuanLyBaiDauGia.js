import React, { useEffect, useState } from "react";
import { Container, Card, Row, Col, Badge, Button, Spinner } from "react-bootstrap";
import { Link } from "react-router-dom";
import { authApis, endpoints } from "../configs/Apis";

const badge = (text, variant) => <Badge bg={variant} className="ms-1">{text}</Badge>;

const QuanLyBaiDauGia = () => {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const run = async () => {
      try {
        const res = await authApis().get(endpoints["seller-auctions"]);
        setItems(res.data || []);
      } catch (e) {
        console.error("Lỗi tải danh sách quản lý:", e);
      } finally {
        setLoading(false);
      }
    };
    run();
  }, []);

  if (loading) return <div className="text-center mt-5"><Spinner animation="border" /></div>;

  if (!items.length) return (
    <Container className="mt-4">
      <h3 className="mb-3">Quản lý bài đấu</h3>
      <p>Chưa có bài/phiên nào.</p>
    </Container>
  );

  return (
    <Container className="mt-4">
      <h3 className="mb-4">Quản lý bài đấu</h3>
      <Row>
        {items.map(it => {
          const daKetThuc = it.trangThaiPhien === "da_ket_thuc";
          const daDuyet = it.trangThaiSanPham === "DUYET";
          const coNguoiThang = !!it.nguoiThangUsername;
          const giaHienTai = it.giaHienTai ?? it.giaKhoiDiem;

          return (
            <Col key={it.phienId} xs={12} className="mb-4">
              <Card className="p-3 shadow-sm">
                <Row>
                  <Col md={3} className="d-flex align-items-center justify-content-center">
                    <Card.Img
                      src={it.hinhAnh || "https://via.placeholder.com/300x200"}
                      alt="Hình"
                      style={{ width: "100%", maxHeight: 180, objectFit: "cover", borderRadius: 8 }}
                    />
                  </Col>
                  <Col md={9}>
                    <div className="d-flex align-items-center">
                      <h5 className="fw-bold mb-0">{it.tenSanPham}</h5>
                      {badge(daDuyet ? "ĐÃ DUYỆT" : it.trangThaiSanPham, daDuyet ? "success" : "secondary")}
                      {badge(daKetThuc ? "ĐÃ KẾT THÚC" : "ĐANG DIỄN RA", daKetThuc ? "dark" : "primary")}
                    </div>

                    <div className="mt-2">
                      <div><strong>Giá khởi điểm:</strong> {Number(it.giaKhoiDiem).toLocaleString()} đ</div>
                      <div><strong>Giá hiện tại:</strong> {Number(giaHienTai).toLocaleString()} đ</div>
                      <div>
                        <strong>Thời gian:</strong>{" "}
                        {new Date(it.thoiGianBatDau).toLocaleString("vi-VN")} →{" "}
                        {new Date(it.thoiGianKetThuc).toLocaleString("vi-VN")}
                      </div>

                      {daKetThuc && (
                        <>
                          <div className="mt-2">
                            <strong>Kết quả:</strong>{" "}
                            {coNguoiThang
                              ? <>Người thắng <strong>{it.nguoiThangUsername}</strong> với giá <strong>{Number(it.giaChot).toLocaleString()} đ</strong></>
                              : "Không có người thắng"}
                          </div>
                          {coNguoiThang && (
                            <div className="mt-1">
                              <strong>Thanh toán:</strong>{" "}
                              {it.trangThaiThanhToan
                                ? (it.trangThaiThanhToan === "PAID" ? badge("ĐÃ TRẢ", "success")
                                  : it.trangThaiThanhToan === "CANCELLED" ? badge("ĐÃ HỦY", "secondary")
                                  : badge("CHƯA TRẢ", "warning"))
                                : badge("CHƯA TRẢ", "warning")}
                            </div>
                          )}
                        </>
                      )}
                    </div>

                    <div className="mt-3">
                      <Link to={`/cuoc-dau-gia/${it.phienId}`}>
                        <Button size="sm">Xem chi tiết</Button>
                      </Link>
                    </div>
                  </Col>
                </Row>
              </Card>
            </Col>
          );
        })}
      </Row>
    </Container>
  );
};

export default QuanLyBaiDauGia;
