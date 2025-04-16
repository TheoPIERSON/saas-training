import { useFetch } from "nuxt/app";

export interface LoginRequest {
  email: string;
  password: string;
}

export interface JwtResponse {
  token: string;
}

export const useAuthApi = () => {
  const config = useRuntimeConfig();
  const baseUrl = config.public.apiBaseUrl || "http://localhost:8080";

  // Fonction de connexion qui appelle votre endpoint /auth/login
  const login = async (credentials: LoginRequest) => {
    return await useFetch<JwtResponse>(`${baseUrl}/auth/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(credentials),
    });
  };

  // Fonction pour vérifier si l'utilisateur est connecté
  const checkAuth = () => {
    const token = localStorage.getItem("token");
    return !!token;
  };

  // Fonction de déconnexion
  const logout = () => {
    localStorage.removeItem("token");
    // Si vous utilisez useState ou Pinia pour la gestion de l'état, vous devrez également réinitialiser l'état ici
  };

  return {
    login,
    checkAuth,
    logout,
  };
};
