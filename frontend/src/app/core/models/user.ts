export interface User {
  username: string;
  userPassword?: string;
  isAdmin: boolean;
}

export interface UserDTO {
  username: string;
  userPassword?: string;
  isAdmin: boolean;
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  username: string;
  isAdmin: boolean;
}
