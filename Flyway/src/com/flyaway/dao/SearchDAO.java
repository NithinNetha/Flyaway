package com.flyaway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbutil.MySQLConnection;
import com.exception.BusinessException;
import com.flyaway.model.Flight;

public class SearchDAO {

	public List<Flight> searchEconomy(String source,String destination) throws BusinessException {
		List<Flight> listEconomy = new ArrayList<Flight>();
		try(Connection connection=MySQLConnection.getConnection()){
			String query = "select flight_id,source_id,destination_id,airline_id,ec_price from flight where source_id=? and destination_id=?";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1, source);
			preparedstatement.setString(2, destination);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next()) {
				Flight flight = new Flight();
				flight.setFlightId(rs.getInt("flight_id"));
				flight.setSource(rs.getString("source_id"));
				flight.setDestination(rs.getString("destination_id"));
				flight.setAirline(rs.getString("airline_id"));
				flight.setEcPrice(rs.getDouble("ec_price"));
				if(flight.getEcPrice()!=0.1)
					listEconomy.add(flight);
			}
		}catch(ClassNotFoundException e) {
			throw new BusinessException("Contact Admin, Error occured"+e);
		}catch(SQLException e) {
			throw new BusinessException("Contact Admin, Error occured"+e);
		}
	return listEconomy;
	}

	public List<Flight> searchFirst(String source,String destination) throws BusinessException {
		List<Flight> listFirst = new ArrayList<Flight>();
		try(Connection connection=MySQLConnection.getConnection()){
			String query = "select flight_id,source_id,destination_id,airline_id,fc_price from flight where source_id=? and destination_id=?";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1, source);
			preparedstatement.setString(2, destination);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next()) {
				Flight flight = new Flight();
				flight.setFlightId(rs.getInt("flight_id"));
				flight.setSource(rs.getString("source_id"));
				flight.setDestination(rs.getString("destination_id"));
				flight.setAirline(rs.getString("airline_id"));
				flight.setFcPrice(rs.getDouble("fc_price"));
				if(flight.getFcPrice()!=0.1)
					listFirst.add(flight);
			}
		}catch(ClassNotFoundException e) {
			throw new BusinessException("Contact Admin, Error occured"+e);
		}catch(SQLException e) {
			throw new BusinessException("Contact Admin, Error occured"+e);
		}
	return listFirst;
	}

	public List<Flight> searchBusiness(String source,String destination) throws BusinessException {
		List<Flight> listBusiness = new ArrayList<Flight>();
		try(Connection connection=MySQLConnection.getConnection()){
			String query = "select flight_id,source_id,destination_id,airline_id,bc_price from flight where source_id=? and destination_id=?";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1, source);
			preparedstatement.setString(2, destination);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next()) {
				Flight flight = new Flight();
				flight.setFlightId(rs.getInt("flight_id"));
				flight.setSource(rs.getString("source_id"));
				flight.setDestination(rs.getString("destination_id"));
				flight.setAirline(rs.getString("airline_id"));
				flight.setBcPrice(rs.getDouble("bc_price"));
				if(flight.getFcPrice()!=0.1)
					listBusiness.add(flight);
			}
		}catch(ClassNotFoundException e) {
			throw new BusinessException("Contact Admin, Error occured"+e);
		}catch(SQLException e) {
			throw new BusinessException("Contact Admin, Error occured"+e);
		}
	return listBusiness;
	}

	public boolean placeSearch(String source, String destination) throws BusinessException {
		boolean status = false;
		List<String> lop = new ArrayList<String>();
		FlightDAO flightdao = new FlightDAO();
		lop=flightdao.listOfPlaces();
		status = checkPlace(lop,source);
		status = checkPlace(lop, destination);
		return status;
	}
	private boolean checkPlace(List<String> list,String place) { //Searching using Naive Pattern Searching 
			boolean status = true;
			int flag=0;
			String name=place.toUpperCase(); //convert file name to all uppercase 
			for(String str:list) //for each loop to retrieve each file name
				{
				String txt=str.toUpperCase(); //convert retrieved file name to uppercase
					int M=name.length(); //get and store length of user entered file name
					int N=txt.length();	 //get and store length of retrieved file name from list
					//A loop to slide pat one by one 
					for (int i = 0; i <= N - M; i++) {
						int j;	  
						// For current index i, check for pattern match 
						for (j = 0; j < M; j++)
							if (txt.charAt(i + j) != name.charAt(j)) //checks for character match
								break;
						// checks for is it only filename or fileName without extension 
						if ((j == M) && ((name.charAt(M-1)==txt.charAt(N-1)) || (txt.charAt(j)=='.'))) 
						{  
							flag++;
						}
					}
		
				}
			if(flag==0)
				status = false;
			return status;
	}

}
