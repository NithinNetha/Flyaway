package com.flyaway.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exception.BusinessException;
import com.flyaway.dao.UserDAO;

/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession  session = request.getSession();
		RequestDispatcher rd=null;
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("uname");
		UserDAO userdao = new UserDAO();
		//Users user = new Users();
		String oldPassword =request.getParameter("oldPassword");
		String newPassword =request.getParameter("newPassword");
		try {
			String existPass = userdao.ExistingPassword(username);
			if(!oldPassword.isEmpty() && !newPassword.isEmpty()) {
				if(existPass.equals(oldPassword)) {
					userdao.changePassword(username,newPassword);
					session.setAttribute("action", "Password Changed Successfully");
					response.sendRedirect("AdminDashboard.jsp");
				}else {
					rd=request.getRequestDispatcher("PasswordChange.jsp");
					rd.include(request, response);
					out.print("<span style='color:red'>Passwords didn't matched</span>");
				}
			}else {
				rd=request.getRequestDispatcher("PasswordChange.jsp");
				rd.include(request, response);
				out.print("<span style='color:red'>Passwords fields must not be empty</span>");
			}
		}catch(BusinessException e) {
			session.setAttribute("exception", e.getMessage());
			response.sendRedirect("AdminDashboard.jsp");
		}
	}

}
