import React, { useContext, useState } from "react";
import { Container, Row, Col, Card, Image, Form, Button, Alert } from "react-bootstrap";
import { MyUserContext } from "../configs/Contexts";
import { authApis, endpoints } from "../configs/Apis";

const ThongTinCaNhan = () => {
    const user = useContext(MyUserContext);
    const [currentPassword, setCurrentPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [message, setMessage] = useState({ type: "", text: "" });

    const handleChangePassword = async (e) => {
        e.preventDefault();

        if (newPassword !== confirmPassword) {
            setMessage({ type: "danger", text: "Mật khẩu mới không khớp!" });
            return;
        }

        try {
            const res = await authApis().post(endpoints["change-password"], {
                oldPassword: currentPassword,
                newPassword: newPassword,
                confirmPassword: confirmPassword
            });

            if (res.status === 200) {
                setMessage({ type: "success", text: "Đổi mật khẩu thành công!" });
                setCurrentPassword("");
                setNewPassword("");
                setConfirmPassword("");
            }
        } catch (err) {
            const msg = err?.response?.data || "Lỗi khi đổi mật khẩu!";
            setMessage({ type: "danger", text: msg });
        }
    };


    if (!user) return <Container><Alert variant="danger">Vui lòng đăng nhập để xem thông tin cá nhân.</Alert></Container>;

    return (
        <Container className="py-4">
            <Row>
                <Col md={4}>
                    <Card className="text-center p-3">
                        <Image
                            src={user.avatar || "https://via.placeholder.com/150"}
                            roundedCircle
                            width={120}
                            height={120}
                            className="mx-auto mb-3"
                        />
                        <h5>{user.hoTen || user.username}</h5>
                        <p className="text-muted">{user.email}</p>
                    </Card>
                </Col>

                <Col md={8}>
                    <Card body>
                        <h4 className="mb-4">Đổi mật khẩu</h4>
                        {message.text && (
                            <Alert variant={message.type}>{message.text}</Alert>
                        )}
                        <Form onSubmit={handleChangePassword}>
                            <Form.Group className="mb-3">
                                <Form.Label>Mật khẩu hiện tại</Form.Label>
                                <Form.Control
                                    type="password"
                                    value={currentPassword}
                                    onChange={(e) => setCurrentPassword(e.target.value)}
                                    required
                                />
                            </Form.Group>

                            <Form.Group className="mb-3">
                                <Form.Label>Mật khẩu mới</Form.Label>
                                <Form.Control
                                    type="password"
                                    value={newPassword}
                                    onChange={(e) => setNewPassword(e.target.value)}
                                    required
                                />
                            </Form.Group>

                            <Form.Group className="mb-3">
                                <Form.Label>Nhập lại mật khẩu mới</Form.Label>
                                <Form.Control
                                    type="password"
                                    value={confirmPassword}
                                    onChange={(e) => setConfirmPassword(e.target.value)}
                                    required
                                />
                            </Form.Group>

                            <Button type="submit" variant="primary">Xác nhận đổi mật khẩu</Button>
                        </Form>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
};

export default ThongTinCaNhan;
