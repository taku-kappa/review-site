import axios from 'axios';

// 1. Axiosの基本設定
const api = axios.create({
  baseURL: "http://localhost:8081/api",
  headers: {
    "Content-Type": "application/json",
  },
});

// 2. リクエストインターセプター（送信前に毎回実行される処理）
api.interceptors.request.use((config) => {
  // localStorageから 'token' を取得
  const token = localStorage.getItem('token');

  if (token) {
    // バックエンドの JwtAuthenticationFilter [5] が期待する形式
    // "Authorization: Bearer <トークン>" をヘッダーにセット
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;