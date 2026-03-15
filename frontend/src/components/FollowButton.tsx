import React, { useState } from 'react';
import api from '../api/axiosInstance';

export const FollowButton: React.FC<{ targetUserId: number; isFollowingInitial: boolean }> = ({ targetUserId, isFollowingInitial }) => {
  const [isFollowing, setIsFollowing] = useState(isFollowingInitial);

  const toggleFollow = async () => {
    try {
      if (isFollowing) {
        await api.delete(`/users/${targetUserId}/unfollow`);
      } else {
        await api.post(`/users/${targetUserId}/follow`);
      }
      setIsFollowing(!isFollowing);
    } catch (e) {
      alert('フォロー操作に失敗しました');
    }
  };

  return (
    <button onClick={toggleFollow}>
      {isFollowing ? 'フォロー解除' : 'フォローする'}
    </button>
  );
};