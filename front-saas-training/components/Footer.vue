<template>
  <footer class="bg-gray-50 border-t border-gray-200/50">
    <div class="max-w-7xl mx-auto px-6 lg:px-8 py-12">
      <div class="grid grid-cols-1 md:grid-cols-4 gap-8">
        <!-- Logo et Description -->
        <div class="col-span-1 md:col-span-2">
          <h3 class="text-xl font-bold text-gray-900 mb-4">SaaS Training</h3>
          <p class="text-gray-600 mb-4">Solution innovante de gestion des abonnements et des finances personnelles.</p>
          <div class="flex space-x-4">
            <a href="#" class="text-gray-400 hover:text-primary">
              <span class="sr-only">Twitter</span>
              <svg class="h-6 w-6" fill="currentColor" viewBox="0 0 24 24">
                <path
                  d="M8.29 20.251c7.547 0 11.675-6.253 11.675-11.675 0-.178 0-.355-.012-.53A8.348 8.348 0 0022 5.92a8.19 8.19 0 01-2.357.646 4.118 4.118 0 001.804-2.27 8.224 8.224 0 01-2.605.996 4.107 4.107 0 00-6.993 3.743 11.65 11.65 0 01-8.457-4.287 4.106 4.106 0 001.27 5.477A4.072 4.072 0 012.8 9.713v.052a4.105 4.105 0 003.292 4.022 4.095 4.095 0 01-1.853.07 4.108 4.108 0 003.834 2.85A8.233 8.233 0 012 18.407a11.616 11.616 0 006.29 1.84"
                />
              </svg>
            </a>
            <a href="#" class="text-gray-400 hover:text-primary">
              <span class="sr-only">LinkedIn</span>
              <svg class="h-6 w-6" fill="currentColor" viewBox="0 0 24 24">
                <path
                  d="M19 0h-14c-2.761 0-5 2.239-5 5v14c0 2.761 2.239 5 5 5h14c2.762 0 5-2.239 5-5v-14c0-2.761-2.238-5-5-5zm-11 19h-3v-11h3v11zm-1.5-12.268c-.966 0-1.75-.79-1.75-1.764s.784-1.764 1.75-1.764 1.75.79 1.75 1.764-.783 1.764-1.75 1.764zm13.5 12.268h-3v-5.604c0-3.368-4-3.113-4 0v5.604h-3v-11h3v1.765c1.396-2.586 7-2.777 7 2.476v6.759z"
                />
              </svg>
            </a>
          </div>
        </div>

        <!-- Navigation -->
        <div>
          <h4 class="text-sm font-semibold text-gray-900 uppercase mb-4">Navigation</h4>
          <ul class="space-y-3">
            <li><NuxtLink to="/" class="text-gray-600 hover:text-primary">Accueil</NuxtLink></li>
            <li><NuxtLink to="/pricing" class="text-gray-600 hover:text-primary">Tarifs</NuxtLink></li>
            <li><NuxtLink to="/contact" class="text-gray-600 hover:text-primary">Contact</NuxtLink></li>
          </ul>
        </div>

        <!-- Légal -->
        <div>
          <h4 class="text-sm font-semibold text-gray-900 uppercase mb-4">Informations Légales</h4>
          <ul class="space-y-3">
            <li>
              <NuxtLink to="/mentions-legales" class="text-gray-600 hover:text-primary">Mentions légales</NuxtLink>
            </li>
            <li><NuxtLink to="/cgv" class="text-gray-600 hover:text-primary">CGV</NuxtLink></li>
            <li>
              <NuxtLink to="/confidentialite" class="text-gray-600 hover:text-primary"
                >Politique de confidentialité</NuxtLink
              >
            </li>
            <li><NuxtLink to="/cookies" class="text-gray-600 hover:text-primary">Gestion des cookies</NuxtLink></li>
          </ul>
        </div>
      </div>

      <!-- Barre de séparation -->
      <div class="border-t border-gray-200/50 mt-12 pt-8">
        <div class="flex flex-col md:flex-row justify-between items-center">
          <p class="text-gray-500 text-sm">© {{ new Date().getFullYear() }} SaaS Training. Tous droits réservés.</p>
          <div class="flex space-x-6 mt-4 md:mt-0">
            <NuxtLink to="/rgpd" class="text-sm text-gray-500 hover:text-primary">RGPD</NuxtLink>
            <NuxtLink to="/accessibilite" class="text-sm text-gray-500 hover:text-primary">Accessibilité</NuxtLink>
          </div>
        </div>
      </div>
    </div>
  </footer>
</template>

<script setup lang="ts">
// La date est automatiquement mise à jour chaque année
</script>
