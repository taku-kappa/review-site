import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { authStorage } from '../utils/authStorage';

/* 追加：スタイル定義 */
const headerStyle: React.CSSProperties = {
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  padding: "12px 20px",
  backgroundColor: "#333",
  color: "white"
};

const navStyle: React.CSSProperties = {
  display: "flex",
  gap: "15px",
  alignItems: "center"
};

const linkStyle: React.CSSProperties = {
  color: "white",
  textDecoration: "none"
};

const postButtonStyle: React.CSSProperties = {
  backgroundColor: "#4CAF50",
  color: "white",
  padding: "6px 10px",
  borderRadius: "4px",
  textDecoration: "none"
};

const logoutButtonStyle: React.CSSProperties = {
  backgroundColor: "#f44336",
  color: "white",
  border: "none",
  padding: "6px 10px",
  borderRadius: "4px",
  cursor: "pointer"
};

export const Header = () => {
  const navigate = useNavigate();

  const isAuthenticated = !!authStorage.getToken();

  const handleLogout = () => {
    authStorage.clearToken();
    navigate('/login');
    window.location.reload();
  };

  return (
    <header style={headerStyle}>
      <div className="logo">
        <Link to="/" style={linkStyle}>
          <strong>Review Site</strong>
        </Link>
      </div>

      <nav style={navStyle}>
        <Link to="/" style={linkStyle}>ホーム</Link>

        {isAuthenticated && (
          <Link to="/reviews/new" style={postButtonStyle}>
            投稿する
          </Link>
        )}

        {isAuthenticated ? (
          <>
            <Link to="/profile" style={linkStyle}>プロフィール</Link>
            <button onClick={handleLogout} style={logoutButtonStyle}>
              ログアウト
            </button>
          </>
        ) : (
          <>
            <Link to="/login" style={linkStyle}>ログイン</Link>
            <Link to="/register" style={linkStyle}>ユーザー登録</Link>
          </>
        )}
      </nav>
    </header>
  );
};