<template>
  <aside class="w-64 h-screen bg-white shadow-md border-r border-gray-200 flex flex-col fixed top-0 left-0">
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
          U
        </div>
        <span class="text-gray-700 font-medium truncate">User Name</span>
        <!-- Add user name dynamically -->
      </button>
    </div>

    <!-- Navigation links -->
    <nav class="flex-grow p-4 space-y-2">
      <NuxtLink
        to="/dashboard"
        class="flex items-center px-4 py-3 rounded-lg text-base font-medium text-gray-700 hover:text-primary hover:bg-gray-50 transition-colors"
      >
        <!-- Add icons here if desired -->
        <span>Dashboard</span>
      </NuxtLink>
      <NuxtLink
        to="/subscriptions"
        class="flex items-center px-4 py-3 rounded-lg text-base font-medium text-gray-700 hover:text-primary hover:bg-gray-50 transition-colors"
      >
        <span>Subscriptions</span>
      </NuxtLink>
      <NuxtLink
        to="/upcoming"
        class="flex items-center px-4 py-3 rounded-lg text-base font-medium text-gray-700 hover:text-primary hover:bg-gray-50 transition-colors"
      >
        <span>Upcoming</span>
      </NuxtLink>
      <NuxtLink
        to="/pricing"
        class="flex items-center px-4 py-3 rounded-lg text-base font-medium text-gray-700 hover:text-primary hover:bg-gray-50 transition-colors"
      >
        <span>Change Plan</span>
      </NuxtLink>
      <!-- Settings link removed from here -->
    </nav>

    <!-- Settings Link at the bottom -->
    <div class="mt-auto p-4 border-t border-gray-200">
      <NuxtLink
        to="/settings"
        class="flex items-center px-4 py-3 rounded-lg text-base font-medium text-gray-700 hover:text-primary hover:bg-gray-50 transition-colors"
      >
        <span>Settings</span>
      </NuxtLink>
    </div>
  </aside>
</template>

<script setup>
// Sidebar logic can be added here if needed (e.g., collapse/expand)
</script>

<style scoped>
/* Add any specific sidebar styles if needed */
/* Ensure content area has appropriate margin/padding to avoid overlap */
/* Example: body { padding-left: 16rem; } or apply margin to the main content container */
</style>
