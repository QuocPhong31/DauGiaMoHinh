import { Container, Row, Col, Card, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import React, { useContext } from "react";
import { MyUserContext } from "../configs/Contexts";

const TrangChu = () => {
    const user = useContext(MyUserContext);


    return (
        <Container className="mt-4">
            {user && (
                <h4 className="text-center text-success">
                    Xin chào, {user.lastName} {user.firstName}!
                </h4>
            )}
            <Row className="justify-content-center mb-5">
                <Col md={8} className="text-center">
                    <h1 className="fw-bold mb-3 text-uppercase">Chào mừng đến với hệ thống đấu giá trực tuyến</h1>
                    <p className="fs-5">Nơi bạn có thể tham gia các phiên đấu giá hấp dẫn và mua bán các mô hình sưu tầm độc đáo.</p>
                    {!user ? (
                        <div className="d-flex justify-content-center gap-3 mt-4">
                            <Link to="/dangnhap">
                                <Button variant="outline-primary" size="lg">Đăng nhập</Button>
                            </Link>
                            <Link to="/dangky">
                                <Button variant="primary" size="lg">Đăng ký</Button>
                            </Link>
                        </div>
                    ) : (
                        <p className="fs-5 text-success">Chúc bạn một phiên đấu giá thành công!</p>
                    )}
                </Col>
            </Row>

            <h3 className="text-center text-secondary mb-4">Tính năng chính</h3>
            <Row className="text-center">
                <Col md={4}>
                    <Card className="mb-4 shadow">
                        <Card.Body>
                            <Card.Title>🔨 Tham gia đấu giá</Card.Title>
                            <Card.Text>Theo dõi và tham gia các phiên đấu giá trực tiếp với nhiều sản phẩm đặc sắc.</Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={4}>
                    <Card className="mb-4 shadow">
                        <Card.Body>
                            <Link to="/tao-dau-gia" className="text-decoration-none text-dark">
                                <Card.Title>📦 Tạo đấu giá</Card.Title>
                                <Card.Text>Đăng sản phẩm để đấu giá và quản lý trạng thái giao dịch.</Card.Text>
                            </Link>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={4}>
                    <Card className="mb-4 shadow">
                        <Card.Body>
                            <Card.Title>👤 Hồ sơ cá nhân</Card.Title>
                            <Card.Text>Quản lý thông tin tài khoản, xem lịch sử giao dịch và cập nhật ảnh đại diện.</Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
};

export default TrangChu;
