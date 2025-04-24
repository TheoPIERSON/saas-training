<template>
  <section class="p-6 backdrop-blur-md bg-white/20 border border-white/30 rounded-xl shadow-glass w-1/3">
    <h2 class="text-2xl font-montserrat font-semibold text-primary-900 mb-6">Mes dépenses</h2>

    <div v-if="isLoading" class="flex justify-center items-center py-8">
      <div class="animate-spin rounded-full h-10 w-10 border-t-2 border-b-2 border-primary"></div>
    </div>

    <div v-else-if="!hasTransactions" class="text-center py-8">
      <p class="text-muted font-montserrat">Aucune transaction disponible</p>
    </div>

    <div v-else>
      <!-- Résumé du mois en cours -->
      <div class="mb-6">
        <div class="flex justify-between items-center mb-2">
          <h3 class="text-lg font-montserrat font-medium text-primary-dark">{{ currentMonth }}</h3>
          <span
            class="text-2xl font-montserrat font-bold"
            :class="monthlyExpenses >= 0 ? 'text-primary' : 'text-accent'"
          >
            {{ formatCurrency(monthlyExpenses) }}
          </span>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup lang="ts">
import { computed, onMounted } from "vue";
import { useTransactions } from "~/services/useTransactions";
import type { Transaction } from "~/types/transaction";

const { fetchTransactions, isLoading, hasTransactions, transactions } = useTransactions();

// Formater le nom du mois
const formatMonth = (date: Date) => {
  try {
    return date.toLocaleDateString("fr-FR", { month: "long", year: "numeric" });
  } catch (error) {
    console.error("Erreur de formatage de date:", error, date);
    return "Date inconnue";
  }
};

// Mois actuel formaté
const currentMonth = computed(() => {
  return formatMonth(new Date());
});

// Obtenir le mois et année actuels pour comparaison
const currentMonthNumber = computed(() => new Date().getMonth());
const currentYear = computed(() => new Date().getFullYear());

// Formater la monnaie
const formatCurrency = (amount: number) => {
  return new Intl.NumberFormat("fr-FR", {
    style: "currency",
    currency: "EUR",
    minimumFractionDigits: 2,
  }).format(amount);
};

// Calculer le total des dépenses du mois courant
const monthlyExpenses = computed(() => {
  if (!transactions.value || transactions.value.length === 0) return 0;

  return transactions.value
    .filter((transaction: Transaction) => {
      const transactionDate = new Date(transaction.date);
      return (
        transactionDate.getMonth() === currentMonthNumber.value && transactionDate.getFullYear() === currentYear.value
      );
    })
    .reduce((total: number, transaction: Transaction) => {
      return total + transaction.montant;
    }, 0);
});

onMounted(async () => {
  await fetchTransactions();
});
</script>
