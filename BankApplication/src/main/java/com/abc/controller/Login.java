package com.abc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.abc.model.Model;
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private HttpSession session;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custmid = request.getParameter("custid");
		String pwd = request.getParameter("pwd");
		session = request.getSession(true);
		try {
			Model m = new Model();
			m.setCustmid(custmid);
			m.setPwd(pwd);
			
			Boolean b = m.login();
			if(b==true) {
				session.setAttribute("accno", m.getAccno());
				response.sendRedirect("/BankApplication/Home.html");
			}
			else {
				response.sendRedirect("/BankApplication/FailureLog.html");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
