// services/authApi.ts
import { useFetch } from "nuxt/app";
import type { User } from "./userApi";

export interface LoginRequest {
  email: string;
  password: string;
}

export interface JwtResponse {
  token: string;
  user?: User; // Ajoutez l'utilisateur à la réponse JWT si votre API le renvoie
}

export const useAuthApi = () => {
  const config = useRuntimeConfig();
  const baseUrl = config.public.apiBaseUrl || "http://localhost:8080";

  const login = async (credentials: LoginRequest) => {
    const response = await useFetch<JwtResponse>(`${baseUrl}/auth/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(credentials),
    });

    if (response.data.value) {
      // Stocker le token
      localStorage.setItem("token", response.data.value.token);
    }

    return response;
  };

  // Ajout de cette nouvelle fonction pour récupérer les infos utilisateur
  const fetchCurrentUser = async () => {
    const token = localStorage.getItem("token");
    if (!token) return null;

    return await useFetch<User>(`${baseUrl}/user/current`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  };

  // Ajout de cette fonction pour récupérer l'utilisateur du localStorage
  const getCurrentUser = () => {
    const userStr = localStorage.getItem("currentUser");
    return userStr ? (JSON.parse(userStr) as User) : null;
  };

  const checkAuth = () => {
    const token = localStorage.getItem("token");
    return !!token;
  };

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("currentUser");
  };

  return {
    login,
    checkAuth,
    logout,
    fetchCurrentUser,
    getCurrentUser,
  };
};
