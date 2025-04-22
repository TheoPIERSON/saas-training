/** @type {import('tailwindcss').Config} */

// Importation des couleurs par défaut de Tailwind pour les réutiliser si besoin
const defaultTheme = require("tailwindcss/defaultTheme");
const colors = require("tailwindcss/colors");

export default {
  content: ["./index.html", "./src/**/*.{vue,js,ts}"],

  theme: {
    extend: {
      colors: {
        primary: {
          DEFAULT: "#0D9488",
          light: "#14B8A6",
          dark: "#0F766E",

          "900": "#134E4A",
          "950": "#042F2E",
        },

        secondary: {
          DEFAULT: colors.slate[600], // #475569 - Bon contraste neutre
          light: colors.slate[500], // #64748B
          dark: colors.slate[700], // #334155
          "100": colors.slate[100], // #F1F5F9
          "300": colors.slate[300], // #CBD5E1
          "800": colors.slate[800], // #1E293B
          "900": colors.slate[900], // #0F172A
        },

        // --- Couleur d'Accentuation (Modifiée pour contraste) ---
        accent: {
          DEFAULT: colors.pink[500], // #EC4899 - Rose vif pour l'accent
          light: colors.pink[400], // #F472B6
          dark: colors.pink[600], // #DB2777
        },

        // --- Couleurs UI Basiques (Ajustées/Complétées) ---
        background: {
          DEFAULT: colors.slate[50], // #F8FAFC - Fond très clair, légèrement froid
          dark: colors.slate[900], // #0F172A - Option pour fond sombre
        },
        surface: {
          DEFAULT: colors.white, // #FFFFFF - Surface principale claire
          dark: colors.slate[800], // #1E293B - Surface principale sombre
        },
        muted: {
          DEFAULT: colors.slate[500], // #64748B - Texte/éléments atténués (aligné sur secondary.light)
          light: colors.slate[400], // #94A3B8
          dark: colors.slate[600], // #475569 (aligné sur secondary.DEFAULT)
        },
        // --- Couleurs de Texte Explicites ---
        "text-primary": colors.slate[800], // Texte principal sur fond clair
        "text-secondary": colors.slate[600], // Texte secondaire sur fond clair
        "text-on-dark": colors.slate[200], // Texte principal sur fond sombre
        "text-muted-dark": colors.slate[400], // Texte atténué sur fond sombre
        "text-on-primary": colors.white, // Texte sur couleur primaire (Teal 600)
        "text-on-accent": colors.white, // Texte sur couleur d'accent (Pink 500)

        // Optionnel: Couleur spécifique pour le fond "verre" si on veut autre chose que blanc/noir transparent
        // glass: {
        //   DEFAULT: 'rgba(255, 255, 255, 0.1)', // Exemple basé sur blanc
        //   border: 'rgba(255, 255, 255, 0.2)'
        // }
        // Note: Il est souvent plus flexible d'utiliser bg-white/10, border-white/20 etc. directement.
      },

      fontFamily: {
        montserrat: ["Montserrat", "sans-serif"],
        merriweather: ["Merriweather", "serif"],
        cormorant: ["Cormorant Garamond", "serif"],
      },

      // --- Rayon de Bordure (Complété) ---
      borderRadius: {
        // Les tailles par défaut (sm, md, lg) sont conservées
        xl: "1rem", // ~16px
        "2xl": "1.5rem", // ~24px
        "3xl": "2rem", // ~32px - Pour des arrondis plus prononcés
        full: "9999px", // Arrondi complet
      },

      // --- Espacement (Conservé) ---
      spacing: {
        "128": "32rem",
        "144": "36rem",
      },

      // --- Ombres Portées (Ajustées/Complétées pour Glassmorphism) ---
      boxShadow: {
        // Les ombres par défaut sont conservées (sm, md, lg, xl, 2xl)
        soft: `0 4px 12px 0 rgba(13, 148, 136, 0.08)`, // Ombre douce teintée Teal très léger
        glass: `0 8px 32px 0 rgba(15, 118, 110, 0.2)`, // Ombre plus diffuse, teintée Teal foncé (700)
        // On pourrait ajouter une version plus large si besoin
        // 'glass-lg': `0 12px 48px 0 rgba(15, 118, 110, 0.25)`,
      },

      // --- Transitions (Conservées) ---
      transitionTimingFunction: {
        "in-expo": "cubic-bezier(0.95, 0.05, 0.795, 0.035)",
        "out-expo": "cubic-bezier(0.19, 1, 0.22, 1)",
      },
    },
  },

  plugins: [],
};
