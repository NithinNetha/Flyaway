package com.flyaway.UServlet;

import java.io.IOException;
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
 * Servlet implementation class AUServlet
 */
public class AUServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AUServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("airlineName_update").isEmpty() && request.getParameter("contactName_update").isEmpty() && request.getParameter("contactNumber_update").isEmpty()) {
			session.setAttribute("exception", "All fields were empty");
			response.sendRedirect("ManageAirline.jsp");
		}else {
			Integer airlineId = Integer.parseInt(request.getParameter("airlineId_update"));
			AirlineDAO airlinedao = new AirlineDAO();
			try {
				List<Airline> singleAirline = airlinedao.getAirline(airlineId);
					for(Airline sa: singleAirline) {
					if(!request.getParameter("airlineName_update").isEmpty()) {
						sa.setAirlineName(request.getParameter("airlineName_update"));
					}
					if(!request.getParameter("contactName_update").isEmpty()) {
						sa.setContactName(request.getParameter("contactName_update"));
					}
					if(!request.getParameter("contactNumber_update").isEmpty()) {
						sa.setContactNumber(request.getParameter("contactNumber_update"));
					}
					int status = airlinedao.updateAirline(sa);
					if (status != 1) {
						session.setAttribute("exception", "Update Failed");
						response.sendRedirect("ManageAirline.jsp");
					} else {
						List<Airline> listAirline = airlinedao.airlineList();
						session.setAttribute("listAirline", listAirline);
						session.setAttribute("action", "Updated Successfully");
						response.sendRedirect("ManageAirline.jsp");
					}
					}
			}catch (BusinessException e) {
				session.setAttribute("exception", "classId Doesn't Exist in Class Table");
				response.sendRedirect("ManageAirline.jsp");
			} catch (Exception er) {
				session.setAttribute("exception", "classId Doesn't Exist in Class Table");
				response.sendRedirect("ManageAirline.jsp");
			}
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
