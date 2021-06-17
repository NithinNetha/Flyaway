package com.flyaway.UServlet;

import java.io.IOException;
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
 * Servlet implementation class PUServlet
 */
public class PUServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PUServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("placeName_update").isEmpty() && request.getParameter("placeType_update").equals("Choose Type")) {
			session.setAttribute("exception", "All fields were empty");
			response.sendRedirect("ManagePlace.jsp");
		}else {
			Integer placeId = Integer.parseInt(request.getParameter("placeId_update"));
			PlaceDAO placedao = new PlaceDAO();
			try {
				List<Place> singlePlace = placedao.getPlace(placeId);
					for(Place sp: singlePlace) {
					if(!request.getParameter("placeName_update").isEmpty()) {
						sp.setPlaceName(request.getParameter("placeName_update"));
					}
					if(!request.getParameter("placeType_update").equals("Choose Type")) {
						sp.setPlaceType(request.getParameter("placeType_update"));
					}
					int status = placedao.updatePlace(sp);
					if (status != 1) {
						session.setAttribute("exception", "Update Failed");
						response.sendRedirect("ManagePlace.jsp");
					} else {
						List<Place> listPlace = placedao.placeList();
						session.setAttribute("listPlace", listPlace);
						session.setAttribute("action", "Updated Successfully");
						response.sendRedirect("ManagePlace.jsp");
					}
					}
			}catch (BusinessException e) {
				session.setAttribute("exception", "classId Doesn't Exist in Class Table");
				response.sendRedirect("viewClass.jsp");
			} catch (Exception er) {
				session.setAttribute("exception", "classId Doesn't Exist in Class Table");
				response.sendRedirect("viewClass.jsp");
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
