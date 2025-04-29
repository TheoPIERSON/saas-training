<!-- components/LogoutButton.vue -->
<template>
  <button
    @click="handleLogout"
    class="relative flex items-center gap-2 rounded-xl bg-primary/10 px-4 py-2.5 backdrop-blur-md transition-all duration-300 hover:bg-primary/15 hover:shadow-glass focus:outline-none focus:ring-2 focus:ring-primary/30 active:scale-95"
  >
    <!-- Effet de bordure glassmorphism -->
    <span class="absolute inset-0 rounded-xl border border-primary/20 group-hover:border-primary/30"></span>

    <!-- Icône de déconnexion -->
    <svg
      xmlns="http://www.w3.org/2000/svg"
      class="h-5 w-5 text-primary-dark transition-transform duration-300 group-hover:-translate-x-0.5"
      fill="none"
      viewBox="0 0 24 24"
      stroke="currentColor"
    >
      <path
        stroke-linecap="round"
        stroke-linejoin="round"
        stroke-width="2"
        d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1"
      />
    </svg>

    <!-- Texte avec effet de transition sur hover -->
    <span class="font-montserrat text-sm font-medium text-primary-dark"> Déconnexion </span>

    <!-- Effet de brillance subtil (highlight) -->
    <div class="absolute inset-0 overflow-hidden rounded-xl">
      <div
        class="absolute -inset-x-1/2 -top-1/2 h-full w-1/3 rotate-12 bg-gradient-to-r from-transparent via-primary/10 to-transparent transform-gpu blur-xl transition-all duration-1000 group-hover:translate-x-full"
      ></div>
    </div>
  </button>
</template>

<script setup>
import { useUserStore } from "~/stores/user";

const userStore = useUserStore();
const router = useRouter();

const handleLogout = async () => {
  await userStore.logout();
  // Redirection vers la page de connexion
  router.push("/connexion");
};

// Initialiser le store si nécessaire
onMounted(() => {
  if (!userStore.isLoggedIn) {
    userStore.init();
  }
});
</script>
