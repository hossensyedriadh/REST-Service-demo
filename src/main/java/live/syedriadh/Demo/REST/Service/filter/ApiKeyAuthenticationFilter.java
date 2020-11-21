package live.syedriadh.Demo.REST.Service.filter;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class ApiKeyAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
    private final String principalRequestHeader;

    public ApiKeyAuthenticationFilter(String principalRequestHeader) {
        this.principalRequestHeader = principalRequestHeader;
    }

    /**
     * Override to extract the principal information from the current request
     *
     * @param request http request to get pre-authenticated header name
     */
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(principalRequestHeader);
    }

    /**
     * Override to extract the credentials (if applicable) from the current request.
     * Should not return null for a valid principal, though some implementations may
     * return a dummy value.
     *
     * @param request request
     */
    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return request.getSession();
    }
}
