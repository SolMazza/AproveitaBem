package Api.configSecurity;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class AppKeyFilter implements Filter {

    @Value("${app.key}")
    private String appKey;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getRequestURI().contains("/usuarios/login") ||
                httpRequest.getRequestURI().contains("/usuarios/cadastrar")) {
            chain.doFilter(request, response);
            return;
        }

        String requestKey = httpRequest.getHeader("App-Key");

        if (appKey.equals(requestKey)) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Chave de aplicação inválida");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}