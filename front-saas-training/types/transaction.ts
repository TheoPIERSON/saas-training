import type { User } from "./user";

export interface Transaction {
  id: number;
  date: string; // Format "YYYY-MM-DD" (LocalDate de Spring)
  description: string;
  montant: number;
  categorie: string;
  user: User | number;
}
