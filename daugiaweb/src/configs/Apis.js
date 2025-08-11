import axios from "axios";
import cookie from "react-cookies";

// ✅ Cập nhật base URL đúng với backend của project dau gia
const BASE_URL = 'http://localhost:8080/SpringDauGiaMoHinh/api/';

export const endpoints = {
    // Authentication
    login: 'login',
    'current-user': 'secure/profile',

    // Người dùng
    'add-user': 'users',                  // POST - thêm người dùng (admin)
    'get-users': 'users',                 // GET - danh sách người dùng (nếu có)
    'change-password': 'secure/change-password',  // POST - đổi mật khẩu
    "add-product": "sanpham/dangsanpham",
    "loai-san-pham": "loaisanpham",
    "cuoc-dau-gia": "phiendaugia/phiendaugia",
    "dat-gia": "phiendaugianguoidung/dat-gia",
    "chuyen-vai-tro": "secure/chuyen-vai-tro-nguoi-ban",
    "them-theo-doi": "theodoisanpham/them",
    "bo-theo-doi": "theodoisanpham/xoa",
    "danh-sach-theo-doi": "theodoisanpham/danhsach", // GET
    "kiem-tra-theo-doi": "theodoisanpham/kiemtra",   
    "lich-su-dat-gia": "phiendaugianguoidung/lich-su/",
    "don-cua-toi": "thanhtoan/cua-toi",
    "thanh-toan-don": (id) => `thanhtoan/${id}/thanh-toan`,

};

// Gọi API có kèm token (xác thực)
export const authApis = () => {
    const token = cookie.load('token');
    console.log(">>> Token trong cookie:", token);
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            Authorization: token ? `Bearer ${token}` : '',
        }
    });
};

// Gọi API không cần token (khách truy cập)
export default axios.create({
    baseURL: BASE_URL,
});