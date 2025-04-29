<!-- pages/reset-password.vue -->
<template>
  <div class="reset-password-container">
    <div class="reset-password-card">
      <h1>Réinitialisation du mot de passe</h1>

      <div v-if="isLoading" class="loading-container">
        <p>Vérification du token...</p>
      </div>

      <div v-else-if="!isTokenValid" class="invalid-token">
        <p>Le lien de réinitialisation est invalide ou a expiré.</p>
        <NuxtLink to="/forgot-password" class="btn btn-primary">Demander un nouveau lien</NuxtLink>
      </div>

      <form v-else @submit.prevent="handleSubmit" class="reset-password-form">
        <div class="form-group">
          <label for="password">Nouveau mot de passe</label>
          <input
            type="password"
            id="password"
            v-model="password"
            placeholder="Entrez votre nouveau mot de passe"
            required
            class="form-control"
            minlength="8"
          />
        </div>

        <div class="form-group">
          <label for="confirmPassword">Confirmer le mot de passe</label>
          <input
            type="password"
            id="confirmPassword"
            v-model="confirmPassword"
            placeholder="Confirmez votre nouveau mot de passe"
            required
            class="form-control"
          />
          <div v-if="passwordError" class="input-error">{{ passwordError }}</div>
        </div>

        <div v-if="message" :class="['alert', messageType === 'error' ? 'alert-error' : 'alert-success']">
          {{ message }}
        </div>

        <div class="form-actions">
          <button type="submit" class="btn btn-primary" :disabled="isSubmitting || !!passwordError">
            {{ isSubmitting ? "Réinitialisation en cours..." : "Réinitialiser mon mot de passe" }}
          </button>
        </div>

        <div v-if="isSuccess" class="success-actions">
          <NuxtLink to="/connexion" class="btn btn-link">Se connecter</NuxtLink>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { usePasswordResetApi } from "~/services/passwordResetApi";

const route = useRoute();
const router = useRouter();
const passwordResetApi = usePasswordResetApi();

const token = ref("");
const isLoading = ref(true);
const isTokenValid = ref(false);
const isSubmitting = ref(false);
const isSuccess = ref(false);
const message = ref("");
const messageType = ref("");
const password = ref("");
const confirmPassword = ref("");

const passwordError = computed(() => {
  if (!password.value || !confirmPassword.value) return "";
  if (password.value !== confirmPassword.value) return "Les mots de passe ne correspondent pas";
  if (password.value.length < 8) return "Le mot de passe doit contenir au moins 8 caractères";
  return "";
});

onMounted(async () => {
  // Récupérer le token depuis l'URL
  token.value = route.query.token?.toString() || "";

  if (!token.value) {
    isTokenValid.value = false;
    isLoading.value = false;
    return;
  }

  try {
    const response = await passwordResetApi.validateToken(token.value);
    isTokenValid.value = !response.error.value;
  } catch (error) {
    isTokenValid.value = false;
  } finally {
    isLoading.value = false;
  }
});

watch([password, confirmPassword], () => {
  // Réinitialiser le message d'erreur lorsque l'utilisateur modifie l'un des champs
  if (message.value && messageType.value === "error") {
    message.value = "";
  }
});

const handleSubmit = async () => {
  if (passwordError.value) return;

  try {
    isSubmitting.value = true;
    message.value = "";

    const response = await passwordResetApi.resetPassword(token.value, password.value);

    if (response.error.value) {
      throw new Error(response.error.value?.data?.message || "Une erreur est survenue");
    }

    message.value = response.data.value?.message || "Votre mot de passe a été réinitialisé avec succès.";
    messageType.value = "success";
    isSuccess.value = true;

    // Réinitialiser les champs
    password.value = "";
    confirmPassword.value = "";
  } catch (error) {
    message.value = error.message;
    messageType.value = "error";
  } finally {
    isSubmitting.value = false;
  }
};
</script>

<style scoped>
.reset-password-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 20px;
  background-color: #f5f5f5;
}

.reset-password-card {
  width: 100%;
  max-width: 500px;
  padding: 40px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

h1 {
  margin-bottom: 30px;
  text-align: center;
  color: #333;
}

.loading-container,
.invalid-token {
  text-align: center;
  padding: 20px 0;
}

.invalid-token p {
  margin-bottom: 20px;
  color: #721c24;
}

.reset-password-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

label {
  font-weight: 500;
  color: #555;
}

.form-control {
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 16px;
  transition: border-color 0.3s;
}

.form-control:focus {
  outline: none;
  border-color: #4a90e2;
}

.input-error {
  color: #dc3545;
  font-size: 14px;
  margin-top: 4px;
}

.form-actions {
  margin-top: 10px;
}

.success-actions {
  margin-top: 20px;
  text-align: center;
}

.btn {
  padding: 12px 16px;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.3s;
  text-align: center;
  width: 100%;
  display: block;
}

.btn-primary {
  background-color: #4a90e2;
  color: white;
}

.btn-primary:hover {
  background-color: #3a80d2;
}

.btn-primary:disabled {
  background-color: #a0c3eb;
  cursor: not-allowed;
}

.btn-link {
  background: none;
  color: #4a90e2;
  text-decoration: none;
}

.btn-link:hover {
  text-decoration: underline;
}

.alert {
  padding: 12px;
  border-radius: 4px;
  font-weight: 500;
}

.alert-success {
  background-color: #d4edda;
  color: #155724;
}

.alert-error {
  background-color: #f8d7da;
  color: #721c24;
}
</style>
