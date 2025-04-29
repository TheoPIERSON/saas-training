<template>
  <section class="md:ml-64"><LogoutButton /></section>
</template>

<script setup>
import LogoutButton from "~/components/LogoutButton.vue";

definePageMeta({
  middleware: ["auth"],
  layout: "dashboard-nav-bar",
});
</script>

<style scoped></style>
