

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}

	private void doWork(HttpServletRequest request, HttpServletResponse response) {		
		
		String id = request.getParameter("id");		
		String userid = request.getParameter("userid");		
		String username = request.getParameter("username");		
		String properties = request.getParameter("properties");		
		
		System.out.println("id done  " +id);
		System.out.println("userid done  " +userid);
		System.out.println("username done  " +username);
		System.out.println("properties done  " +properties);			
		
		String connectionURL = "jdbc:mysql://localhost:3306/realestate";
		Connection connection = null;
		Statement statement = null;	
		ResultSet rs = null;
		
		PreparedStatement prst = null; 
		
		//this not to use in production..
		String dbuser = "root";
		String dbpass = "";		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(connectionURL, dbuser,dbpass);
			statement = connection.createStatement();
			
			System.out.println("connection done");
			System.out.println("Connected With the database successfully");			
			
			String sqlDelete = "DELETE FROM "+ properties +" WHERE (id, userid) = (?,?)";
			 
		    prst = connection.prepareStatement(sqlDelete);
			prst.setString(1,id);
			prst.setString(2,userid);
			
			if (prst.executeUpdate()>0){
				System.out.println("deletion done");
				try {
					response.sendRedirect("aggelies.jsp?username=" + username+"&userid="+userid+"&alert=true&properties="+properties);
				} catch (IOException e) {						
					e.printStackTrace();
				}				
			}
						
		
		} catch ( ClassNotFoundException e) {						
			e.printStackTrace();
		} catch (SQLException e) {						
			e.printStackTrace();
		}
		
		finally{
			try {
				statement.close();
			} catch (SQLException e) {
				// do nothing
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// do nothing
			}				
		}			
		
	}

	
	
}
