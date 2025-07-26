
import { Container, Nav, Navbar } from "react-bootstrap";
import { Link } from "react-router-dom";

const Header = () => {
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
          <Nav>
            <Nav.Link as={Link} to="/dangnhap">Đăng nhập</Nav.Link>
            <Nav.Link as={Link} to="/dangky">Đăng ký</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};
export default Header;
