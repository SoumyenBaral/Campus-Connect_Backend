package com.campus.connect.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; // Use standard interface
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Mark this as a Spring component for injection
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    
    // Inject UserDetailsService (Spring will find your UsersServiceImpl)
    private final UserDetailsService userDetailsService;

    // Constructor Injection
    @Autowired
    public JwtAuthFilter(JwtService jwtService, @Lazy  UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

	@Override
	protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // 1. Check for JWT existence and format
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // No token or token is not in "Bearer " format, proceed down the filter chain
            filterChain.doFilter(request, response);
            return;
        }

        // Extract token (skip "Bearer ")
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        // 2. Validate token and authentication state
        // Check if username is valid AND if the user is not already authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            // Load user details from the database (using your UsersServiceImpl)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Check if the token is valid for the loaded user
            if (jwtService.isTokenValid(jwt, userDetails)) {
                
                // 3. Create Authentication token and update SecurityContext
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // Credentials should be null for JWTs as the token is the credential
                        userDetails.getAuthorities()
                );
                
                // Attach request details (like IP address, session ID) to the token
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                
                // Update the security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continue to the next filter in the chain (e.g., Spring Security's filters, then the Controller)
        filterChain.doFilter(request, response);
	}
}