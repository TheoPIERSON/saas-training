export interface User {
  id: number | string;
  email: string;
  isPremium: boolean;
  password: string;
  fromGoogle: boolean;
  createdAt: Date;
}
