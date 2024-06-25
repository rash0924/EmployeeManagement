package com.letslearn.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.letslearn.Modal.Hr;

public class HrDao {
	private Connection con;
	private String query;
    private PreparedStatement pst;
    private ResultSet rs;

	public HrDao(Connection con) {
		this.con = con;
	}
	
	public int addHr(Hr hr) {
		int result = 0;
  		try {
  			query="INSERT INTO hr (name,email,nic,city,grade,tel)\r\n"
  					+ "VALUES (?,?,?,?,?,?);";
  			pst = this.con.prepareStatement(query);
            pst.setString(1,  hr.getName());
            pst.setString(2, hr.getEmail());
            pst.setString(3, hr.getNic());
            pst.setString(4, hr.getCity());
            pst.setString(5, hr.getGrade());
            pst.setString(6, hr.getTel());
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
	}
	//get coupon id name
	public List<Hr> getAllHrs(){
  		List<Hr> hr = new ArrayList<Hr>();
  		try {
  			query="select * from hr";
  			pst=this.con.prepareStatement(query);
  			rs=pst.executeQuery();
  			while(rs.next()) {
  				Hr cpn = new Hr("","","");
  				cpn.setCity(rs.getString("city"));
  				cpn.setName(rs.getString("name"));
  				cpn.setNic(rs.getString("nic"));
  				cpn.setEmail(rs.getString("email"));
  				cpn.setGrade(rs.getString("grade"));
  				cpn.setTel(rs.getString("tel"));
  				hr.add(cpn);
  				
  			}
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return hr;
  		
  	}
	
	public int deleteHr(String email) {
  		int result=0;
  		try {
  			query="DELETE FROM hr WHERE email=?;";
  			pst = this.con.prepareStatement(query);
            pst.setString(1,email);
      
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
  	}
	
	//updating coupons
  	public int updateHr(Hr hr) {
		System.out.print(hr.getEmail());
  		int result=0;
  		try {
  			query="UPDATE hr SET name = ?, grade= ? , city=?,nic=?,tel=? WHERE email = ?;";
  			pst = this.con.prepareStatement(query);
  			 pst.setString(1,  hr.getName());
             pst.setString(2, hr.getGrade());
             pst.setString(3, hr.getCity());
             pst.setString(4, hr.getNic());
             pst.setString(5, hr.getTel());
             pst.setString(6, hr.getEmail());
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
  	}

}
