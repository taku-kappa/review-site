import api from './axiosInstance';
import type { AuthResponse, LoginRequest, RegisterRequest } from '../types/auth';

export const authApi = {
  // ユーザー登録 [4]
  register: (data: RegisterRequest) =>
    api.post<AuthResponse>('/auth/register', data),

  // ログイン [4]
  login: (data: LoginRequest) =>
    api.post<AuthResponse>('/auth/login', data),
};