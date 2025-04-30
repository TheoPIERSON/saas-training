// stores/user.ts
import { defineStore } from "pinia";
import type { User } from "~/services/userApi";
import { useAuthApi } from "~/services/authApi";

export const useUserStore = defineStore("user", {
  state: () => ({
    currentUser: null as User | null,
    isLoggedIn: false,
  }),

  getters: {
    fullName: (state) => {
      if (!state.currentUser) return "";
      return `${state.currentUser.firstname} ${state.currentUser.lastname}`;
    },
    isPremium: (state) => {
      return state.currentUser?.premium || false;
    },
  },

  actions: {
    async login(email: string, password: string) {
      const authApi = useAuthApi();
      const response = await authApi.login({ email, password });

      if (response.data.value) {
        // Le token a été stocké dans le localStorage par la fonction login
        this.isLoggedIn = true;

        // Récupérer l'utilisateur avec un appel séparé
        await this.fetchCurrentUser();
      }

      return response;
    },

    async fetchCurrentUser() {
      const authApi = useAuthApi();
      const response = await authApi.fetchCurrentUser();

      if (response && response.data.value) {
        this.currentUser = response.data.value;
        // Stocker l'utilisateur dans le localStorage pour une utilisation hors ligne
        localStorage.setItem("currentUser", JSON.stringify(response.data.value));
        this.isLoggedIn = true;
      }

      return response;
    },

    logout() {
      const authApi = useAuthApi();
      authApi.logout();
      this.currentUser = null;
      this.isLoggedIn = false;
    },

    // Nouvelle méthode pour mettre à jour le statut premium
    updatePremiumStatus(isPremium: boolean) {
      if (this.currentUser) {
        this.currentUser.premium = isPremium;
        // Mettre à jour également dans le localStorage
        localStorage.setItem("currentUser", JSON.stringify(this.currentUser));
      }
    },

    // Initialisation du store depuis le localStorage au chargement de l'app
    init() {
      const authApi = useAuthApi();
      if (authApi.checkAuth()) {
        this.isLoggedIn = true;
        this.currentUser = authApi.getCurrentUser();

        // Rafraîchir les données depuis le serveur
        this.fetchCurrentUser();
      }
    },
  },
});
