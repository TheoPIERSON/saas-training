import { loadStripe } from "@stripe/stripe-js";

export default defineNuxtPlugin(async () => {
  const stripe = await loadStripe(
    "pk_test_51QAY0YDwSYneY4XuG8x5Oxb1xEtdxZfKxsdRV3LK5KMGdfsN6aWmJ3cnCxvN2Pqax2y5AprNbgp8iWncvl2nlAvf00kNlkIBmR"
  );

  return {
    provide: {
      stripe,
    },
  };
});
