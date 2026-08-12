export interface User {
  id: number;
  name: string;
  email: string;
  role: 'USER' | 'ADMIN' | 'COORDINATOR' | 'HOST';
  contact: string;
}