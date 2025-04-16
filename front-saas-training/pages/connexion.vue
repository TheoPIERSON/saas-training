<template>
  <div class="max-w-md mx-auto bg-white rounded-xl shadow-md p-8 my-10">
    <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Connexion</h2>

    <form @submit.prevent="handleLogin">
      <!-- Message d'erreur -->
      <div v-if="loginError" class="mb-4 p-3 bg-red-50 border border-red-200 text-red-600 rounded-lg text-sm">
        {{ loginError }}
      </div>

      <!-- Email -->
      <div class="mb-4">
        <label for="email" class="block text-sm font-medium text-gray-700 mb-1">Adresse email</label>
        <input
          id="email"
          v-model="form.email"
          type="email"
          class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:border-transparent"
          placeholder="votre@email.com"
          required
        />
      </div>

      <!-- Mot de passe -->
      <div class="mb-4">
        <div class="flex justify-between items-center mb-1">
          <label for="password" class="block text-sm font-medium text-gray-700">Mot de passe</label>
          <a href="#" class="text-xs text-indigo-600 hover:text-indigo-800">Mot de passe oublié ?</a>
        </div>
        <div class="relative">
          <input
            id="password"
            v-model="form.password"
            :type="showPassword ? 'text' : 'password'"
            class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:border-transparent"
            placeholder="Entrez votre mot de passe"
            required
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
      </div>

      <!-- Se souvenir de moi -->
      <div class="mb-6">
        <div class="flex items-center">
          <input
            id="remember"
            v-model="form.rememberMe"
            type="checkbox"
            class="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded"
          />
          <label for="remember" class="ml-2 block text-sm text-gray-700"> Se souvenir de moi </label>
        </div>
      </div>

      <!-- Bouton de connexion -->
      <div class="mb-4">
        <button
          type="submit"
          class="w-full bg-indigo-600 text-white font-medium py-2 px-4 rounded-lg hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 transition-colors"
          :disabled="isLoading"
        >
          <span v-if="isLoading" class="flex items-center justify-center">
            <svg
              class="animate-spin -ml-1 mr-2 h-4 w-4 text-white"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
            >
              <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
              <path
                class="opacity-75"
                fill="currentColor"
                d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
              ></path>
            </svg>
            Connexion en cours...
          </span>
          <span v-else>Se connecter</span>
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
      <div class="space-y-3">
        <button
          type="button"
          class="w-full flex justify-center items-center bg-white border border-gray-300 text-gray-700 font-medium py-2 px-4 rounded-lg hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500 transition-colors"
          @click="socialLogin('google')"
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

        <button
          type="button"
          class="w-full flex justify-center items-center bg-black border border-gray-800 text-white font-medium py-2 px-4 rounded-lg hover:bg-gray-800 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-800 transition-colors"
          @click="socialLogin('apple')"
        >
          <svg class="w-5 h-5 mr-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512" fill="currentColor">
            <path
              d="M318.7 268.7c-.2-36.7 16.4-64.4 50-84.8-18.8-26.9-47.2-41.7-84.7-44.6-35.5-2.8-74.3 20.7-88.5 20.7-15 0-49.4-19.7-76.4-19.7C63.3 141.2 4 184.8 4 273.5q0 39.3 14.4 81.2c12.8 36.7 59 126.7 107.2 125.2 25.2-.6 43-17.9 75.8-17.9 31.8 0 48.3 17.9 76.4 17.9 48.6-.7 90.4-82.5 102.6-119.3-65.2-30.7-61.7-90-61.7-91.9zm-56.6-164.2c27.3-32.4 24.8-61.9 24-72.5-24.1 1.4-52 16.4-67.9 34.9-17.5 19.8-27.8 44.3-25.6 71.9 26.1 2 49.9-11.4 69.5-34.3z"
            />
          </svg>
          Continuer avec Apple
        </button>
      </div>

      <!-- Lien vers l'inscription -->
      <div class="text-center mt-6">
        <p class="text-sm text-gray-600">
          Pas encore de compte ?
          <NuxtLink to="register" class="text-indigo-600 hover:text-indigo-800 font-medium">Créer un compte</NuxtLink>
        </p>
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from "vue";
import { useAuthApi } from "~/services/authApi"; // Importez le service d'authentification
import { useRouter } from "vue-router"; // Pour la redirection après connexion

// État du formulaire
const form = reactive({
  email: "",
  password: "",
  rememberMe: false,
});

// États UI
const showPassword = ref(false);
const isLoading = ref(false);
const loginError = ref("");

// Récupérez le service d'authentification et le router
const { login } = useAuthApi();
const router = useRouter();

// Gestion de la connexion
const handleLogin = async () => {
  // Réinitialiser les erreurs
  loginError.value = "";
  isLoading.value = true;

  try {
    // Appel à votre API d'authentification
    const { data, error } = await login({
      email: form.email,
      password: form.password,
    });

    // Gestion des erreurs
    if (error.value) {
      throw new Error(error.value?.data?.message || "Erreur lors de la connexion");
    }

    // Si la connexion réussit, stockez le token
    if (data.value?.token) {
      // Stockez le token dans le localStorage
      localStorage.setItem("token", data.value.token);

      // Si l'option "Se souvenir de moi" est activée, vous pouvez stocker des informations supplémentaires
      if (form.rememberMe) {
        localStorage.setItem("rememberedEmail", form.email);
      } else {
        localStorage.removeItem("rememberedEmail");
      }

      // Redirection vers la page d'accueil ou le tableau de bord
      router.push("/dashboard"); // Ou la page de votre choix
    } else {
      throw new Error("Token non reçu");
    }
  } catch (err) {
    // Affichage du message d'erreur
    loginError.value = (err as Error).message || "Email ou mot de passe incorrect. Veuillez réessayer.";
  } finally {
    isLoading.value = false;
  }
};

// Connexion via réseaux sociaux - à implémenter si nécessaire
const socialLogin = (provider: string) => {
  console.log(`Tentative de connexion avec ${provider}`);
  // Cette partie n'est pas liée à votre API Spring Boot, donc elle reste inchangée
};

// Vérifier si l'utilisateur a déjà été mémorisé (pour "Se souvenir de moi")
// À exécuter au chargement de la page
onMounted(() => {
  const rememberedEmail = localStorage.getItem("rememberedEmail");
  if (rememberedEmail) {
    form.email = rememberedEmail;
    form.rememberMe = true;
  }
});
</script>

<style scoped>
/* Styles supplémentaires si nécessaire */
</style>
