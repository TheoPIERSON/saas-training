<template>
  <div class="max-w-md mx-auto bg-white rounded-xl shadow-md p-8 my-10">
    <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Créer un compte</h2>

    <form @submit.prevent="handleSubmit">
      <!-- Nom -->
      <div class="mb-4">
        <label for="lastName" class="block text-sm font-medium text-gray-700 mb-1">Nom</label>
        <input
          id="lastName"
          v-model="form.lastname"
          type="text"
          class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-light focus:border-transparent"
          placeholder="Votre nom"
          required
        />
      </div>

      <!-- Prénom -->
      <div class="mb-4">
        <label for="firstName" class="block text-sm font-medium text-gray-700 mb-1">Prénom</label>
        <input
          id="firstName"
          v-model="form.firstname"
          type="text"
          class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-light focus:border-transparent"
          placeholder="Votre prénom"
          required
        />
      </div>

      <!-- Email -->
      <div class="mb-4">
        <label for="email" class="block text-sm font-medium text-gray-700 mb-1">Adresse email</label>
        <input
          id="email"
          v-model="form.email"
          type="email"
          class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-light focus:border-transparent"
          placeholder="votre@email.com"
          required
        />
      </div>

      <!-- Mot de passe -->
      <div class="mb-4">
        <label for="password" class="block text-sm font-medium text-gray-700 mb-1">Mot de passe</label>
        <div class="relative">
          <input
            id="password"
            v-model="form.password"
            :type="showPassword ? 'text' : 'password'"
            class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-light focus:border-transparent"
            placeholder="Choisissez un mot de passe sécurisé"
            required
            minlength="8"
          />
          <button
            type="button"
            class="absolute inset-y-0 right-0 pr-3 flex items-center text-gray-500 hover:text-gray-700"
            @click="showPassword = !showPassword"
          >
            <svg
              v-if="showPassword"
              class="h-5 w-5"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7A9.97 9.97 0 014.02 8.971m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.88 9.88l-3.29-3.29m7.532 7.532l3.29 3.29M3 3l3.59 3.59m0 0A9.953 9.953 0 0112 5c4.478 0 8.268 2.943 9.543 7a10.025 10.025 0 01-4.132 5.411m0 0L21 21"
              ></path>
            </svg>
            <svg
              v-else
              class="h-5 w-5"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
              ></path>
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"
              ></path>
            </svg>
          </button>
        </div>
        <p class="text-xs text-gray-500 mt-1">Le mot de passe doit contenir au moins 8 caractères</p>
      </div>

      <!-- Confirmation du mot de passe -->
      <div class="mb-6">
        <label for="confirmPassword" class="block text-sm font-medium text-gray-700 mb-1"
          >Confirmer le mot de passe</label
        >
        <input
          id="confirmPassword"
          v-model="confirmPassword"
          :type="showPassword ? 'text' : 'password'"
          class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-light focus:border-transparent"
          placeholder="Confirmez votre mot de passe"
          required
        />
        <p v-if="passwordError" class="text-xs text-red-500 mt-1">{{ passwordError }}</p>
      </div>

      <!-- Conditions d'utilisation -->
      <div class="mb-6">
        <div class="flex items-center">
          <input
            id="terms"
            v-model="termsAccepted"
            type="checkbox"
            class="h-4 w-4 text-primary focus:ring-primary-light border-gray-300 rounded"
            required
          />
          <label for="terms" class="ml-2 block text-sm text-gray-700">
            J'accepte les <a href="#" class="text-primary hover:text-primary-dark">conditions d'utilisation</a> et la
            <a href="#" class="text-primary hover:text-primary-dark">politique de confidentialité</a>
          </label>
        </div>
      </div>

      <!-- Message d'erreur -->
      <div v-if="error" class="mb-4 text-red-500 text-center">
        {{ error }}
      </div>

      <!-- Bouton de soumission -->
      <div class="mb-4">
        <button
          type="submit"
          class="w-full bg-primary text-white font-medium py-2 px-4 rounded-lg hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-primary-light focus:ring-offset-2 transition-colors"
          :disabled="isSubmitting"
        >
          <span v-if="isSubmitting">Création en cours...</span>
          <span v-else>Créer mon compte</span>
        </button>
      </div>

      <!-- Séparateur -->
      <div class="relative my-6">
        <div class="absolute inset-0 flex items-center">
          <div class="w-full border-t border-gray-300"></div>
        </div>
        <div class="relative flex justify-center text-sm">
          <span class="px-2 bg-white text-gray-500">ou</span>
        </div>
      </div>

      <!-- Options alternatives -->
      <div class="mb-4">
        <button
          type="button"
          class="w-full flex justify-center items-center bg-white border border-gray-300 text-gray-700 font-medium py-2 px-4 rounded-lg hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500 transition-colors"
        >
          <svg class="w-5 h-5 mr-2" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
            <path
              d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z"
              fill="#4285F4"
            />
            <path
              d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z"
              fill="#34A853"
            />
            <path
              d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z"
              fill="#FBBC05"
            />
            <path
              d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z"
              fill="#EA4335"
            />
          </svg>
          Continuer avec Google
        </button>
      </div>

      <!-- Lien vers la connexion -->
      <div class="text-center mt-6">
        <p class="text-sm text-gray-600">
          Vous avez déjà un compte?
          <NuxtLink to="/connexion" class="text-primary hover:text-primary-dark font-medium">Connectez-vous</NuxtLink>
        </p>
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from "vue";
import { useUserApi, type User } from "~/services/userApi";

// Service API
const userApi = useUserApi();

// État du formulaire
const form = reactive<User>({
  firstname: "",
  lastname: "",
  email: "",
  password: "",
});

// États UI
const showPassword = ref(false);
const isSubmitting = ref(false);
const confirmPassword = ref("");
const termsAccepted = ref(false);
const error = ref("");
const passwordError = ref("");

// Gestion de la soumission
const handleSubmit = async () => {
  error.value = "";
  passwordError.value = "";

  // Vérification de la confirmation du mot de passe
  if (form.password !== confirmPassword.value) {
    passwordError.value = "Les mots de passe ne correspondent pas";
    return;
  }

  // Vérification des conditions d'utilisation
  if (!termsAccepted.value) {
    error.value = "Vous devez accepter les conditions d'utilisation";
    return;
  }

  try {
    // Soumission du formulaire
    isSubmitting.value = true;

    const { data, error: apiError } = await userApi.addUser(form);

    if (apiError.value) {
      throw new Error(apiError.value.message || "Erreur lors de l'inscription");
    }

    // Traitement après inscription réussie
    console.log("Utilisateur créé avec succès:", data.value);

    // Redirection vers la page de connexion ou dashboard
    navigateTo("/connexion");
  } catch (err) {
    console.error("Erreur d'inscription:", err);
    error.value = err instanceof Error ? err.message : "Une erreur s'est produite lors de l'inscription";
  } finally {
    isSubmitting.value = false;
  }
};
</script>

<style scoped>
/* Styles supplémentaires si nécessaire */
</style>
