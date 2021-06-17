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

public class FlightDAO {
	public List<String> listOfPlaces() throws BusinessException{
		List<String> lp = new ArrayList<String>();
		try(Connection connection = MySQLConnection.getConnection()){
			String query = "select place_name from flyaway.place";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			ResultSet rs= preparedstatement.executeQuery();
			while(rs.next()) {
				lp.add(rs.getString("place_name"));
			}
			
		}catch(ClassNotFoundException e) {
			throw new BusinessException("Internal Error.. contact Database Admin "+e);
		}catch(SQLException el) {
			throw new BusinessException("Internal Error.. contact Database Admin "+el);
		}
		return lp;
	}
	public List<Flight> flightList() throws BusinessException{
		List<Flight> listFlight = new ArrayList<Flight>();
		try(Connection connection = MySQLConnection.getConnection()){
			String query = "select * from flyaway.flight";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			ResultSet rs= preparedstatement.executeQuery();
			while(rs.next()) {
				Flight flight = new Flight();
				flight.setFlightId(rs.getInt("flight_id"));
				flight.setSource(rs.getString("source_id"));
				flight.setDestination(rs.getString("destination_id"));
				flight.setAirline(rs.getString("airline_id"));
				flight.setEcPrice(rs.getDouble("ec_price"));
				flight.setFcPrice(rs.getDouble("fc_price"));
				flight.setBcPrice(rs.getDouble("bc_price"));
				listFlight.add(flight);
			}
			
		}catch(ClassNotFoundException e) {
			throw new BusinessException("Internal Error.. contact Database Admin "+e);
		}catch(SQLException el) {
			throw new BusinessException("Internal Error.. contact Database Admin "+el);
		}
		return listFlight;
	}
	public List<String> listOfAirlines() throws BusinessException{
		List<String> la = new ArrayList<String>();
		try(Connection connection = MySQLConnection.getConnection()){
			String query = "select airline_name from flyaway.airline";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			ResultSet rs= preparedstatement.executeQuery();
			while(rs.next()) {
				la.add(rs.getString("airline_name"));
			}
			
		}catch(ClassNotFoundException e) {
			throw new BusinessException("Internal Error.. contact Database Admin "+e);
		}catch(SQLException el) {
			throw new BusinessException("Internal Error.. contact Database Admin "+el);
		}
		return la;
	}
	public boolean addFlight(Flight flight) throws BusinessException {
		boolean status = false;
		if(flight.getSource().equals("Choose Source") 
				&& flight.getDestination().equals("Choose Destination") 
				&& flight.getAirline().equals("Choose Airline")
				&& flight.getEcPrice()==0 
				&& flight.getBcPrice()==0 
				&& flight.getBcPrice()==0) {
			throw new BusinessException("Fields must not be empty");
		}else {
				int min=10000;
				int max=99999;
				int b = (int)(Math.random()*(max-min+1)+min);
				try(Connection connection=MySQLConnection.getConnection())
				{
					String query="Insert into flyaway.flight values(?,?,?,?,?,?,?)";
					PreparedStatement preparedstatement=connection.prepareStatement(query);
					preparedstatement.setInt(1, b);
					preparedstatement.setString(2, flight.getSource());
					preparedstatement.setString(3, flight.getDestination());
					preparedstatement.setString(4, flight.getAirline());
					preparedstatement.setDouble(5, flight.getEcPrice());
					preparedstatement.setDouble(6, flight.getFcPrice());
					preparedstatement.setDouble(7, flight.getBcPrice());
					int c = preparedstatement.executeUpdate();
					if(c!=1) {
						throw new BusinessException("Insertion Failed");
					}else {status=true;}
				} catch (ClassNotFoundException | SQLException e) {
				throw new BusinessException("Internal Error contact sysadmin with error message "+e);
				}	
		}
		
	return status;
	}
	public int deleteFlight(int flightId) throws BusinessException {
		int status=0;
		try(Connection connection=MySQLConnection.getConnection()){
			String query="DELETE FROM flyaway.flight WHERE flight_id=?";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1, flightId);
			status = preparedstatement.executeUpdate();
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
		return status;
	}
	public List<Flight> getFlight(Integer flightId) throws BusinessException {
		List<Flight> singleFlight=new ArrayList<Flight>();
		try(Connection connection=MySQLConnection.getConnection()){
			String query="select flight_id,source_id,destination_id,airline_id,ec_price,fc_price,bc_price from flyaway.flight where flight_id=?";
			PreparedStatement preparedstatment = connection.prepareStatement(query);
			preparedstatment.setInt(1,flightId);
			ResultSet rs=preparedstatment.executeQuery();
			while(rs.next()) {
				Flight flight=new Flight();
				flight.setFlightId(rs.getInt("flight_id"));
				flight.setSource(rs.getString("source_id"));
				flight.setDestination(rs.getString("destination_id"));
				flight.setAirline(rs.getString("airline_id"));
				flight.setEcPrice(rs.getDouble("ec_price"));
				flight.setFcPrice(rs.getDouble("fc_price"));
				flight.setBcPrice(rs.getDouble("bc_price"));
				singleFlight.add(flight);
			}
		}catch(ClassNotFoundException e) {
			throw new BusinessException("Contact Admin, Error occured");
		}catch(SQLException e) {
			throw new BusinessException("Contact Admin, Error occured");
		}
		return singleFlight;
	}
	public int updateFlight(Flight fa) throws BusinessException {
		int c = 0;
		try(Connection connection = MySQLConnection.getConnection()){
			String query="UPDATE flyaway.flight SET source_id=?,destination_id=?,airline_id=?,ec_price=?,fc_price=?,bc_price=? where flight_id=?";
			PreparedStatement preparedstatment = connection.prepareStatement(query);
			preparedstatment.setString(1, fa.getSource());
			preparedstatment.setString(2, fa.getDestination());
			preparedstatment.setString(3, fa.getAirline());
			preparedstatment.setDouble(4, fa.getEcPrice());
			preparedstatment.setDouble(5, fa.getFcPrice());
			preparedstatment.setDouble(6, fa.getBcPrice());
			preparedstatment.setInt(7, fa.getFlightId());
			c=preparedstatment.executeUpdate();
			
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
	return c;
	}
	public List<Integer> getIdsList(String airlineName) throws BusinessException {
		List<Integer> flightIds = new ArrayList<Integer>();
		try(Connection connection = MySQLConnection.getConnection()){
			String query = "select flight_id from flight where airline_id=?";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1, airlineName);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next()) {
				flightIds.add(rs.getInt("flight_id"));
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
		return flightIds;
	}
	
	
	
}
