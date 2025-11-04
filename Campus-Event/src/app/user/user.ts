
// Define the TypeScript union type that mirrorsJava Enum
export type Role = 'STUDENT' | 'ADMIN' | 'COORDINATOR' | 'HOST';

export interface User {

  id: number; // The primary key
  createdAt: string; // LocalDateTime serializes to a string
  isApproved: boolean;
  
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