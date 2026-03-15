import { useState, useEffect } from 'react';
import api from '../api/axiosInstance';
import { UserResponse } from '../types';

export const useAuth = () => {
  const [user, setUser] = useState<UserResponse | null>(null);

  useEffect(() => {
    // UserController.getMe [8] を呼び出し
    api.get<UserResponse>('/users/me')
      .then(res => setUser(res.data))
      .catch(() => setUser(null));
  }, []);

  return { user, isAuthenticated: !!user };
};