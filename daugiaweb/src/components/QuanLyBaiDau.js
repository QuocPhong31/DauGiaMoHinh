import React, { useEffect, useState } from "react";
import { Container, Row, Col, Card, Badge } from "react-bootstrap";
import { authApis, endpoints } from "../configs/Apis";
import cookie from "react-cookies";

const QuanLyBaiDau = () => {
  const [baiDau, setBaiDau] = useState([]);

  useEffect(() => {
    const fetchBaiDau = async () => {
      const res = await authApis().get(endpoints["quan-ly-bai-dau"]);
      setBaiDau(res.data || []);
    };
    fetchBaiDau();
  }, []);

  return (
    <Container>
      <h3 className="mt-4">Quản lý bài đấu</h3>
      <Row>
        {baiDau.map((phien) => (
          <Col md={4} key={phien.id}>
            <Card className="mb-4">
              <Card.Body>
                <Card.Title>{phien.sanPham.tenSanPham}</Card.Title>
                <Card.Text>
                  <strong>Giá chốt: </strong>
                  {phien.giaHienTai ? phien.giaHienTai.toLocaleString() : "Chưa có giá chốt"}
                </Card.Text>
                {phien.nguoiThangDauGia && (
                  <Card.Text>
                    <strong>Người thắng: </strong>
                    {phien.nguoiThangDauGia.hoTen}
                  </Card.Text>
                )}
                {phien.thanhToan !== undefined && (
                  <Badge bg={phien.thanhToan ? "success" : "warning"}>
                    {phien.thanhToan ? "Đã thanh toán" : "Chưa thanh toán"}
                  </Badge>
                )}
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default QuanLyBaiDau;
