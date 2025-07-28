import { useContext, useState, useRef } from "react";
import { Alert, Button, Col, Form, Row } from "react-bootstrap";
import Apis, { endpoints } from "../configs/Apis";
import MySpinner from "./layout/MySpinner";
import { useNavigate } from "react-router-dom";
import { MyUserContext } from "../configs/Contexts";

const DangKy = () => {
    const info = [
        { label: "Tài khoản", field: "taiKhoan", type: "text" },
        { label: "Mật khẩu", field: "matKhau", type: "password" },
        { label: "Xác nhận mật khẩu", field: "confirm", type: "password" },
        { label: "Họ tên", field: "hoTen", type: "text" },
        { label: "Email", field: "email", type: "email" },
        { label: "Số điện thoại", field: "soDienThoai", type: "tel" },
        { label: "Địa chỉ", field: "diaChi", type: "text" }
    ];

    const avatar = useRef();
    const current_user = useContext(MyUserContext);
    const [user, setUser] = useState({});
    const [msg, setMsg] = useState();
    const [loading, setLoading] = useState(false);
    const nav = useNavigate();

    const validate = () => {
        if (!user.matKhau || user.matKhau !== user.confirm) {
            setMsg("Mật khẩu không khớp");
            return false;
        }
        return true;
    };

    const dangKy = async (e) => {
        e.preventDefault();

        if (validate()) {
            let form = new FormData();
            for (let key in user) {
                if (key !== 'confirm') {
                    form.append(key, user[key]);
                }
            }

            if (avatar.current?.files[0]) {
                form.append("avatar", avatar.current.files[0]);
            }

            try {
                setLoading(true);
                let res = await Apis.post(endpoints['register'], form, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                });

                if (res.status === 201 || res.status === 200) {
                    nav("/dangnhap");
                }
            } catch (err) {
                console.error(err);
                setMsg("Đăng ký thất bại. Vui lòng kiểm tra lại thông tin!");
            } finally {
                setLoading(false);
            }
        }
    };

    return (
        <>
            <h1 className="text-center text-primary mt-2">Đăng ký tài khoản</h1>
            {msg && <Alert variant="danger">{msg}</Alert>}

            <Form onSubmit={dangKy}>
                {info.map(i => (
                    <Form.Group className="mb-3" key={i.field}>
                        <Form.Control
                            value={user[i.field] || ""}
                            onChange={e => setUser({ ...user, [i.field]: e.target.value })}
                            type={i.type}
                            placeholder={i.label}
                            required
                        />
                    </Form.Group>
                ))}

                <Form.Group as={Row} className="mb-3">
                    <Form.Label column sm="2">Ảnh đại diện</Form.Label>
                    <Col sm="10">
                        <Form.Control ref={avatar} type="file" accept="image/*" required />
                    </Col>
                </Form.Group>

                <div className="text-center">
                    {loading ? <MySpinner /> : <Button type="submit" variant="success">Đăng ký</Button>}
                </div>
            </Form>
        </>
    );
};

export default DangKy;
