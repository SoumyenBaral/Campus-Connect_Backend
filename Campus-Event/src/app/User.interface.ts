export interface User {
  id: number;
  name: string;
  email: string;
  role: 'STUDENT' | 'ADMIN' | 'COORDINATOR' | 'HOST';
  contact: string;
  // Note: Do NOT include 'password' in this interface for security
}