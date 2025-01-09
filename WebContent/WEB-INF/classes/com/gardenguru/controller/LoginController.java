// LoginController.java
@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Validate credentials and return appropriate response
        if (isValidUser(loginRequest.getUsername(), loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }

    private boolean isValidUser(String username, String password) {
        // Check credentials against your database or any other authentication mechanism
        // For simplicity, let's consider a hardcoded user
        return "demo".equals(username) && "password".equals(password);
    }
}
