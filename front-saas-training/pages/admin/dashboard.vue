<template>
  <div class="min-h-screen bg-gray-100 p-6 flex flex-col items-center justify-center">
    <div class="bg-white p-6 rounded-2xl shadow-lg w-full max-w-md text-center">
      <h1 class="text-2xl font-semibold mb-2">Bienvenue sur votre Dashboard</h1>
      <p class="text-gray-600 mb-6">Vous utilisez actuellement le plan gratuit.</p>
      <button class="bg-blue-600 text-white px-4 py-2 rounded-xl hover:bg-blue-700 transition">
        🔥 Mettre à niveau vers Premium
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
definePageMeta({
  middleware: ["auth"],
});
</script>

<style scoped>
/* Style supplémentaire si besoin */
</style>
