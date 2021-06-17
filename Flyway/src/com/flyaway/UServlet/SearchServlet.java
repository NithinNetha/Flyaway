package com.flyaway.UServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.dao.FlightDAO;
import com.flyaway.dao.SearchDAO;
import com.flyaway.model.Flight;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		SearchDAO searchdao = new SearchDAO();
		FlightDAO flightdao = new FlightDAO();
		List<Flight> flightSearch = new ArrayList<Flight>();
		List<String> lop = new ArrayList<String>();
		List<String> loa = new ArrayList<String>();
		try {
			if (request.getParameter("source").isEmpty() || request.getParameter("destination").isEmpty()
					|| request.getParameter("dateOfT").isEmpty() || request.getParameter("nop").equals("")
					|| request.getParameter("classType").equals("")) {
				session.setAttribute("action", "Fields must not be empty");
				response.sendRedirect("Home.jsp");
			} else {
				session.setAttribute("userSource", request.getParameter("source"));
				session.setAttribute("userDest", request.getParameter("destination"));
				session.setAttribute("nop", (Integer.parseInt(request.getParameter("nop"))));
				Date date = new Date(
						new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateOfT")).getTime());
				session.setAttribute("doj", date);
				//search.setDateofJ(date);
				session.setAttribute("classType", request.getParameter("classType"));
				boolean status = searchdao.placeSearch(request.getParameter("source"),request.getParameter("destination"));
				if(status==true) {
				if (request.getParameter("classType").equals("Economy")) {
					flightSearch = searchdao.searchEconomy(request.getParameter("source"),request.getParameter("destination"));
				}
				if (request.getParameter("classType").equals("First")) {
					flightSearch = searchdao.searchFirst(request.getParameter("source"),request.getParameter("destination"));
				}
				if (request.getParameter("classType").equals("Business")) {
					flightSearch = searchdao.searchBusiness(request.getParameter("source"),request.getParameter("destination"));
				}
				if(flightSearch.isEmpty()) {
					session.setAttribute("noFlights", "Sorry, NO flights available in the searched route");
					response.sendRedirect("Home.jsp");
				}else {
				session.setAttribute("flightSearch", flightSearch);
				response.sendRedirect("FlightResult.jsp");}
				}else {
					lop=flightdao.listOfPlaces();
					session.setAttribute("lop", lop);
					loa=flightdao.listOfAirlines();
					session.setAttribute("loa", loa);
					response.sendRedirect("updateJSP/checkPlace.jsp");
				}
			}
		} catch (Exception e1) {
			session.setAttribute("action", e1.getMessage());
			response.sendRedirect("Home.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(request.getParameter("custName").isEmpty() || request.getParameter("custEmail").isEmpty() || request.getParameter("custNum").isEmpty()) {
			session.setAttribute("exception", "Customer detials must not be empty");
			response.sendRedirect("ConfirmBooking.jsp");
			}else {
		String customerName = request.getParameter("custName");
		session.setAttribute("custName", customerName);
		String custEmail= request.getParameter("custEmail");
		session.setAttribute("custEmail", custEmail);
		String custNum = request.getParameter("custNum");
		session.setAttribute("custNum", custNum);
		response.sendRedirect("updateJSP/PaymentService.jsp");
		}	
		
	}

}
