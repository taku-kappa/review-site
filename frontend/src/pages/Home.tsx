import React, { useEffect, useState } from 'react';
import { reviewApi } from '../api/reviewApi';
import type { Review } from '../types';
import { ReviewCard } from '../components/ReviewCard';

export const Home = () => {
  const [reviews, setReviews] = useState<Review[]>([]);

  const fetchReviews = async () => {
    const res = await reviewApi.getReviews();
    setReviews(res.data.content);
  };

  useEffect(() => { fetchReviews(); }, []);

  return (
    <div>
      <h1>レビュー一覧</h1>
      {reviews.map(r => (
        <ReviewCard key={r.id} review={r} onUpdate={fetchReviews} />
      ))}
    </div>
  );
};