/** @type {import('tailwindcss').Config} */

export default {
  content: ["./index.html", "./src/**/*.{vue,js,ts}"],

  theme: {
    extend: {
      colors: {
        primary: {
          DEFAULT: "#0D9488", // Teal 600
          light: "#14B8A6", // Teal 500
          dark: "#0F766E", // Teal 700
        },
        secondary: {
          DEFAULT: "#B8B8FF", // Orange 500
          light: "#FB923C",
          dark: "#EA580C",
        },
        accent: "#65A30D", // Emerald 500
        background: "#F9FAFB",
        surface: "#FFFFFF",
        muted: "#6B7280", // Gray 500
      },

      fontFamily: {
        montserrat: ["Montserrat", "sans-serif"],
        merriweather: ["Merriweather", "serif"],
        cormorant: ["Cormorant Garamond", "serif"],
      },

      borderRadius: {
        xl: "1rem",
        "2xl": "1.5rem",
      },

      spacing: {
        "128": "32rem",
        "144": "36rem",
      },

      boxShadow: {
        soft: "0 4px 12px rgba(0, 0, 0, 0.1)",
      },

      transitionTimingFunction: {
        "in-expo": "cubic-bezier(0.95, 0.05, 0.795, 0.035)",
        "out-expo": "cubic-bezier(0.19, 1, 0.22, 1)",
      },
    },
  },

  plugins: [],
};
