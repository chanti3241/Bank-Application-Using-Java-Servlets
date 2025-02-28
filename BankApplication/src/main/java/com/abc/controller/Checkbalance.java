package com.abc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.abc.model.Model;
@WebServlet("/Checkbalance")
public class Checkbalance extends HttpServlet {
	
	private HttpSession session;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		int accno = (int) session.getAttribute("accno");
		
		try {
			Model m = new Model();
			m.setAccno(accno);
			
			boolean b = m.checkBalance(); 
			if(b==true) {
				session.setAttribute("balance", m.getBalance());
				response.sendRedirect("/BankApplication/BalanceView.jsp");
			}
			else {
				response.sendRedirect("/BankApplication/BalanceViewFail.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
