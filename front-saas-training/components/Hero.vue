<template>
  <div class="bg-gradient-to-br from-indigo-50 to-indigo-100 py-16 md:py-24">
    <div class="max-w-7xl mx-auto px-6 lg:px-8">
      <div class="flex flex-col md:flex-row items-center md:justify-between">
        <!-- Partie texte -->
        <div class="md:w-2/3 mb-10 md:mb-0 md:pr-12">
          <h1 class="text-4xl md:text-5xl font-bold text-gray-900 mb-4 leading-tight">
            Reprenez le contrôle de vos abonnements.
          </h1>
          <p class="text-lg text-gray-600 mb-8">
            Visualisez ce que vous payez, chaque mois. Restez à jour, automatiquement.
          </p>
          <div class="flex flex-col sm:flex-row gap-4">
            <NuxtLink
              to="/register"
              class="px-8 py-3 bg-primary text-white font-semibold rounded-lg hover:bg-primary-dark transition-colors shadow-lg hover:shadow-xl"
            >
              Commencer maintenant
            </NuxtLink>
            <button
              class="px-8 py-3 border border-primary text-primary font-semibold rounded-lg hover:bg-indigo-50 transition-colors"
            >
              Voir les tarifs
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// Fonctionnalités optionnelles à ajouter:
// - Compteur de décompte pour une offre limitée
// - Fonction de scroll vers la section des tarifs
// - Système de témoignages rotatifs
</script>

<style scoped>
/* Styles supplémentaires si nécessaire */
</style>
