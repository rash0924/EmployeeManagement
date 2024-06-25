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
import com.letslearn.Dao.HrDao;
import com.letslearn.Modal.Hr;

/**
 * Servlet implementation class HrServlet
 */
@WebServlet("/HrServlet")
public class HrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int result = 0;
	RequestDispatcher dis = null;
	String action = null;
	String nic = null;
	String city = null;
	String tel = null;
	String grade = null;
	String fname = null;
	String emailString = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		action = request.getParameter("action");
		if(action.equals("delete")){
			emailString = request.getParameter("email");
			nic = request.getParameter("nic");
			city = request.getParameter("city");
			tel = request.getParameter("tel");
			grade = request.getParameter("grade");
			fname = request.getParameter("fname");
			
			try {
				HrDao hrDao = new HrDao(DbCon.getConnection());
				result = hrDao.deleteHr(emailString);
				if (result != 0) {
					response.sendRedirect("hrManagement.jsp");
					
				} else {
					// Send a JavaScript alert for an error
					out.write("<script>alert('An error . Please try again.');</script>");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		action = request.getParameter("action");
		
		emailString = request.getParameter("email");
		nic = request.getParameter("nic");
		city = request.getParameter("city");
		tel = request.getParameter("tel");
		grade = request.getParameter("grade");
		fname = request.getParameter("fname");
		
		
		if(action.equals("addHr")) {
			try {
				Hr hr = new Hr(fname, emailString, nic, city, grade, tel);
				HrDao hrDao = new HrDao(DbCon.getConnection());
				result = hrDao.addHr(hr);
				if (result != 0) {
					response.sendRedirect("hrManagement.jsp");
				} else {
					// Send a JavaScript alert for an error
					out.write("<script>alert('An error occurred. Please try again.');</script>");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(action.equals("update")){
			try {
				Hr hr = new Hr(fname, emailString, nic, city, grade, tel);
				HrDao hrDao = new HrDao(DbCon.getConnection());
				result = hrDao.updateHr(hr);
				if (result != 0) {
					response.sendRedirect("hrManagement.jsp");
				} else {
					// Send a JavaScript alert for an error
					out.write("<script>alert('An error  Please try again.');</script>");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
