package com.campus.connect.config; // Create a 'config' package or adjust this path

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; // Import RequestMethod

/**
 * This controller ensures that Spring Boot acts as a single server for the 
 * Angular Single Page Application (SPA).
 * It catches all client-side routes and forwards them to the Angular entry point (index.html).
 */
@Controller
public class FrontendController {

    /**
     * **THE DEFINITIVE FALLBACK ROUTE**
     * Catches ALL GET requests that do not match a specific API endpoint or a static file.
     * This is the most common and robust way to handle HTML5 routing fallback.
     * * The regex "[^\\.]*$" matches any path that does NOT contain a dot ('.') 
     * and ends the path, effectively skipping requests for static files like main.js 
     * but catching client-side routes like /student or /host.
     */
    @RequestMapping(value = "/{path:[^\\.]*}", method = RequestMethod.GET)
    public String forwardToAngularRoutes() {
        // IMPORTANT: Ensure your index.html file is in src/main/resources/static/ or /public/
        return "forward:/index.html"; 
    }
    
    // NOTE: The previous explicit list and the second fallback method are removed 
    // as they sometimes cause conflicts. This single method is the standard solution.
}
