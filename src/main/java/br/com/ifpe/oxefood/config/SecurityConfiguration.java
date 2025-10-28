//liberação de rotas (tipo guard)

package br.com.ifpe.oxefood.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.modelo.seguranca.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean

    //libera rotas
    public SecurityFilterChain securityFilterChain
    (HttpSecurity http) throws Exception {

        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(c -> c.disable())
            .authorizeHttpRequests(authorize -> authorize

 //quais rotas do projeto estão públicas
 //permit all n restringe perfil expecifico
 //hasanyauthority = diz os perfis q vao ter acessso
                .requestMatchers(HttpMethod.POST, "/api/cliente").permitAll() //cadastro
                .requestMatchers(HttpMethod.POST, "/api/auth").permitAll() //login
                .requestMatchers(HttpMethod.POST, "/api/funcionario").permitAll() //cadsatro de funcionario

                .requestMatchers(HttpMethod.GET, "/api/produto/").hasAnyAuthority(
                   Perfil.ROLE_CLIENTE, //, =ou
                   Perfil.ROLE_FUNCIONARIO_ADMIN,
                   Perfil.ROLE_FUNCIONARIO_USER) //Consulta de produto

               .requestMatchers(HttpMethod.POST, "/api/produto").hasAnyAuthority(
                   Perfil.ROLE_FUNCIONARIO_ADMIN,
                   Perfil.ROLE_FUNCIONARIO_USER) //Cadastro de produto

               .requestMatchers(HttpMethod.PUT, "/api/produto/*").hasAnyAuthority(
                   Perfil.ROLE_FUNCIONARIO_ADMIN,
                   Perfil.ROLE_FUNCIONARIO_USER) //Alteração de produto
                  
               .requestMatchers(HttpMethod.DELETE, "/api/produto/*").hasAnyAuthority(
                   Perfil.ROLE_FUNCIONARIO_ADMIN) //Exclusão de produto


                
                //.requestMatchers(HttpMethod.GET, "/api/produto/*").permitAll() //libera o acesso de ver o produto por id
                
                //documentação:
                //.requestMatchers(HttpMethod.GET, "/api-docs/*").permitAll()
                //.requestMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()

                .anyRequest().authenticated()

            )
            .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )            
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
//autoriza o acesso da api nas requisições axios caso o frontend esteja no mesmo pc e na mesma porta
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); //se for colocar o front em outra maquina coloca o ip da outra maquina ao inves do localhost
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
    
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);    
        return source;
    }
}
