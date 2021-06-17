package com.flyaway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbutil.MySQLConnection;
import com.exception.BusinessException;
import com.flyaway.model.Airline;

public class AirlineDAO {

	public List<Airline> airlineList() throws BusinessException {
		List<Airline> listAirline = new ArrayList<Airline>();
		try(Connection connection=MySQLConnection.getConnection()){
			String query="select airline_id,airline_name,contact_name,contact_number from flyaway.airline";
			PreparedStatement preparedstatment = connection.prepareStatement(query);
			ResultSet rs=preparedstatment.executeQuery();
			while(rs.next()) {
				Airline airline = new Airline();
				airline.setAirlineId(rs.getInt("airline_id"));
				airline.setAirlineName(rs.getString("airline_name"));
				airline.setContactName(rs.getString("contact_name"));
				airline.setContactNumber(rs.getString("contact_number"));		
				listAirline.add(airline);
			}
		}catch(ClassNotFoundException e) {
			throw new BusinessException("Contact Admin, Error occured"+e);
		}catch(SQLException e) {
			throw new BusinessException("Contact Admin, Error occured"+e);
		}
		return listAirline;
	}

	public boolean addAirline(Airline airline) throws BusinessException {
		boolean status = false;
		if(airline.getAirlineName().isEmpty() || airline.getContactName().isEmpty() || airline.getContactNumber().isEmpty()) {
			throw new BusinessException("Fields must not be empty");
		}else {
				int min=10;int max=99;int b = (int)(Math.random()*(max-min+1)+min);
				try(Connection connection=MySQLConnection.getConnection())
				{
					String query="Insert into flyaway.airline values(?,?,?,?)";
					PreparedStatement preparedstatement=connection.prepareStatement(query);
					preparedstatement.setInt(1, b);
					preparedstatement.setString(2, airline.getAirlineName());
					preparedstatement.setString(3, airline.getContactName());
					preparedstatement.setString(4, airline.getContactNumber());
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

	public int deleteAirline(int airlineId) throws BusinessException {
		int status=0;
		try(Connection connection=MySQLConnection.getConnection()){
			String query="DELETE FROM flyaway.airline WHERE airline_id=?";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1, airlineId);
			status = preparedstatement.executeUpdate();
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
		return status;
	}

	public List<Airline> getAirline(Integer airlineId) throws BusinessException {
		List<Airline> singleAirline=new ArrayList<Airline>();
		try(Connection connection=MySQLConnection.getConnection()){
			String query="select airline_id,airline_name,contact_name,contact_number from flyaway.airline where airline_id=?";
			PreparedStatement preparedstatment = connection.prepareStatement(query);
			preparedstatment.setInt(1,airlineId);
			ResultSet rs=preparedstatment.executeQuery();
			while(rs.next()) {
				Airline airline=new Airline();
				airline.setAirlineId(rs.getInt("airline_id"));
				airline.setAirlineName(rs.getString("airline_name"));
				airline.setContactName(rs.getString("contact_name"));
				airline.setContactNumber(rs.getString("contact_number"));
				singleAirline.add(airline);
			}
		}catch(ClassNotFoundException e) {
			throw new BusinessException("Contact Admin, Error occured");
		}catch(SQLException e) {
			throw new BusinessException("Contact Admin, Error occured");
		}
		return singleAirline;
	}

	public int updateAirline(Airline sa) throws BusinessException {
		int c = 0;
		try(Connection connection = MySQLConnection.getConnection()){
			String query="UPDATE flyaway.airline SET airline_name=?,contact_name=?,contact_number=? where airline_id=?";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1,sa.getAirlineName());
			preparedstatement.setString(2,sa.getContactName());
			preparedstatement.setString(3,sa.getContactNumber());
			preparedstatement.setInt(4,sa.getAirlineId());
			c=preparedstatement.executeUpdate();
			
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
	return c;
	}

	public String getName(int airlineId) throws BusinessException {
		String airlineName=null;
		try(Connection connection = MySQLConnection.getConnection()){
			String query="Select airline_name from airline where airline_id=?";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1, airlineId);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next()) {
				airlineName=rs.getString("airline_name");
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
		return airlineName;
	}

}
