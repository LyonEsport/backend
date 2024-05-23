package fr.lyonesport.esport.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {

    private static final String USERNAME = "lyon";
    private static final String PASSWORD = "lyon";

    public SecurityConfiguration() {
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager initSecurityUser() {
        List<UserDetails> userDetails = userRepository.findAll().stream()
                .map(user -> User.builder().username(user.getEmail())
                        .password(encoder().encode(user.getPassword()))
                        .roles(checkRole(user.getRole().getLevel()))
                        .build())
                .toList();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public SecurityFilterChain filterChain(@NotNull HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers(HttpMethod.GET, "/room").hasAnyRole(USER, ADMIN, SUPER_ADMIN)
                        .requestMatchers(HttpMethod.GET, "/company/{email}").hasAnyRole(USER, ADMIN, SUPER_ADMIN)
                        .requestMatchers(HttpMethod.GET, "/user/{email}").hasAnyRole(USER, ADMIN, SUPER_ADMIN)
                        .requestMatchers(HttpMethod.PUT, "/user").hasAnyRole(UNKNOWN, USER, ADMIN, SUPER_ADMIN)
                        .requestMatchers(HttpMethod.POST, "/user/favorite").hasAnyRole(USER, ADMIN, SUPER_ADMIN))
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
