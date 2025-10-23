
// Define the TypeScript union type that mirrors your Java Enum (Role.java)
export type Role = 'STUDENT' | 'ADMIN' | 'COORDINATOR' | 'HOST';

// Define the data model for a User, which matches the required fields of your 
// Spring Boot Users Entity for the Sign-Up process.
export interface User {
  // Required fields for sign-up
  name: string;
  email: string;
  password: string;
  contact: string;
  role: Role; 
  
  // Note: Fields like 'id' and 'createdAt' are usually handled by the backend 
  // and are omitted here for simplicity in a sign-up model.
}

// Interface for the data sent during login
export interface LoginRequest {
    email: string;
    password: string;
}