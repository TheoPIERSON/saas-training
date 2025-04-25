import type { Transaction } from "~/types/transaction";

export function useTransactions() {
  const config = useRuntimeConfig();
  const hasTransactions = ref(false);
  const isLoading = ref(true);
  const transactions = ref<Transaction[]>([]); // Ajout du type générique

  // Fonction pour télécharger un fichier CSV
  async function uploadTransactions(file: string | Blob) {
    const formData = new FormData();
    formData.append("file", file);

    const token = process.client ? localStorage.getItem("token") : null;

    const response = await fetch(`${config.public.apiBaseUrl}/api/transactions/upload-csv`, {
      method: "POST",
      body: formData,
      headers: token ? { Authorization: `Bearer ${token}` } : {},
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(`Erreur serveur : ${response.status} - ${errorText}`);
    }

    return await response.json();
  }

  // Fonction pour récupérer les transactions
  async function fetchTransactions() {
    isLoading.value = true;
    try {
      const token = process.client ? localStorage.getItem("token") : null;

      const response = await fetch(`${config.public.apiBaseUrl}/api/transactions/user-transactions`, {
        method: "GET",
        headers: token ? { Authorization: `Bearer ${token}` } : {},
      });

      if (!response.ok) {
        throw new Error(`Erreur serveur : ${response.status}`);
      }

      const data = await response.json();
      transactions.value = data.sort(
        (a: Transaction, b: Transaction) => new Date(b.date).getTime() - new Date(a.date).getTime()
      );

      transactions.value = data;
      hasTransactions.value = data.length > 0;
      return data;
    } catch (error) {
      console.error("Erreur lors de la récupération des transactions:", error);
      hasTransactions.value = false;
      transactions.value = [];
      return [];
    } finally {
      isLoading.value = false;
    }
  }

  return {
    uploadTransactions,
    fetchTransactions,
    hasTransactions,
    isLoading,
    transactions,
  };
}
