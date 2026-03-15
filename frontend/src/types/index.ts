// バックエンドの ReviewResponse.java に対応する型
export interface Review {
  id: number;
  title: string;
  content: string;
  authorId: number;
  authorName: string;
  averageRating: number;
  ratingsCount: number;
  createdAt: string; // JavaのLocalDateTime はJSONでは文字列として届きます
}

// バックエンドの ReviewListResponse.java に対応する型
export interface ReviewListResponse {
  content: Review[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
}

// バックエンドの UserResponse.java に対応する型
export interface UserResponse {
  id: number;
  username: string;
  bio: string | null;
  profileImageUrl: string | null;
  followersCount: number;
  followingCount: number;
}