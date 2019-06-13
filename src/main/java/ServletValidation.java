import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This class use the doFilter() method in the servlet library
 * to help secure the private websites that require sign in to access
 */
@WebFilter("/*")
public class ServletValidation implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // If you have any <init-param> in web.xml, then you could get them
        // here by config.getInitParameter("name") and assign it as field.
    }

    /**
     * This method is used for validating the client request whether the URL request
     * is suitable for the client current state.
     *
     * @param req   use to get the client request information
     * @param res   use to send the service to the client
     * @param chain use to forward the client request to a suitable website in each cases
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Cookie cks[] = request.getCookies();

        String servletPath = request.getServletPath();

        if(servletPath.matches("/Sample.*")) {  // THIS COMPARISON USE REGEX TO CHECK IF THE CLIENT
                                                      // IS TRYING TO ACCESS THE PRIVATE WEBSITES
            if (!cks[0].getName().equals("auth")) {   // ONLY ALLOW USER TO ACCESS THE PRIVATE WEBSITE IF THE USER SIGNED IN

                response.sendRedirect("/Sample_war/sessionExpired.html"); // No logged-in user found, so redirect to login page.
            } else {
                chain.doFilter(req, res); // Logged-in user found, so just continue request.
            }
        }
        else{
            chain.doFilter(req, res);   // If the client access public page then allow
        }
    }

    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }

}