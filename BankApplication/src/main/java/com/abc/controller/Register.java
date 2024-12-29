package com.abc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import com.abc.model.Model;
@WebServlet("/Register")
public class Register extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String custmid = request.getParameter("custid");
		String saccno = request.getParameter("accno");
		int accno = Integer.parseInt(saccno);
		String pwd = request.getParameter("pwd");
		String sbal = request.getParameter("bal");
		int balance = Integer.parseInt(sbal);
		String email = request.getParameter("email");
		
		try {
			Model m = new Model();
			m.setName(name);
			m.setCustmid(custmid);
			m.setAccno(accno);
			m.setPwd(pwd);
			m.setBalance(balance);
			m.setEmail(email);
			
			Boolean b = m.register();
			if(b==true) {
				response.sendRedirect("/BankApplication/SucessReg.html");
			}
			else {
				response.sendRedirect("/BankApplication/FailureReg.html");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
