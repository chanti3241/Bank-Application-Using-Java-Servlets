package com.abc.controller;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ChangePwdFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String npwd = request.getParameter("npwd");
		String cpwd = request.getParameter("cpwd");
		
		 if(npwd.equals(cpwd)) {
			 chain.doFilter(request, response);
		 }
		 else {
			 ((HttpServletResponse) response).sendRedirect("/BankApplication/ChangePwdFail.html");
		 }
	}

}
