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
import com.flyaway.dao.FlightDAO;
import com.flyaway.dao.PlaceDAO;
import com.flyaway.model.Place;

/**
 * Servlet implementation class ADPlaceServlet
 */
public class ADPlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADPlaceServlet() {
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
		PlaceDAO placedao=new PlaceDAO();
		FlightDAO flightdao = new FlightDAO();
		List<Integer> flightIds = new ArrayList<Integer>();
		if(session!=null) {
			try {
				int placeId=Integer.parseInt(request.getParameter("placeId_delete"));
				String placeName = placedao.getName(placeId);
				flightIds = placedao.getIdsList(placeName);
				for(int flightId:flightIds) {
					int c = flightdao.deleteFlight(flightId);
					c=0;
				}
				int status=placedao.deletePlace(placeId);
				if(status!=1) {
					session.setAttribute("exception", "Deletion Failed" );
				}
				else {
				
					session.setAttribute("action", "Deleted Successfully" );
				}
				List<Place> listPlace = placedao.placeList();
				session.setAttribute("listPlace", listPlace);
				response.sendRedirect("ManagePlace.jsp");
			}catch(BusinessException e) {
				session.setAttribute("exception", e);
				response.sendRedirect("ManagePlace.jsp");
			}catch(Exception el) {
				session.setAttribute("exception", el);
				response.sendRedirect("ManagePlace.jsp");
			}
		}else {
			out.print("<center><h3>Session has expired.. Navigating to HomePage</h3></center>");
			response.setHeader("refresh", "5;url='/Flyway'");}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		PlaceDAO placedao=new PlaceDAO();
		Place place = new Place();
		boolean status=false;
		if(session!=null) {
			try{
				place.setPlaceName(request.getParameter("placeName"));
				place.setPlaceType(request.getParameter("placeType"));
				status=placedao.addPlace(place);
				if(status==true) {
					session.setAttribute("action","Added Sucessfully");
				}
				List<Place> listPlace = placedao.placeList();
				session.setAttribute("listPlace", listPlace);
				response.sendRedirect("ManagePlace.jsp");
			}catch(BusinessException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect("ManagePlace.jsp");
			}catch(Exception e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect("ManagePlace.jsp");
			}
		}else {
			out.print("\"<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>\"");
			response.setHeader("refresh", "5;url='/learners'");
		}
	}

}
