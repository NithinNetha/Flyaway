package com.flyaway.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exception.BusinessException;
import com.flyaway.dao.AirlineDAO;
import com.flyaway.model.Airline;

/**
 * Servlet implementation class MAirlineServlet
 */
public class MAirlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MAirlineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		AirlineDAO airlinedao = new AirlineDAO();
		List<Airline> listAirline = new ArrayList<Airline>();
		if(session!=null) {
			try {
				listAirline=airlinedao.airlineList();
				session.setAttribute("listAirline", listAirline);
				response.sendRedirect("ManageAirline.jsp");
			}catch(BusinessException e) {
				session.setAttribute("exception", e);
				response.sendRedirect("AdminDashboard.jsp");
			}catch(Exception el) {
				session.setAttribute("exception", el);
				response.sendRedirect("AdminDashboard.jsp");
			}
		}else {
			out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
			response.setHeader("refresh", "5;url='/Flyway'");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer airlineId = Integer.parseInt(request.getParameter("airlineId_update"));
		AirlineDAO airlinedao = new AirlineDAO();
		try {
			List<Airline> singleAirline=airlinedao.getAirline(airlineId);
			session.setAttribute("singleAirline", singleAirline);
			response.sendRedirect("updateJSP/UpdateAirline.jsp");
		}catch(BusinessException e) {
			session.setAttribute("exception", e.getMessage());
			response.sendRedirect("ManageAirline.jsp");
		}catch(Exception er) {
			session.setAttribute("exception", er.getMessage());
			response.sendRedirect("ManageAirline.jsp");
		}
	}

}
