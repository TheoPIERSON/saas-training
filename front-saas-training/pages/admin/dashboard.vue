<template>
  <section class="ml-64"><CsvFileUpload /></section>
</template>

<script setup lang="ts">
definePageMeta({
  middleware: ["auth"],
  layout: "dashboard-nav-bar", // Added layout property
});
</script>

<style scoped>
/* Style supplémentaire si besoin */
</style>
