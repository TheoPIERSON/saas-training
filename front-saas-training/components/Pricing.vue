<template>
  <section class="py-12">
    <div class="container mx-auto px-4">
      <div class="text-center mb-12">
        <h2 class="text-3xl font-bold text-gray-900 mb-4">Maximisez votre gestion financière</h2>
        <p class="text-lg text-gray-600 max-w-2xl mx-auto">
          Passez au Premium et prenez le contrôle total de vos finances avec des analyses avancées, des projets
          illimités et un support prioritaire. Pour le prix d'un café !
        </p>
      </div>

      <div class="grid md:grid-cols-2 gap-8 max-w-4xl mx-auto">
        <!-- Freemium Plan -->
        <div
          class="backdrop-blur-sm bg-white/70 rounded-xl shadow-xl overflow-hidden border border-white/20 transition-transform duration-300 hover:transform hover:scale-105"
        >
          <div class="px-6 py-8 border-b border-gray-200/30">
            <h3 class="text-2xl font-bold text-gray-900 mb-1">Freemium</h3>
            <p class="text-gray-500">Démarrez gratuitement</p>
            <div class="mt-4">
              <span class="text-4xl font-bold text-gray-900">0€</span>
              <span class="text-gray-500">/mois</span>
            </div>
          </div>
          <div class="px-6 py-8 bg-white/40">
            <ul class="space-y-4">
              <li class="flex items-start">
                <svg class="h-6 w-6 text-green-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                <span>Basic features</span>
              </li>
              <li class="flex items-start">
                <svg class="h-6 w-6 text-green-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                <span>Up to 5 projects</span>
              </li>
              <li class="flex items-start">
                <svg class="h-6 w-6 text-green-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                <span>Community support</span>
              </li>
            </ul>
            <NuxtLink to="/register" class="block">
              <button
                class="mt-8 w-full py-3 px-4 bg-gray-200/80 hover:bg-gray-300/80 text-gray-900 font-medium rounded-lg backdrop-blur-sm transition duration-300"
              >
                Commencer
              </button>
            </NuxtLink>
          </div>
        </div>

        <!-- Premium Plan -->
        <div
          class="backdrop-blur-sm bg-white/70 rounded-xl shadow-xl overflow-hidden border-2 border-primary/40 transition-transform duration-300 hover:transform hover:scale-105"
        >
          <div class="px-6 py-8 border-b border-gray-200/30 bg-primary-light/70">
            <span
              class="inline-block px-3 py-1 text-xs font-semibold text-black bg-secondary/80 backdrop-blur-sm rounded-full mb-2"
              >RECOMMANDÉ</span
            >
            <h3 class="text-2xl font-bold text-gray-900 mb-1">Premium</h3>
            <p class="text-gray-900">Pour les professionnels</p>
            <div class="mt-4">
              <span class="text-4xl font-bold text-gray-900">2.99€</span>
              <span class="text-gray-900">/mois</span>
            </div>
          </div>
          <div class="px-6 py-8 bg-white/40">
            <ul class="space-y-4">
              <li class="flex items-start">
                <svg class="h-6 w-6 text-green-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                <span>All Freemium features</span>
              </li>
              <li class="flex items-start">
                <svg class="h-6 w-6 text-green-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                <span>Unlimited projects</span>
              </li>
              <li class="flex items-start">
                <svg class="h-6 w-6 text-green-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                <span>Priority support</span>
              </li>
              <li class="flex items-start">
                <svg class="h-6 w-6 text-green-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                <span>Advanced analytics</span>
              </li>
            </ul>
            <NuxtLink to="/payment" class="block">
              <button
                class="mt-8 w-full py-3 px-4 bg-primary/90 hover:bg-primary text-white font-medium rounded-lg backdrop-blur-sm transition duration-300"
              >
                Passer au Premium
              </button>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "Pricing",
};
</script>
