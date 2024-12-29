package com.abc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.abc.model.Model;
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	
	private HttpSession session;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pwd = request.getParameter("npwd");
		
		session = request.getSession();
		
		int accno = (int) session.getAttribute("accno");
		try {
			Model m = new Model();
			m.setAccno(accno);
			m.setPwd(pwd);
			boolean b = m.changePwd(); 
			if(b == true) {
				
				response.sendRedirect("/BankApplication/ChangePwdSuccess.html");
			}else {
				
				response.sendRedirect("/BankApplication/ChangePwdFail.html");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
