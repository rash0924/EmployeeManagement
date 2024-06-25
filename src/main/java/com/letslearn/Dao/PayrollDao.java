package com.letslearn.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.letslearn.Modal.Payroll;

public class PayrollDao {
	private Connection con;
	private String query;
    private PreparedStatement pst;
    private ResultSet rs;

	public PayrollDao(Connection con) {
		this.con = con;
	}
	
	public int addPayroll(Payroll payroll) {
		int result = 0;
  		try {
  			query="INSERT INTO payroll (name,paymentFor,email,bonus,salary)\r\n"
  					+ "VALUES (?,?,?,?,?);";
  			pst = this.con.prepareStatement(query);
            pst.setString(1,  payroll.getName());
            pst.setString(2, payroll.getPaymentFor());
            pst.setString(3, payroll.getEmail());
            pst.setString(4, payroll.getBonus());
            pst.setString(5, payroll.getSalary());
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
	}
	//get coupon id name
	public List<Payroll> getAllPayrolls(){
  		List<Payroll> payroll = new ArrayList<Payroll>();
  		try {
  			query="select * from payroll";
  			pst=this.con.prepareStatement(query);
  			rs=pst.executeQuery();
  			while(rs.next()) {
  				Payroll cpn = new Payroll("","","");
  				cpn.setBonus(rs.getString("bonus"));
  				cpn.setName(rs.getString("name"));
  				cpn.setEmail(rs.getString("email"));
  				cpn.setPaymentFor(rs.getString("paymentFor"));
  				cpn.setSalary(rs.getString("salary"));
  				payroll.add(cpn);
  				
  			}
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return payroll;
  		
  	}
	
	public int deletePayroll(String email) {
  		int result=0;
  		try {
  			query="DELETE FROM payroll WHERE email=?;";
  			pst = this.con.prepareStatement(query);
            pst.setString(1,email);
      
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
  	}
	
	//updating coupons
  	public int updatePayroll(Payroll payroll) {
  		int result=0;
  		try {
  			query="UPDATE payroll\r\n"
  					+ "SET name = ?, paymentFor= ? , bonus=?,salary=?\r\n"
  					+ "WHERE email = ?;";
  			pst = this.con.prepareStatement(query);
  			 pst.setString(1,  payroll.getName());
             pst.setString(2, payroll.getPaymentFor());
             pst.setString(3, payroll.getBonus());
             pst.setString(4, payroll.getSalary());
             pst.setString(5, payroll.getEmail());
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
  	}
}
