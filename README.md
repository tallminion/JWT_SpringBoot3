# JWT Authentication with Spring Boot 3.0

Implements the below:

1. To configure in-memory User.
2. JWT authentication using latest spring boot 3.0.
3. Define a protected endpoint using Spring boot security.


## Create a new Spring boot project

Add the below dependencies in pom.xml
- For Web
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
- For security
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
- For JWT
```
<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>

<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId> <!-- or jjwt-gson if Gson is preferred -->
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
```

## Example endpoint to be secured
This endpoint fetches user List

```
@RestController
@RequestMapping("/home")
public class HomeController {
    
    //API: http://localhost:8081/home/users

    @GetMapping("/users")
    public List<User> getUser(){
        System.out.println("getting users");
        return this.userService.getUsers();
    }
}
```
- If inMemory user is not created, default password is provided by `spring-security` dependency.
### custom username and password 
- can be created by the below code in `application.properties`. 
- For plain text passwords, use `{noop}` in front of the password.
```
spring.security.user.name=<username>
spring.security.user.password={noop}<password>
```
## Implement inMemory user with UserDetailService Bean
- If we want to use encoded passwords,we must encode the password correctly and configure Spring Security to use the correct encoder.
- here `BCrypt` password encorder is implemented.
```
@Configuration
public class AppConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user =  User.builder().username(<username>).password(passwordEncoder().encode(<passoword>)).roles(<role>).build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
```

## Create JWT-Athentication-EntryPoint Class 
- JWTauthenticationEntryPoint class implements AuthenticationEntryPoint.
- Method of this class is called whenever an exception is thrown due to unauthenticated user trying to access the resource that required authentication.
```
@Override 
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("Access Denied !! " + authException.getMessage());
    }
```
## Add JWT helper class for token generation, validation, and claims extraction using HS512 algorithm with secure key management.
The `JWThelper` class facilitates the generation, validation, and management of JSON Web Tokens (JWT) in a secure manner.

### Features
- **Token Generation:** Generate JWTs for authenticated users with configurable claims.
- **Token Validation:** Validate tokens to ensure they are not expired and belong to the correct user.
- **Claims Extraction:** Retrieve claims such as the username and expiration date from the token.

**NOTE:** ``` Claims can provide a comprehensive view of a user's identity, roles, permissions, and session-related information, which are critical for authentication and authorization processes in your application.```

## Implement JWT authentication filter to validate and authenticate users based on Bearer token in the request header.
- Bearer token example:->  `Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3`
**5 important logic**
1. Get Token from request
2. Validate Token
3. GetUsername from token
4. Load user associated with this token
5. set authentication





# to be continued...😊



