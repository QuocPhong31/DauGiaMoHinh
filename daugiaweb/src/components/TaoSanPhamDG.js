import React, { useState } from "react";
import { Container, Form, Button, Alert, Card } from "react-bootstrap";
import { authApis, endpoints } from "../configs/Apis";

const TaoSanPhamDG = () => {
  const [tenSanPham, setTenSanPham] = useState("");
  const [moTa, setMoTa] = useState("");
  const [giaKhoiDiem, setGiaKhoiDiem] = useState("");
  const [buocNhay, setBuocNhay] = useState("");
  const [avatar, setAvatar] = useState(null);
  const [message, setMessage] = useState({ type: "", text: "" });

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!tenSanPham || !giaKhoiDiem) {
      setMessage({ type: "danger", text: "Tên sản phẩm và giá khởi điểm là bắt buộc!" });
      return;
    }

    const formData = new FormData();
    formData.append("tenSanPham", tenSanPham);
    formData.append("moTa", moTa);
    formData.append("giaKhoiDiem", giaKhoiDiem);
    formData.append("buocNhay", buocNhay);
    if (avatar) formData.append("avatar", avatar); // optional

    try {
      const res = await authApis().post(endpoints["add-product"], formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      });

      if (res.status === 201) {
        setMessage({ type: "success", text: "Đăng sản phẩm thành công! Vui lòng chờ duyệt." });
        setTenSanPham("");
        setMoTa("");
        setGiaKhoiDiem("");
        setBuocNhay("");
        setAvatar(null);
      }
    } catch (err) {
      const msg = err?.response?.data || "Lỗi khi đăng sản phẩm!";
      setMessage({ type: "danger", text: msg });
    }
  };

  return (
    <Container className="py-4">
      <Card className="p-4 shadow">
        <h3 className="text-center mb-4">📝 Tạo Sản Phẩm Đấu Giá</h3>

        {message.text && (
          <Alert variant={message.type}>{message.text}</Alert>
        )}

        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>Tên sản phẩm</Form.Label>
            <Form.Control
              type="text"
              value={tenSanPham}
              onChange={(e) => setTenSanPham(e.target.value)}
              required
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Mô tả</Form.Label>
            <Form.Control
              as="textarea"
              rows={3}
              value={moTa}
              onChange={(e) => setMoTa(e.target.value)}
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Giá khởi điểm</Form.Label>
            <Form.Control
              type="number"
              value={giaKhoiDiem}
              onChange={(e) => setGiaKhoiDiem(e.target.value)}
              required
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Bước nhảy</Form.Label>
            <Form.Control
              type="number"
              value={buocNhay}
              onChange={(e) => setBuocNhay(e.target.value)}
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Hình ảnh sản phẩm</Form.Label>
            <Form.Control
              type="file"
              accept="image/*"
              onChange={(e) => setAvatar(e.target.files[0])}
            />
          </Form.Group>

          <Button type="submit" variant="primary">Đăng sản phẩm</Button>
        </Form>
      </Card>
    </Container>
  );
};

export default TaoSanPhamDG;
