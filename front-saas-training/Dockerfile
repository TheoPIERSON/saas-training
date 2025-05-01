# Étape 1 : Utilisation de l'image Node officielle
FROM node:18

# Création du répertoire de travail
WORKDIR /app

# Copie des fichiers de dépendances
COPY package*.json ./

# Installation des dépendances
RUN npm install

# Copie du reste de l'application
COPY . .

# Exposition du port utilisé par Nuxt en mode dev
EXPOSE 3000

# Lancer Nuxt en mode développement
CMD ["npm", "run", "dev"]
