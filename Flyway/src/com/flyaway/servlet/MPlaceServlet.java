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
import com.flyaway.dao.PlaceDAO;
import com.flyaway.model.Place;

/**
 * Servlet implementation class ManagePlaces
 */
public class MPlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MPlaceServlet() {
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
		PlaceDAO placedao = new PlaceDAO();
		List<Place> listPlace = new ArrayList<Place>();
		if(session!=null) {
			try {
				listPlace=placedao.placeList();
				session.setAttribute("listPlace", listPlace);
				response.sendRedirect("ManagePlace.jsp");
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
		Integer placeId = Integer.parseInt(request.getParameter("placeId_update"));
		PlaceDAO placedao = new PlaceDAO();
		try {
			List<Place> singlePlace=placedao.getPlace(placeId);
			session.setAttribute("singlePlace", singlePlace);
			response.sendRedirect("updateJSP/UpdatePlace.jsp");
		}catch(BusinessException e) {
			session.setAttribute("exception", e.getMessage());
			response.sendRedirect("ManagePlace.jsp");
		}catch(Exception er) {
			session.setAttribute("exception", er.getMessage());
			response.sendRedirect("ManagePlace.jsp");
		}
	}

}
