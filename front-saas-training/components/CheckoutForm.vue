<template>
  <div class="checkout-form">
    <h2>Paiement</h2>
    <div v-if="!paymentSucceeded">
      <div class="form-row">
        <div id="card-element" class="card-element"></div>
        <div id="card-errors" class="error-message" v-if="errorMessage">{{ errorMessage }}</div>
      </div>
      <button @click="handleSubmit" :disabled="loading" class="payment-button">
        <span v-if="loading">Traitement en cours...</span>
        <span v-else>Payer {{ amount / 100 }} €</span>
      </button>
    </div>
    <div v-else class="payment-success">
      <p>Paiement réussi ! Merci pour votre achat .</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRuntimeConfig } from "#app";

const { $stripe } = useNuxtApp();
const config = useRuntimeConfig();

const props = defineProps({
  amount: {
    type: Number,
    required: true,
  },
  currency: {
    type: String,
    default: "eur",
  },
});

const loading = ref(false);
const paymentSucceeded = ref(false);
const errorMessage = ref("");
let elements = null;
let card = null;

onMounted(async () => {
  // Créer les éléments Stripe
  try {
    // Récupérer le clientSecret depuis le backend
    const response = await fetch("/api/payment/create-payment-intent", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        amount: props.amount,
        currency: props.currency,
      }),
    });

    const data = await response.json();
    const clientSecret = data.clientSecret;

    elements = $stripe.elements({ clientSecret });

    // Créer un élément de carte
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
    card.mount("#card-element");

    // Gérer les erreurs de validation de la carte
    card.on("change", (event) => {
      errorMessage.value = event.error ? event.error.message : "";
    });
  } catch (err) {
    console.error("Erreur lors de l'initialisation du formulaire de paiement:", err);
    errorMessage.value = "Impossible d'initialiser le formulaire de paiement";
  }
});

async function handleSubmit() {
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
            name: "Client Test",
          },
        },
      },
      redirect: "if_required",
    });

    if (result.error) {
      // Afficher l'erreur à l'utilisateur
      errorMessage.value = result.error.message;
    } else {
      // Paiement réussi
      paymentSucceeded.value = true;
    }
  } catch (err) {
    console.error("Erreur de paiement:", err);
    errorMessage.value = "Une erreur est survenue lors du traitement de votre paiement";
  } finally {
    loading.value = false;
  }
}
</script>

<style scoped>
.checkout-form {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
}

.card-element {
  padding: 10px;
  border: 1px solid #e0e0e0;
  border-radius: 4px;
  margin-bottom: 20px;
  background-color: white;
}

.error-message {
  color: #ff0000;
  margin-bottom: 10px;
}

.payment-button {
  background-color: #6772e5;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 4px;
  cursor: pointer;
}

.payment-button:disabled {
  background-color: #b9b9b9;
  cursor: not-allowed;
}

.payment-success {
  color: green;
  text-align: center;
}
</style>
