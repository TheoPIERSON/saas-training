<template>
  <div>
    <h1>Utilisateurs</h1>
    <div v-if="pending">Chargement...</div>
    <div v-else-if="error">
      Erreur lors du chargement: {{ error.message }}
      <pre>{{ error.data }}</pre>
    </div>
    <ul v-else-if="users && users.length">
      <li v-for="user in users" :key="user.id">{{ user.email }} (Premium: {{ user.isPremium }})</li>
    </ul>
    <div v-else>Aucun utilisateur trouvé.</div>

    <hr />
    <button @click="refreshUsers">Rafraîchir</button>
  </div>
</template>

<script setup lang="ts">
import type { User } from "~/types/user";

// Récupérer la configuration runtime
const config = useRuntimeConfig();
const apiBaseUrl = config.public.apiBaseUrl;

const { data: users, pending, error, refresh } = await useFetch<User[]>(`${apiBaseUrl}user/all-users`, {});

// Fonction pour rafraîchir manuellement les données
const refreshUsers = () => {
  console.log("Rafraîchissement demandé...");
  refresh(); // Ré-exécute la requête useFetch
};

// Vous pouvez aussi surveiller les changements
watch(users, (newUsers) => {
  console.log("Données utilisateurs mises à jour:", newUsers);
});

watch(error, (newError) => {
  if (newError) {
    console.error("Erreur API:", newError);
    // Afficher une notification, rediriger, etc.
  }
});

if (error.value && process.server) {
}
</script>
