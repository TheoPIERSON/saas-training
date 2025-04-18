// services/userApi.ts
import { useFetch } from "nuxt/app";

export interface User {
  id?: number;
  firstname: string; // Pour le prénom
  lastname: string; // Pour le nom
  email: string;
  password: string;
}

export const useUserApi = () => {
  const config = useRuntimeConfig();
  const baseUrl = config.public.apiBaseUrl || "http://localhost:8080";

  // Récupérer tous les utilisateurs
  const getAllUsers = async () => {
    return await useFetch(`${baseUrl}/user/all-users`);
  };

  // Récupérer un utilisateur par son ID
  const getUserById = async (id: number) => {
    return await useFetch(`${baseUrl}/user/users/${id}`);
  };

  // Ajouter un nouvel utilisateur (inscription)
  const addUser = async (userData: User) => {
    return await useFetch(`${baseUrl}/user/add-user`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(userData),
    });
  };

  return {
    getAllUsers,
    getUserById,
    addUser,
  };
};
