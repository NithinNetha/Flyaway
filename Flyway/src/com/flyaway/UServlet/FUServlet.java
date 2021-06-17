package com.flyaway.UServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exception.BusinessException;
import com.flyaway.dao.FlightDAO;
import com.flyaway.model.Flight;

/**
 * Servlet implementation class FUServlet
 */
public class FUServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FUServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Double ec = 0.0;
		Double fc = 0.0;
		Double bc = 0.0;
		String so = request.getParameter("source_update");
		String de = request.getParameter("destination_update");
		String ai = request.getParameter("airline_update");
		if (request.getParameter("ecPrice_update") != null && !request.getParameter("ecPrice_update").equals("")
				&& !request.getParameter("ecPrice_update").isEmpty()) {
			ec = Double.parseDouble(request.getParameter("ecPrice_update"));
		} 
		if (request.getParameter("fcPrice_update") != null && !request.getParameter("fcPrice_update").equals("")
				&& !request.getParameter("fcPrice_update").isEmpty()) {
			fc = Double.parseDouble(request.getParameter("fcPrice_update"));
		} 
		if (request.getParameter("bcPrice_update") != null && !request.getParameter("bcPrice_update").equals("")
				&& !request.getParameter("bcPrice_update").isEmpty()) {
			bc = Double.parseDouble(request.getParameter("bcPrice_update"));
		} 
		HttpSession session = request.getSession();
		FlightDAO flightdao = new FlightDAO();
		Integer flightId = Integer.parseInt(request.getParameter("flightId_update"));
		try {
			if (so == null && de == null && ai == null && ec == 0.0 && fc == 0.0 && bc == 0.0) {
				List<Flight> listFlight = flightdao.flightList();
				session.setAttribute("listFlight", listFlight);
				session.setAttribute("exception", "All fields must not be empty");
				response.sendRedirect("ManageFlight.jsp");
			} else {
				List<Flight> singleFlight = flightdao.getFlight(flightId);
				for (Flight sf : singleFlight) {
					if (so != null) {
						sf.setSource(so);
					}
					if (de != null) {
						sf.setDestination(de);
					}
					if (ai != null) {
						sf.setAirline(ai);
					}
					if (ec != 0.0) {
						sf.setEcPrice(ec);
					}
					if (fc != 0.0) {
						sf.setFcPrice(fc);
					}
					if (bc != 0.0) {
						sf.setBcPrice(bc);
					}
					int status = flightdao.updateFlight(sf);
					if (status != 1) {
						session.setAttribute("exception", "Update Failed");
						response.sendRedirect("ManageFlight.jsp");
					} else {
						List<Flight> listFlight = flightdao.flightList();
						session.setAttribute("listFlight", listFlight);
						session.setAttribute("action", "Updated Successfully");
						response.sendRedirect("ManageFlight.jsp");
					}
				}
			}
		} catch (BusinessException e) {
			session.setAttribute("exception", "classId Doesn't Exist in Class Table");
			response.sendRedirect("ManageFlight.jsp");
		} catch (Exception er) {
			session.setAttribute("exception", "classId Doesn't Exist in Class Table");
			response.sendRedirect("ManageFlight.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
