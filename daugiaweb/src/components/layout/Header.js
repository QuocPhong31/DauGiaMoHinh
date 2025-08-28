import React, { useContext } from "react";
import { Container, Nav, Navbar, Image, Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { MyUserContext, MyDispatchContext } from "../../configs/Contexts";
import cookie from "react-cookies";
import "../../css/Header.css";

const Header = () => {
  const user = useContext(MyUserContext);
  const dispatch = useContext(MyDispatchContext);
  const navigate = useNavigate();

  const handleLogout = () => {
    // Xoá token, dispatch logout, điều hướng về trang chủ
    cookie.remove("token");
    dispatch({ type: "logout" });
    navigate("/");
  };

  const laNguoiBan = user && (user.vaiTro === "ROLE_NGUOIBAN");

  return (
    <Navbar bg="light" expand="lg" className="shadow-sm navbar-animated">
      <Container>
        <Navbar.Brand as={Link} to="/" className="fw-bold">
          <span style={{ fontWeight: "bold", fontSize: "24px" }}>
            <span className="brand-jh">JH</span>
            <span className="brand-figure">FIGURE</span>
            <span className="brand-sub">Đấu Giá</span>
          </span>
        </Navbar.Brand>

        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/" className="nav-anim">Trang chủ</Nav.Link>
            <Nav.Link as={Link} to="/cuocdaugia" className="nav-anim">Cuộc đấu giá</Nav.Link>

            {laNguoiBan && (
              <Nav.Link as={Link} to="/taodaugia" className="nav-anim">Tạo đấu giá</Nav.Link>
            )}

            <Nav.Link as={Link} to="/lienhe" className="nav-anim">Liên hệ với mọi người</Nav.Link>
            <Nav.Link as={Link} to="/themtaikhoan" className="nav-anim">Thêm tk ngân hàng</Nav.Link>
            <Nav.Link as={Link} to="/thanhtoan" className="nav-anim">Thanh toán</Nav.Link>

            {laNguoiBan && (
              <Nav.Link as={Link} to="/quanlybaidau" className="nav-anim fw-semibold text-success">
                Quản lý bài đấu
              </Nav.Link>
            )}
          </Nav>

          <Nav className="d-flex align-items-center gap-3">
            {!user ? (
              <>
                <Nav.Link as={Link} to="/dangnhap" className="nav-anim text-primary">Đăng nhập</Nav.Link>
                <Nav.Link as={Link} to="/dangky" className="nav-anim text-primary">Đăng ký</Nav.Link>
              </>
            ) : (
              <>
                <Link to="/thongtincanhan" className="d-flex align-items-center gap-2 text-dark text-decoration-none">
                  <Image src={user.avatar || "https://via.placeholder.com/40"} roundedCircle width={40} height={40} alt="Avatar" />
                  <span title={user.username}>{user.username}</span>
                </Link>
                <Button variant="outline-danger" size="sm" className="btn-anim" onClick={handleLogout}>
                  Đăng xuất
                </Button>
              </>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default Header;
