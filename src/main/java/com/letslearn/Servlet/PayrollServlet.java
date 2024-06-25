package com.letslearn.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.letslearn.DBcon.DbCon;
import com.letslearn.Dao.PayrollDao;
import com.letslearn.Modal.Payroll;

@WebServlet("/PayrollServlet")
public class PayrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int result = 0;
	RequestDispatcher dis = null;
	String action = null;
	String nic = null;
	String bonus = null;
	String salary = null;
	String paymentFor = null;
	String fname = null;
	String emailString = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		action = request.getParameter("action");
		if(action.equals("delete")){
			emailString = request.getParameter("email");
			nic = request.getParameter("nic");
			bonus = request.getParameter("bonus");
			salary = request.getParameter("salary");
			paymentFor = request.getParameter("paymentFor");
			fname = request.getParameter("fname");
			
			try {
				PayrollDao payrollDao = new PayrollDao(DbCon.getConnection());
				result = payrollDao.deletePayroll(emailString);
				if (result != 0) {
					response.sendRedirect("payrollManagement.jsp");
					
				} else {
					// Send a JavaScript alert for an error
					out.write("<script>alert('An error occurred. Please try again.');</script>");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		action = request.getParameter("action");
		
		emailString = request.getParameter("email");
		nic = request.getParameter("nic");
		bonus = request.getParameter("bonus");
		salary = request.getParameter("salary");
		paymentFor = request.getParameter("paymentFor");
		fname = request.getParameter("fname");
		
		
		if(action.equals("addPayroll")) {
			try {
				Payroll payroll = new Payroll(fname, emailString, nic, paymentFor, bonus, salary);
				PayrollDao payrollDao = new PayrollDao(DbCon.getConnection());
				result = payrollDao.addPayroll(payroll);
				if (result != 0) {
					response.sendRedirect("payrollManagement.jsp");
				} else {
					// Send a JavaScript alert for an error
					out.write("<script>alert('An error occurred. Please try again.');</script>");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(action.equals("update")){
			try {
				Payroll payroll = new Payroll(fname, emailString, nic, paymentFor, bonus, salary);
				PayrollDao payrollDao = new PayrollDao(DbCon.getConnection());
				result = payrollDao.updatePayroll(payroll);
				if (result != 0) {
					response.sendRedirect("payrollManagement.jsp");
				} else {
					// Send a JavaScript alert for an error
					out.write("<script>alert('An error occurred. Please try again.');</script>");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
