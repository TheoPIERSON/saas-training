<template>
  <div
    class="flex items-center justify-center min-h-screen w-full bg-gradient-to-br from-primary-light to-primary-dark p-4"
  >
    <form
      @submit.prevent="handleSubmit"
      class="w-full max-w-lg flex flex-col items-center gap-6 text-center p-8 md:p-12 bg-surface/5 backdrop-blur-lg rounded-3xl border border-white/10 shadow-glass text-slate-100"
    >
      <h2 class="text-2xl md:text-3xl font-sans font-light mb-4 text-white">Importer Transactions CSV</h2>

      <input type="file" id="csv-upload-themed" @change="handleFile" accept=".csv" hidden />

      <label
        for="csv-upload-themed"
        class="flex flex-col items-center justify-center w-full p-6 md:p-10 rounded-2xl border-2 border-dashed border-white/30 cursor-pointer transition duration-300 ease-in-out hover:bg-white/5 hover:border-white/50 text-sm md:text-base"
      >
        <svg
          class="w-12 h-12 md:w-16 md:h-16 mb-4 text-white/60"
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 24 24"
          fill="currentColor"
          aria-hidden="true"
        >
          <path d="M0 0h24v24H0z" fill="none" />
          <path d="M9 16h6v-6h4l-7-7-7 7h4zm-4 2h14v2H5z" />
        </svg>
        <span>Cliquez ou déposez votre fichier .CSV</span>
      </label>

      <div class="min-h-[1.5em] w-full mt-1">
        <span v-if="file" class="text-xs md:text-sm text-slate-100/80 break-all"> Fichier : {{ file.name }} </span>
        <span v-else class="text-xs md:text-sm text-slate-100/60 italic"> Aucun fichier sélectionné </span>
      </div>

      <button
        type="submit"
        :disabled="!file || isLoading"
        class="mt-4 px-8 py-3 rounded-xl font-semibold font-sans transition duration-300 ease-in-out bg-primary hover:bg-primary-dark border border-primary-dark text-text-on-primary active:enabled:scale-95 disabled:bg-primary/40 disabled:border-primary/20 disabled:text-text-on-primary/60 disabled:cursor-not-allowed"
      >
        {{ isLoading ? "Importation..." : "Importer le Fichier" }}
      </button>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";

// --- Configuration ---
// Assurez-vous que useTransactions est correctement importé/configuré
// import { useTransactions } from "@/services/useTransactions";

// Simulation pour l'exemple
const useTransactions = () => {
  const uploadTransactions = async (fileToUpload) => {
    console.log("Simulating themed upload:", fileToUpload.name);
    await new Promise((resolve) => setTimeout(resolve, 1500));
    // Simuler succès ou échec
    if (Math.random() > 0.2) {
      return { success: true, message: `${fileToUpload.name} imported.` };
    } else {
      throw new Error("Simulated upload failure.");
    }
  };
  return { uploadTransactions };
};
// --- Fin Simulation ---

// --- State ---
const file = ref(null);
const isLoading = ref(false);
const { uploadTransactions } = useTransactions();

// --- Fonctions ---
function handleFile(event) {
  const selectedFile = event.target.files[0];
  const inputElement = event.target;
  isLoading.value = false; // Reset loading state if a new file is chosen

  if (selectedFile) {
    const fileType = selectedFile.type;
    const fileName = selectedFile.name.toLowerCase();
    if (fileType === "text/csv" || fileName.endsWith(".csv")) {
      file.value = selectedFile;
    } else {
      file.value = null;
      alert("Veuillez sélectionner un fichier au format .CSV.");
      if (inputElement) inputElement.value = "";
    }
  } else {
    file.value = null;
  }
}

async function handleSubmit() {
  if (!file.value || isLoading.value) {
    return;
  }

  isLoading.value = true;

  try {
    const data = await uploadTransactions(file.value);
    console.log("Import successful:", data);
    alert(`Fichier ${file.value.name} importé avec succès !`);
    file.value = null;
    const input = document.getElementById("csv-upload-themed");
    if (input) input.value = "";
  } catch (err) {
    console.error("Import error:", err);
    alert("Erreur lors de l'import : " + (err.message || "Erreur inconnue"));
    // Garder le fichier sélectionné pour permettre de réessayer
  } finally {
    isLoading.value = false;
  }
}
</script>
