import React, { useEffect, useState } from 'react';
import api from '../api/axiosInstance';
import type { UserResponse } from '../types';

export const Profile = () => {
  const [profile, setProfile] = useState<UserResponse | null>(null);

  useEffect(() => {
    api.get<UserResponse>('/users/me').then(res => setProfile(res.data));
  }, []);

  if (!profile) return <div>読み込み中...</div>;

  return (
    <div>
      <img src={profile.profileImageUrl || '/default-user.png'} alt="profile" />
      <h1>{profile.username}</h1>
      <p>{profile.bio}</p>
      <div>
        <span>フォロー: {profile.followingCount}</span>
        <span>フォロワー: {profile.followersCount}</span>
      </div>
    </div>
  );
};