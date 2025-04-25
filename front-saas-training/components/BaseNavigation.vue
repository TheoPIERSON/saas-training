<template>
  <div>
    <nav class="bg-white shadow-md px-3 md:px-6 py-4 md:py-3 flex items-center justify-between">
      <!-- Logo et nom du site -->
      <div class="flex items-center space-x-2">
        <NuxtLink to="/" class="w-8 h-8 bg-primary rounded-full flex items-center justify-center">
          <span class="text-white font-bold text-lg">S</span>
        </NuxtLink>
        <NuxtLink to="/">
          <span class="text-xl font-semibold text-gray-800">Subguard</span>
        </NuxtLink>
      </div>

      <!-- Navigation centrale (visible sur desktop) -->
      <div class="hidden md:flex space-x-6">
        <a href="#" class="text-gray-600 hover:text-primary font-medium transition-colors">Accueil</a>
        <a href="#" class="text-gray-600 hover:text-primary font-medium transition-colors">Produits</a>
        <a href="#" class="text-gray-600 hover:text-primary font-medium transition-colors">Services</a>
        <a href="#" class="text-gray-600 hover:text-primary font-medium transition-colors">À propos</a>
      </div>

      <!-- Boutons d'action (toujours visibles mais adaptés) -->
      <div class="flex items-center space-x-1 md:space-x-4">
        <!-- Boutons toujours visibles mais adaptés pour mobile -->
        <div class="flex space-x-4 sm:space-x-2">
          <NuxtLink
            to="/register"
            class="px-3 py-2 sm:px-3 sm:py-2 md:px-4 md:py-2 text-xs sm:text-sm md:text-base bg-primary text-white font-medium rounded-lg hover:bg-primary-dark transition-colors flex items-center"
          >
            <span>Inscription</span>
          </NuxtLink>
          <NuxtLink
            to="/connexion"
            class="px-3 py-2 sm:px-3 sm:py-1.5 md:px-4 md:py-2 text-xs sm:text-sm md:text-base bg-primary text-white font-medium rounded-lg hover:bg-primary-dark transition-colors flex items-center"
          >
            <span>Connexion</span>
          </NuxtLink>
        </div>

        <!-- Menu mobile (hamburger) -->
        <button
          @click="toggleMobileMenu"
          class="md:hidden ml-1 text-gray-600 hover:text-primary focus:outline-none"
          aria-label="Menu mobile"
        >
          <svg
            v-if="!isMobileMenuOpen"
            class="w-6 h-6"
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16"></path>
          </svg>
          <svg
            v-else
            class="w-6 h-6"
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
          </svg>
        </button>
      </div>
    </nav>

    <!-- Menu mobile déroulant (sans les boutons d'action) -->
    <div
      v-if="isMobileMenuOpen"
      class="fixed top-[62px] left-0 right-0 bg-white shadow-md md:hidden border-t border-gray-100 animate-slideDown z-50"
    >
      <div class="flex flex-col p-4 space-y-3">
        <!-- Liens de navigation mobile -->
        <a
          href="#"
          class="text-gray-600 hover:text-primary font-medium py-2 px-3 rounded hover:bg-gray-50 transition-colors"
          >Accueil</a
        >
        <a
          href="#"
          class="text-gray-600 hover:text-primary font-medium py-2 px-3 rounded hover:bg-gray-50 transition-colors"
          >Produits</a
        >
        <a
          href="#"
          class="text-gray-600 hover:text-primary font-medium py-2 px-3 rounded hover:bg-gray-50 transition-colors"
          >Services</a
        >
        <a
          href="#"
          class="text-gray-600 hover:text-primary font-medium py-2 px-3 rounded hover:bg-gray-50 transition-colors"
          >À propos</a
        >
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";

// État pour le menu mobile
const isMobileMenuOpen = ref(false);

// Fonction pour basculer le menu mobile
const toggleMobileMenu = () => {
  isMobileMenuOpen.value = !isMobileMenuOpen.value;
};

// Optionnel: fermer le menu lorsqu'une route change
// Si vous utilisez Vue Router ou Nuxt
// À décommenter si nécessaire:
/*
import { useRouter } from 'vue-router'; // ou 'nuxt/app' pour Nuxt
const router = useRouter();

router.afterEach(() => {
  isMobileMenuOpen.value = false;
});
*/
</script>

<style scoped>
.animate-slideDown {
  animation: slideDown 0.2s ease-out forwards;
}

@keyframes slideDown {
  from {
    opacity: 0;
    transform: translateY(-10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
