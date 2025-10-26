import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthService } from './auth.service';
import { User } from '../User.interface'; // Ensure path is correct
import { LoginRequest } from '../Login-request.interface'; // Ensure path is correct

describe('AuthService', () => { // FIX 1: Rename to match class name
  let service: AuthService;
  let httpTestingController: HttpTestingController; // FIX 2: Controller for mocking HTTP

  const apiUrl = 'http://localhost:8080/api';

  beforeEach(() => {
    TestBed.configureTestingModule({
      // FIX 3: Import the testing module to mock HTTP requests
      imports: [HttpClientTestingModule], 
      providers: [AuthService]
    });
    
    // Inject the service and the mocking controller
    service = TestBed.inject(AuthService);
    httpTestingController = TestBed.inject(HttpTestingController);
    
    // Clean up local storage before each test
    localStorage.clear();
  });

  // FIX 4: Ensure all mock requests are checked after each test
  afterEach(() => {
    httpTestingController.verify(); 
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  // --- Test Case 1: Testing the isLoggedIn method ---
  it('should return true if user is in localStorage', () => {
    const mockUser: User = { id: 1, email: 'test@uni.edu', name: 'Test User', role: 'STUDENT', contact: '123' };
    localStorage.setItem('currentUser', JSON.stringify(mockUser));
    
    expect(service.isLoggedIn()).toBeTrue();
  });

  it('should return false if user is NOT in localStorage', () => {
    expect(service.isLoggedIn()).toBeFalse();
  });


  // --- Test Case 2: Testing the login method ---
  it('should send POST request and return user on successful login', () => {
    const mockCredentials: LoginRequest = { email: 'test@uni.edu', password: 'password' };
    const mockUserResponse: User = { id: 1, email: 'test@uni.edu', name: 'Test User', role: 'STUDENT', contact: '123' };
    
    service.login(mockCredentials).subscribe(user => {
      expect(user).toEqual(mockUserResponse);
    });

    // Expect a POST request to the correct URL
    const req = httpTestingController.expectOne(`${apiUrl}/login`);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockCredentials);
    
    // Respond to the request with mock data
    req.flush(mockUserResponse); 
  });
  
  // --- Test Case 3: Testing the getDashboardRoute method ---
  it('should return /admin for ADMIN role', () => {
    const mockUser: User = { id: 1, email: 'admin@uni.edu', name: 'Admin', role: 'ADMIN', contact: '123' };
    localStorage.setItem('currentUser', JSON.stringify(mockUser));
    
    expect(service.getDashboardRoute()).toBe('admin');
  });
});