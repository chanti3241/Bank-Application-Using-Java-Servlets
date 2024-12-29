package com.abc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.abc.model.Model;

@WebServlet("/transfer")
public class Transfer extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int accno = (int)session.getAttribute("accno");
		int raccno = Integer.parseInt(request.getParameter("raccno"));
		int amount = Integer.parseInt(request.getParameter("amt"));
		try {
			Model m = new Model();
			m.setAccno(accno);
			m.setRaccno(raccno);
			m.setBalance(amount);
			boolean b = m.transfer();
			if(b==true) {
				response.sendRedirect("/BankApplication/TransferSuccess.html");
			}else {
				response.sendRedirect("/BankApplication/TransferFail.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
