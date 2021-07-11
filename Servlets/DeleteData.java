package com.higradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteData
 */
@WebServlet("/DeleteData")
public class DeleteData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Get the delete info
		int page = Integer.parseInt(request.getParameter("page"));
		String checkbox = request.getParameter("checkbox");
		
		// Get the checkboxes to delete
		String checkboxes[] = checkbox.split(",");
		int rows_to_delete[] = new int[checkboxes.length];
		for (int i = 0; i < checkboxes.length; i++) {
			int checkbox_id = Integer.parseInt(checkboxes[i]);
			rows_to_delete[i] = (page - 1) * 11 + (checkbox_id - 1);
		}
		
		// Connection to the SQL database
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			System.out.println("Connected to database...");
			
			// Query of the checkbox list
			String range = "(";
			for (int i = 0; i < rows_to_delete.length; i++) {
				
				if (i == rows_to_delete.length - 1)
					range = range + rows_to_delete[i] + ");";
				else
					range = range + rows_to_delete[i] + ", ";
			}
			
			// Prepare query to get data from SQL
			String query = "DELETE FROM mytable WHERE FIELD1 IN " + range;

			// Fetch data from SQL
			Statement stmt = con.createStatement();
			int status = stmt.executeUpdate(query);
			System.out.println(status + " rows deleted.");
					
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
