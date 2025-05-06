<template>
  <div class="bg-gradient-to-br from-white to-green-100 py-16 md:py-24">
    <div class="max-w-7xl mx-auto px-6 lg:px-8">
      <div class="text-center mb-12">
        <h1 class="text-4xl md:text-5xl font-bold text-gray-900 mb-4 leading-tight">
          Choisissez le plan qui vous correspond
        </h1>
        <p class="text-lg text-gray-600 mb-8">Comparez nos offres et trouvez celle qui répond le mieux à vos besoins</p>
      </div>

      <div class="backdrop-blur-sm bg-white/70 rounded-xl shadow-xl overflow-hidden border border-white/20">
        <table class="w-full">
          <thead>
            <tr class="border-b border-gray-200/30">
              <th class="py-4 px-6 text-left">Fonctionnalités</th>
              <th class="py-4 px-6 text-center bg-gray-50/50">
                <span class="block text-lg font-bold text-gray-900">Freemium</span>
                <span class="text-sm text-gray-500">0€/mois</span>
              </th>
              <th class="py-4 px-6 text-center bg-primary-light/20">
                <span class="inline-block px-3 py-1 text-xs font-semibold text-black bg-secondary/80 rounded-full mb-1"
                  >RECOMMANDÉ</span
                >
                <span class="block text-lg font-bold text-gray-900">Premium</span>
                <span class="text-sm text-gray-900">2.99€/mois</span>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr class="border-b border-gray-200/30">
              <td class="py-4 px-6">Import de transactions</td>
              <td class="py-4 px-6 text-center">Limité à 100/mois</td>
              <td class="py-4 px-6 text-center">Illimité</td>
            </tr>
            <tr class="border-b border-gray-200/30">
              <td class="py-4 px-6">Projets</td>
              <td class="py-4 px-6 text-center">5 maximum</td>
              <td class="py-4 px-6 text-center">Illimité</td>
            </tr>
            <tr class="border-b border-gray-200/30">
              <td class="py-4 px-6">Analyses et rapports</td>
              <td class="py-4 px-6 text-center">Basique</td>
              <td class="py-4 px-6 text-center">Avancés</td>
            </tr>
            <tr class="border-b border-gray-200/30">
              <td class="py-4 px-6">Support</td>
              <td class="py-4 px-6 text-center">Communautaire</td>
              <td class="py-4 px-6 text-center">Prioritaire</td>
            </tr>
            <tr class="border-b border-gray-200/30">
              <td class="py-4 px-6">Catégorisation automatique</td>
              <td class="py-4 px-6 text-center">
                <svg class="h-6 w-6 text-red-500 mx-auto" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                </svg>
              </td>
              <td class="py-4 px-6 text-center">
                <svg class="h-6 w-6 text-green-500 mx-auto" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
              </td>
            </tr>
            <tr>
              <td class="py-4 px-6"></td>
              <td class="py-4 px-6 text-center">
                <NuxtLink
                  to="/register"
                  class="inline-block px-6 py-2 bg-gray-200/80 hover:bg-gray-300/80 text-gray-900 font-medium rounded-lg transition duration-300"
                >
                  Commencer
                </NuxtLink>
              </td>
              <td class="py-4 px-6 text-center">
                <NuxtLink
                  to="/payment"
                  class="inline-block px-6 py-2 bg-primary/90 hover:bg-primary text-white font-medium rounded-lg transition duration-300"
                >
                  Passer au Premium
                </NuxtLink>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// Les fonctionnalités peuvent être étendues avec :
// - Animation au survol des lignes
// - Tooltips pour plus de détails sur chaque fonctionnalité
// - Basculement mensuel/annuel des prix
</script>

<style scoped>
/* Styles supplémentaires si nécessaire */
</style>
