

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		String connectionURL = "jdbc:mysql://localhost:3306/realestate";
		Connection connection = null;
		Statement statement = null;	
		ResultSet rs = null;
		
		PreparedStatement prst = null; 
		
		//this not to use in production..
		String dbuser = "root";
		String dbpass = "";		
		
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			System.out.println("username done  " +username);
			System.out.println("password done  "+password);	
			
			if ( (username != null) && (password != null) ) {	
				
				
				Class.forName("com.mysql.cj.jdbc.Driver");
			
				connection = DriverManager.getConnection(connectionURL, dbuser,dbpass);
				statement = connection.createStatement();
				
				System.out.println("connection done");
				System.out.println("Connected With the database successfully");		
										
				String sqlSelect = "SELECT userid, table_name FROM users WHERE (username, password) = (?,?)";
			 
			    prst = connection.prepareStatement(sqlSelect);
				prst.setString(1,username);
				prst.setString(2,password);
							
				rs = prst.executeQuery();							
						
				if (rs ==null ||!rs.next()){
					
					System.out.println("wrong name does not exists in DB");
					response.setContentType("text/html");
					
					PrintWriter out = null;
					try {
						out = response.getWriter();
						out.println("<html><head><title>Mistake</title></head>");
						out.println("<body><h1>name does not exists in DB</h1></body></html>");
					} catch (IOException e1) {							
						e1.printStackTrace();
					}
					out.close();
					
				}else{
					int userid = rs.getInt("userid");
					System.out.println("userid = "+userid);
					
					String table_name = rs.getString("table_name");
					System.out.println("table_name = "+table_name);
					try {
						response.sendRedirect("aggelies.jsp?username=" + username+"&userid="+userid+"&alert=false&properties="+table_name);
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					try {
						rs.close();
					} catch (SQLException e) {
						// do nothing
					}					
				}
						
						
				}
			
			} catch (ClassNotFoundException e) {			
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
