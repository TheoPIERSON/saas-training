export interface User {
  id: number | string;
  firstName: string;
  lastName: string;
  email: string;
  isPremium: boolean;
  password: string;
  fromGoogle: boolean;
  createdAt: Date;
}
