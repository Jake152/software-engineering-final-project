import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Task;
import util.Info;
import util.UtilDB;

@WebServlet("/SearchTask")
public class SearchTask extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SearchTask() {
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

      List<Task> listTasks = null;
      if (keyword != null && !keyword.isEmpty()) {
         listTasks = UtilDB.listTasks(keyword);
      } else {
         listTasks = UtilDB.listTasks();
      }
      display(listTasks, out);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchTaskName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   void display(List<Task> listTasks, PrintWriter out) {
      for (Task task : listTasks) {
         System.out.println("[DBG] " + task.getId() + ", " //
               + task.getTaskName());

         out.println("<li>" + task.getId() + ", " //
               + task.getTaskName() + ", " + (task.isArchived() ? "Archived":"Active") + "</li>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
