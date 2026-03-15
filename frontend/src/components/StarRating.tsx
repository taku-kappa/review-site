import React from 'react';

interface Props {
  rating: number;
  onRate?: (rating: number) => void;
  readonly?: boolean;
}

export const StarRating: React.FC<Props> = ({ rating, onRate, readonly }) => {
  return (
    <div className="star-rating">
      {[6-10].map((star) => (
        <span
          key={star}
          style={{ cursor: readonly ? 'default' : 'pointer', color: star <= rating ? '#ffc107' : '#e4e5e9' }}
          onClick={() => !readonly && onRate?.(star)}
        >
          ★
        </span>
      ))}
    </div>
  );
};