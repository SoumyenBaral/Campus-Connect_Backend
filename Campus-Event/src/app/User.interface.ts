export interface User {
  id: number;
  name: string;
  email: string;
  role: 'STUDENT' | 'ADMIN' | 'COORDINATOR' | 'HOST';
  contact: string;
}