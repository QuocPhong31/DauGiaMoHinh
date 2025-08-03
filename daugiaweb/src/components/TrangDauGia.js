import React, { useEffect, useState } from "react";
import { Card, Row, Col, Container, Spinner, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { endpoints, authApis } from "../configs/Apis";

const DanhSachDauGia = () => {
    const [dsPhien, setDsPhien] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchPhien = async () => {
            try {
                const res = await authApis().get(endpoints["cuoc-dau-gia"]);
                setDsPhien(res.data);
            } catch (err) {
                console.error("Lỗi khi lấy danh sách phiên:", err);
            } finally {
                setLoading(false);
            }
        };
        fetchPhien();
    }, []);

    if (loading) return <div className="text-center mt-5"><Spinner animation="border" /></div>;

    return (
        <Container className="mt-4">
            <h2 className="text-center mb-4">Danh sách phiên đấu giá đang diễn ra</h2>
            <Row>
                {dsPhien.map(phien => (
                    <Col key={phien.id} xs={12} className="mb-4">
                        <Card className="p-3 shadow-sm">
                            <Row>
                                <Col md={3} xs={12} className="d-flex align-items-center justify-content-center">
                                    <Card.Img
                                        src={phien.sanPham?.hinhAnh || "https://via.placeholder.com/300x200"}
                                        alt="Hình sản phẩm"
                                        style={{ width: "100%", maxHeight: "180px", objectFit: "cover", borderRadius: "8px" }}
                                    />
                                </Col>
                                <Col md={9} xs={12}>
                                    <h5 className="fw-bold">{phien.sanPham?.tenSanPham}</h5>
                                    <p className="mb-1"><strong>Mô tả: </strong>{phien.sanPham?.moTa}</p>
                                    <p className="mb-1"><strong>Giá khởi điểm:</strong> {phien.sanPham?.giaKhoiDiem?.toLocaleString()} đ</p>
                                    <p className="mb-2"><strong>Kết thúc:</strong> {new Date(phien.thoiGianKetThuc).toLocaleString("vi-VN")}</p>
                                    <Link to={`/cuoc-dau-gia/${phien.id}`}>
                                        <Button variant="primary" size="sm">Xem chi tiết</Button>
                                    </Link>
                                </Col>
                            </Row>
                        </Card>
                    </Col>
                ))}
            </Row>
        </Container>
    );
};

export default DanhSachDauGia;
