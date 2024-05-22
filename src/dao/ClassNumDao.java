package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
//a
public class ClassNumDao extends Dao {



    public List<String> filter(School school)throws Exception{
		List<String> list =new ArrayList<>();
    	Connection connection = getConnection();
    	PreparedStatement statement = null;

    	try {
    		statement = connection.prepareStatement("SELECT class_num FROM class_num WHERE school_cd = ?");
    		statement.setString(1,school.getCd());
   			ResultSet rSet = statement.executeQuery();

    		if (rSet.next()){
    			list.add(rSet.getString("class_num"));

			} else {
				list.add(null);
			}
    	}catch (Exception e){
    		throw e;
    	}finally{
    		if (statement != null) {
    			try {
    				statement.close();
    			}catch (SQLException sqle){
    				throw sqle;
    			}
    		}
    		if (connection != null) {
    			try {
    				connection.close();
    			}catch (SQLException sqle){
    				throw sqle;
    			}
    		}
    	}

   		return list;
   	}

}
