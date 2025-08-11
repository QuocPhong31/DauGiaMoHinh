// src/pages/DonThanhToan.js
import React, { useEffect, useState } from "react";
import { Container, Card, Row, Col, Form, Button, Badge, Alert } from "react-bootstrap";
import { authApis, endpoints } from "../configs/Apis";
import cookie from "react-cookies";

const TrangDonThanhToan = () => {
  const [orders, setOrders] = useState([]);
  const [message, setMessage] = useState({ type: "", text: "" });
  const [submittingId, setSubmittingId] = useState(null);

  const fetchOrders = async () => {
    try {
      const res = await authApis().get(endpoints["don-cua-toi"]);
      setOrders(res.data || []);
    } catch (e) {
      console.error("Lỗi tải đơn:", e);
    }
  };

  useEffect(() => { fetchOrders(); }, []);

  const setField = (id, field, value) => {
    setOrders((prev) => prev.map((d) => (d.id === id ? { ...d, [field]: value } : d)));
  };

  const handlePay = async (don) => {
    setSubmittingId(don.id);
    setMessage({ type: "", text: "" });

    const payload = {
      phuongThuc: don._phuongThuc || don.phuongThuc || "COD",
      hoTenNhan: don._hoTenNhan || "",
      soDienThoai: don._soDienThoai || "",
      diaChiNhan: don._diaChiNhan || "",
      ghiChu: don._ghiChu || "",
    };

    try {
      await authApis().put(
        endpoints["thanh-toan-don"](don.id),
        payload,
        { headers: { Authorization: `Bearer ${cookie.load("token")}` } }
      );
      setMessage({ type: "success", text: "Thanh toán thành công!" });
      await fetchOrders();
    } catch (e) {
      const msg = e?.response?.data || "Thanh toán thất bại!";
      setMessage({ type: "danger", text: msg });
    } finally {
      setSubmittingId(null);
    }
  };

  const pending = orders.filter(o => o.trangThai === "PENDING");

  return (
    <Container className="mt-4">
      <div className="d-flex align-items-center mb-3">
        <h3 className="mb-0">Hóa đơn đã thắng</h3>
        <Badge bg="warning" text="dark" className="ms-2">{pending.length} chờ thanh toán</Badge>
      </div>

      {message.text && <Alert variant={message.type}>{message.text}</Alert>}

      {orders.length === 0 ? (
        <div className="text-muted">Bạn chưa có hóa đơn nào.</div>
      ) : (
        <Row xs={1} md={2} className="g-3">
          {orders.map((d) => {
            const sp = d?.phienDauGia?.sanPham;
            const status = d.trangThai; // PENDING/PAID/CANCELLED
            return (
              <Col key={d.id}>
                <Card className="h-100 shadow-sm">
                  <Card.Body>
                    <div className="d-flex justify-content-between align-items-start">
                      <Card.Title className="mb-2" style={{maxWidth: '70%'}}>
                        {sp?.tenSanPham || `Phiên #${d?.phienDauGia?.id}`}
                      </Card.Title>
                      <Badge bg={
                        status === "PAID" ? "success" :
                        status === "PENDING" ? "warning" : "secondary"
                      }>
                        {status}
                      </Badge>
                    </div>

                    <div className="mb-2">
                      <strong>Giá phải trả:</strong> {Number(d.soTien || 0).toLocaleString("vi-VN")} đ
                    </div>
                    <div className="text-muted mb-3" style={{fontSize: 13}}>
                      Tạo lúc {new Date(d.ngayTao).toLocaleString("vi-VN")}
                    </div>

                    {status === "PENDING" ? (
                      <>
                        <Form>
                          <Row className="g-2">
                            <Col md={6}>
                              <Form.Group>
                                <Form.Label>Họ tên nhận</Form.Label>
                                <Form.Control
                                  value={d._hoTenNhan || ""}
                                  onChange={(e) => setField(d.id, "_hoTenNhan", e.target.value)}
                                />
                              </Form.Group>
                            </Col>
                            <Col md={6}>
                              <Form.Group>
                                <Form.Label>Số điện thoại</Form.Label>
                                <Form.Control
                                  value={d._soDienThoai || ""}
                                  onChange={(e) => setField(d.id, "_soDienThoai", e.target.value)}
                                />
                              </Form.Group>
                            </Col>
                          </Row>

                          <Form.Group className="mt-2">
                            <Form.Label>Địa chỉ nhận</Form.Label>
                            <Form.Control
                              value={d._diaChiNhan || ""}
                              onChange={(e) => setField(d.id, "_diaChiNhan", e.target.value)}
                            />
                          </Form.Group>

                          <Row className="g-2 mt-2">
                            <Col md={6}>
                              <Form.Group>
                                <Form.Label>Phương thức</Form.Label>
                                <Form.Select
                                  value={d._phuongThuc || d.phuongThuc || "COD"}
                                  onChange={(e) => setField(d.id, "_phuongThuc", e.target.value)}
                                >
                                  <option value="COD">COD (Thanh toán khi nhận)</option>
                                  <option value="BANK">Chuyển khoản</option>
                                </Form.Select>
                              </Form.Group>
                            </Col>
                            <Col md={6}>
                              <Form.Group>
                                <Form.Label>Ghi chú</Form.Label>
                                <Form.Control
                                  value={d._ghiChu || ""}
                                  onChange={(e) => setField(d.id, "_ghiChu", e.target.value)}
                                />
                              </Form.Group>
                            </Col>
                          </Row>
                        </Form>

                        <div className="d-flex justify-content-end mt-3">
                          <Button
                            variant="success"
                            disabled={submittingId === d.id}
                            onClick={() => handlePay(d)}
                          >
                            {submittingId === d.id ? "Đang xử lý..." : "Thanh toán"}
                          </Button>
                        </div>
                      </>
                    ) : (
                      <>
                        <div><strong>Phương thức:</strong> {d.phuongThuc}</div>
                        {d.ngayThanhToan && (
                          <div><strong>Ngày thanh toán:</strong> {new Date(d.ngayThanhToan).toLocaleString("vi-VN")}</div>
                        )}
                      </>
                    )}
                  </Card.Body>
                </Card>
              </Col>
            );
          })}
        </Row>
      )}
    </Container>
  );
};

export default TrangDonThanhToan;
