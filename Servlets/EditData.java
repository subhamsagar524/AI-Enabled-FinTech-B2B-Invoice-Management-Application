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
 * Servlet implementation class EditData
 */
@WebServlet("/EditData")
public class EditData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
				
		// Get the edit info
		int page = Integer.parseInt(request.getParameter("page"));
		String checkbox = request.getParameter("checkbox");
		int checkbox_id = Integer.parseInt(checkbox);
		double total_open_amount = Double.parseDouble(request.getParameter("total_open_amount"));
		String notes = request.getParameter("notes");
		int filed1 = (page - 1) * 11 + (checkbox_id - 1);
		
		// Connection to the SQL database
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			System.out.println("Connected to database...");
			
			// Prepare query to get data from SQL
			String query = "UPDATE mytable SET total_open_amount=" + total_open_amount + ", notes='" + notes
					     + "' WHERE FIELD1=" + filed1 + ";";
			
			// Fetch data from SQL
			Statement stmt = con.createStatement();
			int status = stmt.executeUpdate(query);
			System.out.println(status + " row updated.");
					
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
