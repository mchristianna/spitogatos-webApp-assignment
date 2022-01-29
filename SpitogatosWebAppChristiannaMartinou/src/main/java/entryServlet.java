

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class entryServlet
 */
@WebServlet("/entryServlet")
public class entryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private int priceD;	
	private int squareMetersD;
	private Set<String> areas = new HashSet<String>(Arrays.asList("Athens", "Thessaloniki", "Patra", "Hrakleio"));
	private Set<String> availabilities = new HashSet<String>(Arrays.asList("rent", "sell"));	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public entryServlet() {
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
		PreparedStatement prst = null;
		
		String properties = request.getParameter("properties");
		System.out.println("properties done  " +properties);
		
		String myInsert = "INSERT INTO " + properties +" (userid, area, price, availability, squareMeters) VALUES (?,?,?,?,?)";
		
		//this not to use in production..
		String dbuser = "root";
		String dbpass = "";
		
		try {
			String userid = request.getParameter("userid");			
			String username = request.getParameter("username");			
			
			String area = request.getParameter("area");
			String price = request.getParameter("price");
			String availability = request.getParameter("availability");
			String squareMeters = request.getParameter("squareMeters");
						
			System.out.println("userid done  " +userid);
			System.out.println("username done  " +username);
			System.out.println("area done  " +area);
			System.out.println("price done  " +price);
			System.out.println("availability done  "+availability);	
			System.out.println("squareMeters done  "+squareMeters);		
						
			
			if (( area != null ) && ( price != null ) && ( availability != null ) && (squareMeters != null)) {				
				
				try {
					priceD = Integer.parseInt(price);					
					if(priceD < 50 || priceD > 5000000){
						System.out.println("price not right value");
						throw new Exception("not correct range of price - need to be between 50 and 5000000");
					}
					System.out.println("priceD = "+priceD);
				} catch (Exception e) {
					
					response.setContentType("text/html");					
					PrintWriter out = null;
					try {
						out = response.getWriter();
						out.println("<html><head><title>WRONG</title></head>");
						out.println("<body><h1>Please insert correct price!  "+e.getMessage()+"</h1></body></html>");
					} catch (IOException e2) {						
						e2.printStackTrace();
					}					
					out.close();
				}
					
				if(!areas.contains(area)){
					
					response.setContentType("text/html");					
					PrintWriter out = null;
					try {
						out = response.getWriter();
						out.println("<html><head><title>WRONG</title></head>");
						out.println("<body><h1>Area Error!</h1></body></html>");
					} catch (IOException e) {						
						e.printStackTrace();
					}
					out.close();
				}else {					
					System.out.println("areadb = "+area);
				}
					
				if(!availabilities.contains(availability)){
					
					response.setContentType("text/html");					
					PrintWriter out = null;
					try {
						out = response.getWriter();
						out.println("<html><head><title>WRONG</title></head>");
						out.println("<body><h1>Availability Error!</h1></body></html>");
					} catch (IOException e) {						
						e.printStackTrace();
					}
					out.close();
				}else {					
					System.out.println("availabilitydb = "+availability);
				}	
					
				try {
					squareMetersD = Integer.parseInt(squareMeters);						
					if(squareMetersD < 20 || squareMetersD > 1000){
						throw new Exception("squareMeters not right value - need to be between 20 and 1000");
					}
					System.out.println("squareMetersD = "+squareMetersD);
				} catch (Exception e) {
					
					response.setContentType("text/html");					
					PrintWriter out = null;
					try {
						out = response.getWriter();
						out.println("<html><head><title>WRONG</title></head>");
						out.println("<body><h1>Please insert correct square meters! "+e.getMessage()+"</h1></body></html>");
					} catch (IOException e5) {						
						e5.printStackTrace();
					}
					out.close();
				}	
					
						
				Class.forName("com.mysql.cj.jdbc.Driver");
			
				connection = DriverManager.getConnection(connectionURL, dbuser,dbpass);				
				System.out.println("connection done");
				statement = connection.createStatement();
				
				prst = connection.prepareStatement(myInsert);				
				prst.setString(1,userid);
				prst.setString(2, area);
				prst.setInt(3, priceD);
				prst.setString(4, availability);
				prst.setInt(5, squareMetersD);
				
				if (prst.executeUpdate()>0){
					System.out.println("insertion done");
					try {
						response.sendRedirect("aggelies.jsp?username=" + username+"&userid="+userid+"&alert=false&properties="+properties);
					} catch (IOException e) {						
						e.printStackTrace();
					}					
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
