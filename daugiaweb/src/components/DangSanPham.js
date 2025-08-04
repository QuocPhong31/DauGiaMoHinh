import React, { useEffect, useState } from "react";
import { Form, Button, Alert, Container, Card } from "react-bootstrap";
import { endpoints, authApis } from "../configs/Apis";
import axios from "axios";
import cookie from "react-cookies";

const DangSanPham = () => {
  const [dsLoai, setDsLoai] = useState([]);
  const [loaiSanPhamId, setLoaiSanPhamId] = useState("");
  const [tenSanPham, setTenSanPham] = useState("");
  const [moTa, setMoTa] = useState("");
  const [giaKhoiDiem, setGiaKhoiDiem] = useState("");
  const [buocNhay, setBuocNhay] = useState("");
  const [giaBua, setGiaBua] = useState("");
  const [thoiGianKetThuc, setThoiGianKetThuc] = useState("");
  const [avatar, setAvatar] = useState(null);
  const [message, setMessage] = useState(null);

  useEffect(() => {
    const fetchLoaiSP = async () => {
        try {
        const res = await authApis().get(endpoints["loai-san-pham"]);
        setDsLoai(res.data);
        } catch (err) {
        console.error("Lỗi khi load loại sản phẩm:", err);
        }
    };
    fetchLoaiSP();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const startPrice = parseFloat(giaKhoiDiem);
    const stepPrice = parseFloat(buocNhay);
    const hammerPrice = parseFloat(giaBua || 0);

    // Kiểm tra giá trị âm
    if (startPrice <= 0) {
      setMessage({ type: "danger", text: "Giá khởi điểm phải lớn hơn 0." });
      return;
    }
    // Kiểm tra giá trị âm
    if (stepPrice <= 0) {
      setMessage({ type: "danger", text: "Bước nhảy phải lớn hơn 0." });
      return;
    }

    // Nếu có nhập giá búa thì kiểm tra phải > giá khởi điểm
    if (giaBua && hammerPrice <= startPrice) {
      setMessage({ type: "danger", text: "Giá búa phải lớn hơn giá khởi điểm." });
      return;
    }

    const token = cookie.load("token");
    if (!token) {
      setMessage({ type: "danger", text: "Bạn cần đăng nhập để đăng sản phẩm." });
      return;
    }

    const formData = new FormData();
    formData.append("tenSanPham", tenSanPham);
    formData.append("moTa", moTa);
    formData.append("giaKhoiDiem", giaKhoiDiem);
    formData.append("buocNhay", Math.round(parseFloat(buocNhay)));
    formData.append("giaBua", giaBua || 0);
    formData.append("thoiGianKetThuc", thoiGianKetThuc);
    formData.append("loaiSanPham_id", loaiSanPhamId);
    if (avatar) formData.append("avatar", avatar);

    try {
      const res = await authApis().post(endpoints['add-product'], formData, {
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${token}`,
        },
      });
      setMessage({ type: "success", text: "Đăng sản phẩm thành công!" });
      // Reset form
      setTenSanPham("");
      setMoTa("");
      setGiaKhoiDiem("");
      setBuocNhay("");
      setGiaBua(null);
      setThoiGianKetThuc("");
      setAvatar(null);
      setLoaiSanPhamId("");
    } catch (err) {
      console.error(err);
      setMessage({
        type: "danger",
        text: err.response?.data || "Đã xảy ra lỗi khi đăng sản phẩm",
      });
    }
  };

  return (
    <Container className="mt-5">
      <Card className="p-4 shadow">
        <h4 className="mb-4">📄 Tạo Sản Phẩm Đấu Giá</h4>
        {message && <Alert variant={message.type}>{message.text}</Alert>}
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>Loại sản phẩm</Form.Label>
            <Form.Select value={loaiSanPhamId} onChange={(e) => setLoaiSanPhamId(e.target.value)} required>
              <option value="">-- Chọn loại sản phẩm --</option>
              {dsLoai.map((loai) => (
                <option key={loai.id} value={loai.id}>
                  {loai.tenLoai}
                </option>
              ))}
            </Form.Select>
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Tên sản phẩm</Form.Label>
            <Form.Control value={tenSanPham} onChange={(e) => setTenSanPham(e.target.value)} required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Mô tả</Form.Label>
            <Form.Control as="textarea" rows={3} value={moTa} onChange={(e) => setMoTa(e.target.value)} required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Giá khởi điểm</Form.Label>
            <Form.Control type="number" value={giaKhoiDiem} onChange={(e) => setGiaKhoiDiem(e.target.value)} required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Bước nhảy</Form.Label>
            <Form.Control type="number" value={buocNhay} onChange={(e) => setBuocNhay(e.target.value)} required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Giá búa (nếu có)</Form.Label>
            <Form.Control type="number" value={giaBua} onChange={(e) => setGiaBua(e.target.value)} />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Thời gian kết thúc</Form.Label>
            <Form.Control
              type="datetime-local"
              value={thoiGianKetThuc}
              onChange={(e) => setThoiGianKetThuc(e.target.value)}
              required
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Hình ảnh sản phẩm</Form.Label>
            <Form.Control type="file" onChange={(e) => setAvatar(e.target.files[0])} accept="image/*" />
          </Form.Group>

          <Button variant="primary" type="submit">
            Đăng sản phẩm
          </Button>
        </Form>
      </Card>
    </Container>
  );
};

export default DangSanPham;
