
// Define the TypeScript union type that mirrorsJava Enum
export type Role = 'STUDENT' | 'ADMIN' | 'COORDINATOR' | 'HOST';

export interface User {
  name: string;
  email: string;
  password: string;
  contact: string;
  role: Role; 
  
  
}
export interface LoginRequest {
    email: string;
    password: string;
}