package servlets;



import model.ModelManager;
import net.sf.jasperreports.engine.*;
import org.apache.commons.io.output.ByteArrayOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "Statistics", urlPatterns = "/Statistics")
public class Statistics extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String reportPath = "/media/muhammed/Shared/Work/CSED19/Java/book-me/templates/";
        String file_name = "";
        if (request.getParameter("stat1") != null) {
            System.out.print("stat1");
            file_name = "dummy0.jrxml";//"total_sales.jrxml";
        }
        else if (request.getParameter("stat2") != null) {
            System.out.print("stat2");
            file_name = "TopFiveCustomers.jrxml";
        }
        else if (request.getParameter("stat3") != null) {
            System.out.print("stat3");
            file_name = "TopTenBooks.jrxml";

        }
        prepare_report(response,file_name,reportPath+file_name);
        request.getRequestDispatcher("statistics.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void prepare_report(HttpServletResponse response , String reportFileName,String reportPath){
        JasperPrint jasperPrint;
        try {
            String targetFileName=reportFileName.replace(".jrxml", ".pdf");
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            jasperPrint = JasperFillManager.fillReport(jasperReport, null, ModelManager.getInstance().getConnection());
            ServletOutputStream outputstream = response.getOutputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
            response.setContentType("application/pdf");
            outputstream.write(byteArrayOutputStream.toByteArray());
            response.setHeader("Cache-Control", "max-age=0");
            response.setHeader("Content-Disposition", "attachment; filename=" + targetFileName);
            outputstream.flush();
            outputstream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
