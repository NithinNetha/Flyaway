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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		response.setContentType("text/html");
    	RequestDispatcher rd=null;
    	PrintWriter out=response.getWriter();
    	HttpSession session=request.getSession();
    	UserDAO userdao = new UserDAO();
    	String username=request.getParameter("username");
		String password=request.getParameter("password");
		try {
			String utype=userdao.userType(username, password);
			if(!username.isEmpty() && !password.isEmpty())
			{
				if(userdao.userValid(username, password)) {
					session.setAttribute("usertype", utype);
					session.setAttribute("uname", username);
					rd=request.getRequestDispatcher("AdminDashboard.jsp");
					rd.forward(request, response);
				}
				else {
					rd=request.getRequestDispatcher("Login.jsp");
					rd.include(request, response);
					out.print("<center><span style='color:red'>Invalid credentials</span></center>");
				}
				
			}
			else {
				rd=request.getRequestDispatcher("Login.jsp");
				rd.include(request, response);
				out.print("<center><span style='color:red'>Username and Password cannot be null</span></center>");
			}
			} catch (BusinessException e) {
				rd=request.getRequestDispatcher("Login.jsp");
				rd.include(request, response);	
				out.print("<center><span style='color:red'>"+e.getMessage()+"</span></center>");
			}
	}

}
