package servlets;

import model.ModelManager;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static net.sf.dynamicreports.report.constant.HorizontalAlignment.CENTER;


@WebServlet(name = "Statistics", urlPatterns = "/Statistics")
public class Statistics extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JasperReportBuilder report = DynamicReports.report();
        if (request.getParameter("stat1") != null) {
            System.out.print("stat1");

            // THIS QUERY WORKS
            String query = "SELECT SUM(Sale.copies * price) from Sale Join Book on (Sale.ISBN = Book.ISBN) " +
                    "WHERE sale_date > (current_Date() - Interval 1 MONTH);";


            /**
             *
             *  This is the right query as assume that we are in the middle of month May and we called to make this report
             *  then the result will contain the sales of last Month which is April and the period I spent in May
             *  which is not what we want, so this query below get the total sales of last month only which would be April
             *  according to my assumption said.
             *
             *
             * Extra: I have tested this Query on MySQL workbench and worked fine.
             *             String query = "SELECT SUM(Sale.copies * price) from Sale Join book on (Sale.ISBN = Book.ISBN) " +
             *                     "WHERE YEAR(sale_date) = YEAR(current_Date() - Interval 1 MONTH)" +
             *                     " AND MONTH(sale_date) = MONTH(current_Date()- Interval 1 MONTH);";
             *
             * */

            report
                    .columns(Columns.column("Total Profit Last Month", "SUM(Sale.copies * price)",
                            DataTypes.stringType()))
                    .title(Components.text("Total Profit Last Month")
                            .setHorizontalAlignment(CENTER))
                    .pageFooter(Components.pageXofY())
                    .setDataSource(query, ModelManager.getInstance().getConnection());
        }
        else if (request.getParameter("stat2") != null) {
            System.out.print("stat2");

            String query = "SELECT User.* , SUM(Sale.copies) AS sum_copies,\n" +
                    " SUM(Sale.copies * Book.price) AS sum_paid\n" +
                    " FROM Sale,Book,User\n" +
                    " WHERE   YEAR(Sale.sale_date) >= YEAR(CURRENT_DATE - INTERVAL 3 MONTH)\n" +
                    "AND YEAR(Sale.sale_date) < YEAR(CURRENT_DATE)\n" +
                    "AND MONTH(Sale.sale_date) >= MONTH(CURRENT_DATE - INTERVAL 3 MONTH)\n" +
                    "AND MONTH(Sale.sale_date) < MONTH(CURRENT_DATE)\n" +
                    "AND Sale.ISBN=Book.ISBN AND Sale.user_id = User.user_id\n" +
                    "GROUP BY User.user_id \n" +
                    "ORDER BY sum_paid DESC\n" +
                    "LIMIT 5;";

            report
                    .columns(
                            Columns.column("First Name", "first_name", DataTypes.stringType()),
                            Columns.column("Last Name", "last_name", DataTypes.stringType()),
                            Columns.column("number of books Bought", "sum_copies",
                                    DataTypes.stringType()))
                    .title(
                            Components.text("Top 5 Customer")
                                    .setHorizontalAlignment(CENTER))
                    .pageFooter(Components.pageXofY())
                    .setDataSource(query, ModelManager.getInstance().getConnection());
        }
        else if (request.getParameter("stat3") != null) {
            System.out.print("stat3");

            String query = "SELECT DISTINCT Book.*,\n" +
                    "\tSUM( Sale.copies) AS sum_copies\n" +
                    "FROM `Book`,\n" +
                    "\t`Sale`\n" +
                    "WHERE \n" +
                    "\t `Book`.`ISBN` = `Sale`.`ISBN` \n" +
                    "GROUP BY `Book`.`ISBN`;";

            report
                    .columns(
                            Columns.column("ISBN", "ISBN", DataTypes.stringType()),
                            Columns.column("Number of books Sold", "sum_copies",
                                    DataTypes.stringType()))
                    .title(
                            Components.text("Top 10 Books")
                                    .setHorizontalAlignment(CENTER))
                    .pageFooter(Components.pageXofY())
                    .setDataSource(query, ModelManager.getInstance().getConnection());
        }

        try {
            response.setContentType("application/pdf");
            report.toPdf(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        prepare_report(response,file_name,reportPath+file_name);
//        request.getRequestDispatcher("statistics.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
