import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { authApis, endpoints } from "../configs/Apis";
import { Container, Row, Col, Card, Form, Button, Alert } from "react-bootstrap";
import cookie from "react-cookies";

const ChiTietBaiDauGia = () => {
    const { id } = useParams();
    const [phien, setPhien] = useState(null);
    const [giaDauGia, setGiaDauGia] = useState("");
    const [message, setMessage] = useState({ type: "", text: "" });
    const [disabled, setDisabled] = useState(false);

    useEffect(() => {
        const fetchPhien = async () => {
            try {
                const res = await authApis().get(`${endpoints["cuoc-dau-gia"]}/${id}`);
                setPhien(res.data);
            } catch (err) {
                console.error("Lỗi khi tải chi tiết phiên:", err);
            }
        };
        fetchPhien();
    }, [id]);

    useEffect(() => {
        if (phien) {
            const now = new Date();
            const end = new Date(phien.thoiGianKetThuc);
            const ended = now > end || phien.giaChot !== null;
            setDisabled(ended);
        }
    }, [phien]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!giaDauGia) return;

        try {
            const form = new FormData();
            form.append("phienDauGiaId", id);
            form.append("gia", giaDauGia);

            const res = await authApis().post(endpoints["dat-gia"], form, {
                headers: {
                    Authorization: `Bearer ${cookie.load("token")}`
                }
            });

            setMessage({ type: "success", text: "Đấu giá thành công!" });
        } catch (err) {
            console.error(err);
            setMessage({ type: "danger", text: "Lỗi khi đặt giá!" });
        }
    };

    if (!phien) return <p className="text-center mt-5">Đang tải...</p>;

    const sp = phien.sanPham;
    const now = new Date();
    const endTime = new Date(phien.thoiGianKetThuc);
    const highestBid = phien.giaChot || 0;
    const minBid = highestBid ? highestBid + sp.buocNhay : sp.giaKhoiDiem;

    return (
        <Container className="mt-4">
            <h3 className="text-center mb-4">{sp.tenSanPham}</h3>
            <Row>
                <Col md={7}>
                    <Card body>
                        <p><strong>Loại sản phẩm:</strong> {sp.loaiSanPham?.tenLoai}</p>
                        <p><strong>Mô tả:</strong> {sp.moTa}</p>
                        <p><strong>Giá khởi điểm:</strong> {sp.giaKhoiDiem.toLocaleString()} đ</p>
                        <p><strong>Bước nhảy:</strong> {sp.buocNhay.toLocaleString()} đ</p>
                        {sp.giaBua && <p><strong>Giá búa:</strong> {sp.giaBua.toLocaleString()} đ</p>}
                        <p><strong>Thời gian kết thúc:</strong> {endTime.toLocaleString("vi-VN")}</p>
                        <p><strong>Giá hiện tại:</strong> {highestBid?.toLocaleString() || "Chưa có"} đ</p>
                        {disabled ? (
                            <Alert variant="secondary">Phiên đấu giá đã kết thúc!</Alert>
                        ) : (
                            <>
                                <Form onSubmit={handleSubmit}>
                                    <Form.Group className="mb-2">
                                        <Form.Label>Nhập giá của bạn (≥ {minBid.toLocaleString()} đ)</Form.Label>
                                        <Form.Control
                                            type="number"
                                            min={minBid}
                                            step={sp.buocNhay}
                                            value={giaDauGia}
                                            onChange={(e) => setGiaDauGia(e.target.value)}
                                            required
                                        />
                                    </Form.Group>
                                    <Button type="submit" variant="success">Đặt giá</Button>
                                </Form>
                                {message.text && <Alert variant={message.type} className="mt-3">{message.text}</Alert>}
                            </>
                        )}
                    </Card>
                </Col>
                <Col md={5}>
                    <Card>
                        <Card.Img variant="top" src={sp.hinhAnh || "https://via.placeholder.com/400"} />
                    </Card>
                </Col>
            </Row>
        </Container>
    );
};

export default ChiTietBaiDauGia;
