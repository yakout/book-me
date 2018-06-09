package servlets;

import model.ModelManager;
import model.SalesDAO;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;

import static net.sf.dynamicreports.report.constant.HorizontalAlignment.CENTER;

@WebServlet(name = "Statistics", urlPatterns = "/Statistics")
public class Statistics extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("stat1") != null) {
            System.out.print("stat1");
            String query = "SELECT DISTINCT Sale.copies, Sale.ISBN, Sale.sale_date,"
                    + " User.first_name, User.last_name "
                    + " FROM (Sale NATURAL JOIN User) "
                    + " WHERE "
                    + " YEAR(Sale.sale_date) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) "
                    + " AND MONTH(Sale.sale_date) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH);";

            String query2 = "SELECT SUM(Sale.copies * price) from Sale Join book on (Sale.ISBN = Book.ISBN) " +
                    "WHERE sale_date > (current_Date() - Interval 1 MONTH);"; // THIS ONE WORKING

            SalesDAO.getTotalSales();
            JasperReportBuilder totalSales = DynamicReports.report();
            totalSales.columns(
                            Columns.column("Total Profit Last Month", "sum(salesNumber*price)",
                                    DataTypes.stringType()))
                    .title(
                            Components.text("Total Profit Last Month")
                                    .setHorizontalAlignment(CENTER))
//                     .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER))
                    .pageFooter(Components.pageXofY())
                    .setDataSource(query, ModelManager.getInstance().getConnection());
            try {
                totalSales.show();

                response.setContentType("application/pdf");
                totalSales.toPdf(response.getOutputStream());

            } catch (Exception e1) {
                 e1.printStackTrace();
            }
        }

        if (request.getParameter("stat2") != null) {
            System.out.print("stat2");
        }

        if (request.getParameter("stat3") != null) {
            System.out.print("stat3");
        }


        request.getRequestDispatcher("statistics.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
