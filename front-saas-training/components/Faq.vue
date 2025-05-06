<template>
  <div class="max-w-7xl mx-auto px-6 lg:px-8 py-16">
    <div class="text-center mb-12">
      <h2 class="text-3xl font-bold text-gray-900 mb-4">Questions Fréquentes</h2>
      <p class="text-lg text-gray-600">Tout ce que vous devez savoir sur nos offres</p>
    </div>

    <div class="grid md:grid-cols-2 gap-8">
      <div
        v-for="(faq, index) in faqs"
        :key="index"
        class="backdrop-blur-sm bg-white/70 rounded-xl p-6 shadow-lg border border-white/20 transition-all duration-300 hover:shadow-xl hover:scale-[1.02]"
      >
        <h3 class="text-xl font-semibold text-gray-900 mb-3">{{ faq.question }}</h3>
        <p class="text-gray-600">{{ faq.answer }}</p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
const faqs = [
  {
    question: "Puis-je changer de plan à tout moment ?",
    answer:
      "Oui, vous pouvez passer du plan Freemium au Premium ou inversement à tout moment. Les changements prennent effet immédiatement.",
  },
  {
    question: "Comment fonctionne la période d'essai ?",
    answer:
      "Le plan Premium inclut une période d'essai de 14 jours. Vous pouvez tester toutes les fonctionnalités sans engagement.",
  },
  {
    question: "Mes données sont-elles sécurisées ?",
    answer:
      "Absolument. Nous utilisons un chiffrement de bout en bout et respectons les normes RGPD pour garantir la sécurité de vos données.",
  },
  {
    question: "Comment fonctionne le support prioritaire ?",
    answer:
      "Les utilisateurs Premium bénéficient d'un support dédié avec un temps de réponse garanti sous 24h ouvrées.",
  },
];
</script>
