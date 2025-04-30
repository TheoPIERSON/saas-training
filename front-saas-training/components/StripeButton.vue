<template>
  <NuxtLink
    to="/payment"
    class="group mt-8 px-8 py-4 rounded-xl font-semibold font-sans transition-all duration-300 ease-in-out bg-gradient-to-r from-yellow-400 to-yellow-500 hover:from-yellow-500 hover:to-yellow-600 text-gray-900 shadow-lg hover:shadow-xl active:scale-95 flex items-center gap-2"
  >
    <svg
      xmlns="http://www.w3.org/2000/svg"
      class="h-5 w-5 group-hover:scale-110 transition-transform"
      viewBox="0 0 24 24"
      fill="currentColor"
    >
      <path
        d="M11.999 1.5c-2.485 0-4.5 2.015-4.5 4.5s2.015 4.5 4.5 4.5 4.5-2.015 4.5-4.5-2.015-4.5-4.5-4.5zm0 7.5c-1.657 0-3-1.343-3-3s1.343-3 3-3 3 1.343 3 3-1.343 3-3 3zm-5 2c-1.103 0-2 .897-2 2v7c0 1.103.897 2 2 2h10c1.103 0 2-.897 2-2v-7c0-1.103-.897-2-2-2h-10z"
      />
    </svg>
    <span>Passer au premium</span>
    <svg
      xmlns="http://www.w3.org/2000/svg"
      class="h-4 w-4 group-hover:translate-x-1 transition-transform"
      viewBox="0 0 24 24"
      fill="currentColor"
    >
      <path d="M5 12h14M12 5l7 7-7 7" />
    </svg>
  </NuxtLink>
</template>

<script setup lang="ts"></script>

<style scoped>
/* Animation subtile au survol */
.NuxtLink {
  transform-origin: center;
}

.NuxtLink:hover {
  transform: translateY(-1px);
}
</style>
