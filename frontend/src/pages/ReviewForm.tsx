import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { reviewApi } from '../api/reviewApi';

export const ReviewForm = () => {
  const { id } = useParams(); // IDがあれば更新モード
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (id) {
      await reviewApi.updateReview(Number(id), { title, content });
    } else {
      // 新規作成時は targetType 等も必要 [12]
      await reviewApi.createReview({ title, content, targetType: 'ITEM', targetId: 1 });
    }
    window.location.href = '/';
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>レビュー{id ? '編集' : '投稿'}</h2>
      <input value={title} onChange={e => setTitle(e.target.value)} placeholder="タイトル" />
      <textarea value={content} onChange={e => setContent(e.target.value)} placeholder="内容" />
      <button type="submit">保存</button>
    </form>
  );
};