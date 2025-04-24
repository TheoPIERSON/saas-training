import type { User } from "./user";

export interface Transaction {
  id: number;
  date: string; // Ou Date si vous transformez la string en Date côté front
  description: string;
  montant: number;
  categorie: string;
  user: User | number; // Selon si vous recevez l'objet complet ou juste l'ID
}
