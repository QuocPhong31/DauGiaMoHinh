import React, { useEffect, useState } from "react";
import { Container, Row, Col, Card, Button } from "react-bootstrap";
import { authApis, endpoints } from "../configs/Apis";
import { Link } from "react-router-dom";
import '../css/QuanLyBaiDau.css'; // Thêm file CSS để cải tiến giao diện

const QuanLyBaiDau = () => {
  const [baiDau, setBaiDau] = useState([]);

  useEffect(() => {
    const fetchBaiDau = async () => {
      try {
        const res = await authApis().get(endpoints["quan-ly-bai-dau"]);
        console.log(res); // Kiểm tra kết quả từ API
        setBaiDau(res.data || []);
      } catch (error) {
        console.error("Yêu cầu thất bại với mã lỗi:", error.response?.status);
        console.error("Chi tiết lỗi:", error.response?.data);
      }
    };
    fetchBaiDau();
  }, []);

  return (
    <Container className="mt-4">
      <h3>Quản lý bài đấu</h3>
      <Row className="g-4">
        {baiDau.map((phien) => (
          <Col md={4} key={phien.id}>
            <Card className="custom-card">
              <Card.Body>
                <Row>
                  <Col md={4}>
                    <Card.Img
                      variant="top"
                      src={phien.sanPham?.hinhAnh || "https://via.placeholder.com/150"}
                      alt="Hình sản phẩm"
                      className="product-img"
                    />
                  </Col>
                  <Col md={8}>
                    <Card.Title>{phien.sanPham.tenSanPham}</Card.Title>
                    <Card.Text>
                      <strong>Giá hiện tại: </strong>
                      {phien.giaHienTai
                        ? phien.giaHienTai.toLocaleString() + " đ"
                        : "Chưa có giá chốt"}
                    </Card.Text>
                    {phien.nguoiThangDauGia && (
                      <Card.Text>
                        <strong>Người thắng: </strong>
                        {phien.nguoiThangDauGia.hoTen}
                      </Card.Text>
                    )}
                    {phien.thanhToan !== undefined && (
                      <Card.Text>
                        <strong>Trạng thái thanh toán: </strong>
                        {phien.thanhToan ? "Đã thanh toán" : "Chưa thanh toán"}
                      </Card.Text>
                    )}
                    <Link to={`/cuoc-dau-gia/${phien.id}`}>
                      <Button variant="primary" className="w-100">Xem chi tiết</Button>
                    </Link>
                  </Col>
                </Row>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default QuanLyBaiDau;
