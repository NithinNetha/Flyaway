package com.flyaway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

import com.dbutil.MySQLConnection;
import com.exception.BusinessException;
import com.flyaway.model.Place;

public class PlaceDAO {

	public List<Place> placeList() throws BusinessException{
		List<Place> listPlace = new ArrayList<Place>();
		try(Connection connection=MySQLConnection.getConnection()){
			String query="select place_id,place_name,place_type from flyaway.place";
			PreparedStatement preparedstatment = connection.prepareStatement(query);
			ResultSet rs=preparedstatment.executeQuery();
			while(rs.next()) {
				Place place = new Place();
				place.setPlaceId(rs.getInt("place_id"));
				place.setPlaceName(rs.getString("place_name"));
				place.setPlaceType(rs.getString("place_type"));
				
				listPlace.add(place);
			}
		}catch(ClassNotFoundException e) {
			throw new BusinessException("Contact Admin, Error occured");
		}catch(SQLException e) {
			throw new BusinessException("Contact Admin, Error occured");
		}
		return listPlace;
	}

	public boolean addPlace(Place place) throws BusinessException {
		boolean status = false;
		if(place.getPlaceName().isEmpty() || place.getPlaceType().equals("Choose Type")) {
			throw new BusinessException("Place Name or Place Type can't be empty");
		}else {
			if(place.getPlaceName().matches("^[A-Za-z\\S]+$")) {
				int min=100;int max=999;int b = (int)(Math.random()*(max-min+1)+min);
				try(Connection connection=MySQLConnection.getConnection())
				{
					String query="Insert into flyaway.place values(?,?,?)";
					PreparedStatement preparedstatement=connection.prepareStatement(query);
					preparedstatement.setInt(1, b);
					preparedstatement.setString(2, place.getPlaceName());
					preparedstatement.setString(3, place.getPlaceType());
					int c = preparedstatement.executeUpdate();
					if(c!=1) {
						throw new BusinessException("Insertion Failed");
					}else {status=true;}
				} catch (ClassNotFoundException | SQLException e) {
				throw new BusinessException("Internal Error contact sysadmin with error message "+e);
				}	
			}	
		}
		
	return status;
	}

	public int deletePlace(int placeId) throws BusinessException {
		int status=0;
		try(Connection connection=MySQLConnection.getConnection()){
			String query="DELETE FROM flyaway.place WHERE place_id=?";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1, placeId);
			status = preparedstatement.executeUpdate();
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
		return status;
	}

	public List<Place> getPlace(Integer placeId) throws BusinessException {
		List<Place> singlePlace=new ArrayList<Place>();
		try(Connection connection=MySQLConnection.getConnection()){
			String query="select place_id,place_name,place_type from flyaway.place where place_id=?";
			PreparedStatement preparedstatment = connection.prepareStatement(query);
			preparedstatment.setInt(1,placeId);
			ResultSet rs=preparedstatment.executeQuery();
			while(rs.next()) {
				Place place=new Place();
				place.setPlaceId(rs.getInt("place_id"));
				place.setPlaceName(rs.getString("place_name"));
				place.setPlaceType(rs.getString("place_type"));
				singlePlace.add(place);
			}
		}catch(ClassNotFoundException e) {
			throw new BusinessException("Contact Admin, Error occured");
		}catch(SQLException e) {
			throw new BusinessException("Contact Admin, Error occured");
		}
		return singlePlace;
		
	}

	public int updatePlace(Place place) throws BusinessException {
		int c = 0;
		try(Connection connection = MySQLConnection.getConnection()){
			String query="UPDATE flyaway.place SET place_name=?,place_type=? where place_id=?";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1,place.getPlaceName());
			preparedstatement.setString(2,place.getPlaceType());
			preparedstatement.setInt(3,place.getPlaceId());
			c=preparedstatement.executeUpdate();
			
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
	return c;
	}

	public String getName(int placeId) throws BusinessException {
		String placeName=null;
		try(Connection connection = MySQLConnection.getConnection()){
			String query="Select place_name from place where place_id=?";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1, placeId);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next()) {
				placeName=rs.getString("place_name");
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
		return placeName;
	}

	public List<Integer> getIdsList(String placeName) throws BusinessException {
		List<Integer> placeIds = new ArrayList<Integer>();
		try(Connection connection = MySQLConnection.getConnection()){
			String query = "select flight_id from flight where source_id=? or destination_id=?";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1, placeName);
			preparedstatement.setString(2, placeName);
			ResultSet rs = preparedstatement.executeQuery();
			while(rs.next()) {
				placeIds.add(rs.getInt("flight_id"));
			}	
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
		return placeIds;
	}

}
