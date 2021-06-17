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
 * Servlet implementation class BookingServlet
 */
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session= request.getSession();
		Integer flightId = Integer.parseInt(request.getParameter("flightId_book"));
		session.setAttribute("flightId", flightId);
		String classType = (String)session.getAttribute("classType");
		FlightDAO flightdao = new FlightDAO();
		try {
			List<Flight> singleFlight = flightdao.getFlight(flightId);
			int nop = (Integer)(session.getAttribute("nop"));
			for(Flight flight:singleFlight) {
				session.setAttribute("userAirline",flight.getAirline());
				if(classType.equals("Economy")) {
					double price=flight.getEcPrice()*nop;
					session.setAttribute("price", price);
				}
				if(classType.equals("First")) {
					double price=flight.getFcPrice()*nop;
					session.setAttribute("price", price);
				}
				if(classType.equals("Business")) {
					double price=flight.getBcPrice()*nop;
					session.setAttribute("price", price);
				}
			response.sendRedirect("ConfirmBooking.jsp");
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
