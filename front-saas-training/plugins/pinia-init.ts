// plugins/pinia-init.ts
import { defineNuxtPlugin } from "#app";
import { useUserStore } from "~/stores/user";

import type { Pinia } from "pinia";

export default defineNuxtPlugin((nuxtApp) => {
  // Initialiser le store utilisateur
  setTimeout(() => {
    const userStore = useUserStore();
    userStore.init();
  }, 0);
});
