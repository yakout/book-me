package servlets;

import model.ModelManager;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;

import javax.servlet.ServletException;
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
