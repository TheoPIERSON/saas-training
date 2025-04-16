// plugins/auth.ts
export default defineNuxtPlugin((nuxtApp) => {
  const { vueApp } = nuxtApp;

  // Utiliser le hook onRequest de Nuxt pour intercepter les requêtes
  nuxtApp.hook("app:created", () => {
    // Au lieu de modifier $fetch globalement, nous allons ajouter un middleware pour useFetch
    nuxtApp.provide("apiFetch", (url: string, options: any = {}) => {
      // Récupérer le token depuis localStorage uniquement côté client
      const token = process.client ? localStorage.getItem("token") : null;

      // Ajouter l'en-tête d'autorisation si le token existe
      if (token) {
        options.headers = {
          ...options.headers,
          Authorization: `Bearer ${token}`,
        };
      }

      // Effectuer la requête avec useFetch
      return useFetch(url, options);
    });
  });
});
