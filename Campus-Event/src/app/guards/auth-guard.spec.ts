import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Component } from '@angular/core';
import { AuthGuard } from './auth-guard';

// Mock Component for testing Router
@Component({ template: '' })
class DummyComponent {}

// Mock the AuthService to control login state during tests
class MockAuthService {
  isLoggedIn = jasmine.createSpy('isLoggedIn');
}

describe('AuthGuard', () => {
  let guard: AuthGuard;
  let authService: MockAuthService;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        // Configure the RouterTestingModule with a mock route for redirection
        RouterTestingModule.withRoutes([
          { path: 'login', component: DummyComponent },
          { path: 'protected-route', component: DummyComponent }
        ])
      ],
      providers: [
        AuthGuard,
        // Provide the mock service so the guard uses our controlled spies
        { provide: AuthService, useClass: MockAuthService }
      ],
      declarations: [DummyComponent]
    });
    
    // Inject the real guard and router, and the mock service instance
    guard = TestBed.inject(AuthGuard);
    authService = TestBed.inject(AuthService) as unknown as MockAuthService;
    router = TestBed.inject(Router);
    spyOn(router, 'navigateByUrl'); // Spy on navigation to check if a redirect occurs
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  // -------------------------------------------------------------------
  // TEST 1: User is logged in (should return true)
  // -------------------------------------------------------------------
  it('should allow access for an authenticated user', () => {
    // Arrange: Tell the mock service that the user IS logged in
    authService.isLoggedIn.and.returnValue(true);

    // Act: Call the guard
    const result = guard.canActivate();

    // Assert: Check if the result is true and navigation was NOT attempted
    expect(result).toBe(true);
    expect(router.navigateByUrl).not.toHaveBeenCalled();
  });

  // -------------------------------------------------------------------
  // TEST 2: User is NOT logged in (should redirect to /login)
  // -------------------------------------------------------------------
  it('should redirect an unauthenticated user to the login page', () => {
    // Arrange: Tell the mock service that the user IS NOT logged in
    authService.isLoggedIn.and.returnValue(false);

    // Act: Call the guard
    const result = guard.canActivate();

    // Assert: Check if the result is a UrlTree (indicating a redirect) 
    // and that the router was commanded to navigate to '/login'
    expect(result).toEqual(router.createUrlTree(['/login']));
    // NOTE: For UrlTree, we check the returned value, not the navigateByUrl spy.
  });
});