// services/passwordResetApi.ts
import { useFetch } from "nuxt/app";

export interface PasswordResetRequest {
  email?: string;
  token?: string;
  password?: string;
}

export interface PasswordResetResponse {
  message: string;
}

export const usePasswordResetApi = () => {
  const config = useRuntimeConfig();
  const baseUrl = config.public.apiBaseUrl || "http://localhost:8080";

  const forgotPassword = async (email: string) => {
    return await useFetch<PasswordResetResponse>(`${baseUrl}/password/forgot`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ email }),
    });
  };

  const validateToken = async (token: string) => {
    return await useFetch(`${baseUrl}/password/validate-token`, {
      method: "GET",
      params: { token },
    });
  };

  const resetPassword = async (token: string, password: string) => {
    return await useFetch<PasswordResetResponse>(`${baseUrl}/password/reset`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ token, password }),
    });
  };

  return {
    forgotPassword,
    validateToken,
    resetPassword,
  };
};
