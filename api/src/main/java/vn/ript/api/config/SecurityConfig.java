// package vn.ript.api.config;

// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.List;
// import java.util.Map;
// import java.util.stream.Collectors;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.convert.converter.Converter;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.oauth2.jwt.Jwt;
// import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
// import org.springframework.security.web.SecurityFilterChain;

// import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;

// import vn.ript.api.utils.Env;

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity
// public class SecurityConfig {

//     private static String KEYCLOAK_CLIENT_ID = Env.KEYCLOAK_CLIENT_ID;

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//                 .csrf(cfrs -> cfrs.disable())
//                 .cors(cors -> cors.disable())
//                 .oauth2ResourceServer(resourceServer -> resourceServer
//                         .jwt(jwt -> jwt.jwtAuthenticationConverter(
//                                 jwtAuthenticationConverterForKeycloak())))
//                 .authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                         .requestMatchers("/api/v1/**").permitAll()
//                         .requestMatchers("/api/test-auth").authenticated()
//                         .requestMatchers("/api/test-auth-user").hasAnyAuthority("ROLE_USER")
//                         .requestMatchers("/api/test-auth-admin").hasAnyAuthority("ROLE_ADMIN")
//                         .requestMatchers("/api/test-no-auth").permitAll()
//                         .anyRequest().permitAll());
//         return http.build();
//     }

//     public JwtAuthenticationConverter jwtAuthenticationConverterForKeycloak() {
//         Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = jwt -> {
//             Map<String, List<String>> resourceAccess = jwt.getClaim("resource_access");
//             Object client = resourceAccess.get(KEYCLOAK_CLIENT_ID);
//             LinkedTreeMap<String, List<String>> clientRoleMap = (LinkedTreeMap<String, List<String>>) client;
//             List<String> clientRoles = new ArrayList<>(clientRoleMap.get("roles"));
//             return clientRoles.stream()
//                     .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase().replace(" ", "_")))
//                     .collect(Collectors.toList());
//         };
//         JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//         jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//         return jwtAuthenticationConverter;
//     }

// }