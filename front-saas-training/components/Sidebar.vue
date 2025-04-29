<template>
  <!-- Desktop Sidebar (visible uniquement sur écrans moyens et grands) -->
  <aside class="hidden md:flex md:w-64 md:h-screen bg-white shadow-md border-r border-gray-200 md:flex-col md:fixed">
    <!-- Logo and brand -->
    <div class="p-4 border-b border-gray-200">
      <NuxtLink to="/" class="flex items-center">
        <div class="w-8 h-8 bg-primary rounded-full flex items-center justify-center mr-2 flex-shrink-0">
          <span class="text-white font-bold text-lg">S</span>
        </div>
        <span class="text-xl font-semibold text-gray-800">Subguard</span>
      </NuxtLink>
    </div>

    <!-- User profile section -->
    <div class="p-4">
      <button
        class="w-full flex items-center text-sm rounded-md p-2 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary"
      >
        <div
          class="h-8 w-8 rounded-full bg-primary-light flex items-center justify-center text-primary font-medium mr-3 flex-shrink-0"
        >
          {{ getUserInitial }}
        </div>
        <span class="text-gray-700 font-medium truncate">{{ userStore.fullName }}</span>
      </button>
    </div>

    <!-- Navigation links -->
    <nav class="flex-grow p-4 space-y-2">
      <NuxtLink
        to="/admin/dashboard"
        class="flex items-center px-4 py-3 rounded-lg text-base font-medium text-gray-700 hover:text-primary hover:bg-gray-50 transition-colors"
      >
        <span>Dashboard</span>
      </NuxtLink>
      <NuxtLink
        to="/admin/dashboard"
        class="flex items-center px-4 py-3 rounded-lg text-base font-medium text-gray-700 hover:text-primary hover:bg-gray-50 transition-colors"
      >
        <span>Subscriptions</span>
      </NuxtLink>
      <NuxtLink
        to="/admin/dashboard"
        class="flex items-center px-4 py-3 rounded-lg text-base font-medium text-gray-700 hover:text-primary hover:bg-gray-50 transition-colors"
      >
        <span>Upcoming</span>
      </NuxtLink>
      <NuxtLink
        to="/"
        class="flex items-center px-4 py-3 rounded-lg text-base font-medium text-gray-700 hover:text-primary hover:bg-gray-50 transition-colors"
      >
        <span>Change Plan</span>
      </NuxtLink>
    </nav>

    <!-- Settings Link at the bottom -->
    <div class="mt-auto p-4 border-t border-gray-200">
      <NuxtLink
        to="/admin/settings"
        class="flex items-center px-4 py-3 rounded-lg text-base font-medium text-gray-700 hover:text-primary hover:bg-gray-50 transition-colors"
      >
        <span>Settings</span>
      </NuxtLink>
    </div>
  </aside>

  <!-- Mobile Top Navigation (visible uniquement sur petits écrans) -->
  <div class="md:hidden fixed top-0 left-0 right-0 bg-white shadow-md z-10">
    <div class="flex items-center justify-between p-4">
      <!-- Logo -->
      <NuxtLink to="/" class="flex items-center">
        <div class="w-8 h-8 bg-primary rounded-full flex items-center justify-center mr-2 flex-shrink-0">
          <span class="text-white font-bold text-lg">S</span>
        </div>
        <span class="text-xl font-semibold text-gray-800">Subguard</span>
      </NuxtLink>

      <!-- User & Settings -->
      <div class="flex items-center space-x-2">
        <!-- User profile -->
        <button
          class="flex items-center text-sm rounded-md p-2 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary"
        >
          <div
            class="h-8 w-8 rounded-full bg-primary-light flex items-center justify-center text-primary font-medium flex-shrink-0"
          >
            {{ getUserInitial }}
          </div>
        </button>

        <!-- Settings -->
        <NuxtLink
          to="/admin/settings"
          class="flex items-center p-2 rounded-lg text-base font-medium text-gray-700 hover:text-primary hover:bg-gray-50 transition-colors"
        >
          <span>⚙️</span>
        </NuxtLink>
      </div>
    </div>
  </div>

  <!-- Mobile Bottom Navigation (visible uniquement sur petits écrans) -->
  <div class="md:hidden fixed bottom-0 left-0 right-0 bg-white shadow-md border-t border-gray-200 z-10">
    <nav class="flex justify-around p-2">
      <NuxtLink
        to="/admin/dashboard"
        class="flex flex-col items-center px-2 py-2 text-xs font-medium text-gray-700 hover:text-primary transition-colors"
      >
        <span>Dashboard</span>
      </NuxtLink>
      <NuxtLink
        to="/admin/dashboard"
        class="flex flex-col items-center px-2 py-2 text-xs font-medium text-gray-700 hover:text-primary transition-colors"
      >
        <span>Subscriptions</span>
      </NuxtLink>
      <NuxtLink
        to="/admin/dashboard"
        class="flex flex-col items-center px-2 py-2 text-xs font-medium text-gray-700 hover:text-primary transition-colors"
      >
        <span>Upcoming</span>
      </NuxtLink>
      <NuxtLink
        to="/"
        class="flex flex-col items-center px-2 py-2 text-xs font-medium text-gray-700 hover:text-primary transition-colors"
      >
        <span>Change Plan</span>
      </NuxtLink>
    </nav>
  </div>

  <!-- Contenu principal - ajoutez une marge pour éviter le chevauchement avec les navbars -->
  <div class="md:ml-64 pt-4 pb-16 md:pt-0 md:pb-0">
    <!-- Slot pour contenu principal -->
    <slot></slot>
  </div>
</template>

<script setup>
import { useUserStore } from "~/stores/user";
import { computed } from "vue";

const userStore = useUserStore();

// Calculer l'initiale de l'utilisateur à partir de son prénom
const getUserInitial = computed(() => {
  // En supposant que userStore.fullName contient le nom complet
  // et que userStore.firstName existe ou peut être extrait du nom complet

  // Si firstName existe directement dans le store
  if (userStore.firstName) {
    return userStore.firstName.charAt(0).toUpperCase();
  }

  // Sinon, on extrait le prénom à partir du nom complet
  else if (userStore.fullName) {
    // Prendre le premier mot du nom complet comme prénom
    const firstName = userStore.fullName.split(" ")[0];
    return firstName.charAt(0).toUpperCase();
  }

  // Fallback si aucune information n'est disponible
  return "U";
});
</script>

<style scoped>
/* Vous pouvez ajouter des styles spécifiques si nécessaire */
</style>
