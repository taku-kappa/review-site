import api from './axiosInstance';
import type { Review, ReviewListResponse } from '../types';

export const reviewApi = {
  getReviews: () => api.get<ReviewListResponse>('/reviews'),

  createReview: (data: {
    title: string;
    content: string;
    targetType: string;
    targetId: number;
  }) => api.post('/reviews', data),

  deleteReview: (id: number) => api.delete(`/reviews/${id}`),

  rateReview: (reviewId: number, rating: number) =>
    api.post(`/reviews/${reviewId}/rating`, { rating })
};