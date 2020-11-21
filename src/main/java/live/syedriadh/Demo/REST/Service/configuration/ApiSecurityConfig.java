package live.syedriadh.Demo.REST.Service.configuration;

import live.syedriadh.Demo.REST.Service.filter.ApiKeyAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${HTTP.HEADER_NAME}")
    private String principalRequestHeader;

    @Value("${HTTP.API_KEY}")
    private String principalRequestHeaderValue;

    /**
     * Override this method to configure the {@link HttpSecurity}. Typically subclasses
     * should not invoke this method by calling super as it may override their
     * configuration. The default configuration is:
     *
     * <pre>
     * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
     * </pre>
     * <p>
     * Any endpoint that requires defense against common vulnerabilities can be specified
     * here, including public ones. See {@link HttpSecurity#authorizeRequests} and the
     * `permitAll()` authorization rule for more details on public endpoints.
     *
     * @param httpSecurity the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ApiKeyAuthenticationFilter apiKeyAuthenticationFilter = new ApiKeyAuthenticationFilter(principalRequestHeader);
        apiKeyAuthenticationFilter.setAuthenticationManager((authentication) -> {
            String principal = (String) authentication.getPrincipal();
            if (!principalRequestHeaderValue.equals(principal)) {
                throw new BadCredentialsException("Invalid key provided");
            }

            authentication.setAuthenticated(true);
            return authentication;
        });

        httpSecurity.antMatcher("/api/**").csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilter(apiKeyAuthenticationFilter).authorizeRequests().anyRequest().authenticated();
    }
}
