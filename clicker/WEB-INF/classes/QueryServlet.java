import java.io.*;
import java.sql.*; 
import jakarta.servlet.*;            // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import javax.servlet.*;            // Tomcat 9
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;

@WebServlet("/display")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class QueryServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
      // Print an HTML page as the output of the query
      out.println("<html>");
      out.println("<head><title>Query Response</title></head>");
      out.println("<body>");

      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/clicker?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
        String[] xValues = {"a", "b", "c", "d"};
        int[] yValues = new int[xValues.length];

        // Step 3: Query the database for the count of each response
        String sqlQuery = "SELECT choice, COUNT(*) AS count FROM responses WHERE choice IN ('a', 'b', 'c', 'd') GROUP BY choice";
        ResultSet rs = stmt.executeQuery(sqlQuery);

        // Step 4: Populate the yValues array with the count of each response
        while (rs.next()) {
            String choice = rs.getString("choice");
            int count = rs.getInt("count");
            switch (choice) {
                case "a":
                    yValues[0] = count;
                    break;
                case "b":
                    yValues[1] = count;
                    break;
                case "c":
                    yValues[2] = count;
                    break;
                case "d":
                    yValues[3] = count;
                    break;
                default:
                    // Handle unexpected choices
            }
        }

        // Generate JavaScript code for the bar chart
        out.println("<canvas id=\"myChart\"></canvas>");
        out.println("<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>");
        out.println("<script>");
        out.println("var xValues = ['a', 'b', 'c', 'd'];");
        out.println("var yValues = [" + yValues[0] + ", " + yValues[1] + ", " + yValues[2] + ", " + yValues[3] + "];");
        out.println("var barColors = ['red', 'green', 'blue', 'orange'];");
        out.println("new Chart(\"myChart\", {");
        out.println("  type: \"bar\",");
        out.println("  data: {");
        out.println("    labels: xValues,");
        out.println("    datasets: [{");
        out.println("      backgroundColor: barColors,");
        out.println("      data: yValues");
        out.println("    }]");
        out.println("  },");
        out.println("  options: {}");
        out.println("});");
        out.println("</script>");


      } catch(SQLException ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
 
      out.println("</body></html>");
      out.close();
   }
}