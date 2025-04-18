export interface User {
  id: number | string;
  firstname: string;
  lastname: string;
  email: string;
  isPremium: boolean;
  password: string;
  fromGoogle: boolean;
  createdAt: Date;
}
