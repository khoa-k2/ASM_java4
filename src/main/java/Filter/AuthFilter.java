package Filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.com.model.User;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter({"/change-password","/edit-profile","/manager"})
public class AuthFilter extends HttpFilter implements Filter {
       public static final String SECURITY_URI="securityUri";
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AuthFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		String uri = req.getRequestURI();
		 session.setAttribute(SECURITY_URI, uri);
		if (user == null) {
//            session.setAttribute(SECURITY_URI, uri);
            session.setAttribute("error", "Bạn cần đăng nhập để tiếp tục.");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // 2️⃣ Không phải admin nhưng truy cập /manager
        if (uri.contains("/manager") && !user.isAdmin()) {
            session.setAttribute("error", "Bạn phải đăng nhập với vai trò Admin để truy cập trang này!");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
			chain.doFilter(request, response);
		

		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
