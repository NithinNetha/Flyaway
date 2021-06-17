package com.flyaway.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exception.BusinessException;
import com.flyaway.dao.AirlineDAO;
import com.flyaway.dao.FlightDAO;
import com.flyaway.model.Airline;
import com.flyaway.model.Flight;

/**
 * Servlet implementation class ADFlightServlet
 */
public class ADFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ADFlightServlet() {
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
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		FlightDAO flightdao = new FlightDAO();
		if (session != null) {
			try {
				int flightId = Integer.parseInt(request.getParameter("flightId_delete"));
				int status = flightdao.deleteFlight(flightId);
				if (status != 1) {
					session.setAttribute("exception", "Deletion Failed");
				} else {
					session.setAttribute("action", "Deleted Successfully");
				}
				List<Flight> listFlight = flightdao.flightList();
				session.setAttribute("listFlight", listFlight);
				response.sendRedirect("ManageFlight.jsp");
			} catch (BusinessException e) {
				session.setAttribute("exception", e);
				response.sendRedirect("ManageFlight.jsp");
			} catch (Exception el) {
				session.setAttribute("exception", el);
				response.sendRedirect("ManageFlight.jsp");
			}
		} else {
			out.print("<center><h3>Session has expired.. Navigating to HomePage</h3></center>");
			response.setHeader("refresh", "5;url='/Flyway'");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		FlightDAO flightdao = new FlightDAO();
		Flight flight = new Flight();
		boolean status = false;
		if (session != null) {
			try {
				if (request.getParameter("source")==null || request.getParameter("destination")==null
						|| request.getParameter("airline")==null) {
					List<Flight> listFlight = flightdao.flightList();
					session.setAttribute("listFlight", listFlight);
					session.setAttribute("exception", "Required feilds were empty");
					response.sendRedirect("ManageFlight.jsp");
				} else {
					flight.setSource(request.getParameter("source"));
					flight.setDestination(request.getParameter("destination"));
					flight.setAirline(request.getParameter("airline"));
					if (!request.getParameter("ecPrice").isEmpty()) {
						flight.setEcPrice(Float.parseFloat(request.getParameter("ecPrice")));
					} else {
						flight.setEcPrice(0.1);
					}
					if (!request.getParameter("fcPrice").isEmpty()) {
						flight.setFcPrice(Float.parseFloat(request.getParameter("fcPrice")));
					} else {
						flight.setFcPrice(0.1);
					}
					if (!request.getParameter("bcPrice").isEmpty()) {
						flight.setBcPrice(Float.parseFloat(request.getParameter("bcPrice")));
					} else {
						flight.setBcPrice(0.1);
					}
					if (flight.getSource().equals(flight.getDestination())) {
						session.setAttribute("exception", "Source and Destination must not be same");
					} else if (request.getParameter("fcPrice").isEmpty() && request.getParameter("ecPrice").isEmpty()
							&& request.getParameter("bcPrice").isEmpty()) {
						session.setAttribute("exception", "All Class prices were empty");
					} else {
						status = flightdao.addFlight(flight);
					}
					if (status == true) {
						session.setAttribute("action", "Added Sucessfully");
					}
					List<Flight> listFlight = flightdao.flightList();
					session.setAttribute("listFlight", listFlight);
					response.sendRedirect("ManageFlight.jsp");
				}
			} catch (BusinessException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect("ManageFlight.jsp");
			} catch (Exception e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect("ManageFlight.jsp");
			}
		} else {
			out.print("\"<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>\"");
			response.setHeader("refresh", "5;url='/Flyway'");
		}
	}

}
