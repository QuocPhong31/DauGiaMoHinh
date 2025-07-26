import { useReducer, useEffect } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Container } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';

import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";

import TrangChu from "./components/TrangChu";
import DangNhap from "./components/DangNhap";
import DangKy from "./components/DangKy";
// Thêm các page khác khi bạn đã tạo

import { MyUserContext, MyDispatchContext } from "./configs/Contexts";
import MyUserReducer from "./reducers/MyUserReducer";
import { authApis, endpoints } from "./configs/Apis";
import cookie from "react-cookies";

console.log("Header", Header);
console.log("Footer", Footer);
console.log("TrangChu", TrangChu);
console.log("DangNhap", DangNhap);
console.log("DangKy", DangKy);
const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, null);

  useEffect(() => {
    const checkLogin = async () => {
      const token = cookie.load("token");
      if (token !== undefined) {
        try {
          let res = await authApis().get(endpoints['current-user']);
          dispatch({
            type: "login",
            payload: res.data
          });
        } catch (err) {
          cookie.remove("token");
        }
      }
    };
    checkLogin();
  }, []);

  return (
    <MyUserContext.Provider value={user}>
      <MyDispatchContext.Provider value={dispatch}>
        <BrowserRouter>
          <Header />
          <Container>
            <Routes>
              <Route path="/" element={<TrangChu />} />
              <Route path="/dangnhap" element={<DangNhap />} />
              <Route path="/dangky" element={<DangKy />} />
              {/* Thêm route cho trang đấu giá, sản phẩm, v.v. */}
            </Routes>
          </Container>
          <Footer />
        </BrowserRouter>
      </MyDispatchContext.Provider>
    </MyUserContext.Provider>
  );
};

export default App;
