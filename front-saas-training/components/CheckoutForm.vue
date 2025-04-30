<template>
  <div class="premium-container">
    <div class="premium-card">
      <div class="premium-header">
        <h2>Passez à la formule Premium</h2>
        <p class="premium-price">2,99 €</p>
      </div>

      <div class="premium-features">
        <h3>Avantages Premium :</h3>
        <ul>
          <li>Accès illimité à toutes les fonctionnalités</li>
          <li>Pas de publicités</li>
          <li>Support prioritaire</li>
          <li>Contenu exclusif</li>
        </ul>
      </div>

      <button @click="showPayment = true" v-if="!showPayment" class="premium-button">Passer à Premium</button>

      <div v-if="showPayment" class="payment-section">
        <h3>Finaliser votre achat</h3>
        <div v-if="!paymentSucceeded">
          <div id="card-element" class="card-element"></div>
          <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
          <button @click="processPayment" :disabled="loading" class="pay-button">
            <span v-if="loading">Traitement en cours...</span>
            <span v-else>Payer 2,99 €</span>
          </button>
        </div>
        <div v-else class="success-message">
          <p>🎉 Félicitations ! Votre compte est maintenant premium.</p>
          <button @click="goToDashboard" class="dashboard-button">Accéder à mon espace</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "~/stores/user"; // Supposons que vous avez un store pour l'utilisateur

const router = useRouter();
const userStore = useUserStore();
const { $stripe } = useNuxtApp();
const config = useRuntimeConfig();

const showPayment = ref(false);
const loading = ref(false);
const paymentSucceeded = ref(false);
const errorMessage = ref("");
let elements = null;
let card = null;
let clientSecret = null;

// Afficher la section de paiement
async function initializePaymentForm() {
  try {
    // Récupérer le clientSecret depuis le backend
    const response = await fetch(`${config.public.apiBaseUrl}/api/premium/create-payment`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
    });

    const data = await response.json();
    clientSecret = data.clientSecret;

    // Initialiser Stripe Elements
    elements = $stripe.elements({ clientSecret });

    // Créer l'élément de carte
    const style = {
      base: {
        color: "#32325d",
        fontFamily: '"Helvetica Neue", Helvetica, sans-serif',
        fontSmoothing: "antialiased",
        fontSize: "16px",
        "::placeholder": {
          color: "#aab7c4",
        },
      },
      invalid: {
        color: "#fa755a",
        iconColor: "#fa755a",
      },
    };

    card = elements.create("card", { style });

    // Monter l'élément de carte après le rendu du DOM
    setTimeout(() => {
      card.mount("#card-element");

      // Gérer les erreurs de validation de la carte
      card.on("change", (event) => {
        errorMessage.value = event.error ? event.error.message : "";
      });
    }, 100);
  } catch (err) {
    console.error("Erreur lors de l'initialisation du formulaire de paiement:", err);
    errorMessage.value = "Impossible d'initialiser le formulaire de paiement";
  }
}

// Lorsque l'utilisateur clique pour afficher le paiement
watch(showPayment, (newValue) => {
  if (newValue) {
    initializePaymentForm();
  }
});

// Traiter le paiement
async function processPayment() {
  if (!card || !elements) {
    return;
  }

  loading.value = true;
  errorMessage.value = "";

  try {
    const result = await $stripe.confirmPayment({
      elements,
      confirmParams: {
        return_url: `${window.location.origin}/payment-success`,
        payment_method_data: {
          billing_details: {
            name: userStore.currentUser?.firstname
              ? `${userStore.currentUser.firstname} ${userStore.currentUser.lastname}`
              : "Utilisateur",
          },
        },
      },
      redirect: "if_required",
    });

    if (result.error) {
      // Afficher l'erreur à l'utilisateur
      errorMessage.value = result.error.message;
    } else {
      // Paiement réussi - mettre à jour le statut utilisateur côté backend
      const confirmResponse = await fetch("/api/premium/confirm", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          paymentIntentId: result.paymentIntent.id,
          userId: userStore.currentUser?.id, // Assurez-vous que c'est bien un nombre (Long)
        }),
      });

      if (confirmResponse.ok) {
        // Mettre à jour le store utilisateur
        userStore.updatePremiumStatus(true);
        paymentSucceeded.value = true;
      } else {
        errorMessage.value = "Erreur lors de la confirmation de l'achat";
      }
    }
  } catch (err) {
    console.error("Erreur de paiement:", err);
    errorMessage.value = "Une erreur est survenue lors du traitement de votre paiement";
  } finally {
    loading.value = false;
  }
}

// Rediriger vers le tableau de bord
function goToDashboard() {
  router.push("/dashboard");
}
</script>

<style scoped>
.premium-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.premium-card {
  border-radius: 10px;
  border: 1px solid #e0e0e0;
  padding: 30px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.05);
  background-color: white;
}

.premium-header {
  text-align: center;
  margin-bottom: 30px;
}

.premium-price {
  font-size: 28px;
  font-weight: bold;
  color: #6772e5;
  margin-top: 10px;
}

.premium-features {
  margin-bottom: 30px;
}

.premium-features ul {
  list-style-type: none;
  padding: 0;
}

.premium-features li {
  padding: 8px 0;
  position: relative;
  padding-left: 30px;
}

.premium-features li:before {
  content: "✓";
  color: #6772e5;
  position: absolute;
  left: 0;
  font-weight: bold;
}

.premium-button,
.pay-button,
.dashboard-button {
  width: 100%;
  padding: 12px;
  background-color: #6772e5;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.premium-button:hover,
.pay-button:hover,
.dashboard-button:hover {
  background-color: #5a65d6;
}

.premium-button:disabled,
.pay-button:disabled {
  background-color: #b9b9b9;
  cursor: not-allowed;
}

.payment-section {
  margin-top: 30px;
  padding-top: 20px;
  border-top: 1px solid #e0e0e0;
}

.card-element {
  padding: 12px;
  border: 1px solid #e0e0e0;
  border-radius: 4px;
  margin-bottom: 20px;
  background-color: white;
}

.error-message {
  color: #ff0000;
  margin-bottom: 15px;
}

.success-message {
  text-align: center;
  color: #43a047;
  padding: 20px 0;
}

.dashboard-button {
  margin-top: 15px;
}
</style>
