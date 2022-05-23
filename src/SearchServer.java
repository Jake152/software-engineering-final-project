import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Server;
import util.Info;
import util.UtilDB;

@WebServlet("/SearchServer")
public class SearchServer extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SearchServer() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");

      List<Server> listServers = null;
      if (keyword != null && !keyword.isEmpty()) {
         listServers = UtilDB.listServers(keyword);
      } else {
         listServers = UtilDB.listServers();
      }
      display(listServers, out);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchEmployeeName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   void display(List<Server> listServers, PrintWriter out) {
      for (Server server : listServers) {
         System.out.println("[DBG] " + server.getId() + ", " //
               + server.getHostname());

         out.println("<li>" + server.getId() + ", " //
               + server.getHostname() +", " + (server.isArchived() ? "Archived":"Active") + "</li>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
