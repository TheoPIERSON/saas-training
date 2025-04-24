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

const { fetchTransactions, isLoading, hasTransactions, transactions } = useTransactions();

// Formater la monnaie
const formatCurrency = (amount: number) => {
  return new Intl.NumberFormat("fr-FR", {
    style: "currency",
    currency: "EUR",
    minimumFractionDigits: 2,
  }).format(amount);
};

// Mois actuel formaté pour l'affichage
const currentMonth = computed(() => {
  return new Date().toLocaleDateString("fr-FR", {
    month: "long",
    year: "numeric",
  });
});

const monthlyExpenses = computed(() => {
  console.log("Recalculating monthlyExpenses. Transactions:", transactions.value); // Log initial data

  if (!transactions.value?.length) {
    console.log("No transactions found, returning 0.");
    return 0;
  }

  const now = new Date();
  const currentYear = now.getFullYear();
  const currentMonth = now.getMonth() + 1; // Mois actuel (ex: 4 pour Avril)
  console.log(`Filtering for Year: ${currentYear}, Month: ${currentMonth}`);

  const filteredTransactions = transactions.value.filter((transaction) => {
    try {
      // Vérifier si la date existe et est une chaîne
      if (typeof transaction.date !== "string") {
        console.warn("Transaction date is not a string:", transaction);
        return false;
      }
      const [year, month] = transaction.date.split("-");
      const isCurrentMonth = parseInt(year) === currentYear && parseInt(month) === currentMonth;
      const isExpense = typeof transaction.montant === "number" && transaction.montant < 0; // Vérifie aussi le type de montant

      // Log pour chaque transaction (peut être verbeux)
      // console.log(`Checking transaction ${transaction.id}: Date=${transaction.date}, Amount=${transaction.montant}, IsCurrentMonth=${isCurrentMonth}, IsExpense=${isExpense}`);

      return isCurrentMonth && isExpense;
    } catch (e) {
      console.error("Error processing transaction date:", transaction.date, e);
      return false; // Exclure en cas d'erreur de parsing
    }
  });

  console.log("Filtered transactions for current month's expenses:", filteredTransactions); // Log les transactions filtrées

  const sum = filteredTransactions.reduce((total, t) => {
    // Vérification supplémentaire du type au cas où
    const amount = typeof t.montant === "number" ? t.montant : 0;
    console.log(`Reducing: total=${total}, adding amount=${amount}`); // Log chaque étape du reduce
    return total + amount;
  }, 0);

  console.log("Final calculated sum:", sum); // Log la somme finale
  return sum;
});

onMounted(async () => {
  await fetchTransactions();
});
</script>
