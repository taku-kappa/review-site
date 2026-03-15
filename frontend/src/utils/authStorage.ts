/**
 * 認証トークン（JWT）をLocalStorageで管理するためのユーティリティ
 */
export const authStorage = {
  // 保存に使用するキー名（一貫していれば何でも良いですが 'token' とします）
  TOKEN_KEY: 'token',

  // トークンを取得する
  getToken: (): string | null => {
    return localStorage.getItem(authStorage.TOKEN_KEY);
  },

  // トークンを保存する（ログイン・会員登録成功時に呼び出す）
  setToken: (token: string): void => {
    localStorage.setItem(authStorage.TOKEN_KEY, token);
  },

  // トークンを削除する（ログアウト時に呼び出す）
  clearToken: (): void => {
    localStorage.removeItem(authStorage.TOKEN_KEY);
  },
};