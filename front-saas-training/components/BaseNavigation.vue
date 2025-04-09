<template>
  <nav class="bg-white shadow-md px-6 py-4 flex items-center justify-between">
    <!-- Logo et nom du site -->
    <div class="flex items-center space-x-2">
      <NuxtLink to="/" class="w-8 h-8 bg-indigo-600 rounded-full flex items-center justify-center">
        <span class="text-white font-bold text-lg">A</span>
      </NuxtLink>
      <span class="text-xl font-semibold text-gray-800">AppName</span>
    </div>

    <!-- Navigation centrale (optionnelle) -->
    <div class="hidden md:flex space-x-6">
      <a href="#" class="text-gray-600 hover:text-indigo-600 font-medium transition-colors">Accueil</a>
      <a href="#" class="text-gray-600 hover:text-indigo-600 font-medium transition-colors">Produits</a>
      <a href="#" class="text-gray-600 hover:text-indigo-600 font-medium transition-colors">Services</a>
      <a href="#" class="text-gray-600 hover:text-indigo-600 font-medium transition-colors">À propos</a>
    </div>

    <!-- Boutons d'action -->
    <div class="flex items-center space-x-4">
      <!-- Bouton Connexion/Inscription -->
      <NuxtLink
        to="/register"
        class="px-4 py-2 bg-indigo-600 text-white font-medium rounded-lg hover:bg-indigo-700 transition-colors flex items-center"
      >
        <span>Connexion</span>
      </NuxtLink>

      <!-- Menu mobile (hamburger) -->
      <div class="md:hidden">
        <button class="text-gray-600 hover:text-indigo-600 focus:outline-none">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16"></path>
          </svg>
        </button>
      </div>
    </div>
  </nav>
</template>

<script setup lang="ts">
// Vous pouvez ajouter vos logiques ici
// Par exemple:
// const isLoggedIn = ref(false)
// const toggleMenu = () => { ... }
</script>

<style scoped>
/* Styles supplémentaires si nécessaire */
</style>
