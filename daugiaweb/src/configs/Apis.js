import axios from "axios";
import cookie from "react-cookies";

// ✅ Cập nhật base URL đúng với backend của project dau gia
const BASE_URL = 'http://localhost:8080/SpringDauGiaMoHinh/api/';

export const endpoints = {
    // 🔐 Authentication
    login: 'login',
    'current-user': 'secure/profile',

    // 🧑 Người dùng
    'add-user': 'users',                  // POST - thêm người dùng (admin)
    'get-users': 'users',                 // GET - danh sách người dùng (nếu có)
    'change-password': 'secure/change-password'  // POST - đổi mật khẩu

    // 👉 Bạn có thể thêm các endpoint khác như sản phẩm, đấu giá... ở đây
};

// Gọi API có kèm token (xác thực)
export const authApis = () => {
    const token = cookie.load('token');
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