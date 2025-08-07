import React, { useEffect, useState } from "react";
import { Card, Row, Col, Container, Spinner, Button, ListGroup } from "react-bootstrap";
import { Link } from "react-router-dom";
import { endpoints, authApis } from "../configs/Apis";

const TrangDauGia = () => {
    const [dsPhien, setDsPhien] = useState([]);
    const [loading, setLoading] = useState(true);
    const [tab, setTab] = useState("homNay");
    const [dsDangTheoDoi, setDsDangTheoDoi] = useState([]);
    const [tuKhoa, setTuKhoa] = useState("");

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

        const fetchDsDangTheoDoi = async () => {
            try {
                const res = await authApis().get(endpoints["danh-sach-theo-doi"]);
                const ids = res.data.map(item => item.phienDauGia.id);
                setDsDangTheoDoi(ids);
            } catch (err) {
                console.error("Lỗi khi lấy danh sách đang theo dõi:", err);
            }
        };
        fetchDsDangTheoDoi();
    }, []);

    if (loading) return <div className="text-center mt-5"><Spinner animation="border" /></div>;

    const today = new Date();
    today.setHours(0, 0, 0, 0);

    const laHomNay = (ngay) => {
        const d = new Date(ngay);
        d.setHours(0, 0, 0, 0);
        return d.getTime() === today.getTime();
    };

    const laTruocHomNay = (ngay) => {
        const d = new Date(ngay);
        d.setHours(0, 0, 0, 0);
        return d.getTime() < today.getTime();
    };

    const filterByTuKhoa = (list) => {
        return list.filter(phien =>
            phien.sanPham?.tenSanPham?.toLowerCase().includes(tuKhoa.toLowerCase())
        );
    };

    const dsHomNay = dsPhien.filter(p => laHomNay(p.thoiGianBatDau) && p.trangThai !== "da_ket_thuc");
    const dsTruocHomNay = dsPhien.filter(p => laTruocHomNay(p.thoiGianBatDau) && p.trangThai !== "da_ket_thuc");
    const dsKetThuc = dsPhien.filter(p => p.trangThai === "da_ket_thuc");
    const dsTheoDoi = dsPhien.filter(p => dsDangTheoDoi.includes(p.id));

    const ChuyenTrangThaiTheoDoi = async (phienId) => {
        try {
            if (dsDangTheoDoi.includes(phienId)) {
                await authApis().delete(`${endpoints["bo-theo-doi"]}/${phienId}`);
                setDsDangTheoDoi(dsDangTheoDoi.filter(id => id !== phienId));
            } else {
                await authApis().post(`${endpoints["them-theo-doi"]}/${phienId}`, {});
                setDsDangTheoDoi([...dsDangTheoDoi, phienId]);
            }
        } catch (err) {
            console.error("Lỗi khi cập nhật theo dõi:", err);
        }
    };

    const renderPhienCards = (list) => (
        <Row>
            {list.map(phien => (
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
                                <Button
                                    variant={dsDangTheoDoi.includes(phien.id) ? "outline-danger" : "outline-success"}
                                    size="sm"
                                    className="ms-2"
                                    onClick={() => ChuyenTrangThaiTheoDoi(phien.id)}
                                >
                                    {dsDangTheoDoi.includes(phien.id) ? "Bỏ theo dõi" : "Theo dõi"}
                                </Button>
                            </Col>
                        </Row>
                    </Card>
                </Col>
            ))}
        </Row>
    );

    const renderNoData = (label) => <p className="text-center">Không có {label}.</p>;

    return (
        <Container className="mt-4">
            <Row className="mb-3">
                <Col md={{ span: 6, offset: 3 }}>
                    <input
                        type="text"
                        placeholder="Tìm kiếm theo tên sản phẩm..."
                        className="form-control"
                        value={tuKhoa}
                        onChange={(e) => setTuKhoa(e.target.value)}
                    />
                </Col>
            </Row>

            <Row>
                <Col md={3}>
                    <ListGroup>
                        <ListGroup.Item action active={tab === "homNay"} onClick={() => setTab("homNay")}>
                            Bài đấu giá hôm nay
                        </ListGroup.Item>
                        <ListGroup.Item action active={tab === "truocDo"} onClick={() => setTab("truocDo")}>
                            Bài đấu giá hôm qua hoặc trước đó
                        </ListGroup.Item>
                        <ListGroup.Item action active={tab === "ketThuc"} onClick={() => setTab("ketThuc")}>
                            Bài đấu giá đã kết thúc
                        </ListGroup.Item>
                        <ListGroup.Item action active={tab === "dangTheoDoi"} onClick={() => setTab("dangTheoDoi")}>
                            Bài đấu giá đang theo dõi
                        </ListGroup.Item>
                    </ListGroup>
                </Col>

                <Col md={9}>
                    {tab === "homNay" && (
                        <>
                            <h2 className="text-center mb-4">Bài đấu giá diễn ra hôm nay</h2>
                            {filterByTuKhoa(dsHomNay).length > 0
                                ? renderPhienCards(filterByTuKhoa(dsHomNay))
                                : renderNoData("phiên nào hôm nay")}
                        </>
                    )}

                    {tab === "truocDo" && (
                        <>
                            <h2 className="text-center mb-4">Bài đấu giá hôm qua hoặc trước đó</h2>
                            {filterByTuKhoa(dsTruocHomNay).length > 0
                                ? renderPhienCards(filterByTuKhoa(dsTruocHomNay))
                                : renderNoData("phiên nào hôm qua hoặc trước đó")}
                        </>
                    )}

                    {tab === "ketThuc" && (
                        <>
                            <h2 className="text-center mb-4">Bài đấu giá đã kết thúc</h2>
                            {filterByTuKhoa(dsKetThuc).length > 0
                                ? renderPhienCards(filterByTuKhoa(dsKetThuc))
                                : renderNoData("phiên đã kết thúc")}
                        </>
                    )}

                    {tab === "dangTheoDoi" && (
                        <>
                            <h2 className="text-center mb-4">Bài đấu giá đang theo dõi</h2>
                            {filterByTuKhoa(dsTheoDoi).length > 0
                                ? renderPhienCards(filterByTuKhoa(dsTheoDoi))
                                : renderNoData("phiên đang theo dõi")}
                        </>
                    )}
                </Col>
            </Row>
        </Container>
    );
};

export default TrangDauGia;
