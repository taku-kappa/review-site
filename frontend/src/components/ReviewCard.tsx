import React from 'react';
import type { Review } from '../types';
import { reviewApi } from '../api/reviewApi';

export const ReviewCard: React.FC<{ review: Review; onUpdate: () => void }> = ({ review, onUpdate }) => {
  const handleDelete = async () => {
    if (window.confirm('削除しますか？')) {
      await reviewApi.deleteReview(review.id);
      onUpdate();
    }
  };

  return (
    <div style={{ border: '1px solid #ccc', margin: '10px', padding: '10px' }}>
      <h4>{review.authorName}</h4>
      <h3>{review.title}</h3>
      <p>{review.content}</p>
      <small>評価: {review.averageRating} ({review.ratingsCount}件)</small>
      <button onClick={handleDelete}>削除</button>
    </div>
  );
};