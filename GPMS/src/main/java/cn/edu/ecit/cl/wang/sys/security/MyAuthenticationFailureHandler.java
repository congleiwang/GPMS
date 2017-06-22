package cn.edu.ecit.cl.wang.sys.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import cn.edu.ecit.cl.wang.sys.service.IUserService;

public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Autowired
	private IUserService userService;
	private String defaultFailureUrl;
	private boolean forwardToDestination = false;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public MyAuthenticationFailureHandler() {
	}

	public MyAuthenticationFailureHandler(String defaultFailureUrl) {
		setDefaultFailureUrl(defaultFailureUrl);
	}

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if (defaultFailureUrl == null) {
			logger.debug("No failure URL set, sending 401 Unauthorized error");
			response.sendError(401, "Authentication Failed: " + exception.getMessage());
		} else {
			saveException(request, exception);
			if ((exception instanceof BadCredentialsException)) {
				logger.debug("BadCredentialsException->Password is wrong");
				try {
					Long userId=userService.getIdByLoginNm(request.getParameter("j_username"));
					userService.updatePassErr(userId);
					if (userService.isUserLocked(userId) == true) {
						exception.initCause(
								new LockedException("密码错误!用户 " + request.getParameter("j_username") + " 已被锁定!"));
						request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION",
								exception.getCause().getMessage());
					} else {
						request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", "账号密码错误");
					}
				} catch (Exception e) {
					request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", e.getMessage());
				}
			} else {
				request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception.getMessage());
			}

			if (forwardToDestination) {
				logger.debug("Forwarding to " + defaultFailureUrl);

				request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
			} else {
				logger.debug("Redirecting to " + defaultFailureUrl);
				redirectStrategy.sendRedirect(request, response, defaultFailureUrl);
			}
		}
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}
}
