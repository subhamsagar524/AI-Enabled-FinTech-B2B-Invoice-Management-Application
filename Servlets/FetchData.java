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
 * Servlet implementation class FetchData
 */
@WebServlet("/FetchData")
public class FetchData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// List to store all the rows of a page and converting directly to json
		List<Response> all_rows = new ArrayList<Response>();
		
		// Get the page info
		String received = request.getParameter("page");
		int page = Integer.parseInt(received);
		String start = String.valueOf((page - 1) * 10 + (page - 1));
		String end = String.valueOf(page * 10 + (page - 1));
		
		// Connection to the SQL database
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			System.out.println("Connected to database...");
			
			// Prepare query to get data from SQL
			String query = "SELECT FIELD1, name_customer, due_in_date, cust_number, predicted_clear_date, cust_number, invoice_id, total_open_amount, notes"
					     + " FROM mytable WHERE FIELD1 >= " + start + " AND FIELD1 <= " + end;
			
			// Fetch data from SQL
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery(query);
			System.out.println("Fetched from "+ start + " to " + end + " rows.");
			
			// Iterate over all the results to make a json
			while (results.next()) {
				
				// Initialize temporary variables to iterate
				int field1;
				long customerID, invoiceID;
				String name, due_date, payment_date, notes;
				double amount;
				
				// Extract values from the result set of current column
				field1 = results.getInt("FIELD1");
				name = results.getString("name_customer");
				due_date = results.getString("due_in_date");
				payment_date = results.getString("predicted_clear_date");
				customerID = results.getLong("cust_number");
				invoiceID = results.getLong("invoice_id");
				amount = results.getDouble("total_open_amount");
				notes = results.getString("notes");
				
				// Loading the row to Response(POJO class) object
				Response resp = new Response();
				resp.setAll(field1, name, due_date, payment_date, customerID, invoiceID, amount, notes);
				
				// Adding row to list
				all_rows.add(resp);
				
				}
					
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		// Using GSON to convert the results to JSON type
		Gson gson = new Gson();
		String data = gson.toJson(all_rows);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(data);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
