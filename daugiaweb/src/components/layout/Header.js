import React, { useContext } from "react";
import { Container, Nav, Navbar, Image, Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { MyUserContext, MyDispatchContext } from "../../configs/Contexts";
import cookie from "react-cookies";

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

  return (
    <Navbar bg="light" expand="lg" className="shadow-sm">
      <Container>
        <Navbar.Brand as={Link} to="/" className="fw-bold text-danger">
          <span style={{ fontWeight: "bold", fontSize: "24px" }}>🪙 Auction</span>
        </Navbar.Brand>

        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/">Trang chủ</Nav.Link>
            <Nav.Link as={Link} to="/cuoc-dau-gia">Cuộc đấu giá</Nav.Link>
            <Nav.Link as={Link} to="/tin-tuc">Tin tức</Nav.Link>
            <Nav.Link as={Link} to="/gioi-thieu">Giới thiệu</Nav.Link>
            <Nav.Link as={Link} to="/lien-he">Liên hệ & Góp ý</Nav.Link>
          </Nav>

          <Nav className="d-flex align-items-center gap-3">
            {!user ? (
              <>
                <Nav.Link as={Link} to="/dangnhap" className="text-primary">Đăng nhập</Nav.Link>
                <Nav.Link as={Link} to="/dangky" className="text-primary">Đăng ký</Nav.Link>
              </>
            ) : (
              <>
                <div className="d-flex align-items-center gap-2 text-dark">
                  <Image
                    src={user.avatar || "https://via.placeholder.com/40"}
                    roundedCircle
                    width={40}
                    height={40}
                    alt="Avatar"
                  />
                  <span title={user.username}>
                    {user.username}
                  </span>
                </div>
                <Button variant="outline-danger" size="sm" onClick={handleLogout}>
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
