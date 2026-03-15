import React, { useState } from 'react';
import { authApi } from '../api/authApi';
import { authStorage } from '../utils/authStorage';

export const Register = () => {
  const [form, setForm] = useState({ username: '', email: '', password: '' });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const res = await authApi.register(form);
    authStorage.setToken(res.data.accessToken);
    window.location.href = '/';
  };

  return (
    <form onSubmit={handleSubmit}>
      <h1>ユーザー登録</h1>
      <input type="text" placeholder="名前" onChange={e => setForm({...form, username: e.target.value})} />
      <input type="email" placeholder="メール" onChange={e => setForm({...form, email: e.target.value})} />
      <input type="password" placeholder="パスワード" onChange={e => setForm({...form, password: e.target.value})} />
      <button type="submit">登録</button>
    </form>
  );
};