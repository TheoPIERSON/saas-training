// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: "2024-11-01",
  devtools: { enabled: true },
  modules: ["@nuxt/fonts", "@nuxtjs/tailwindcss", "@pinia/nuxt", "@nuxt/icon"],
  runtimeConfig: {
    public: {
      apiBaseUrl: process.env.API_BASE_URL || "http://localhost:8080",
    },
  },
  plugins: ["~/plugins/auth.ts"],
});