// middleware/auth.ts
export default defineNuxtRouteMiddleware((to) => {
  // Éviter l'exécution côté serveur pour éviter les erreurs de localStorage
  if (process.client) {
    const token = localStorage.getItem("token");

    // Si aucun token n'est trouvé, l'utilisateur n'est pas authentifié
    if (!token) {
      // Stocker l'URL d'origine pour rediriger l'utilisateur après connexion
      localStorage.setItem("redirectPath", to.fullPath);

      // Rediriger vers la page de connexion
      return navigateTo({
        path: "/connexion",
        query: { redirect: to.fullPath }, // Ajouter l'URL en tant que paramètre de requête
      });
    }

    // Vérifier si le token est valide (optionnel)
    // Vous pourriez ajouter une vérification de validité du token ici
    // par exemple en décodant le JWT ou en faisant une requête à l'API
  }
});
