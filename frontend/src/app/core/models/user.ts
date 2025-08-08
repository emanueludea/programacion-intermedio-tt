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
  username: string;
  isAdmin: boolean;
}

export interface AuthStatusResponse {
  authenticated: boolean;
  user?: User;
}
