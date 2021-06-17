package com.flyaway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbutil.MySQLConnection;
import com.exception.BusinessException;

public class UserDAO {

	public boolean userValid(String username, String password) throws BusinessException {
		boolean bool=false;
		try(Connection connection=MySQLConnection.getConnection()){
			PreparedStatement  preparedStatement=connection.prepareStatement("select * from users where username=? and password=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next())
				bool=true;
		
		}catch(ClassNotFoundException e) {throw new BusinessException("Internal Error contact sysadmin with error message "+e);	
		}catch(SQLException e) {throw new BusinessException("Internal Error contact sysadmin with error message "+e);}
		return bool;
	}

	public String userType(String username, String password) throws BusinessException {
		String usertype=null;
		try(Connection connection=MySQLConnection.getConnection()){
			PreparedStatement  preparedStatement=connection.prepareStatement("select userType from users where username=? and password=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next())
				usertype=rs.getString("Usertype");
			
		
		}catch(ClassNotFoundException e) {throw new BusinessException("Internal Error contact sysadmin with error message "+e);
			
		}catch(SQLException e) {throw new BusinessException("Internal Error contact sysadmin with error message "+e);}
	return usertype;	
	}

	public String ExistingPassword(String username) throws BusinessException {
		String oldPassword=null;
		try(Connection connection=MySQLConnection.getConnection()){
			PreparedStatement  preparedStatement=connection.prepareStatement("select password from users where username=?");
			preparedStatement.setString(1, username);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next())
				oldPassword=rs.getString("password");
			
		
		}catch(ClassNotFoundException e) {throw new BusinessException("Internal Error contact sysadmin with error message "+e);
			
		}catch(SQLException e) {throw new BusinessException("Internal Error contact sysadmin with error message "+e);}
	return oldPassword;
	}

	public void changePassword(String username, String newPassword) throws BusinessException {
		try(Connection connection=MySQLConnection.getConnection()){
			PreparedStatement  preparedStatement=connection.prepareStatement("update users set password=? where username=?");
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, username);
			preparedStatement.executeUpdate();
		}catch(ClassNotFoundException e) {throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		
		}catch(SQLException e) {throw new BusinessException("Internal Error contact sysadmin with error message "+e);}
	}
}
