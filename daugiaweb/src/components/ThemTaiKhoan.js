import { useState, useEffect } from "react";
import { Container, Form, Button, Alert, Spinner } from "react-bootstrap";
import { authApis, endpoints } from "../configs/Apis";
import { useNavigate } from "react-router-dom";

const ThemTaiKhoan = () => {
  const [form, setForm] = useState({
    tenNguoiNhan: "",
    nganHang: "",
    soTaiKhoan: "",
    qrFile: null,
  });

  const [qrPreview, setQrPreview] = useState(null);
  const [message, setMessage] = useState(null);
  const [submitting, setSubmitting] = useState(false);
  const [daCoTaiKhoan, setDaCoTaiKhoan] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const kiemTraTaiKhoan = async () => {
      try {
        const res = await authApis().get(endpoints["tk-nganhang-cua-toi"]);
        if (Array.isArray(res.data) && res.data.length > 0) {
          setDaCoTaiKhoan(true); // đã có tài khoản
        }
      } catch (err) {
        console.error("Lỗi kiểm tra tài khoản:", err);
      }
    };

    kiemTraTaiKhoan();
  }, []);

  const NGAN_HANGS = [
    "VCB - Vietcombank",
    "TCB - Techcombank",
    "ACB - ACB",
    "BIDV - BIDV",
    "MB - MB Bank",
    "VPB - VPBank",
    "VIB - VIB",
    "SHB - SHB",
    "TPB - TPBank",
    "HDB - HDBank",
  ];

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleFileChange = (e) => {
    const file = e.target.files?.[0];
    setForm({ ...form, qrFile: file });

    if (file) {
      const reader = new FileReader();
      reader.onload = (e) => setQrPreview(e.target.result);
      reader.readAsDataURL(file);
    } else {
      setQrPreview(null);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);
    setMessage(null);

    try {
      const fd = new FormData();
      fd.append("tenNguoiNhan", form.tenNguoiNhan);
      fd.append("nganHang", form.nganHang);
      fd.append("soTaiKhoan", form.soTaiKhoan);
      fd.append("qrFile", form.qrFile); // ảnh

      const res = await authApis().post(endpoints["them-tk-nganhang"], fd, {
        headers: { "Content-Type": "multipart/form-data" },
      });

      setMessage({ type: "success", text: "Thêm tài khoản thành công!" });
      setTimeout(() => navigate("/"), 1500);
    } catch (err) {
      console.error(err);
      setMessage({ type: "danger", text: "Thêm thất bại!" });
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <Container className="mt-5" style={{ maxWidth: 600 }}>
      <h3>Thêm tài khoản ngân hàng</h3>

      {daCoTaiKhoan ? (
        <Alert variant="info">
          Bạn đã có tài khoản ngân hàng. Không thể thêm mới.
        </Alert>
      ) : (
        <>
          {message && <Alert variant={message.type}>{message.text}</Alert>}

          <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-3">
              <Form.Label>Tên người nhận</Form.Label>
              <Form.Control
                name="tenNguoiNhan"
                value={form.tenNguoiNhan}
                onChange={handleChange}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Ngân hàng</Form.Label>
              <Form.Select
                name="nganHang"
                value={form.nganHang}
                onChange={handleChange}
                required
              >
                <option value="">-- Chọn ngân hàng --</option>
                {NGAN_HANGS.map((bank, i) => (
                  <option key={i} value={bank}>{bank}</option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Số tài khoản</Form.Label>
              <Form.Control
                name="soTaiKhoan"
                value={form.soTaiKhoan}
                onChange={handleChange}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Ảnh QR (nhận chuyển khoản)</Form.Label>
              <Form.Control
                type="file"
                accept="image/*"
                onChange={handleFileChange}
                required
              />
              {qrPreview && (
                <img src={qrPreview} alt="QR Preview" style={{ maxWidth: "200px", marginTop: "10px" }} />
              )}
            </Form.Group>

            <Button type="submit" variant="success" disabled={submitting}>
              {submitting ? <Spinner animation="border" size="sm" /> : "Lưu"}
            </Button>
          </Form>
        </>
      )}
    </Container>
  );

};

export default ThemTaiKhoan;
