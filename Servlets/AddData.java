package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class AddData
 */
@WebServlet("/AddData")
public class AddData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Get the row info
		int total_rows = 0;
		String name_customer = request.getParameter("name_customer");
		String cust_number = request.getParameter("cust_number");
		long invoice_id = Long.parseLong(request.getParameter("invoice_id"));
		double total_open_amount = Double.parseDouble(request.getParameter("total_open_amount"));
		String due_in_date = request.getParameter("due_in_date");
		String notes = "NA";
		if(request.getParameter("notes") != null)
			notes = request.getParameter("notes");
		
		// Connection to the SQL database
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			System.out.println("Connected to database...");
			
			// Prepare query to get data from SQL
			// Query to get the total number of columns in database
			String query = "SELECT MAX(FIELD1) AS total_rows FROM mytable;";
			
			// Fetch data from SQL
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery(query);
			
			while(results.next()) {
				total_rows = results.getInt("total_rows");
			}
			
			// Prepare query to add new rows to database
			query = "INSERT INTO mytable(FIELD1, name_customer, cust_number, invoice_id, total_open_amount, due_in_date, notes)"
					+ " VALUES (" + (total_rows + 1) + ", '" + name_customer + "', '" 
					+ cust_number + "', " + invoice_id + ", " + total_open_amount 
					+ ", '" + due_in_date + "', '" + notes + "');";
			
			// Insert data into database
			stmt = con.createStatement();
			int rowcount = stmt.executeUpdate(query);
			System.out.println(rowcount + " rows added.");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
