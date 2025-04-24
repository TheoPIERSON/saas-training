<template>
  <section class="p-6 bg-white rounded-lg shadow-md">
    <h2 class="text-2xl font-semibold text-gray-800 mb-6">Mes Transactions</h2>

    <!-- Message de chargement -->
    <div v-if="isLoading" class="flex justify-center items-center py-10">
      <div class="animate-spin rounded-full h-10 w-10 border-b-2 border-primary"></div>
    </div>

    <!-- Message si pas de transactions -->
    <div v-else-if="!transactions || transactions.length === 0" class="text-center py-10 text-gray-600">
      Aucune transaction disponible.
    </div>

    <!-- Tableau des transactions -->
    <div v-else class="overflow-x-auto">
      <table class="min-w-full bg-white">
        <thead class="bg-gray-100 border-b">
          <tr>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-600 uppercase tracking-wider">Date</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-600 uppercase tracking-wider">Description</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-600 uppercase tracking-wider">Catégorie</th>
            <th class="px-6 py-3 text-right text-xs font-medium text-gray-600 uppercase tracking-wider">Montant</th>
          </tr>
        </thead>
        <tbody class="divide-y divide-gray-200">
          <tr v-for="(transaction, index) in transactions" :key="index" class="hover:bg-gray-50 transition-colors">
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700">
              {{ formatDate(transaction.date) }}
            </td>
            <td class="px-6 py-4 text-sm text-gray-700 max-w-xs truncate">
              {{ transaction.description }}
            </td>
            <td class="px-6 py-4 whitespace-nowrap text-sm">
              <span
                class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full"
                :class="getCategoryClass(transaction.categorie)"
              >
                {{ transaction.categorie }}
              </span>
            </td>
            <td
              class="px-6 py-4 whitespace-nowrap text-sm font-medium text-right"
              :class="transaction.montant < 0 ? 'text-red-600' : 'text-green-600'"
            >
              {{ formatMontant(transaction.montant) }} €
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Pagination (pour version future) -->
    <div class="flex justify-between items-center mt-6">
      <div class="text-sm text-gray-600">Affichage de {{ transactions?.length || 0 }} transaction(s)</div>
    </div>
  </section>
</template>

<script setup lang="ts">
import { computed, onMounted, ref } from "vue";

// Définition du type Transaction
interface Transaction {
  description: string;
  date: string;
  montant: number;
  categorie: string;
}

// Props pour recevoir les transactions du parent
const props = defineProps({
  transactions: {
    type: Array as () => Transaction[],
    required: true,
  },
  isLoading: {
    type: Boolean,
    default: false,
  },
});

// Fonction pour formater la date
function formatDate(dateString: string): string {
  try {
    const date = new Date(dateString);
    return new Intl.DateTimeFormat("fr-FR", {
      year: "numeric",
      month: "short",
      day: "numeric",
    }).format(date);
  } catch (e) {
    return dateString;
  }
}

// Fonction pour formater le montant
function formatMontant(montant: number): string {
  return new Intl.NumberFormat("fr-FR", {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  }).format(montant);
}

// Fonction pour déterminer la classe CSS de la catégorie
function getCategoryClass(category: string): string {
  const categoryMap: Record<string, string> = {
    Alimentation: "bg-green-100/70 text-green-800 border border-green-200/30",
    Logement: "bg-blue-100/70 text-blue-800 border border-blue-200/30",
    Transport: "bg-purple-100/70 text-purple-800 border border-purple-200/30",
    Santé: "bg-red-100/70 text-red-800 border border-red-200/30",
    Loisirs: "bg-yellow-100/70 text-yellow-800 border border-yellow-200/30",
    Revenu: "bg-emerald-100/70 text-emerald-800 border border-emerald-200/30",
    Salaire: "bg-emerald-100/70 text-emerald-800 border border-emerald-200/30",
    Facture: "bg-orange-100/70 text-orange-800 border border-orange-200/30",
    Abonnement: "bg-indigo-100/70 text-indigo-800 border border-indigo-200/30",
  };

  return categoryMap[category] || "bg-gray-100 text-gray-800";
}
</script>

<style scoped>
/* Styles supplémentaires si besoin */
</style>
