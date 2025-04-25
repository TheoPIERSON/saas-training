<template>
  <section class="ml-64">
    <div v-if="isLoading">
      <p>Chargement en cours...</p>
    </div>
    <div v-else>
      <CsvFileUpload v-if="!hasTransactions" @upload-success="checkTransactions" />
      <SpendingMonthly v-if="hasTransactions" :transactions="transactions" />
      <TransactionsList v-if="hasTransactions" :transactions="transactions" />
    </div>
  </section>
</template>

<script setup lang="ts">
import { onMounted } from "vue";
import { useTransactions } from "~/services/useTransactions";

definePageMeta({
  middleware: ["auth"],
  layout: "dashboard-nav-bar",
});

const { fetchTransactions, hasTransactions, isLoading, transactions } = useTransactions();

// Fonction pour vérifier les transactions après un upload réussi
async function checkTransactions() {
  await fetchTransactions();
}

// Vérifier les transactions au chargement de la page
onMounted(async () => {
  await fetchTransactions();
});
</script>

<style scoped>
/* Style supplémentaire si besoin */
</style>
