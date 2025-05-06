<template>
  <div class="bg-gradient-to-br from-primary/10 to-primary/5 py-16">
    <div class="max-w-7xl mx-auto px-6 lg:px-8">
      <div class="backdrop-blur-sm bg-white/70 rounded-xl shadow-xl overflow-hidden border border-white/20 p-8 md:p-12">
        <div class="flex flex-col md:flex-row items-center justify-between gap-8">
          <div class="text-center md:text-left">
            <h2 class="text-3xl font-bold text-gray-900 mb-4">Prêt à optimiser la gestion de vos finances ?</h2>
            <p class="text-lg text-gray-600 max-w-2xl">
              Rejoignez des milliers d'utilisateurs satisfaits et commencez gratuitement dès aujourd'hui.
            </p>
          </div>
          <div class="flex flex-col sm:flex-row gap-4">
            <NuxtLink
              to="/register"
              class="px-8 py-3 bg-primary text-white font-semibold rounded-lg hover:bg-primary-dark transition-all duration-300 shadow-lg hover:shadow-xl hover:scale-105"
            >
              Commencer gratuitement
            </NuxtLink>
            <NuxtLink
              to="/payment"
              class="px-8 py-3 border-2 border-primary text-primary font-semibold rounded-lg hover:bg-primary hover:text-white transition-all duration-300"
            >
              Passer au Premium
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// Possibilité d'ajouter des props pour personnaliser les textes et les liens
</script>
