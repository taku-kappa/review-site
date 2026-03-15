import React, { useState } from 'react';
import api from '../api/axiosInstance';
import { authStorage } from '../utils/authStorage';

export const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    // AuthService.login [10] を呼び出し
    const res = await api.post('/auth/login', { email, password });
    authStorage.setToken(res.data.accessToken);
    window.location.href = '/';
  };

  return (
    <form onSubmit={handleLogin}>
      <input type="email" value={email} onChange={e => setEmail(e.target.value)} placeholder="Email" />
      <input type="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="Password" />
      <button type="submit">ログイン</button>
    </form>
  );
};