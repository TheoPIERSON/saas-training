<template>
  <form @submit.prevent="handleSubmit">
    <input type="file" @change="handleFile" />
    <button type="submit">Importer</button>
  </form>
</template>

<script setup>
import { ref } from "vue";
import { useTransactions } from "@/services/useTransactions";
definePageMeta({
  middleware: ["auth"],
});

const file = ref(null);
const { uploadTransactions } = useTransactions();

function handleFile(event) {
  file.value = event.target.files[0];
}

async function handleSubmit() {
  if (!file.value) return;

  try {
    const data = await uploadTransactions(file.value);
    console.log("Transactions importées :", data);
  } catch (err) {
    alert("Erreur lors de l'import : " + err.message);
  }
}
</script>
