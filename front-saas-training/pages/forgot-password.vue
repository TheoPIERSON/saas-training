<!-- pages/forgot-password.vue -->
<template>
  <div class="forgot-password-container">
    <div class="forgot-password-card">
      <h1>Mot de passe oublié</h1>
      <p class="instruction">
        Entrez votre adresse e-mail et nous vous enverrons un lien pour réinitialiser votre mot de passe.
      </p>

      <form @submit.prevent="handleSubmit" class="forgot-password-form">
        <div class="form-group">
          <label for="email">Adresse e-mail</label>
          <input
            type="email"
            id="email"
            v-model="email"
            placeholder="Votre adresse e-mail"
            required
            class="form-control"
          />
        </div>

        <div v-if="message" :class="['alert', messageType === 'error' ? 'alert-error' : 'alert-success']">
          {{ message }}
        </div>

        <div class="form-actions">
          <button type="submit" class="btn btn-primary" :disabled="isSubmitting">
            {{ isSubmitting ? "Envoi en cours..." : "Envoyer le lien" }}
          </button>
          <NuxtLink to="/connexion" class="btn btn-link">Retour à la connexion</NuxtLink>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { usePasswordResetApi } from "~/services/passwordResetApi";

const email = ref("");
const isSubmitting = ref(false);
const message = ref("");
const messageType = ref("");
const passwordResetApi = usePasswordResetApi();

const handleSubmit = async () => {
  if (!email.value) return;

  try {
    isSubmitting.value = true;
    message.value = "";

    const response = await passwordResetApi.forgotPassword(email.value);

    if (response.error.value) {
      throw new Error(response.error.value?.data?.message || "Une erreur est survenue");
    }

    message.value =
      response.data.value?.message ||
      "Un email a été envoyé avec les instructions pour réinitialiser votre mot de passe.";
    messageType.value = "success";
    email.value = ""; // Réinitialiser le formulaire
  } catch (error) {
    message.value = error.message;
    messageType.value = "error";
  } finally {
    isSubmitting.value = false;
  }
};
</script>

<style scoped>
.forgot-password-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 20px;
  background-color: #f5f5f5;
}

.forgot-password-card {
  width: 100%;
  max-width: 500px;
  padding: 40px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

h1 {
  margin-bottom: 20px;
  text-align: center;
  color: #333;
}

.instruction {
  margin-bottom: 25px;
  text-align: center;
  color: #666;
}

.forgot-password-form {
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

.form-actions {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-top: 10px;
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
