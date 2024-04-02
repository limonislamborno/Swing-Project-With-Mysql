/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import util.DbCon;

/**
 *
 * @author User
 */
public class MainForm extends javax.swing.JFrame {
     String sql = "";
    PreparedStatement ps;
    ResultSet rs;
    DbCon con = new DbCon();
    private String selectedImagePath;



    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        getAllData();
        getAllDataStuffTable();
        getStudentCount();
        getJeePassStudentCount();
        getJeeTotalStudentCount();
        getJeePassParcentage();
        
        getNtPassStudentCount();
        getNtTotalStudentCount();
        getNtPassParcentage();
        
        getDbPassStudentCount();
        getDbTotalStudentCount();
        getDbPassParcentage();
        
        getGpPassStudentCount();
        getGpTotalStudentCount();
        getGpPassParcentage();
        
        getCPassStudentCount();
        getCTotalStudentCount();
        getCPassParcentage();
        
        getWebPassStudentCount();
        getWebTotalStudentCount();
        getWebPassParcentage();
        
        showPieChart();
        showLineChart();
        showBarChart();
        setTime();
    }
    
    
    public void setTime(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                   java.util.Date date=new java.util.Date();
                   SimpleDateFormat tf=new SimpleDateFormat("h:mm:ss: aa");
                   SimpleDateFormat df=new SimpleDateFormat("EEEE, dd-MM-yyyy");
                   String time=tf.format(date);
                   timeShow.setText(time.split(" ")[0]+" "+time.split(" ")[1]);
                   dateShow.setText(df.format(date));
                }
            }
        }).start();
    }
    public void showPieChart(){
        
        //create dataset
        
        DefaultPieDataset barDataset=new DefaultPieDataset();
//        DefaultPieDataseteDataset barDataset = new DefaultPieDataset( );
        int totalStudent=Integer.parseInt(countStudent.getText());
        
        int javaStudent=Integer.parseInt(jeeTotalStudentCount.getText());
        int javaParcentage=(javaStudent*100)/totalStudent;
            
        int ntStudent=Integer.parseInt(ntTotalStudentCount.getText());
        int ntParcentage=(ntStudent*100)/totalStudent;
        
        int dbStudent=Integer.parseInt(dbTotalStudentCount.getText());
        int dbParcentage=(dbStudent*100)/totalStudent;
        
        int graphicsStudent=Integer.parseInt(graphicsTotalStudentCount.getText());
        int graphicsParcentage=(graphicsStudent*100)/totalStudent;
        
        int cStudent=Integer.parseInt(cTotalStudentCount.getText());
        int cParcentage=(cStudent*100)/totalStudent;
        
        int webStudent=Integer.parseInt(webTotalStudentCount.getText());
        int webParcentage=(webStudent*100)/totalStudent;
        
       
        
      
        





      barDataset.setValue( "Java" , new Double( javaParcentage ) );  
      barDataset.setValue( "Graphics" , new Double( graphicsParcentage ) );   
      barDataset.setValue( "Database" , new Double( dbParcentage ) );    
      barDataset.setValue( "WebDesign" , new Double( webParcentage ) );  
      barDataset.setValue( "C#" , new Double( cParcentage ) );  
      barDataset.setValue( "Networking" , new Double( ntParcentage ) );  
      
      //create chart
        JFreeChart piechart = ChartFactory.createPieChart("Student Show",barDataset, false,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("Java", new Color(253,99,0));
        piePlot.setSectionPaint("Graphics", new Color(100,149,237));
        piePlot.setSectionPaint("Database", new Color(0,127,255));
        piePlot.setSectionPaint("WebDesign", new Color(255,153,102));
        piePlot.setSectionPaint("C#", new Color(239,222,205));
        piePlot.setSectionPaint("Networking", new Color(153,102,204));
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panelPieChart.removeAll();
        panelPieChart.add(barChartPanel, BorderLayout.CENTER);
        panelPieChart.validate();
        
    }
    
    
      public void showLineChart(){
        //create dataset for the graph
          DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(70, "Amount", "2020");
        dataset.setValue(80, "Amount", "2022");
        dataset.setValue(58, "Amount", "2021");
        dataset.setValue(69, "Amount", "2020");
        dataset.setValue(92, "Amount", "2019");
        dataset.setValue(70, "Amount", "2018");
        dataset.setValue(92, "Amount", "2017");
        
        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("Our Succes","Yearly","Percentage", 
                dataset, PlotOrientation.VERTICAL, false,true,false);
        
        //create plot object
          CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
       // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);
        
        //create render object to change the moficy the line properties like color
          LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204,0,51);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        
         //create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        careerHubHomePage.removeAll();
        careerHubHomePage.add(lineChartPanel, BorderLayout.CENTER);
        careerHubHomePage.validate();
    }
      
        public void showBarChart(){
     

 try {
        int jee = parseAndValidateInteger(jeePassParcentageLabelHome.getText());
        int c = parseAndValidateInteger(cPassParcentageLabelHome.getText());
        int database = parseAndValidateInteger(dbPassParcentageLabelHome.getText());
        int networking = parseAndValidateInteger(ntPassParcentageLabelHome.getText());
        int graphics = parseAndValidateInteger(graphicsPassParcentageLabelHome.getText());
        int web = parseAndValidateInteger(webPassParcentageLabelHome.getText());
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(jee, "Amount", "Java");
        dataset.setValue(graphics, "Amount", "Graphics");
        dataset.setValue(database, "Amount", "Database");
        dataset.setValue(web, "Amount", "WebDesign");
        dataset.setValue(c, "Amount", "C#");
        dataset.setValue(networking, "Amount", "Networking");
        
        JFreeChart chart = ChartFactory.createBarChart("Passing Rating", "Department", "Rating out of 100", 
                dataset, PlotOrientation.VERTICAL, false, true, false);
        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(204, 0, 51);
        renderer.setSeriesPaint(0, clr3);
        
        ChartPanel barChartPanel = new ChartPanel(chart);
        panelSChart.removeAll();
        panelSChart.add(barChartPanel, BorderLayout.CENTER);
        panelSChart.validate();
    } catch (NumberFormatException e) {
        
    }
}

private int parseAndValidateInteger(String text) throws NumberFormatException {
    text = text.replace("%", "").trim(); // Remove percentage sign and trim whitespace
    if (text.isEmpty()) {
       
        return 0; // Return a default value (you can change this to another suitable value)
    }
    return Integer.parseInt(text);

        
    }
        
        public void showHistogram(){
        
         double[] values = { 95, 49, 14, 59, 50, 66, 47, 40, 1, 67,
                            12, 58, 28, 63, 14, 9, 31, 17, 94, 71,
                            49, 64, 73, 97, 15, 63, 10, 12, 31, 62,
                            93, 49, 74, 90, 59, 14, 15, 88, 26, 57,
                            77, 44, 58, 91, 10, 67, 57, 19, 88, 84                                
                          };
 
 
            HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", values, 20);
        
         JFreeChart chart = ChartFactory.createHistogram("JFreeChart Histogram","Data", "Frequency", dataset,PlotOrientation.VERTICAL, false,true,false);
            XYPlot plot= chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);

        
        
        ChartPanel barpChartPanel2 = new ChartPanel(chart);
        jPanel3.removeAll();
        jPanel3.add(barpChartPanel2, BorderLayout.CENTER);
        jPanel3.validate();
    }
    
    
    
    public void getAllData() {
        String[] columnName = {"ID", "Name", "Gender", "Fathers Name", "Mothers Name", "Email", "Assress", "DOB", "Course", "Round", "Center", "Batch"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnName);
        tblDisplayFromAddTrainee.setModel(model);

        sql = "select * from student_table_1";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String fathers_name = rs.getString("fathers_name");
                String mothers_name = rs.getString("mothers_name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Date dob = rs.getDate("dob");
                String course = rs.getString("course");
                String round = rs.getString("round");
                String center = rs.getString("center");
                String batch_id = rs.getString("batch_id");
                String picture_path = rs.getString("picture_path");

                model.addRow(new Object[]{id, name, gender, fathers_name, mothers_name, email, address, dob, course, round, center, batch_id});

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void getAllDataStuffTable() {
        String[] columnName = {"ID", "Name", "Gender", "Designation", "Specialist", "Email", "Qualification", "Address","Phone Number"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnName);
        tblDisplayFromAddStuff.setModel(model);

        sql = "select * from stuff_table_1";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String designation = rs.getString("designation");
                String specialist = rs.getString("specialist");
                String email = rs.getString("email");
                String qualification = rs.getString("qualification");
       
                String address = rs.getString("address");
                String phone_number = rs.getString("phone_number");
                
//                String picture_path = rs.getString("picture_path");
                

                model.addRow(new Object[]{id, name, gender, designation, specialist, email, qualification, address,phone_number});

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void reset() {
        txtIdFromAddTrainee.setText(null);
        txtNameFromAddTrainee.setText(null);
        txtFathersNameFromAddTrainee.setText(null);
        txtMothersNameFromAddTrainee.setText(null);
        txtEmailFromAddTrainee.setText(null);
        txtAddressFromAddTrainee.setText(null);
        dateDobFromAddTrainee.setDate(null);
        comboRoundFromAddTrainee.setSelectedIndex(0);
        comboTspFromAddTrainee.setSelectedIndex(0);
        comboSubjectFromAddTrainee.setSelectedIndex(0);
        txtBatchIdFromAddTrainee.setText(null);
        buttonGroup1.clearSelection();
    }

    public void getAllDataFortTraineeDetails() {
        String[] columnName = {"ID", "Name", "Gender", "Fathers Name", "Mothers Name", "Email", "Assress", "DOB", "Course", "Round", "Center"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnName);
        tblDisplayFromAddTrainee.setModel(model);

        sql = "select * from student_table_1";
        try {
            ps = con.getCon().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String fathers_name = rs.getString("fathers_name");
                String mothers_name = rs.getString("mothers_name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Date dob = rs.getDate("dob");
                String course = rs.getString("course");
                String round = rs.getString("round");
                String center = rs.getString("center");

                model.addRow(new Object[]{id, name, gender, fathers_name, mothers_name, email, address, dob, course, round, center});

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Date dateConverter(java.util.Date utilDate) {
        if (utilDate != null) {

            return new Date(utilDate.getTime());
        }
        return null;
    }

    public java.util.Date stringToUtilDate(String dateString) {
        String formatPattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void resultCalculate() {
        int mid1 = Integer.parseInt(txtExternalMidtermResulFromAddResult.getText().trim());
        int mid2 = Integer.parseInt(txtEvidenceMidtermResulFromAddResult.getText().trim());
        int month1 = Integer.parseInt(txtExternaMonthlylResulFromAddResult.getText().trim());
        int month2 = Integer.parseInt(txtEvidenceMonthlyResulFromAddResult.getText().trim());

        double mid = (mid1 + mid2) * (20.0 / 100);

        double month = (month1 + month2) * (80.0 / 100);

        double total = mid + month;

        txtTotalMark.setText(String.format("%.0f", total));

    }

    public void resetResult() {
        txtIdFromAddResult.setText(null);
        txtNameFromAddResult.setText(null);
        txtCourseNameFromAddResult.setText(null);
        txtRoundFromAddResult.setText(null);
        comboMonthFromAddResult1.setSelectedItem(null);
        txtDateMidterm.setDate(null);
        txtDateMonthly.setDate(null);
        txtExternalMidtermResulFromAddResult.setText(null);
        txtEvidenceMidtermResulFromAddResult.setText(null);

        txtExternaMonthlylResulFromAddResult.setText(null);
        txtEvidenceMonthlyResulFromAddResult.setText(null);
        txtDateMidterm1.setText(null);
        txtDateMonthly1.setText(null);
    }

    public void resultShow() {
        txtIdFromAddResult1.setText(txtIdFromAddResult.getText().trim());
        txtNameFromAddResult2.setText(txtNameFromAddResult.getText().trim());
        txtCourseNameFromAddResult2.setText(txtCourseNameFromAddResult.getText().trim());
        txtCourseNameFromAddResult1.setText(txtRoundFromAddResult.getText().trim());
        txtMonthFromAddResult1.setText(comboMonthFromAddResult1.getSelectedItem().toString());
        txtExternalMidtermResulFromAddResult1.setText(txtExternalMidtermResulFromAddResult.getText().trim());
        txtEvidenceMidtermResulFromAddResult1.setText(txtEvidenceMidtermResulFromAddResult.getText().trim());
        txtExternaMonthlylResulFromAddResult1.setText(txtExternaMonthlylResulFromAddResult.getText().trim());
        txtEvidenceMonthlyResulFromAddResult1.setText(txtEvidenceMonthlyResulFromAddResult.getText().trim());
        txtDateMidterm1.setText(dateConverter(txtDateMidterm.getDate()).toString());
        txtDateMonthly1.setText(dateConverter(txtDateMonthly.getDate()).toString());

    }
    
    
    public void getMobileBankingNumber(){
    sql = "select * from stuff_table_1 where id =?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdFromAddStuff3.getText()));
            rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String phone_number = rs.getString("phone_number");
               
                txtPhoneFromAddStuffPay.setText(phone_number);
//                txtCourseNameFromAddResult.setText(course);
              

            } else {
//                txtNameFromAddResult.setText("No data found for ID " + txtIdFromAddResult);
//                txtCourseNameFromAddResult.setText("");
//                txtRoundFromAddResult.setText("");

            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public void getOnlinBankingNumber(){
    sql = "select * from stuff_table_1 where id =?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdFromAddStuff4.getText()));
            rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String phone_number = rs.getString("phone_number");
               
                txtPhoneFromAddStuffPay1.setText(phone_number);
//                txtCourseNameFromAddResult.setText(course);
              

            } else {
//                txtNameFromAddResult.setText("No data found for ID " + txtIdFromAddResult);
//                txtCourseNameFromAddResult.setText("");
//                txtRoundFromAddResult.setText("");

            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void getStudentCount(){
    sql = "SELECT COUNT(id) FROM student_table_1";

        
        try {
            ps=con.getCon().prepareStatement(sql);
            rs=ps.executeQuery();
             rs.next();

        // Get the count from the first (and only) column of the first row
        int rowCount = rs.getInt(1);

        // Assuming you have a JTextField named "textFieldCount" to display the count
        countStudent.setText(Integer.toString(rowCount));

        // Close the ResultSet and PreparedStatement
        rs.close();
        ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    public void getJeePassStudentCount(){
    sql = "SELECT COUNT(id) FROM result_chart_1 where course=? and status=?";
    

        
        try {
            ps=con.getCon().prepareStatement(sql);
            ps.setString(1, "JEE");
//            ps.setString(2, comboMonthFromHome.getSelectedItem().toString());

          
           
            ps.setString(2, "Pass");
            rs=ps.executeQuery();
             rs.next();

        // Get the count from the first (and only) column of the first row
        int passJee = rs.getInt(1);
           
        
        

        // Assuming you have a JTextField named "textFieldCount" to display the count
        jeePassStudentCount.setText(Integer.toString(passJee));

        // Close the ResultSet and PreparedStatement
        rs.close();
        ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    public void getJeeTotalStudentCount(){
    sql = "SELECT COUNT(id) FROM result_chart_1 where course=? ";

        try {
            ps=con.getCon().prepareStatement(sql);
            ps.setString(1, "JEE");
           
            rs=ps.executeQuery();
             rs.next();

        int totalJee = rs.getInt(1);
        

        jeeTotalStudentCount.setText(Integer.toString(totalJee));

        
        rs.close();
        ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    
    public void getJeePassParcentage() {
    int total = Integer.parseInt(jeeTotalStudentCount.getText().trim());
    int pass = Integer.parseInt(jeePassStudentCount.getText().trim());

    int jeePassPercentage = (pass * 100) / total;
    jeePassParcentageLabelHome.setText(Integer.toString(jeePassPercentage));
}
    public void getNtPassStudentCount(){
    sql = "SELECT COUNT(id) FROM result_chart_1 where course=?  and status=?";

        try {
            ps=con.getCon().prepareStatement(sql);
            ps.setString(1, "Networking");

            ps.setString(2, "Pass");
            rs=ps.executeQuery();
             rs.next();

        int passNt = rs.getInt(1);

        ntPassStudentCount.setText(Integer.toString(passNt));

        rs.close();
        ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    public void getNtTotalStudentCount(){
    sql = "SELECT COUNT(id) FROM result_chart_1 where course=? ";

        try {
            ps=con.getCon().prepareStatement(sql);
            ps.setString(1, "Networking");
           
            rs=ps.executeQuery();
             rs.next();

        int totalNt = rs.getInt(1);
        ntTotalStudentCount.setText(Integer.toString(totalNt));
        rs.close();
        ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   public void getNtPassParcentage() {
    int total = Integer.parseInt(ntTotalStudentCount.getText().trim());
    int pass = Integer.parseInt(ntPassStudentCount.getText().trim());

    if (total != 0) {
        int ntPassPercentage = (pass * 100) / total;
        ntPassParcentageLabelHome.setText(Integer.toString(ntPassPercentage));
    } else {
//        ntPassParcentageLabelHome.setText("0%");
    }
}
    public void getDbPassStudentCount(){
    sql = "SELECT COUNT(id) FROM result_chart_1 where course=?  and status=?";

        try {
            ps=con.getCon().prepareStatement(sql);
            ps.setString(1, "Database Management");

            ps.setString(2, "Pass");
            rs=ps.executeQuery();
             rs.next();

        int passNt = rs.getInt(1);

        dbPassStudentCount.setText(Integer.toString(passNt));

        rs.close();
        ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    public void getDbTotalStudentCount(){
    sql = "SELECT COUNT(id) FROM result_chart_1 where course=? ";

        try {
            ps=con.getCon().prepareStatement(sql);
            ps.setString(1, "Database Management");
           
            rs=ps.executeQuery();
             rs.next();

        int totalDb = rs.getInt(1);
     dbTotalStudentCount.setText(Integer.toString(totalDb));
        rs.close();
        ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   public void getDbPassParcentage() {
    int total = Integer.parseInt(dbTotalStudentCount.getText().trim());
    int pass = Integer.parseInt(dbPassStudentCount.getText().trim());

    if (total != 0) {
        int dbPassPercentage = (pass * 100) / total;
        dbPassParcentageLabelHome.setText(Integer.toString(dbPassPercentage));
    } else {
//        dbPassParcentageLabelHome.setText("0%");
    }
}
    public void getGpPassStudentCount(){
    sql = "SELECT COUNT(id) FROM result_chart_1 where course=?  and status=?";

        try {
            ps=con.getCon().prepareStatement(sql);
            ps.setString(1, "Graphics Design");

            ps.setString(2, "Pass");
            rs=ps.executeQuery();
             rs.next();

        int passNt = rs.getInt(1);

        graphicsPassStudentCount.setText(Integer.toString(passNt));

        rs.close();
        ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    public void getGpTotalStudentCount(){
    sql = "SELECT COUNT(id) FROM result_chart_1 where course=? ";

        try {
            ps=con.getCon().prepareStatement(sql);
            ps.setString(1, "Graphics Design");
           
            rs=ps.executeQuery();
             rs.next();

        int totalGp = rs.getInt(1);
     graphicsTotalStudentCount.setText(Integer.toString(totalGp));
        rs.close();
        ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   public void getGpPassParcentage() {
    int total = Integer.parseInt(graphicsTotalStudentCount.getText().trim());
    int pass = Integer.parseInt(graphicsPassStudentCount.getText().trim());

    if (total != 0) {
        int graphicsPassPercentage = (pass * 100) / total;
        graphicsPassParcentageLabelHome.setText(Integer.toString(graphicsPassPercentage));
    } else {
//        graphicsPassParcentageLabelHome.setText("0%");
    }
}
    public void getCPassStudentCount(){
    sql = "SELECT COUNT(id) FROM result_chart_1 where course=?  and status=?";

        try {
            ps=con.getCon().prepareStatement(sql);
            ps.setString(1, "C #");

            ps.setString(2, "Pass");
            rs=ps.executeQuery();
             rs.next();

        int passC = rs.getInt(1);

        cPassStudentCount.setText(Integer.toString(passC));

        rs.close();
        ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    public void getCTotalStudentCount(){
    sql = "SELECT COUNT(id) FROM result_chart_1 where course=? ";

        try {
            ps=con.getCon().prepareStatement(sql);
            ps.setString(1, "C #");
           
            rs=ps.executeQuery();
             rs.next();

        int totalC = rs.getInt(1);
     cTotalStudentCount.setText(Integer.toString(totalC));
        rs.close();
        ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   public void getCPassParcentage() {
    int total = Integer.parseInt(cTotalStudentCount.getText().trim());
    int pass = Integer.parseInt(cPassStudentCount.getText().trim());

    if (total != 0) {
        int cPassPercentage = (pass * 100) / total;
        cPassParcentageLabelHome.setText(Integer.toString(cPassPercentage));
    } else {
//        cPassParcentageLabelHome.setText("0%");
    }
}
    public void getWebPassStudentCount(){
    sql = "SELECT COUNT(id) FROM result_chart_1 where course=?  and status=?";

        try {
            ps=con.getCon().prepareStatement(sql);
            ps.setString(1, "Web Design & Devlopment");

            ps.setString(2, "Pass");
            rs=ps.executeQuery();
             rs.next();

        int passWeb = rs.getInt(1);

        webPassStudentCount.setText(Integer.toString(passWeb));

        rs.close();
        ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    public void getWebTotalStudentCount(){
    sql = "SELECT COUNT(id) FROM result_chart_1 where course=? ";

        try {
            ps=con.getCon().prepareStatement(sql);
            ps.setString(1, "C #");
           
            rs=ps.executeQuery();
             rs.next();

        int totalWeb = rs.getInt(1);
     webTotalStudentCount.setText(Integer.toString(totalWeb));
        rs.close();
        ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   public void getWebPassParcentage() {
    int total = Integer.parseInt(webTotalStudentCount.getText().trim());
    int pass = Integer.parseInt(webPassStudentCount.getText().trim());

    if (total != 0) {
        int cPassPercentage = (pass * 100) / total;
        webPassParcentageLabelHome.setText(Integer.toString(cPassPercentage));
    } else {
//        webPassParcentageLabelHome.setText("0%");
    }
}



    
    
    
    public void attachPhoto(){
//    JFileChooser browseImageFile = new JFileChooser();
//
//        //for filtering image extension
//        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
//        browseImageFile.addChoosableFileFilter(fnef);
//
//        int showOpenDialogue = browseImageFile.showOpenDialog(null);
//
//        if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
//            File selectedImageFile = browseImageFile.getSelectedFile();
//            selectedImagePath = selectedImageFile.getAbsolutePath();
//            JOptionPane.showMessageDialog(null, selectedImagePath);
//
//            //for displaying image on jlabel
//            ImageIcon li = new ImageIcon(selectedImagePath);
//
//            //for resize image in jlabel
//            Image image = li.getImage().getScaledInstance(photoAddFromAddTrainee.getWidth(), photoAddFromAddTrainee.getHeight(), Image.SCALE_SMOOTH);
    JFileChooser browseImageFile=new JFileChooser();
    FileNameExtensionFilter fnef=new FileNameExtensionFilter("Images", "png","jpg","jpeg");
    browseImageFile.addChoosableFileFilter(fnef);
    int showOpenDialogue=browseImageFile.showOpenDialog(null);
    if(showOpenDialogue==JFileChooser.APPROVE_OPTION){
    
    File selectedImageFile = browseImageFile.getSelectedFile();
     selectedImagePath = selectedImageFile.getAbsolutePath();
         JOptionPane.showMessageDialog(null, selectedImagePath);
   
    }
    
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnLogOut = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnTrainee = new javax.swing.JButton();
        btnStuff = new javax.swing.JButton();
        btnCareerHub = new javax.swing.JButton();
        btnPayment = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        mainTabMother = new javax.swing.JTabbedPane();
        homeTab = new javax.swing.JTabbedPane();
        totalTrainee = new javax.swing.JPanel();
        panelPieChart = new javax.swing.JPanel();
        panelSChart = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        countStudent = new javax.swing.JLabel();
        dateShow = new javax.swing.JLabel();
        timeShow = new javax.swing.JLabel();
        raf = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        jeePassStudentCount = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jeeTotalStudentCount = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        ntPassStudentCount = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        ntTotalStudentCount = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        dbPassStudentCount = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        dbTotalStudentCount = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        jLabel181 = new javax.swing.JLabel();
        graphicsPassStudentCount = new javax.swing.JLabel();
        graphicsTotalStudentCount = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        cPassStudentCount = new javax.swing.JLabel();
        cTotalStudentCount = new javax.swing.JLabel();
        webPassStudentCount = new javax.swing.JLabel();
        jLabel184 = new javax.swing.JLabel();
        jLabel200 = new javax.swing.JLabel();
        webTotalStudentCount = new javax.swing.JLabel();
        jeePassParcentageLabelHome = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        cPassParcentageLabelHome = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        webPassParcentageLabelHome = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        ntPassParcentageLabelHome = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        graphicsPassParcentageLabelHome = new javax.swing.JLabel();
        dbPassParcentageLabelHome = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel58 = new javax.swing.JPanel();
        traineeTab = new javax.swing.JTabbedPane();
        addTrainee = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtIdFromAddTrainee = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNameFromAddTrainee = new javax.swing.JTextField();
        radioBtnFemaleFromAddTrainee = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        radioBtnMaleFromAddTrainee = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txtFathersNameFromAddTrainee = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtMothersNameFromAddTrainee = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtEmailFromAddTrainee = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtAddressFromAddTrainee = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        dateDobFromAddTrainee = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        comboSubjectFromAddTrainee = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        comboRoundFromAddTrainee = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        comboTspFromAddTrainee = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        txtBatchIdFromAddTrainee = new javax.swing.JTextField();
        btnSaveFromAddTrainee = new javax.swing.JButton();
        btnUpdateFromAddTrainee = new javax.swing.JButton();
        btnDeleteFromAddTrainee = new javax.swing.JButton();
        btnAttachPhotoFromAddTrainee = new javax.swing.JButton();
        photoAddFromAddTrainee = new javax.swing.JLabel();
        btnResetFromAddTrainee2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDisplayFromAddTrainee = new javax.swing.JTable();
        btnAddTrainee = new javax.swing.JButton();
        btnTraineDetails = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        searchTrainee = new javax.swing.JPanel();
        comboRoundFromShowTraineeDetails = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        comboSubFromShowTraineeDetails = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        comboTspFromShowTraineDatails = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDisplayFromShowTrainee = new javax.swing.JTable();
        btnSearchForAllTraineeDetails = new javax.swing.JButton();
        btnSerarvhTspWiseFromTraineeDetails = new javax.swing.JButton();
        btnSearchById = new javax.swing.JButton();
        btnPrint2 = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        showIdCard = new javax.swing.JPanel();
        idCardPrint = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtRoundFromIdCard = new javax.swing.JTextField();
        txtTraineeIdFromIdCard = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtTraineeNameFromIdCard = new javax.swing.JTextField();
        txtBatchIdFromIdCard = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        traineDetailsS = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        addResult = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        txtNameFromAddResult = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        txtCourseNameFromAddResult = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        txtRoundFromAddResult = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        comboMonthFromAddResult1 = new javax.swing.JComboBox<>();
        jLabel85 = new javax.swing.JLabel();
        txtDateMidterm = new com.toedter.calendar.JDateChooser();
        jLabel97 = new javax.swing.JLabel();
        txtDateMonthly = new com.toedter.calendar.JDateChooser();
        jPanel51 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        txtExternalMidtermResulFromAddResult = new javax.swing.JTextField();
        txtEvidenceMidtermResulFromAddResult = new javax.swing.JTextField();
        jPanel54 = new javax.swing.JPanel();
        jPanel55 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        txtExternaMonthlylResulFromAddResult = new javax.swing.JTextField();
        txtEvidenceMonthlyResulFromAddResult = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        txtIdFromAddResult = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        btnAddNew = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        txtIdFromAddResult1 = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        txtCourseNameFromAddResult2 = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        txtCourseNameFromAddResult1 = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        txtMonthFromAddResult1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtTotalMark = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        txtDateMidterm1 = new javax.swing.JTextField();
        txtExternalMidtermResulFromAddResult1 = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        txtEvidenceMidtermResulFromAddResult1 = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        txtDateMonthly1 = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        txtExternaMonthlylResulFromAddResult1 = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        txtEvidenceMonthlyResulFromAddResult1 = new javax.swing.JTextField();
        txtPassFail = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtNameFromAddResult2 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        resultPhoto = new javax.swing.JLabel();
        searchResult = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        comboRoundFromShowTraineeDetails1 = new javax.swing.JComboBox<>();
        jLabel82 = new javax.swing.JLabel();
        comboCourseFromShowResult1 = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        comboTspFromShowTraineDatails1 = new javax.swing.JComboBox<>();
        txtIdFromShowResultSingle = new javax.swing.JTextField();
        btnSearchById1 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDisplayFromShowResult1 = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        searchShowDetailsS = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        txtIdShowFromTraineeDetails = new javax.swing.JTextField();
        btnSearchTraineeDetails3 = new javax.swing.JButton();
        txtName4 = new javax.swing.JLabel();
        stuffDetailsS1 = new javax.swing.JLabel();
        txtName = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel130 = new javax.swing.JLabel();
        txtTraineeId2 = new javax.swing.JTextField();
        jLabel147 = new javax.swing.JLabel();
        txtGender2 = new javax.swing.JTextField();
        jLabel148 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        txtCourse2 = new javax.swing.JTextField();
        jLabel158 = new javax.swing.JLabel();
        txtFathersName2 = new javax.swing.JTextField();
        jLabel169 = new javax.swing.JLabel();
        txtEmail2 = new javax.swing.JTextField();
        jLabel173 = new javax.swing.JLabel();
        txtTsp2 = new javax.swing.JTextField();
        jLabel174 = new javax.swing.JLabel();
        txtAddress2 = new javax.swing.JTextField();
        jLabel175 = new javax.swing.JLabel();
        txtCourse3 = new javax.swing.JTextField();
        jLabel176 = new javax.swing.JLabel();
        txtTsp3 = new javax.swing.JTextField();
        jLabel177 = new javax.swing.JLabel();
        txtRound2 = new javax.swing.JTextField();
        txtMothersName5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        stuffTab = new javax.swing.JTabbedPane();
        addStuff = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        txtIdFromAddStuff = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txtNameFromAddStuff = new javax.swing.JTextField();
        radioBtnFemaleFromAddStuff = new javax.swing.JRadioButton();
        jLabel55 = new javax.swing.JLabel();
        radioBtnMaleFromAddStuff = new javax.swing.JRadioButton();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txtEmaiFromAddStuff = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        txtAddressFromAddStuff = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        btnSaveFromAddTrainee1 = new javax.swing.JButton();
        btnUpdateFromAddTrainee1 = new javax.swing.JButton();
        btnResetFromAddTrainee1 = new javax.swing.JButton();
        comboDesignationFromAddStuff = new javax.swing.JComboBox<>();
        comboSpecialistFromAddStuff = new javax.swing.JComboBox<>();
        comboQualificationFromAddStuff = new javax.swing.JComboBox<>();
        jLabel63 = new javax.swing.JLabel();
        txtPhoneFromAddStuff = new javax.swing.JTextField();
        btnAttachPhotoFromAddTrainee1 = new javax.swing.JButton();
        photoAddFromAddStuff = new javax.swing.JLabel();
        btnDeleteStuff = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDisplayFromAddStuff = new javax.swing.JTable();
        btnTraineDetails1 = new javax.swing.JButton();
        searchStuff = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDisplayFromShowStuff = new javax.swing.JTable();
        btnSerarvhTspWiseFromTraineeDetails1 = new javax.swing.JButton();
        btnSearchById2 = new javax.swing.JButton();
        btnPrint3 = new javax.swing.JButton();
        btnBack2 = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        comboDesignationFromAddStuff1 = new javax.swing.JComboBox<>();
        showIdCard1 = new javax.swing.JPanel();
        idCardPrintForStuff = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        txtStuffdFromIdCard = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        txtStuffNameFromIdCard = new javax.swing.JTextField();
        txtDesignationFromIdCard = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        stuffDetailsSingle = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        searchShowDetailsS1 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        txtIdShowFromStuff = new javax.swing.JTextField();
        btnSearchTraineeDetails2 = new javax.swing.JButton();
        btnBack3 = new javax.swing.JButton();
        txtName3 = new javax.swing.JLabel();
        stuffDetailsS = new javax.swing.JLabel();
        txtNameStuffShowData = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel98 = new javax.swing.JLabel();
        txtIdStuffShowData = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        txtGenderStuffShowData = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        txtDesignationStuffShowData = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        txtSpecialistStuffShowData = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        txtEmailStuffShowData = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        txtQualificationStuffShowData = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        txtAddressStuffShowData = new javax.swing.JTextField();
        careeerHubTab = new javax.swing.JTabbedPane();
        careerHome = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel105 = new javax.swing.JLabel();
        txtIdFromCareerHub = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        careerHubHomePage = new javax.swing.JPanel();
        cvTab = new javax.swing.JPanel();
        cvPanel = new javax.swing.JPanel();
        cvNameTrainee = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jLabel159 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jLabel172 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel161 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        jLabel163 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        cvEmail = new javax.swing.JLabel();
        cvAddress = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel167 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jLabel168 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jLabel191 = new javax.swing.JLabel();
        jLabel192 = new javax.swing.JLabel();
        jLabel193 = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        jLabel195 = new javax.swing.JLabel();
        jLabel196 = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        jLabel198 = new javax.swing.JLabel();
        jLabel199 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel170 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel171 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jLabel185 = new javax.swing.JLabel();
        jLabel186 = new javax.swing.JLabel();
        jLabel187 = new javax.swing.JLabel();
        jLabel188 = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        jLabel190 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jLabel201 = new javax.swing.JLabel();
        jLabel202 = new javax.swing.JLabel();
        jLabel203 = new javax.swing.JLabel();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        jLabel206 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jLabel207 = new javax.swing.JLabel();
        jLabel208 = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        jLabel212 = new javax.swing.JLabel();
        jLabel213 = new javax.swing.JLabel();
        jLabel214 = new javax.swing.JLabel();
        jLabel215 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        jLabel216 = new javax.swing.JLabel();
        jLabel217 = new javax.swing.JLabel();
        jLabel218 = new javax.swing.JLabel();
        jLabel219 = new javax.swing.JLabel();
        jLabel220 = new javax.swing.JLabel();
        jLabel221 = new javax.swing.JLabel();
        jLabel222 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jLabel223 = new javax.swing.JLabel();
        jLabel224 = new javax.swing.JLabel();
        jLabel225 = new javax.swing.JLabel();
        jLabel226 = new javax.swing.JLabel();
        jLabel227 = new javax.swing.JLabel();
        jLabel228 = new javax.swing.JLabel();
        jLabel229 = new javax.swing.JLabel();
        jLabel230 = new javax.swing.JLabel();
        jLabel231 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        jLabel232 = new javax.swing.JLabel();
        jLabel233 = new javax.swing.JLabel();
        jLabel234 = new javax.swing.JLabel();
        jLabel235 = new javax.swing.JLabel();
        jLabel236 = new javax.swing.JLabel();
        jLabel237 = new javax.swing.JLabel();
        jLabel238 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        jLabel239 = new javax.swing.JLabel();
        jLabel240 = new javax.swing.JLabel();
        jLabel241 = new javax.swing.JLabel();
        jLabel242 = new javax.swing.JLabel();
        jLabel243 = new javax.swing.JLabel();
        jLabel244 = new javax.swing.JLabel();
        jLabel245 = new javax.swing.JLabel();
        jLabel246 = new javax.swing.JLabel();
        jLabel247 = new javax.swing.JLabel();
        cvPhotoTrainee = new javax.swing.JLabel();
        cvPanel1 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        cvNameTrainee1 = new javax.swing.JTextField();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jLabel160 = new javax.swing.JLabel();
        paymentTab = new javax.swing.JTabbedPane();
        addPayment = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel120 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel25 = new javax.swing.JPanel();
        txtIdFromAddStuff3 = new javax.swing.JTextField();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        txtPhoneFromAddStuffPay = new javax.swing.JTextField();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        txtNameFromAddStuffPay = new javax.swing.JTextField();
        jLabel137 = new javax.swing.JLabel();
        txtAmountToPay = new javax.swing.JTextField();
        jLabel138 = new javax.swing.JLabel();
        radioBtnBkash = new javax.swing.JRadioButton();
        radioBtnNogod = new javax.swing.JRadioButton();
        radioBtnO = new javax.swing.JRadioButton();
        jLabel118 = new javax.swing.JLabel();
        btnPayBank = new javax.swing.JButton();
        comboMonthPay = new javax.swing.JComboBox<>();
        jPanel26 = new javax.swing.JPanel();
        txtIdFromAddStuff4 = new javax.swing.JTextField();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        txtPhoneFromAddStuffPay1 = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        txtNameFromAddStuffPay1 = new javax.swing.JTextField();
        jLabel145 = new javax.swing.JLabel();
        txtAmountToPay1 = new javax.swing.JTextField();
        jLabel146 = new javax.swing.JLabel();
        radioBtnIdbBank = new javax.swing.JRadioButton();
        radioBtnDBL = new javax.swing.JRadioButton();
        radioBtnO2 = new javax.swing.JRadioButton();
        jLabel119 = new javax.swing.JLabel();
        btnPayBank1 = new javax.swing.JButton();
        comboMonthPay1 = new javax.swing.JComboBox<>();
        checkPayment = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDisplayFromShowStuff1 = new javax.swing.JTable();
        btnSerarvhTspWiseFromTraineeDetails2 = new javax.swing.JButton();
        btnSearchById3 = new javax.swing.JButton();
        btnPrint4 = new javax.swing.JButton();
        btnBack4 = new javax.swing.JButton();
        jLabel100 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        comboDesignationFromAddStuff2 = new javax.swing.JComboBox<>();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(5, 160, 200));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("IsDB Management System");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 90));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/idb_logo.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 0, 60, 90));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("IsDB-BISEW");
        jPanel2.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 20, 145, 21));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("Islamic Development Bank");
        jPanel2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 40, -1, 24));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Bangladesh Islamic Solidarity Educational Wakf");
        jPanel2.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 60, 268, -1));

        jPanel3.setBackground(new java.awt.Color(5, 160, 200));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogOut.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogOut.setText("Log Out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        jPanel3.add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 140, 30));

        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel3.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 140, 30));

        btnTrainee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTrainee.setText("Trainee");
        btnTrainee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraineeActionPerformed(evt);
            }
        });
        jPanel3.add(btnTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 140, 30));

        btnStuff.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnStuff.setText("Stuff");
        btnStuff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStuffActionPerformed(evt);
            }
        });
        jPanel3.add(btnStuff, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 140, 30));

        btnCareerHub.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCareerHub.setText("CareerHub");
        btnCareerHub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCareerHubMouseClicked(evt);
            }
        });
        btnCareerHub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCareerHubActionPerformed(evt);
            }
        });
        jPanel3.add(btnCareerHub, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 140, 30));

        btnPayment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPayment.setText("Payment");
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });
        jPanel3.add(btnPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 140, 30));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        totalTrainee.setBackground(new java.awt.Color(255, 255, 255));
        totalTrainee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        totalTrainee.setMinimumSize(new java.awt.Dimension(1280, 720));
        totalTrainee.setPreferredSize(new java.awt.Dimension(1280, 720));
        totalTrainee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPieChart.setLayout(new java.awt.BorderLayout());
        totalTrainee.add(panelPieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 350, 280));

        panelSChart.setLayout(new java.awt.BorderLayout());
        totalTrainee.add(panelSChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 590, 370));

        jPanel9.setBackground(new java.awt.Color(255, 102, 102));

        jLabel3.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Total Trainee");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        countStudent.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        countStudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(countStudent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(countStudent, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
        );

        totalTrainee.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 190, 130));

        dateShow.setFont(new java.awt.Font("Yu Gothic Medium", 1, 18)); // NOI18N
        totalTrainee.add(dateShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 240, 40));

        timeShow.setFont(new java.awt.Font("Yu Gothic Medium", 1, 18)); // NOI18N
        totalTrainee.add(timeShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 20, 130, 40));

        homeTab.addTab("tab1", totalTrainee);

        raf.setBackground(new java.awt.Color(255, 255, 255));
        raf.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel50.setBackground(new java.awt.Color(255, 255, 255));
        jPanel50.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jeePassStudentCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(jeePassStudentCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 52, 78, 27));

        jLabel110.setText("JeePass StuCount");
        jPanel50.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 52, 106, 27));

        jLabel117.setText("JeeTotalStuCount");
        jPanel50.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 85, 106, 27));

        jeeTotalStudentCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(jeeTotalStudentCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 85, 78, 27));

        jLabel165.setText("ntPass StuCount");
        jPanel50.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 106, 27));

        ntPassStudentCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(ntPassStudentCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 78, 27));

        jLabel166.setText("ntTotalStuCount");
        jPanel50.add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 106, 27));

        ntTotalStudentCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(ntTotalStudentCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 78, 27));

        jLabel178.setText("dtPass StuCount");
        jPanel50.add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 106, 27));

        dbPassStudentCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(dbPassStudentCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 78, 27));

        jLabel179.setText("dtTotalStuCount");
        jPanel50.add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 106, 27));

        dbTotalStudentCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(dbTotalStudentCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 78, 27));

        jLabel180.setText("graphicsPass StuCount");
        jPanel50.add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 106, 27));

        jLabel181.setText("graphicsTotalStuCount");
        jPanel50.add(jLabel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 106, 27));

        graphicsPassStudentCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(graphicsPassStudentCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 78, 27));

        graphicsTotalStudentCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(graphicsTotalStudentCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, 78, 27));

        jLabel182.setText("cTotalStuCount");
        jPanel50.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 106, 27));

        jLabel183.setText("cPass StuCount");
        jPanel50.add(jLabel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 106, 27));

        cPassStudentCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(cPassStudentCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 78, 27));

        cTotalStudentCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(cTotalStudentCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 78, 27));

        webPassStudentCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(webPassStudentCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, 78, 27));

        jLabel184.setText("webPass StuCount");
        jPanel50.add(jLabel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 106, 27));

        jLabel200.setText("webTotalStuCount");
        jPanel50.add(jLabel200, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 106, 27));

        webTotalStudentCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(webTotalStudentCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 78, 27));

        jeePassParcentageLabelHome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(jeePassParcentageLabelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 30, 120, 30));

        jLabel34.setText("JEE");
        jPanel50.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 40, 30, -1));

        jLabel35.setText("c#");
        jPanel50.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, 30, -1));

        cPassParcentageLabelHome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(cPassParcentageLabelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 80, 120, 30));

        jLabel36.setText("Web");
        jPanel50.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 30, -1));

        webPassParcentageLabelHome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(webPassParcentageLabelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 120, 120, 30));

        jLabel41.setText("Nt");
        jPanel50.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, 30, -1));

        ntPassParcentageLabelHome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(ntPassParcentageLabelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 160, 120, 30));

        jLabel44.setText("Graphics");
        jPanel50.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, 30, -1));

        graphicsPassParcentageLabelHome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(graphicsPassParcentageLabelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 200, 120, 30));

        dbPassParcentageLabelHome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel50.add(dbPassParcentageLabelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 250, 120, 30));

        jLabel47.setText("Database");
        jPanel50.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 260, 50, -1));

        raf.add(jPanel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1130, 310));

        jPanel58.setBackground(new java.awt.Color(255, 255, 255));
        jPanel58.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel58.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        raf.add(jPanel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 1130, 290));

        homeTab.addTab("addTrainee", raf);

        mainTabMother.addTab("HomeTab", homeTab);

        addTrainee.setBackground(new java.awt.Color(255, 255, 255));
        addTrainee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdFromAddTrainee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.add(txtIdFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 22, 213, 29));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Student Id :");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 21, 82, 29));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Name :");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 57, 64, 29));

        txtNameFromAddTrainee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.add(txtNameFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 58, 213, 29));

        buttonGroup1.add(radioBtnFemaleFromAddTrainee);
        radioBtnFemaleFromAddTrainee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radioBtnFemaleFromAddTrainee.setText("Female");
        jPanel6.add(radioBtnFemaleFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 97, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Gender :");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 95, 64, 29));

        buttonGroup1.add(radioBtnMaleFromAddTrainee);
        radioBtnMaleFromAddTrainee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radioBtnMaleFromAddTrainee.setText("Male");
        jPanel6.add(radioBtnMaleFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 97, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Fathers Name :");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 136, 102, 29));

        txtFathersNameFromAddTrainee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.add(txtFathersNameFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 137, 208, 29));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Mothers Name :");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 172, 102, 29));

        txtMothersNameFromAddTrainee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.add(txtMothersNameFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 173, 208, 29));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Email :");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 208, 64, 29));

        txtEmailFromAddTrainee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.add(txtEmailFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 209, 208, 29));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Address :");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 22, 64, 29));

        txtAddressFromAddTrainee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.add(txtAddressFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(571, 22, 220, 29));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Date Of Birth :");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 157, 29));
        jPanel6.add(dateDobFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 148, 29));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Course Name :");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, -1, 29));

        comboSubjectFromAddTrainee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboSubjectFromAddTrainee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select One--", "JEE", "Web Design & Devlopment", "C #", "Networking", "Graphics Design", "Database Management", " " }));
        comboSubjectFromAddTrainee.setBorder(null);
        jPanel6.add(comboSubjectFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 137, 29));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Round :");
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, -1, 29));

        comboRoundFromAddTrainee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboRoundFromAddTrainee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select Round--", "50", "51", "52", "53", "54", "55", "56", "57", "58", " " }));
        comboRoundFromAddTrainee.setBorder(null);
        jPanel6.add(comboRoundFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 137, 29));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Center :");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 60, 29));

        comboTspFromAddTrainee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboTspFromAddTrainee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select One--", "CCSL", "New Horizon", "Panthapath Tsp", "Shewrapara Tsp", "Bhuiyan It Limited", "Polton Tsp" }));
        comboTspFromAddTrainee.setBorder(null);
        comboTspFromAddTrainee.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboTspFromAddTraineeFocusLost(evt);
            }
        });
        jPanel6.add(comboTspFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 137, 29));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Batch ID:");
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 72, 28));

        txtBatchIdFromAddTrainee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBatchIdFromAddTrainee.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBatchIdFromAddTraineeFocusGained(evt);
            }
        });
        txtBatchIdFromAddTrainee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBatchIdFromAddTraineeKeyPressed(evt);
            }
        });
        jPanel6.add(txtBatchIdFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 140, 30));

        btnSaveFromAddTrainee.setBackground(new java.awt.Color(0, 153, 51));
        btnSaveFromAddTrainee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSaveFromAddTrainee.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveFromAddTrainee.setText("Save");
        btnSaveFromAddTrainee.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnSaveFromAddTrainee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveFromAddTraineeActionPerformed(evt);
            }
        });
        jPanel6.add(btnSaveFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, -1, 32));

        btnUpdateFromAddTrainee.setBackground(new java.awt.Color(102, 102, 255));
        btnUpdateFromAddTrainee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdateFromAddTrainee.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateFromAddTrainee.setText("Update");
        btnUpdateFromAddTrainee.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnUpdateFromAddTrainee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateFromAddTraineeActionPerformed(evt);
            }
        });
        jPanel6.add(btnUpdateFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, -1, 32));

        btnDeleteFromAddTrainee.setBackground(new java.awt.Color(255, 51, 51));
        btnDeleteFromAddTrainee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDeleteFromAddTrainee.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteFromAddTrainee.setText("Delete");
        btnDeleteFromAddTrainee.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnDeleteFromAddTrainee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteFromAddTraineeActionPerformed(evt);
            }
        });
        jPanel6.add(btnDeleteFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 300, -1, 32));

        btnAttachPhotoFromAddTrainee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/attachPhoto.png"))); // NOI18N
        btnAttachPhotoFromAddTrainee.setText("Browse Photo");
        btnAttachPhotoFromAddTrainee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAttachPhotoFromAddTrainee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttachPhotoFromAddTraineeActionPerformed(evt);
            }
        });
        jPanel6.add(btnAttachPhotoFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(819, 100, 140, 30));

        photoAddFromAddTrainee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/man.png"))); // NOI18N
        photoAddFromAddTrainee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.add(photoAddFromAddTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(972, 48, 130, 140));

        btnResetFromAddTrainee2.setBackground(new java.awt.Color(153, 153, 153));
        btnResetFromAddTrainee2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnResetFromAddTrainee2.setForeground(new java.awt.Color(255, 255, 255));
        btnResetFromAddTrainee2.setText("Reset");
        btnResetFromAddTrainee2.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnResetFromAddTrainee2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFromAddTrainee2ActionPerformed(evt);
            }
        });
        jPanel6.add(btnResetFromAddTrainee2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, -1, 32));

        addTrainee.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1120, 340));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        tblDisplayFromAddTrainee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDisplayFromAddTrainee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDisplayFromAddTraineeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDisplayFromAddTrainee);

        btnAddTrainee.setBackground(new java.awt.Color(204, 204, 204));
        btnAddTrainee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddTrainee.setText("Add Trainee");
        btnAddTrainee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddTrainee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTraineeActionPerformed(evt);
            }
        });

        btnTraineDetails.setBackground(new java.awt.Color(204, 204, 204));
        btnTraineDetails.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTraineDetails.setText("Trainee Details");
        btnTraineDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTraineDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraineDetailsActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Result");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(733, Short.MAX_VALUE)
                .addComponent(btnAddTrainee)
                .addGap(26, 26, 26)
                .addComponent(btnTraineDetails)
                .addGap(27, 27, 27)
                .addComponent(jButton4)
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddTrainee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTraineDetails)
                        .addComponent(jButton4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );

        addTrainee.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 1120, 260));

        traineeTab.addTab("addTrainee", addTrainee);

        searchTrainee.setBackground(new java.awt.Color(255, 255, 255));
        searchTrainee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        comboRoundFromShowTraineeDetails.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboRoundFromShowTraineeDetails.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select Round--", "50", "51", "52", "53", "54", "55", "56", "57", "58", " " }));
        comboRoundFromShowTraineeDetails.setBorder(null);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Round :");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Subject :");

        comboSubFromShowTraineeDetails.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboSubFromShowTraineeDetails.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select One--", "JEE", "WPDF", "C #", "NT", "Graphics Design", " " }));
        comboSubFromShowTraineeDetails.setBorder(null);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("Center :");

        comboTspFromShowTraineDatails.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboTspFromShowTraineDatails.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select One--", "CCSL", "New Horizon", "Panthapath Tsp", "Shewrapara Tsp", "Bhuiyan It Limited", "Polton Tsp" }));
        comboTspFromShowTraineDatails.setBorder(null);

        tblDisplayFromShowTrainee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblDisplayFromShowTrainee);

        btnSearchForAllTraineeDetails.setBackground(new java.awt.Color(0, 153, 0));
        btnSearchForAllTraineeDetails.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearchForAllTraineeDetails.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchForAllTraineeDetails.setText("Search By Multiple");
        btnSearchForAllTraineeDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnSearchForAllTraineeDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchForAllTraineeDetailsActionPerformed(evt);
            }
        });

        btnSerarvhTspWiseFromTraineeDetails.setBackground(new java.awt.Color(0, 153, 0));
        btnSerarvhTspWiseFromTraineeDetails.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSerarvhTspWiseFromTraineeDetails.setForeground(new java.awt.Color(255, 255, 255));
        btnSerarvhTspWiseFromTraineeDetails.setText("Search");
        btnSerarvhTspWiseFromTraineeDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnSerarvhTspWiseFromTraineeDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerarvhTspWiseFromTraineeDetailsActionPerformed(evt);
            }
        });

        btnSearchById.setBackground(new java.awt.Color(102, 102, 255));
        btnSearchById.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearchById.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchById.setText("Search By Id");
        btnSearchById.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearchById.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchByIdActionPerformed(evt);
            }
        });

        btnPrint2.setBackground(new java.awt.Color(255, 102, 102));
        btnPrint2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrint2.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint2.setText("Print");

        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/back-button.png"))); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel45.setText("Trainee Details");

        javax.swing.GroupLayout searchTraineeLayout = new javax.swing.GroupLayout(searchTrainee);
        searchTrainee.setLayout(searchTraineeLayout);
        searchTraineeLayout.setHorizontalGroup(
            searchTraineeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchTraineeLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(searchTraineeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(searchTraineeLayout.createSequentialGroup()
                        .addGroup(searchTraineeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel20))
                        .addGap(25, 25, 25)
                        .addGroup(searchTraineeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboSubFromShowTraineeDetails, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboRoundFromShowTraineeDetails, 0, 1, Short.MAX_VALUE)))
                    .addGroup(searchTraineeLayout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboTspFromShowTraineDatails, 0, 1, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearchForAllTraineeDetails)
                .addGap(25, 25, 25)
                .addComponent(btnSerarvhTspWiseFromTraineeDetails)
                .addGap(25, 25, 25)
                .addComponent(btnSearchById)
                .addGap(228, 228, 228)
                .addComponent(btnBack)
                .addGap(32, 32, 32))
            .addGroup(searchTraineeLayout.createSequentialGroup()
                .addGroup(searchTraineeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchTraineeLayout.createSequentialGroup()
                        .addGap(419, 419, 419)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchTraineeLayout.createSequentialGroup()
                        .addGap(539, 539, 539)
                        .addComponent(btnPrint2)))
                .addGap(13, 459, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchTraineeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        searchTraineeLayout.setVerticalGroup(
            searchTraineeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchTraineeLayout.createSequentialGroup()
                .addGroup(searchTraineeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchTraineeLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchTraineeLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnSearchForAllTraineeDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchTraineeLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnSerarvhTspWiseFromTraineeDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchTraineeLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnSearchById, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchTraineeLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(searchTraineeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(comboRoundFromShowTraineeDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(searchTraineeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(comboSubFromShowTraineeDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(searchTraineeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboTspFromShowTraineDatails, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnPrint2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        traineeTab.addTab("searchShowDetails", searchTrainee);

        showIdCard.setBackground(new java.awt.Color(255, 255, 255));
        showIdCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idCardPrint.setBackground(new java.awt.Color(255, 255, 255));
        idCardPrint.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/1.jpg"))); // NOI18N
        idCardPrint.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel23.setText("IsDB-BISEW");
        idCardPrint.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 140, 40));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("IT Scholarship Programme");
        idCardPrint.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, -1, -1));

        jLabel50.setText("Round-");
        idCardPrint.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, -1, -1));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel51.setText("Trainee Id :");
        idCardPrint.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, -1, -1));

        txtRoundFromIdCard.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRoundFromIdCard.setBorder(null);
        idCardPrint.add(txtRoundFromIdCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, 50, -1));

        txtTraineeIdFromIdCard.setBorder(null);
        idCardPrint.add(txtTraineeIdFromIdCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, 100, -1));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel52.setText("Name :");
        idCardPrint.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, -1, -1));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel53.setText("Batch :");
        idCardPrint.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, -1, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel37.setText("Issued By");
        idCardPrint.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 360, -1, -1));

        txtTraineeNameFromIdCard.setBorder(null);
        idCardPrint.add(txtTraineeNameFromIdCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 110, -1));

        txtBatchIdFromIdCard.setBorder(null);
        idCardPrint.add(txtBatchIdFromIdCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 110, -1));

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel62.setText("Programme Athority");
        idCardPrint.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, 120, -1));

        jLabel24.setFont(new java.awt.Font("Maiandra GD", 2, 20)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/original (1).png"))); // NOI18N
        idCardPrint.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, -1, 40));

        traineDetailsS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        idCardPrint.add(traineDetailsS, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 200, 60, 70));

        jLabel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        idCardPrint.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, 390, 240));

        showIdCard.add(idCardPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1140, 760));

        traineeTab.addTab("showIdCard", showIdCard);

        addResult.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel71.setText("Name :");

        txtNameFromAddResult.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNameFromAddResultFocusGained(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel72.setText("Course Name :");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel73.setText("Round :");

        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel74.setText("Month :");

        comboMonthFromAddResult1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboMonthFromAddResult1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select One--", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        comboMonthFromAddResult1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMonthFromAddResult1ActionPerformed(evt);
            }
        });

        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel85.setText("Midterm Date :");

        jLabel97.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel97.setText("Monthly Date :");

        jPanel51.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));

        jPanel53.setBackground(new java.awt.Color(255, 204, 255));

        jLabel75.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel75.setText("Midterm");

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel75)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel76.setText("External :");

        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel77.setText("Evidence :");

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExternalMidtermResulFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEvidenceMidtermResulFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExternalMidtermResulFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEvidenceMidtermResulFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel54.setBackground(new java.awt.Color(255, 255, 255));

        jPanel55.setBackground(new java.awt.Color(255, 204, 255));

        jLabel78.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel78.setText("Monthly");

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel78)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel79.setText("External :");

        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel80.setText("Evidence :");

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExternaMonthlylResulFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEvidenceMonthlyResulFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExternaMonthlylResulFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEvidenceMonthlyResulFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel70.setText("Trainee Id :");

        txtIdFromAddResult.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdFromAddResultFocusLost(evt);
            }
        });

        btnSubmit.setBackground(new java.awt.Color(0, 153, 0));
        btnSubmit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Submit");
        btnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnAddNew.setBackground(new java.awt.Color(153, 153, 153));
        btnAddNew.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddNew.setForeground(new java.awt.Color(255, 255, 255));
        btnAddNew.setText("Add New");
        btnAddNew.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel73)
                                    .addComponent(jLabel74)
                                    .addComponent(jLabel72)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel85)
                                            .addComponent(jLabel97)
                                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtIdFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNameFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCourseNameFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtRoundFromAddResult)
                                                .addComponent(comboMonthFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtDateMonthly, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtDateMidterm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))))))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(btnSubmit)
                                .addGap(31, 31, 31)
                                .addComponent(btnAddNew)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCourseNameFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRoundFromAddResult, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMonthFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDateMidterm, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateMonthly, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel49.setBackground(new java.awt.Color(5, 160, 200));
        jPanel49.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/idb_logo.png"))); // NOI18N
        jPanel49.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 59, 70));

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("IsDB-BISEW");
        jPanel49.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 145, 21));

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("Islamic Development Bank");
        jPanel49.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, 24));

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("Bangladesh Islamic Solidarity Educational Wakf");
        jPanel49.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 268, -1));

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel84.setText("Trainee Id :");

        txtIdFromAddResult1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdFromAddResult1FocusLost(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel86.setText("Course Name :");

        jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel87.setText("Round :");

        jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel88.setText("Month :");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setText("Toatl Marks:");

        txtTotalMark.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel89.setText("Midterm");

        jLabel90.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel90.setText("Monthly");

        jLabel91.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel91.setText("Exam Date");

        jLabel92.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel92.setText("External");

        jLabel93.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel93.setText("Evidence");

        jLabel94.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel94.setText("Exam Date");

        jLabel95.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel95.setText("External");

        jLabel96.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel96.setText("Evidence");

        txtPassFail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtPassFail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/back-button.png"))); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtNameFromAddResult2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNameFromAddResult2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNameFromAddResult2.setBorder(null);
        txtNameFromAddResult2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNameFromAddResult2FocusLost(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(102, 102, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Search Result");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        resultPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel84)
                    .addComponent(jLabel86)
                    .addComponent(jLabel87)
                    .addComponent(jLabel88))
                .addGap(12, 12, 12)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCourseNameFromAddResult2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCourseNameFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMonthFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(103, 103, 103)
                .addComponent(resultPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtTotalMark, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(290, 290, 290)
                .addComponent(txtNameFromAddResult2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(jLabel89)
                .addGap(292, 292, 292)
                .addComponent(jLabel90))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel91)
                .addGap(35, 35, 35)
                .addComponent(jLabel92)
                .addGap(42, 42, 42)
                .addComponent(jLabel93)
                .addGap(107, 107, 107)
                .addComponent(jLabel94)
                .addGap(35, 35, 35)
                .addComponent(jLabel95)
                .addGap(32, 32, 32)
                .addComponent(jLabel96))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(txtDateMidterm1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtExternalMidtermResulFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(txtEvidenceMidtermResulFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(txtDateMonthly1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtExternaMonthlylResulFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtEvidenceMonthlyResulFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addComponent(txtPassFail, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton2)
                .addGap(58, 58, 58))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel84)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(txtIdFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtCourseNameFromAddResult2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtCourseNameFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtMonthFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(10, 10, 10)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTotalMark, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameFromAddResult2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(1, 1, 1)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDateMidterm1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExternalMidtermResulFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEvidenceMidtermResulFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateMonthly1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExternaMonthlylResulFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEvidenceMonthlyResulFromAddResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(txtPassFail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout addResultLayout = new javax.swing.GroupLayout(addResult);
        addResult.setLayout(addResultLayout);
        addResultLayout.setHorizontalGroup(
            addResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addResultLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        addResultLayout.setVerticalGroup(
            addResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addResultLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(addResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(318, 318, 318))
        );

        traineeTab.addTab("addResult", addResult);

        searchResult.setBackground(new java.awt.Color(255, 255, 255));
        searchResult.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel81.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel81.setText("Round :");
        searchResult.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 65, 30));

        comboRoundFromShowTraineeDetails1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboRoundFromShowTraineeDetails1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select Round--", "50", "51", "52", "53", "54", "55", "56", "57", "58", " " }));
        comboRoundFromShowTraineeDetails1.setBorder(null);
        searchResult.add(comboRoundFromShowTraineeDetails1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 151, 29));

        jLabel82.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel82.setText("Subject :");
        searchResult.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 65, 30));

        comboCourseFromShowResult1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboCourseFromShowResult1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select One--", "JEE", "WPDF", "C #", "NT", "Graphics Design", " " }));
        comboCourseFromShowResult1.setBorder(null);
        searchResult.add(comboCourseFromShowResult1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 151, 31));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setText("Center :");
        searchResult.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 65, 30));

        comboTspFromShowTraineDatails1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboTspFromShowTraineDatails1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select One--", "CCSL", "New Horizon", "Panthapath Tsp", "Shewrapara Tsp", "Bhuiyan It Limited", "Polton Tsp" }));
        comboTspFromShowTraineDatails1.setBorder(null);
        searchResult.add(comboTspFromShowTraineDatails1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, -1, 29));
        searchResult.add(txtIdFromShowResultSingle, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, 137, 30));

        btnSearchById1.setBackground(new java.awt.Color(0, 153, 0));
        btnSearchById1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearchById1.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchById1.setText("Search By ID");
        btnSearchById1.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnSearchById1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchById1ActionPerformed(evt);
            }
        });
        searchResult.add(btnSearchById1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, -1, 30));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        tblDisplayFromShowResult1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblDisplayFromShowResult1);

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel48.setText("Trainee Result");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 372, Short.MAX_VALUE))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(425, 425, 425)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
        );

        searchResult.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 164, -1, 450));

        jButton6.setBackground(new java.awt.Color(0, 153, 0));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Search");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        searchResult.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 80, 30));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/back-button.png"))); // NOI18N
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        searchResult.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 50, -1, 40));

        traineeTab.addTab("searchResult", searchResult);

        searchShowDetailsS.setBackground(new java.awt.Color(255, 255, 255));

        jLabel106.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel106.setText("Search By Id :");

        btnSearchTraineeDetails3.setBackground(new java.awt.Color(0, 153, 0));
        btnSearchTraineeDetails3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearchTraineeDetails3.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchTraineeDetails3.setText("Search");
        btnSearchTraineeDetails3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchTraineeDetails3ActionPerformed(evt);
            }
        });

        txtName4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N

        stuffDetailsS1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtName.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jButton9.setBackground(new java.awt.Color(255, 102, 102));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Print Id Card");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel130.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel130.setText("Name :");

        txtTraineeId2.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtTraineeId2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTraineeId2.setBorder(null);

        jLabel147.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel147.setText("Gender :");

        txtGender2.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtGender2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtGender2.setBorder(null);

        jLabel148.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel148.setText("ID :");

        jLabel152.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel152.setText("Mothers Name :");

        txtCourse2.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtCourse2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCourse2.setBorder(null);

        jLabel158.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel158.setText("Fathers Name :");

        txtFathersName2.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtFathersName2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFathersName2.setBorder(null);

        jLabel169.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel169.setText("Email :");

        txtEmail2.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtEmail2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEmail2.setBorder(null);

        jLabel173.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel173.setText("Course :");

        txtTsp2.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtTsp2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTsp2.setBorder(null);

        jLabel174.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel174.setText("Address :");

        txtAddress2.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtAddress2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtAddress2.setBorder(null);

        jLabel175.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel175.setText("Center :");

        txtCourse3.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtCourse3.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCourse3.setBorder(null);

        jLabel176.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel176.setText("Course :");

        txtTsp3.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtTsp3.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTsp3.setBorder(null);

        jLabel177.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel177.setText("Round :");

        txtRound2.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtRound2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtRound2.setBorder(null);

        txtMothersName5.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtMothersName5.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMothersName5.setBorder(null);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/back-button.png"))); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchShowDetailsSLayout = new javax.swing.GroupLayout(searchShowDetailsS);
        searchShowDetailsS.setLayout(searchShowDetailsSLayout);
        searchShowDetailsSLayout.setHorizontalGroup(
            searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                        .addGap(540, 540, 540)
                        .addComponent(jLabel130)
                        .addGap(115, 115, 115)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(stuffDetailsS1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170)
                        .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel169, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel174)
                            .addComponent(jLabel158))
                        .addGap(53, 53, 53)
                        .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGender2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddress2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFathersName2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel148))
                            .addComponent(jLabel177))
                        .addGap(37, 37, 37)
                        .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTraineeId2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRound2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(170, 170, 170)
                        .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel152)
                            .addComponent(jLabel173)
                            .addComponent(jLabel176))
                        .addGap(45, 45, 45)
                        .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMothersName5, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCourse2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                        .addGap(540, 540, 540)
                        .addComponent(jLabel175)
                        .addGap(108, 108, 108)
                        .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTsp3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCourse3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTsp2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                        .addGap(510, 510, 510)
                        .addComponent(jButton9))
                    .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                        .addGap(501, 501, 501)
                        .addComponent(txtName4, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(txtIdShowFromTraineeDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addComponent(btnSearchTraineeDetails3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(45, 45, 45))
        );
        searchShowDetailsSLayout.setVerticalGroup(
            searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdShowFromTraineeDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearchTraineeDetails3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58)
                .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stuffDetailsS1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                                .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel169, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel174, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel158, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                                .addComponent(txtGender2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(txtEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(txtAddress2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(txtFathersName2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(9, 9, 9)
                .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                        .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel177, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                        .addComponent(txtTraineeId2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(txtRound2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                                .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel173, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel176, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(searchShowDetailsSLayout.createSequentialGroup()
                                .addComponent(txtMothersName5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(txtCourse2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(9, 9, 9)
                .addGroup(searchShowDetailsSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel175, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTsp3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCourse3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTsp2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(txtName4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        traineeTab.addTab("searchShowDetailsS", searchShowDetailsS);

        mainTabMother.addTab("traineeTab", traineeTab);

        addStuff.setBackground(new java.awt.Color(255, 255, 255));
        addStuff.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        txtIdFromAddStuff.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setText("Stuff Id :");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel54.setText("Name :");

        txtNameFromAddStuff.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup1.add(radioBtnFemaleFromAddStuff);
        radioBtnFemaleFromAddStuff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radioBtnFemaleFromAddStuff.setText("Female");

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel55.setText("Gender :");

        buttonGroup1.add(radioBtnMaleFromAddStuff);
        radioBtnMaleFromAddStuff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radioBtnMaleFromAddStuff.setText("Male");

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel56.setText("Designation :");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel57.setText("Specialist :");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel58.setText("Email :");

        txtEmaiFromAddStuff.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel59.setText("Address :");

        txtAddressFromAddStuff.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel60.setText("Qualification :");

        btnSaveFromAddTrainee1.setBackground(new java.awt.Color(0, 153, 0));
        btnSaveFromAddTrainee1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSaveFromAddTrainee1.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveFromAddTrainee1.setText("Save");
        btnSaveFromAddTrainee1.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnSaveFromAddTrainee1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveFromAddTrainee1ActionPerformed(evt);
            }
        });

        btnUpdateFromAddTrainee1.setBackground(new java.awt.Color(102, 102, 255));
        btnUpdateFromAddTrainee1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdateFromAddTrainee1.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateFromAddTrainee1.setText("Update");
        btnUpdateFromAddTrainee1.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnUpdateFromAddTrainee1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateFromAddTrainee1ActionPerformed(evt);
            }
        });

        btnResetFromAddTrainee1.setBackground(new java.awt.Color(153, 153, 153));
        btnResetFromAddTrainee1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnResetFromAddTrainee1.setForeground(new java.awt.Color(255, 255, 255));
        btnResetFromAddTrainee1.setText("Reset");
        btnResetFromAddTrainee1.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnResetFromAddTrainee1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFromAddTrainee1ActionPerformed(evt);
            }
        });

        comboDesignationFromAddStuff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboDesignationFromAddStuff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select One--", "Admin Authority", "Consultant", "Instructor", "Others", " " }));

        comboSpecialistFromAddStuff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboSpecialistFromAddStuff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select One--", "JEE", "Graphics", "Web Desingn & Developer", "Networking", "Database Management", "C #", " " }));

        comboQualificationFromAddStuff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboQualificationFromAddStuff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select One--", "SSC", "HSC", "BSC", "MSC", " ", " " }));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel63.setText("Phone :");

        txtPhoneFromAddStuff.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAttachPhotoFromAddTrainee1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/attachPhoto.png"))); // NOI18N
        btnAttachPhotoFromAddTrainee1.setText("Browse Photo");
        btnAttachPhotoFromAddTrainee1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAttachPhotoFromAddTrainee1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttachPhotoFromAddTrainee1ActionPerformed(evt);
            }
        });

        photoAddFromAddStuff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/man.png"))); // NOI18N
        photoAddFromAddStuff.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnDeleteStuff.setBackground(new java.awt.Color(255, 51, 51));
        btnDeleteStuff.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDeleteStuff.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteStuff.setText("Delete");
        btnDeleteStuff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteStuffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(24, 24, 24)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(comboDesignationFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtEmaiFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboSpecialistFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(5, 5, 5))
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62)
                                        .addComponent(txtNameFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62)
                                        .addComponent(radioBtnMaleFromAddStuff)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioBtnFemaleFromAddStuff))))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(txtIdFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(246, 246, 246)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addComponent(btnAttachPhotoFromAddTrainee1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(photoAddFromAddStuff))
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)
                                        .addComponent(txtPhoneFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)
                                        .addComponent(txtAddressFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(comboQualificationFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(391, 391, 391)
                        .addComponent(btnSaveFromAddTrainee1)
                        .addGap(38, 38, 38)
                        .addComponent(btnUpdateFromAddTrainee1)
                        .addGap(38, 38, 38)
                        .addComponent(btnResetFromAddTrainee1)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteStuff)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboQualificationFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddressFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhoneFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(btnAttachPhotoFromAddTrainee1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(photoAddFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtIdFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtNameFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(radioBtnMaleFromAddStuff))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(radioBtnFemaleFromAddStuff)))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(comboDesignationFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(comboSpecialistFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtEmaiFromAddStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSaveFromAddTrainee1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateFromAddTrainee1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetFromAddTrainee1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteStuff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        addStuff.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1120, 340));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        tblDisplayFromAddStuff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDisplayFromAddStuff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDisplayFromAddStuffMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblDisplayFromAddStuff);

        btnTraineDetails1.setBackground(new java.awt.Color(204, 204, 204));
        btnTraineDetails1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTraineDetails1.setText("Stuff Details");
        btnTraineDetails1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTraineDetails1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraineDetails1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(949, Short.MAX_VALUE)
                .addComponent(btnTraineDetails1)
                .addGap(61, 61, 61))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTraineDetails1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        addStuff.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 1120, 280));

        stuffTab.addTab("addTrainee", addStuff);

        searchStuff.setBackground(new java.awt.Color(255, 255, 255));
        searchStuff.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        searchStuff.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDisplayFromShowStuff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tblDisplayFromShowStuff);

        searchStuff.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 211, 1120, 330));

        btnSerarvhTspWiseFromTraineeDetails1.setBackground(new java.awt.Color(0, 153, 0));
        btnSerarvhTspWiseFromTraineeDetails1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSerarvhTspWiseFromTraineeDetails1.setForeground(new java.awt.Color(255, 255, 255));
        btnSerarvhTspWiseFromTraineeDetails1.setText("Search");
        btnSerarvhTspWiseFromTraineeDetails1.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnSerarvhTspWiseFromTraineeDetails1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerarvhTspWiseFromTraineeDetails1ActionPerformed(evt);
            }
        });
        searchStuff.add(btnSerarvhTspWiseFromTraineeDetails1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, -1, 31));

        btnSearchById2.setBackground(new java.awt.Color(0, 153, 0));
        btnSearchById2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearchById2.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchById2.setText("Search By Id");
        btnSearchById2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearchById2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchById2ActionPerformed(evt);
            }
        });
        searchStuff.add(btnSearchById2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, -1, 31));

        btnPrint3.setBackground(new java.awt.Color(255, 102, 102));
        btnPrint3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrint3.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint3.setText("Print");
        searchStuff.add(btnPrint3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 570, -1, 35));

        btnBack2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/back-button.png"))); // NOI18N
        btnBack2.setText("Back");
        btnBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2ActionPerformed(evt);
            }
        });
        searchStuff.add(btnBack2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 70, -1, 35));

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel64.setText("Stuff Details");
        searchStuff.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 220, 40));

        jLabel115.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel115.setText("Designation :");
        searchStuff.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 102, 29));

        comboDesignationFromAddStuff1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboDesignationFromAddStuff1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select One--", "Admin Authority", "Consultant", "Instructor", "Others", " " }));
        searchStuff.add(comboDesignationFromAddStuff1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 208, 28));

        stuffTab.addTab("searchShowDetails", searchStuff);

        showIdCard1.setBackground(new java.awt.Color(255, 255, 255));
        showIdCard1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idCardPrintForStuff.setBackground(new java.awt.Color(255, 255, 255));
        idCardPrintForStuff.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel108.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel108.setText("IsDB-BISEW");
        idCardPrintForStuff.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 140, 40));

        jLabel109.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel109.setText("IT Scholarship Programme");
        idCardPrintForStuff.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 190, -1, -1));

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel111.setText("Id :");
        idCardPrintForStuff.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, -1, -1));

        txtStuffdFromIdCard.setBorder(null);
        idCardPrintForStuff.add(txtStuffdFromIdCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 230, 100, -1));

        jLabel112.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel112.setText("Name :");
        idCardPrintForStuff.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, -1, -1));

        jLabel113.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel113.setText("Designatrion :");
        idCardPrintForStuff.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, -1, -1));

        jLabel114.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel114.setText("Issued By");
        idCardPrintForStuff.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, -1, -1));

        txtStuffNameFromIdCard.setBorder(null);
        idCardPrintForStuff.add(txtStuffNameFromIdCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, 110, -1));

        txtDesignationFromIdCard.setBorder(null);
        idCardPrintForStuff.add(txtDesignationFromIdCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, 110, -1));

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel61.setText("Programme Athority");
        idCardPrintForStuff.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 350, 120, -1));

        stuffDetailsSingle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        idCardPrintForStuff.add(stuffDetailsSingle, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, 60, 70));

        jLabel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        idCardPrintForStuff.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 380, 230));

        jLabel32.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/original (1).png"))); // NOI18N
        jLabel32.setText("Abdul Selim");
        idCardPrintForStuff.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 310, 140, 40));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/1.jpg"))); // NOI18N
        idCardPrintForStuff.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, 60, 60));

        showIdCard1.add(idCardPrintForStuff, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1140, 820));

        stuffTab.addTab("showIdCard", showIdCard1);

        searchShowDetailsS1.setBackground(new java.awt.Color(255, 255, 255));
        searchShowDetailsS1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel65.setText("Search By Id :");
        searchShowDetailsS1.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 112, 30));
        searchShowDetailsS1.add(txtIdShowFromStuff, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 128, 30));

        btnSearchTraineeDetails2.setBackground(new java.awt.Color(0, 204, 0));
        btnSearchTraineeDetails2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearchTraineeDetails2.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchTraineeDetails2.setText("Search");
        btnSearchTraineeDetails2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchTraineeDetails2ActionPerformed(evt);
            }
        });
        searchShowDetailsS1.add(btnSearchTraineeDetails2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 81, 32));

        btnBack3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/back-button.png"))); // NOI18N
        btnBack3.setText("Back");
        btnBack3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack3ActionPerformed(evt);
            }
        });
        searchShowDetailsS1.add(btnBack3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 50, -1, 35));

        txtName3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        searchShowDetailsS1.add(txtName3, new org.netbeans.lib.awtextra.AbsoluteConstraints(501, 633, 360, 60));

        stuffDetailsS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        searchShowDetailsS1.add(stuffDetailsS, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 170, 180));

        txtNameStuffShowData.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtNameStuffShowData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        searchShowDetailsS1.add(txtNameStuffShowData, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 130, 240, 34));

        jButton8.setBackground(new java.awt.Color(255, 102, 102));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Print Id Card");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        searchShowDetailsS1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 550, -1, 39));

        jLabel98.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel98.setText("Name :");
        searchShowDetailsS1.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, -1, 30));

        txtIdStuffShowData.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtIdStuffShowData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdStuffShowData.setBorder(null);
        searchShowDetailsS1.add(txtIdStuffShowData, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 100, 31));

        jLabel99.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel99.setText("Gender :");
        searchShowDetailsS1.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 69, 30));

        txtGenderStuffShowData.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtGenderStuffShowData.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtGenderStuffShowData.setBorder(null);
        searchShowDetailsS1.add(txtGenderStuffShowData, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 180, 175, 31));

        jLabel121.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel121.setText("ID :");
        searchShowDetailsS1.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, -1, 30));

        jLabel101.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel101.setText("Designation :");
        searchShowDetailsS1.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, -1, 30));

        txtDesignationStuffShowData.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtDesignationStuffShowData.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDesignationStuffShowData.setBorder(null);
        searchShowDetailsS1.add(txtDesignationStuffShowData, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 380, 175, 31));

        jLabel102.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel102.setText("Specialist :");
        searchShowDetailsS1.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, -1, 30));

        txtSpecialistStuffShowData.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtSpecialistStuffShowData.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtSpecialistStuffShowData.setBorder(null);
        searchShowDetailsS1.add(txtSpecialistStuffShowData, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 330, 175, 31));

        jLabel83.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel83.setText("Email :");
        searchShowDetailsS1.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, 98, 30));

        txtEmailStuffShowData.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtEmailStuffShowData.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEmailStuffShowData.setBorder(null);
        searchShowDetailsS1.add(txtEmailStuffShowData, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 230, 175, 31));

        jLabel104.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel104.setText("Qualification :");
        searchShowDetailsS1.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 430, -1, 30));

        txtQualificationStuffShowData.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtQualificationStuffShowData.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtQualificationStuffShowData.setBorder(null);
        searchShowDetailsS1.add(txtQualificationStuffShowData, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 430, 175, 31));

        jLabel103.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel103.setText("Address :");
        searchShowDetailsS1.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, -1, 30));

        txtAddressStuffShowData.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtAddressStuffShowData.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtAddressStuffShowData.setBorder(null);
        searchShowDetailsS1.add(txtAddressStuffShowData, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 280, 240, 31));

        stuffTab.addTab("searchShowDetailsS", searchShowDetailsS1);

        mainTabMother.addTab("stuffTab", stuffTab);

        careerHome.setBackground(new java.awt.Color(255, 255, 255));
        careerHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBackground(new java.awt.Color(255, 102, 102));

        jLabel105.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(255, 255, 255));
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel105.setText("CareerHub Panel");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        careerHome.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 460, 60));

        txtIdFromCareerHub.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        txtIdFromCareerHub.setText("Id here");
        careerHome.add(txtIdFromCareerHub, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 310, 120, 30));

        jButton7.setBackground(new java.awt.Color(0, 153, 0));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Print CV");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        careerHome.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 310, -1, 30));

        careerHubHomePage.setLayout(new java.awt.BorderLayout());
        careerHome.add(careerHubHomePage, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 470, 290));

        careeerHubTab.addTab("addTrainee", careerHome);

        cvTab.setBackground(new java.awt.Color(255, 255, 255));
        cvTab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cvTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cvPanel.setBackground(new java.awt.Color(255, 255, 255));
        cvPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 0, 0), 2));
        cvPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cvNameTrainee.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        cvNameTrainee.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cvNameTrainee.setText("Limon Islam Borno");
        cvNameTrainee.setBorder(null);
        cvPanel.add(cvNameTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 340, 80));

        jPanel33.setBackground(new java.awt.Color(5, 160, 200));
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel159.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel159.setForeground(new java.awt.Color(255, 255, 255));
        jLabel159.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel159.setText("Language");
        jPanel33.add(jLabel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 157, 50));

        jPanel39.setBackground(new java.awt.Color(5, 160, 200));
        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel172.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel172.setForeground(new java.awt.Color(255, 255, 255));
        jLabel172.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel172.setText("Experience");
        jPanel39.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 157, 50));

        jPanel33.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 450, 190, 50));

        cvPanel.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 820, 190, 50));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel161.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel161.setText("Self-taught programmer with 3+ years of experience in developing mobile apps using Java and Android Studio. Proven ability ");
        jPanel24.add(jLabel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 910, 30));

        jLabel162.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel162.setText("friendly apps.");
        jPanel24.add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 900, 30));

        jLabel163.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel163.setText("to learn new technologies quickly and apply them to real-world projects. Passionate about building innovative and user-");
        jPanel24.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 900, 30));

        cvPanel.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 930, 80));

        jLabel164.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel164.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel164.setText("IT Programmer");
        cvPanel.add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, 180, 40));

        cvEmail.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        cvEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cvEmail.setText("limonislambornobd@gmail.com");
        cvPanel.add(cvEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 140, 270, 40));

        cvAddress.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        cvAddress.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cvAddress.setText("Komorpur,Faridpur");
        cvPanel.add(cvAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 170, 180, 40));

        jPanel35.setBackground(new java.awt.Color(5, 160, 200));
        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel167.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel167.setForeground(new java.awt.Color(255, 255, 255));
        jLabel167.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel167.setText("Profiles");
        jPanel35.add(jLabel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 157, 50));

        cvPanel.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 190, 50));

        jPanel36.setBackground(new java.awt.Color(5, 160, 200));
        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel168.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel168.setForeground(new java.awt.Color(255, 255, 255));
        jLabel168.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel168.setText("Skills");
        jPanel36.add(jLabel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 157, 50));

        cvPanel.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 820, 190, 50));

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel122.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel122.setText("Start Date – End Date, e.g., June 20XX – Present");
        jPanel27.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 40));

        jLabel123.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel123.setText("Company Name, City, State");
        jPanel27.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel124.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel124.setText("Job Title, e.g., Software Developer");
        jPanel27.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 40));

        jLabel125.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel125.setText("Start Date – End Date, e.g., May 20XX – June 20XX");
        jPanel27.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 430, 40));

        jLabel126.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel126.setText("Company Name, City, State");
        jPanel27.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 430, 40));

        jLabel129.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel129.setText("Start Date – End Date, e.g., June 20XX – Present");
        jPanel27.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 430, 40));

        cvPanel.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 520, 450, 280));

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel149.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel149.setText("Hindi");
        jPanel29.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 40));

        jLabel150.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel150.setText("English");
        jPanel29.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel151.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel151.setText("Bangla");
        jPanel29.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 40));

        jPanel41.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel191.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel191.setText("Year of Graduation, e.g., May 20XX");
        jPanel41.add(jLabel191, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 40));

        jLabel192.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel192.setText(" Degree Earned, e.g., Bachelor of Science in Education  ");
        jPanel41.add(jLabel192, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 40));

        jLabel193.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel193.setText("University Name, City, State");
        jPanel41.add(jLabel193, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel194.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel194.setText("Year of Graduation, e.g., May 20XX");
        jPanel41.add(jLabel194, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 40));

        jLabel195.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel195.setText("University Name, City, State");
        jPanel41.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel196.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel196.setText(" Degree Earned, e.g., Bachelor of Science in Education  ");
        jPanel41.add(jLabel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 40));

        jLabel197.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel197.setText("Year of Graduation, e.g., May 20XX");
        jPanel41.add(jLabel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 430, 40));

        jLabel198.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel198.setText("University Name, City, State");
        jPanel41.add(jLabel198, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 430, 40));

        jLabel199.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel199.setText(" Degree Earned, e.g., Bachelor of Science in Education  ");
        jPanel41.add(jLabel199, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 430, 40));

        jPanel29.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 880, 450, 280));

        cvPanel.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 880, 450, 280));

        jPanel37.setBackground(new java.awt.Color(5, 160, 200));
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel170.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel170.setForeground(new java.awt.Color(255, 255, 255));
        jLabel170.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel170.setText("Education");
        jPanel37.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 157, 50));

        cvPanel.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 450, 190, 50));

        jPanel38.setBackground(new java.awt.Color(5, 160, 200));
        jPanel38.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel171.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel171.setForeground(new java.awt.Color(255, 255, 255));
        jLabel171.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel171.setText("Experience");
        jPanel38.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 157, 50));

        cvPanel.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 450, 190, 50));

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel185.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel185.setText("Year of Graduation, e.g., May 20XX");
        jPanel40.add(jLabel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 40));

        jLabel186.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel186.setText("University Name, City, State");
        jPanel40.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel187.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel187.setText(" Degree Earned, e.g., Bachelor of Science in Education  ");
        jPanel40.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 40));

        jLabel188.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel188.setText("Year of Graduation, e.g., May 20XX");
        jPanel40.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 430, 40));

        jLabel189.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel189.setText("University Name, City, State");
        jPanel40.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 430, 40));

        jLabel190.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel190.setText(" Degree Earned, e.g., Bachelor of Science in Education  ");
        jPanel40.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 430, 40));

        cvPanel.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 520, 450, 280));

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel201.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel201.setText("Software Development");
        jPanel42.add(jLabel201, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 40));

        jLabel202.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel202.setText("University Name, City, State");
        jPanel42.add(jLabel202, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel203.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel203.setText("Programming Languages: Java, C++");
        jPanel42.add(jLabel203, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 40));

        jLabel204.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel204.setText("Database Management");
        jPanel42.add(jLabel204, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 430, 40));

        jLabel205.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel205.setText("Back-end Development");
        jPanel42.add(jLabel205, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 430, 40));

        jLabel206.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel206.setText("Web Development:");
        jPanel42.add(jLabel206, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 430, 40));

        jPanel43.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel207.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel207.setText("Year of Graduation, e.g., May 20XX");
        jPanel43.add(jLabel207, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 40));

        jLabel208.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel208.setText(" Degree Earned, e.g., Bachelor of Science in Education  ");
        jPanel43.add(jLabel208, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 40));

        jLabel209.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel209.setText("University Name, City, State");
        jPanel43.add(jLabel209, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel210.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel210.setText("Year of Graduation, e.g., May 20XX");
        jPanel43.add(jLabel210, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 40));

        jLabel211.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel211.setText("University Name, City, State");
        jPanel43.add(jLabel211, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel212.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel212.setText(" Degree Earned, e.g., Bachelor of Science in Education  ");
        jPanel43.add(jLabel212, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 40));

        jLabel213.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel213.setText("Year of Graduation, e.g., May 20XX");
        jPanel43.add(jLabel213, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 430, 40));

        jLabel214.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel214.setText("University Name, City, State");
        jPanel43.add(jLabel214, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 430, 40));

        jLabel215.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel215.setText(" Degree Earned, e.g., Bachelor of Science in Education  ");
        jPanel43.add(jLabel215, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 430, 40));

        jPanel42.add(jPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 880, 450, 280));

        cvPanel.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 880, 450, 280));

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));
        jPanel44.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel216.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel216.setText("University Name, City, State");
        jPanel44.add(jLabel216, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel217.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel217.setText("Software Development");
        jPanel44.add(jLabel217, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 40));

        jLabel218.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel218.setText("University Name, City, State");
        jPanel44.add(jLabel218, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel219.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel219.setText("Programming Languages: Java, C++");
        jPanel44.add(jLabel219, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 40));

        jLabel220.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel220.setText("Database Management");
        jPanel44.add(jLabel220, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 430, 40));

        jLabel221.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel221.setText("Back-end Development");
        jPanel44.add(jLabel221, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 430, 40));

        jLabel222.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel222.setText("Web Development:");
        jPanel44.add(jLabel222, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 430, 40));

        jPanel45.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel223.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel223.setText("Year of Graduation, e.g., May 20XX");
        jPanel45.add(jLabel223, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 40));

        jLabel224.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel224.setText(" Degree Earned, e.g., Bachelor of Science in Education  ");
        jPanel45.add(jLabel224, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 40));

        jLabel225.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel225.setText("University Name, City, State");
        jPanel45.add(jLabel225, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel226.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel226.setText("Year of Graduation, e.g., May 20XX");
        jPanel45.add(jLabel226, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 40));

        jLabel227.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel227.setText("University Name, City, State");
        jPanel45.add(jLabel227, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel228.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel228.setText(" Degree Earned, e.g., Bachelor of Science in Education  ");
        jPanel45.add(jLabel228, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 40));

        jLabel229.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel229.setText("Year of Graduation, e.g., May 20XX");
        jPanel45.add(jLabel229, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 430, 40));

        jLabel230.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel230.setText("University Name, City, State");
        jPanel45.add(jLabel230, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 430, 40));

        jLabel231.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel231.setText(" Degree Earned, e.g., Bachelor of Science in Education  ");
        jPanel45.add(jLabel231, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 430, 40));

        jPanel44.add(jPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 880, 450, 280));

        cvPanel.add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 880, 450, 280));

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));
        jPanel46.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel232.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel232.setText("University Name, City, State");
        jPanel46.add(jLabel232, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel233.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel233.setText("Software Development");
        jPanel46.add(jLabel233, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 40));

        jLabel234.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel234.setText("University Name, City, State");
        jPanel46.add(jLabel234, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel235.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel235.setText("Programming Languages: Java, C++");
        jPanel46.add(jLabel235, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 40));

        jLabel236.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel236.setText("Database Management");
        jPanel46.add(jLabel236, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 430, 40));

        jLabel237.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel237.setText("Back-end Development");
        jPanel46.add(jLabel237, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 430, 40));

        jLabel238.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel238.setText("Web Development:");
        jPanel46.add(jLabel238, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 430, 40));

        jPanel47.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel239.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel239.setText("Year of Graduation, e.g., May 20XX");
        jPanel47.add(jLabel239, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 40));

        jLabel240.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel240.setText(" Degree Earned, e.g., Bachelor of Science in Education  ");
        jPanel47.add(jLabel240, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 40));

        jLabel241.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel241.setText("University Name, City, State");
        jPanel47.add(jLabel241, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel242.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel242.setText("Year of Graduation, e.g., May 20XX");
        jPanel47.add(jLabel242, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 40));

        jLabel243.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel243.setText("University Name, City, State");
        jPanel47.add(jLabel243, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jLabel244.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel244.setText(" Degree Earned, e.g., Bachelor of Science in Education  ");
        jPanel47.add(jLabel244, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 40));

        jLabel245.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel245.setText("Year of Graduation, e.g., May 20XX");
        jPanel47.add(jLabel245, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 430, 40));

        jLabel246.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel246.setText("University Name, City, State");
        jPanel47.add(jLabel246, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 430, 40));

        jLabel247.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel247.setText(" Degree Earned, e.g., Bachelor of Science in Education  ");
        jPanel47.add(jLabel247, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 430, 40));

        jPanel46.add(jPanel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 880, 450, 280));

        cvPanel.add(jPanel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 880, 450, 280));

        cvPhotoTrainee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cvPanel.add(cvPhotoTrainee, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 210, 210));

        cvTab.add(cvPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -20, 1270, 1380));

        careeerHubTab.addTab("searchShowDetails", cvTab);

        cvPanel1.setBackground(new java.awt.Color(255, 255, 255));
        cvPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 0, 0), 2));

        jPanel28.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 239, Short.MAX_VALUE)
        );

        cvNameTrainee1.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        cvNameTrainee1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cvNameTrainee1.setText("Limon Islam Borno");
        cvNameTrainee1.setBorder(null);

        jLabel127.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel127.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel127.setText("Komorpur,Faridpur");

        jLabel128.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel128.setText("IT Programmer");

        jLabel153.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel153.setText("limonislambornobd@gmail.com");

        jPanel30.setBackground(new java.awt.Color(5, 160, 200));

        jLabel154.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel154.setForeground(new java.awt.Color(255, 255, 255));
        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel154.setText("Profiles");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel154, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jLabel155.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel155.setText("Self-taught programmer with 3+ years of experience in developing mobile apps using Java and Android Studio. Proven ability ");

        jLabel156.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel156.setText("to learn new technologies quickly and apply them to real-world projects. Passionate about building innovative and user-");

        jLabel157.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel157.setText("friendly apps.");

        jPanel34.setBackground(new java.awt.Color(5, 160, 200));

        jLabel160.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel160.setForeground(new java.awt.Color(255, 255, 255));
        jLabel160.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel160.setText("Profiles");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jLabel160, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel160, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout cvPanel1Layout = new javax.swing.GroupLayout(cvPanel1);
        cvPanel1.setLayout(cvPanel1Layout);
        cvPanel1Layout.setHorizontalGroup(
            cvPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cvPanel1Layout.createSequentialGroup()
                .addGroup(cvPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cvPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(338, 338, 338)
                        .addGroup(cvPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cvNameTrainee1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(cvPanel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(cvPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(cvPanel1Layout.createSequentialGroup()
                                        .addGap(90, 90, 90)
                                        .addGroup(cvPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(cvPanel1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(cvPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cvPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel155))
                            .addGroup(cvPanel1Layout.createSequentialGroup()
                                .addGroup(cvPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel157)
                                    .addComponent(jLabel156)
                                    .addGroup(cvPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(218, 218, 218))
            .addGroup(cvPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(cvPanel1Layout.createSequentialGroup()
                    .addGap(148, 148, 148)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(998, Short.MAX_VALUE)))
        );
        cvPanel1Layout.setVerticalGroup(
            cvPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cvPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(cvPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cvPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(cvNameTrainee1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(cvPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(cvPanel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(cvPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(90, 90, 90)
                .addComponent(jLabel155)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel156)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel157)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(cvPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(cvPanel1Layout.createSequentialGroup()
                    .addGap(318, 318, 318)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(566, Short.MAX_VALUE)))
        );

        careeerHubTab.addTab("tab4", cvPanel1);

        mainTabMother.addTab("careerhubTab", careeerHubTab);

        addPayment.setBackground(new java.awt.Color(255, 255, 255));
        addPayment.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel120.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(255, 102, 102));
        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel120.setText("Make Payment");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel120, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel120, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        txtIdFromAddStuff3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtIdFromAddStuff3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdFromAddStuff3FocusGained(evt);
            }
        });

        jLabel131.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel131.setText("Stuff Id :");

        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel132.setText("Payment Method :");

        txtPhoneFromAddStuffPay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPhoneFromAddStuffPay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPhoneFromAddStuffPayFocusLost(evt);
            }
        });

        jLabel133.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel133.setText("Mobile Number :");

        jLabel134.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Bkash-logo-1024x683.png"))); // NOI18N

        jLabel135.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/nogod.png"))); // NOI18N

        jLabel136.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel136.setText("Account Name :");

        txtNameFromAddStuffPay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNameFromAddStuffPay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNameFromAddStuffPayFocusGained(evt);
            }
        });

        jLabel137.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel137.setText("Amount :");

        txtAmountToPay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel138.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/logo-upay-removebg-preview (2).png"))); // NOI18N

        buttonGroup1.add(radioBtnBkash);
        radioBtnBkash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioBtnBkashMouseClicked(evt);
            }
        });

        buttonGroup1.add(radioBtnNogod);
        radioBtnNogod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioBtnNogodMouseClicked(evt);
            }
        });

        buttonGroup1.add(radioBtnO);
        radioBtnO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioBtnOMouseClicked(evt);
            }
        });

        jLabel118.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel118.setText("Month");

        btnPayBank.setBackground(new java.awt.Color(255, 102, 102));
        btnPayBank.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPayBank.setForeground(new java.awt.Color(255, 255, 255));
        btnPayBank.setText("Pay");
        btnPayBank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayBankActionPerformed(evt);
            }
        });

        comboMonthPay.setBackground(new java.awt.Color(0, 204, 0));
        comboMonthPay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboMonthPay.setForeground(new java.awt.Color(255, 255, 255));
        comboMonthPay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select first", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        comboMonthPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMonthPayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel133)
                            .addComponent(jLabel136)
                            .addComponent(jLabel137))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNameFromAddStuffPay, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhoneFromAddStuffPay, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAmountToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel132))
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(radioBtnBkash)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(radioBtnNogod)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel135)
                                .addGap(18, 18, 18)
                                .addComponent(radioBtnO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(txtIdFromAddStuff3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)))
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel138)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboMonthPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(65, 65, 65))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPayBank)
                .addGap(425, 425, 425))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdFromAddStuff3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel131, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboMonthPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel25Layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(radioBtnNogod))
                                .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel25Layout.createSequentialGroup()
                                    .addGap(41, 41, 41)
                                    .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel25Layout.createSequentialGroup()
                                    .addGap(48, 48, 48)
                                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(radioBtnBkash))))
                            .addGap(10, 10, 10)))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(radioBtnO)))
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhoneFromAddStuffPay, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNameFromAddStuffPay, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAmountToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(btnPayBank, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Payment On Mobile Banking", jPanel25);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        txtIdFromAddStuff4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtIdFromAddStuff4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdFromAddStuff4FocusGained(evt);
            }
        });

        jLabel139.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel139.setText("Stuff Id :");

        jLabel140.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel140.setText("Payment Method :");

        txtPhoneFromAddStuffPay1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPhoneFromAddStuffPay1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPhoneFromAddStuffPay1FocusLost(evt);
            }
        });

        jLabel141.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel141.setText("Card Number :");

        jLabel142.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/IslamiBank.png"))); // NOI18N

        jLabel143.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/card.png"))); // NOI18N

        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel144.setText("Account Name :");

        txtNameFromAddStuffPay1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNameFromAddStuffPay1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNameFromAddStuffPay1FocusGained(evt);
            }
        });

        jLabel145.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel145.setText("Amount :");

        txtAmountToPay1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel146.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/City-bank--2106051100-removebg-preview (4).png"))); // NOI18N

        buttonGroup1.add(radioBtnIdbBank);

        buttonGroup1.add(radioBtnDBL);

        buttonGroup1.add(radioBtnO2);

        jLabel119.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel119.setText("Month");

        btnPayBank1.setBackground(new java.awt.Color(255, 102, 102));
        btnPayBank1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPayBank1.setForeground(new java.awt.Color(255, 255, 255));
        btnPayBank1.setText("Pay");
        btnPayBank1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayBank1ActionPerformed(evt);
            }
        });

        comboMonthPay1.setBackground(new java.awt.Color(0, 204, 0));
        comboMonthPay1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboMonthPay1.setForeground(new java.awt.Color(255, 255, 255));
        comboMonthPay1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select first", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        comboMonthPay1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMonthPay1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel141)
                            .addComponent(jLabel144)
                            .addComponent(jLabel145))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNameFromAddStuffPay1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhoneFromAddStuffPay1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAmountToPay1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel140))
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(radioBtnIdbBank)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(radioBtnDBL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel143)
                                .addGap(18, 18, 18)
                                .addComponent(radioBtnO2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(txtIdFromAddStuff4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)))
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel146)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboMonthPay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(65, 65, 65))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPayBank1)
                .addGap(456, 456, 456))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdFromAddStuff4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel139, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboMonthPay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel26Layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(radioBtnDBL))
                                .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel26Layout.createSequentialGroup()
                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel26Layout.createSequentialGroup()
                                    .addGap(41, 41, 41)
                                    .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel26Layout.createSequentialGroup()
                                    .addGap(48, 48, 48)
                                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(radioBtnIdbBank))))
                            .addGap(10, 10, 10)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(radioBtnO2)))
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhoneFromAddStuffPay1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNameFromAddStuffPay1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel145, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAmountToPay1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(btnPayBank1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jTabbedPane2.addTab("Payment On Bank", jPanel26);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane2)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        addPayment.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1120, 610));

        paymentTab.addTab("addTrainee", addPayment);

        checkPayment.setBackground(new java.awt.Color(255, 255, 255));
        checkPayment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        checkPayment.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDisplayFromShowStuff1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tblDisplayFromShowStuff1);

        checkPayment.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 211, 1120, 330));

        btnSerarvhTspWiseFromTraineeDetails2.setBackground(new java.awt.Color(51, 204, 0));
        btnSerarvhTspWiseFromTraineeDetails2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSerarvhTspWiseFromTraineeDetails2.setText("Search");
        btnSerarvhTspWiseFromTraineeDetails2.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnSerarvhTspWiseFromTraineeDetails2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerarvhTspWiseFromTraineeDetails2ActionPerformed(evt);
            }
        });
        checkPayment.add(btnSerarvhTspWiseFromTraineeDetails2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, -1, 31));

        btnSearchById3.setBackground(new java.awt.Color(204, 204, 204));
        btnSearchById3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearchById3.setText("Search By Id");
        btnSearchById3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearchById3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchById3ActionPerformed(evt);
            }
        });
        checkPayment.add(btnSearchById3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, -1, 31));

        btnPrint4.setBackground(new java.awt.Color(255, 102, 102));
        btnPrint4.setText("Print");
        checkPayment.add(btnPrint4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 570, -1, 35));

        btnBack4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/back-button.png"))); // NOI18N
        btnBack4.setText("Back");
        btnBack4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack4ActionPerformed(evt);
            }
        });
        checkPayment.add(btnBack4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 70, -1, 35));

        jLabel100.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel100.setText("Stuff Details");
        checkPayment.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 220, 40));

        jLabel116.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel116.setText("Designation :");
        checkPayment.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 102, 29));

        comboDesignationFromAddStuff2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboDesignationFromAddStuff2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select One--", "Admin Authority", "Consultant", "Instructor", "Others", " " }));
        checkPayment.add(comboDesignationFromAddStuff2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 208, 28));

        paymentTab.addTab("searchShowDetails", checkPayment);

        mainTabMother.addTab("tab5", paymentTab);

        jTabbedPane7.setBackground(new java.awt.Color(255, 255, 255));
        mainTabMother.addTab("tab6", jTabbedPane7);
        mainTabMother.addTab("tab7", jTabbedPane1);

        jPanel4.add(mainTabMother, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -70, 1140, 1000));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCareerHubMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCareerHubMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnCareerHubMouseClicked

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
        getJeePassStudentCount();
        getJeeTotalStudentCount();
        getJeePassParcentage();
        
        getNtPassStudentCount();
        getNtTotalStudentCount();
        getNtPassParcentage();
        
         getDbPassStudentCount();
        getDbTotalStudentCount();
        getDbPassParcentage();
        
         getGpPassStudentCount();
        getGpTotalStudentCount();
        getGpPassParcentage();
        
          getCPassStudentCount();
        getCTotalStudentCount();
        getCPassParcentage();
        
          getWebPassStudentCount();
        getWebTotalStudentCount();
        getWebPassParcentage();
        
        showPieChart();
        showLineChart();
        showBarChart();
        
        reset();
        
        mainTabMother.setSelectedIndex(0);
        homeTab.setSelectedIndex(0);
        btnHome.setBackground(new Color(239, 114, 108));
        btnTrainee.setBackground(getBackground());
        btnStuff.setBackground(getBackground());
        btnCareerHub.setBackground(getBackground());
        btnPayment.setBackground(getBackground());
        btnLogOut.setBackground(getBackground());
          
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnTraineeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraineeActionPerformed
        // TODO add your handling code here:
        mainTabMother.setSelectedIndex(1);
        traineeTab.setSelectedIndex(0);
         btnTrainee.setBackground(new Color(239, 114, 108));

        btnHome.setBackground(getBackground());
        btnStuff.setBackground(getBackground());
        btnCareerHub.setBackground(getBackground());
        btnPayment.setBackground(getBackground());
        btnLogOut.setBackground(getBackground());
    }//GEN-LAST:event_btnTraineeActionPerformed

    private void btnStuffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStuffActionPerformed
        // TODO add your handling code here:
        mainTabMother.setSelectedIndex(2);
        stuffTab.setSelectedIndex(0);
         btnStuff.setBackground(new Color(239, 114, 108));
        btnTrainee.setBackground(getBackground());
        btnHome.setBackground(getBackground());
        btnCareerHub.setBackground(getBackground());
        btnPayment.setBackground(getBackground());
        btnLogOut.setBackground(getBackground());
    }//GEN-LAST:event_btnStuffActionPerformed

    private void btnCareerHubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCareerHubActionPerformed
        // TODO add your handling code here:
        mainTabMother.setSelectedIndex(3);
        btnCareerHub.setBackground(new Color(239, 114, 108));

        btnTrainee.setBackground(getBackground());
        btnStuff.setBackground(getBackground());
        btnHome.setBackground(getBackground());
        btnPayment.setBackground(getBackground());
        btnLogOut.setBackground(getBackground());
    }//GEN-LAST:event_btnCareerHubActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        // TODO add your handling code here:
        mainTabMother.setSelectedIndex(4);
       
        btnPayment.setBackground(new Color(239, 114, 108));

        btnTrainee.setBackground(getBackground());
        btnCareerHub.setBackground(getBackground());
        btnStuff.setBackground(getBackground());
      
        btnHome.setBackground(getBackground());
        btnLogOut.setBackground(getBackground());
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        mainTabMother.setSelectedIndex(5);
         btnLogOut.setBackground(new Color(239, 114, 108));

        btnTrainee.setBackground(getBackground());
        btnStuff.setBackground(getBackground());
        btnHome.setBackground(getBackground());
        btnPayment.setBackground(getBackground());
        careeerHubTab.setBackground(getBackground());
        
       int response= JOptionPane.showConfirmDialog(null, "Are you shure to log out?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response==JOptionPane.YES_OPTION){
        MainForm mf=new MainForm();
        mf.dispose();
        LogInForm lf=new LogInForm();
        lf.setVisible(true);
        
        
        }
        
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void comboTspFromAddTraineeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboTspFromAddTraineeFocusLost
        // TODO add your handling code here:

        String course = comboSubjectFromAddTrainee.getSelectedItem().toString();
        String center = comboTspFromAddTrainee.getSelectedItem().toString();
        String round = comboRoundFromAddTrainee.getSelectedItem().toString();
        String gender = "";
        if (radioBtnMaleFromAddTrainee.isSelected()) {
            gender = "M";

        } else if (radioBtnFemaleFromAddTrainee.isSelected()) {
            gender = "F";

        }

        txtBatchIdFromAddTrainee.setText(course + "/" + center + "-" + gender + "/" + round + "/01");
    }//GEN-LAST:event_comboTspFromAddTraineeFocusLost

    private void btnAddTraineeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTraineeActionPerformed
        // TODO add your handling code here:
         reset();
        traineeTab.setSelectedIndex(0);
    }//GEN-LAST:event_btnAddTraineeActionPerformed

    private void btnTraineDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraineDetailsActionPerformed
        // TODO add your handling code here:
        traineeTab.setSelectedIndex(1);
        
    }//GEN-LAST:event_btnTraineDetailsActionPerformed

    private void btnSearchByIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByIdActionPerformed
        // TODO add your handling code here:
       traineeTab.setSelectedIndex(5);
       
        
    }//GEN-LAST:event_btnSearchByIdActionPerformed

    private void txtIdFromAddResult1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFromAddResult1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdFromAddResult1FocusLost

    private void txtNameFromAddResult2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFromAddResult2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameFromAddResult2FocusLost

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        traineeTab.setSelectedIndex(3);
     
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnSaveFromAddTraineeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveFromAddTraineeActionPerformed
        // TODO add your handling code here:
          sql = "insert into student_table_1(id,name,gender,fathers_name,mothers_name,email,address,dob,course,round,center,batch_id,picture_path) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String gender = "";
        if (radioBtnMaleFromAddTrainee.isSelected()) {
            gender = "Male";
        } else if (radioBtnFemaleFromAddTrainee.isSelected()) {
            gender = "Female";
        }
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdFromAddTrainee.getText().trim()));
            ps.setString(2, txtNameFromAddTrainee.getText().trim());
            ps.setString(3, gender);
            ps.setString(4, txtFathersNameFromAddTrainee.getText().trim());
            ps.setString(5, txtMothersNameFromAddTrainee.getText().trim());
            ps.setString(6, txtEmailFromAddTrainee.getText().trim());
            ps.setString(7, txtAddressFromAddTrainee.getText().trim());
            ps.setDate(8, dateConverter(dateDobFromAddTrainee.getDate()));
            ps.setString(9, comboSubjectFromAddTrainee.getSelectedItem().toString());
            ps.setString(10, comboRoundFromAddTrainee.getSelectedItem().toString());
            ps.setString(11, comboTspFromAddTrainee.getSelectedItem().toString());
            ps.setString(12, txtBatchIdFromAddTrainee.getText().trim());
            ps.setString(13, selectedImagePath);

            ps.executeUpdate();
            ps.close();
            con.getCon().close();
            JOptionPane.showMessageDialog(rootPane, "Data Saved");
            getAllData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Data Not Saved");
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }//GEN-LAST:event_btnSaveFromAddTraineeActionPerformed

    private void txtBatchIdFromAddTraineeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBatchIdFromAddTraineeFocusGained
        // TODO add your handling code here:
          sql = "select course,center,gender,round from student_table_1 where id=?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdFromAddTrainee.getText()));
            rs = ps.executeQuery();

            while (rs.next()) {
                String course = rs.getString("course");
                String center = rs.getString("center");
                String gender = rs.getString("gender");

                String round = rs.getString("round");

                txtBatchIdFromAddTrainee.setText(course+"/"+center+"/"+"-"+round);
//                txtBatchIdFromAddTrainee.setText(course);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtBatchIdFromAddTraineeFocusGained

    private void txtBatchIdFromAddTraineeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBatchIdFromAddTraineeKeyPressed
        // TODO add your handling code here:
        sql = "select * from student_table_1 where id=?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdFromAddTrainee.getText()));
            rs = ps.executeQuery();

            while (rs.next()) {

                //              String course = rs.getString("course");
                //              String center = rs.getString("center");
                //              String gender = rs.getString("gender");
                String email = rs.getString("email");

                //                String round = rs.getString("round");
                //                txtBatchIdFromAddTrainee.setText(course+"/"+center+"/"+"-"+round);
                txtBatchIdFromAddTrainee.setText(email);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtBatchIdFromAddTraineeKeyPressed

    private void btnAttachPhotoFromAddTraineeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachPhotoFromAddTraineeActionPerformed
        // TODO add your handling code here:
        JFileChooser browseImageFile = new JFileChooser();

        //for filtering image extension
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
        browseImageFile.addChoosableFileFilter(fnef);

        int showOpenDialogue = browseImageFile.showOpenDialog(null);

        if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
            File selectedImageFile = browseImageFile.getSelectedFile();
            selectedImagePath = selectedImageFile.getAbsolutePath();
            JOptionPane.showMessageDialog(null, selectedImagePath);

            //for displaying image on jlabel
            ImageIcon li = new ImageIcon(selectedImagePath);

            //for resize image in jlabel
            Image image = li.getImage().getScaledInstance(photoAddFromAddTrainee.getWidth(), photoAddFromAddTrainee.getHeight(), Image.SCALE_SMOOTH);

            photoAddFromAddTrainee.setIcon(new ImageIcon(image));
        }
    }//GEN-LAST:event_btnAttachPhotoFromAddTraineeActionPerformed

    private void btnUpdateFromAddTraineeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateFromAddTraineeActionPerformed
        // TODO add your handling code here:
           sql = "update student_table_1 set name=?,gender=?,fathers_name=?,mothers_name=?,email=?,address=?,dob=?,course=?,round=?,center=?,batch_id=? where id=?";
        String gender = "";
        if (radioBtnMaleFromAddTrainee.isSelected()) {
            gender = "Male";
        } else if (radioBtnFemaleFromAddTrainee.isSelected()) {
            gender = "Female";
        }
        try {
            ps = con.getCon().prepareStatement(sql);

            ps.setString(1, txtNameFromAddTrainee.getText().trim());
            ps.setString(2, gender);
            ps.setString(3, txtFathersNameFromAddTrainee.getText().trim());
            ps.setString(4, txtMothersNameFromAddTrainee.getText().trim());
            ps.setString(5, txtEmailFromAddTrainee.getText().trim());
            ps.setString(6, txtAddressFromAddTrainee.getText().trim());
            ps.setDate(7, dateConverter(dateDobFromAddTrainee.getDate()));
            ps.setString(8, comboSubjectFromAddTrainee.getSelectedItem().toString());
            ps.setString(9, comboRoundFromAddTrainee.getSelectedItem().toString());
            ps.setString(10, comboTspFromAddTrainee.getSelectedItem().toString());

            ps.setString(11, txtBatchIdFromAddTrainee.getText().trim());
//              ps.setString(12, selectedImagePath);
            ps.setInt(12, Integer.parseInt(txtIdFromAddTrainee.getText().trim()));

            ps.executeUpdate();
            ps.close();
            con.getCon().close();
            JOptionPane.showMessageDialog(rootPane, "Data Updated");
            getAllData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Data Not Updated");
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }//GEN-LAST:event_btnUpdateFromAddTraineeActionPerformed

    private void btnDeleteFromAddTraineeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteFromAddTraineeActionPerformed
        // TODO add your handling code here:
        String name = txtNameFromAddTrainee.getText().trim();
        sql = "delete from student_table_1 where name=?";

        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();
            ps.close();
            con.getCon().close();
            JOptionPane.showMessageDialog(rootPane, "Data deleted");
            reset();
            getAllData();
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_btnDeleteFromAddTraineeActionPerformed

    private void tblDisplayFromAddTraineeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDisplayFromAddTraineeMouseClicked
        // TODO add your handling code here:
         int row = tblDisplayFromAddTrainee.getSelectedRow();

        String id = tblDisplayFromAddTrainee.getModel().getValueAt(row, 0).toString();
        String name = tblDisplayFromAddTrainee.getModel().getValueAt(row, 1).toString();
        String gender = tblDisplayFromAddTrainee.getModel().getValueAt(row, 2).toString();
        String fathers_name = tblDisplayFromAddTrainee.getModel().getValueAt(row, 3).toString();
        String mothers_name = tblDisplayFromAddTrainee.getModel().getValueAt(row, 4).toString();
        String email = tblDisplayFromAddTrainee.getModel().getValueAt(row, 5).toString();
        String address = tblDisplayFromAddTrainee.getModel().getValueAt(row, 6).toString();
        String dob = tblDisplayFromAddTrainee.getModel().getValueAt(row, 7).toString();
        String course = tblDisplayFromAddTrainee.getModel().getValueAt(row, 8).toString();
        String round = tblDisplayFromAddTrainee.getModel().getValueAt(row, 9).toString();
        String center = tblDisplayFromAddTrainee.getModel().getValueAt(row, 10).toString();
        String batch_id = tblDisplayFromAddTrainee.getModel().getValueAt(row, 11).toString();
//        String picture_path = tblDisplayFromAddTrainee.getModel().getValueAt(row, 12).toString();

        txtIdFromAddTrainee.setText(id);
        txtNameFromAddTrainee.setText(name);
        if (gender.equalsIgnoreCase("Male")) {
            radioBtnMaleFromAddTrainee.setSelected(rootPaneCheckingEnabled);
        } else if (gender.equalsIgnoreCase("Female")) {
            radioBtnFemaleFromAddTrainee.setSelected(rootPaneCheckingEnabled);
        }
        txtFathersNameFromAddTrainee.setText(fathers_name);
        txtMothersNameFromAddTrainee.setText(mothers_name);
        txtEmailFromAddTrainee.setText(email);
        txtAddressFromAddTrainee.setText(address);
        dateDobFromAddTrainee.setDate(stringToUtilDate(dob));

        comboSubjectFromAddTrainee.setSelectedItem(course);
        comboRoundFromAddTrainee.setSelectedItem(round);
        comboTspFromAddTrainee.setSelectedItem(center);
        txtBatchIdFromAddTrainee.setText(batch_id);
//        photoAddFromAddTrainee.setIcon(picture_path);
    }//GEN-LAST:event_tblDisplayFromAddTraineeMouseClicked

    private void btnSearchForAllTraineeDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchForAllTraineeDetailsActionPerformed
        // TODO add your handling code here:
         String columnName[] = {"ID", "Name", "Gender", "Fathers_Name", "Mothers_Name", "Email", "Address", "DOB", "Course", "Round", "Center"};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnName);
        tblDisplayFromShowTrainee.setModel(model);

        try {
            sql = "select * from student_table_1 where round=? and course=?";
            ps = con.getCon().prepareStatement(sql);
//              ps.setInt(1,Integer.parseInt(txtIdShowFromTraineeDetails.getText()));
            ps.setString(1, comboRoundFromShowTraineeDetails.getSelectedItem().toString());
            ps.setString(2, comboSubFromShowTraineeDetails.getSelectedItem().toString());

            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String fathers_name = rs.getString("fathers_name");
                String mothers_name = rs.getString("mothers_name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Date dob = rs.getDate("dob");
                String course = rs.getString("course");
                String round = rs.getString("round");
                String center = rs.getString("center");
                model.addRow(new Object[]{id, name, gender, fathers_name, mothers_name, email, address, dob, course, round, center});

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchForAllTraineeDetailsActionPerformed

    private void btnSerarvhTspWiseFromTraineeDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerarvhTspWiseFromTraineeDetailsActionPerformed
        // TODO add your handling code here:
           String columnName[] = {"ID", "Name", "Gender", "Fathers_Name", "Mothers_Name", "Email", "Address", "DOB", "Course", "Round", "Center"};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnName);
        tblDisplayFromShowTrainee.setModel(model);

        try {
            sql = "select * from student_table_1 where round=? and course=? and center=?";
            ps = con.getCon().prepareStatement(sql);
//              ps.setInt(1,Integer.parseInt(txtIdShowFromTraineeDetails.getText()));
            ps.setString(1, comboRoundFromShowTraineeDetails.getSelectedItem().toString());
            ps.setString(2, comboSubFromShowTraineeDetails.getSelectedItem().toString());
            ps.setString(3, comboTspFromShowTraineDatails.getSelectedItem().toString());

            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String fathers_name = rs.getString("fathers_name");
                String mothers_name = rs.getString("mothers_name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Date dob = rs.getDate("dob");
                String course = rs.getString("course");
                String round = rs.getString("round");
                String center = rs.getString("center");
                model.addRow(new Object[]{id, name, gender, fathers_name, mothers_name, email, address, dob, course, round, center});

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSerarvhTspWiseFromTraineeDetailsActionPerformed

    private void txtIdFromAddResultFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFromAddResultFocusLost
        // TODO add your handling code here:

//        sql = "select name, course, round from student_table_1 where id =?";
//        try {
//            ps = con.getCon().prepareStatement(sql);
//            ps.setInt(1, Integer.parseInt(txtIdFromAddResult.getText()));
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                String name = rs.getString("name");
//                String course = rs.getString("course");
//                String round = rs.getString("round");
//                txtNameFromAddResult.setText(name);
//                txtCourseNameFromAddResult.setText(course);
//                txtRoundFromAddResult.setText(round);
//
//            } else {
////                txtNameFromAddResult.setText("No data found for ID " + txtIdFromAddResult);
////                txtCourseNameFromAddResult.setText("");
////                txtRoundFromAddResult.setText("");
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_txtIdFromAddResultFocusLost

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
         String columnName[] = {"ID", "Name","Course", "Round", "Center","Month","Midterm Date","Midterm External","Midterm Evidence","Monthly Date","Monthly External","Monthly Evidence","Status"};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnName);
        tblDisplayFromShowResult1.setModel(model);


    sql = "select * from student_table_1 join result_chart_1 on student_table_1.id=result_chart_1.id where student_table_1.round=? and student_table_1.course=? and student_table_1.center=? ORDER BY monthly_date ASC";
    try {
        ps = con.getCon().prepareStatement(sql);
        ps.setString(1, comboRoundFromShowTraineeDetails1.getSelectedItem().toString());
        ps.setString(2, comboCourseFromShowResult1.getSelectedItem().toString());
        ps.setString(3, comboTspFromShowTraineDatails1.getSelectedItem().toString());

        rs = ps.executeQuery();

        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String month = rs.getString("month");
            
            Date midterm_date = rs.getDate("midterm_date");
            Date monthly_date = rs.getDate("monthly_date");
            
            String midterm_evidence = rs.getString("midterm_evidence");
            String midterm_external = rs.getString("midterm_external");
            
            String monthly_evidence = rs.getString("monthly_evidence");
            String monthly_external = rs.getString("monthly_external");
            
            String course = rs.getString("course");
            String round = rs.getString("round");
            String center = rs.getString("center");
            String status = rs.getString("status");
            

            System.out.println(monthly_date);
            
              model.addRow(new Object[]{id, name, course, round, center, month, midterm_date, midterm_external, midterm_evidence, monthly_date, monthly_external,monthly_evidence,status});
        }
    } catch (SQLException ex) {
        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
    }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnSearchById1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchById1ActionPerformed
        // TODO add your handling code here:
         String columnName[] = {"ID", "Name","Course", "Round", "Center","Month","Midterm Date","Midterm External","Midterm Evidence","Monthly Date","Monthly External","Monthly Evidence","Status"};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnName);
        tblDisplayFromShowResult1.setModel(model);


    sql = "select * from student_table_1 join result_chart_1 on student_table_1.id=result_chart_1.id where student_table_1.id=? ORDER BY monthly_date ASC";
    try {
        ps = con.getCon().prepareStatement(sql);
       ps.setInt(1, Integer.parseInt(txtIdFromShowResultSingle.getText()));
        rs = ps.executeQuery();
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String month = rs.getString("month");
            Date midterm_date = rs.getDate("midterm_date");
            Date monthly_date = rs.getDate("monthly_date");
            String midterm_evidence = rs.getString("midterm_evidence");
            String midterm_external = rs.getString("midterm_external");
            String monthly_evidence = rs.getString("monthly_evidence");
            String c = rs.getString("monthly_external");
            String course = rs.getString("course");
            String round = rs.getString("round");
            String center = rs.getString("center");
            String status = rs.getString("status");
          
            
              model.addRow(new Object[]{id, name, course, round, center, month, midterm_date, midterm_external, midterm_evidence, monthly_date, monthly_evidence,monthly_evidence,status});
        }
    } catch (SQLException ex) {
        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
    }

    }//GEN-LAST:event_btnSearchById1ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        traineeTab.setSelectedIndex(0);
    }//GEN-LAST:event_btnBackActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
           traineeTab.setSelectedIndex(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
             traineeTab.setSelectedIndex(3);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        traineeTab.setSelectedIndex(4);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
          //        resultCalculate();
        int mid1 = Integer.parseInt(txtExternalMidtermResulFromAddResult.getText().trim());
        int mid2 = Integer.parseInt(txtEvidenceMidtermResulFromAddResult.getText().trim());
        int month1 = Integer.parseInt(txtExternaMonthlylResulFromAddResult.getText().trim());
        int month2 = Integer.parseInt(txtEvidenceMonthlyResulFromAddResult.getText().trim());

        double mid = (mid1 + mid2) * (20.0 / 100);

        double month = (month1 + month2) * (80.0 / 100);

        double total = mid + month;
        
           sql = "select * from student_table_1 where id =?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdFromAddResult.getText()));
            rs = ps.executeQuery();

            if (rs.next()) {
                String picture_path = rs.getString("picture_path");
                ImageIcon imageIcon=new ImageIcon(picture_path);
            Image image=imageIcon.getImage().getScaledInstance(resultPhoto.getWidth(), resultPhoto.getHeight(), Image.SCALE_SMOOTH);
            resultPhoto.setIcon(new ImageIcon(image));
                

            } else {
//                txtNameFromAddResult.setText("No data found for ID " + txtIdFromAddResult);
//                txtCourseNameFromAddResult.setText("");
//                txtRoundFromAddResult.setText("");

            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        

        txtTotalMark.setText(String.format("%.0f", total));
        //        resultShow();
        txtIdFromAddResult1.setText(txtIdFromAddResult.getText().trim());
        txtNameFromAddResult2.setText(txtNameFromAddResult.getText().trim());
        txtCourseNameFromAddResult2.setText(txtCourseNameFromAddResult.getText().trim());
        txtCourseNameFromAddResult1.setText(txtRoundFromAddResult.getText().trim());
        txtMonthFromAddResult1.setText(comboMonthFromAddResult1.getSelectedItem().toString());
        txtExternalMidtermResulFromAddResult1.setText(txtExternalMidtermResulFromAddResult.getText().trim());
        txtEvidenceMidtermResulFromAddResult1.setText(txtEvidenceMidtermResulFromAddResult.getText().trim());
        txtExternaMonthlylResulFromAddResult1.setText(txtExternaMonthlylResulFromAddResult.getText().trim());
        txtEvidenceMonthlyResulFromAddResult1.setText(txtEvidenceMonthlyResulFromAddResult.getText().trim());
        txtDateMidterm1.setText(dateConverter(txtDateMidterm.getDate()).toString());
        txtDateMonthly1.setText(dateConverter(txtDateMonthly.getDate()).toString());
//        resultPhoto
        
        
        
        

        String totalMarkText = txtTotalMark.getText().trim();
        if (!totalMarkText.isEmpty()) {
            int p = Integer.parseInt(totalMarkText);
            if (p >= 60) {
                txtPassFail.setText("Pass");
                txtPassFail.setForeground(Color.green);
            } else {
                txtPassFail.setText("Fail"); // Fix the typo: "Faill" to "Fail"
                txtPassFail.setForeground(Color.red);
            }
        } else {
            // Handle the case where txtTotalMark is empty
            JOptionPane.showMessageDialog(rootPane, "Total Mark is empty. Please enter a valid mark.");
        }

        try {
            // Your existing code for database insert
            sql = "insert into result_chart_1 (id,name,course,round,month,midterm_date,monthly_date,midterm_evidence,midterm_external,monthly_evidence,monthly_external,total_marks,status) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdFromAddResult.getText()));
            ps.setString(2, txtNameFromAddResult.getText().trim());
            ps.setString(3, txtCourseNameFromAddResult.getText().trim());
            ps.setString(4, txtRoundFromAddResult.getText().trim());
            
            ps.setString(5, comboMonthFromAddResult1.getSelectedItem().toString());

            ps.setDate(6, dateConverter(txtDateMidterm.getDate()));
            ps.setDate(7, dateConverter(txtDateMonthly.getDate()));
            ps.setString(8, txtExternalMidtermResulFromAddResult.getText().trim());
            ps.setString(9, txtEvidenceMidtermResulFromAddResult.getText().trim());

            ps.setString(10, txtExternaMonthlylResulFromAddResult.getText().trim());
            ps.setString(11, txtEvidenceMonthlyResulFromAddResult.getText().trim());
            ps.setString(12, txtTotalMark.getText().trim());
            ps.setString(13, txtPassFail.getText().trim());
            ps.executeUpdate();
            ps.close();
            con.getCon().close();
            JOptionPane.showMessageDialog(rootPane, "Result Submitted");

            if (!totalMarkText.isEmpty()) {
                int p = Integer.parseInt(totalMarkText);
                if (p >= 60) {
                    txtPassFail.setText("Pass");
                    txtPassFail.setForeground(Color.green);
                } else {
                    txtPassFail.setText("Fail"); // Fix the typo: "Faill" to "Fail"
                    txtPassFail.setForeground(Color.red);
                }
            } else {
                // Handle the case where txtTotalMark is empty
                JOptionPane.showMessageDialog(rootPane, "Total Mark is empty. Please enter a valid mark.");
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            // Handle the unique constraint violation
            JOptionPane.showMessageDialog(rootPane, "Duplicate entry. Please make sure the entry doesn't already exist.");
        } catch (java.lang.NumberFormatException ex) {
            // Handle the case where parsing txtTotalMark to an integer fails
            JOptionPane.showMessageDialog(rootPane, "Invalid Total Mark. Please enter a valid integer.");
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        // TODO add your handling code here:re
        resetResult();
        txtIdFromAddResult1.setText(null);
        txtCourseNameFromAddResult2.setText(null);
        txtCourseNameFromAddResult1.setText(null);
        txtMonthFromAddResult1.setText(null);
        comboMonthFromAddResult1.setSelectedItem(null);
//        txtDateMidterm1.setDate(null);
        txtDateMonthly.setDate(null);
        txtDateMidterm1.setText(null);
        txtExternalMidtermResulFromAddResult1.setText(null);
        txtEvidenceMidtermResulFromAddResult1.setText(null);

        txtExternaMonthlylResulFromAddResult1.setText(null);
        txtEvidenceMonthlyResulFromAddResult1.setText(null);
        txtNameFromAddResult2.setText(null);
        
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void btnSaveFromAddTrainee1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveFromAddTrainee1ActionPerformed
        // TODO add your handling code here:
         sql = "insert into stuff_table_1(id,name,gender,designation,specialist,email,qualification,address,phone_number,picture_path) values (?,?,?,?,?,?,?,?,?,?)";
        String gender = "";
        if (radioBtnMaleFromAddStuff.isSelected()) {
            gender = "Male";
        } else if (radioBtnFemaleFromAddStuff.isSelected()) {
            gender = "Female";
        }
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdFromAddStuff.getText().trim()));
            ps.setString(2, txtNameFromAddStuff.getText().trim());
            ps.setString(3, gender);
            ps.setString(4, comboDesignationFromAddStuff.getSelectedItem().toString());
            ps.setString(5, comboSpecialistFromAddStuff.getSelectedItem().toString());
            
            ps.setString(6, txtEmaiFromAddStuff.getText().trim());
            ps.setString(7, comboQualificationFromAddStuff.getSelectedItem().toString());
            
            
            ps.setString(8, txtAddressFromAddStuff.getText().trim());
            ps.setString(9, txtPhoneFromAddStuff.getText().trim());
          
            ps.setString(10, selectedImagePath);

            ps.executeUpdate();
            ps.close();
            con.getCon().close();
            JOptionPane.showMessageDialog(rootPane, "Data Saved");
            getAllDataStuffTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Data Not Saved");
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_btnSaveFromAddTrainee1ActionPerformed

    private void btnUpdateFromAddTrainee1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateFromAddTrainee1ActionPerformed
        // TODO add your handling code here:
         sql = "update stuff_table_1 set name=?,gender=?,designation=?,specialist=?,email=?,qualification=?,address=?,phone_number,picture_path=? where id=?";
        String gender = "";
        if (radioBtnMaleFromAddStuff.isSelected()) {
            gender = "Male";
        } else if (radioBtnFemaleFromAddStuff.isSelected()) {
            gender = "Female";
        }
        try {
            ps = con.getCon().prepareStatement(sql);
          
            ps.setString(1, txtNameFromAddStuff.getText().trim());
            ps.setString(2, gender);
            ps.setString(3, comboDesignationFromAddStuff.getSelectedItem().toString());
            ps.setString(4, comboSpecialistFromAddStuff.getSelectedItem().toString());
            
            ps.setString(5, txtEmaiFromAddStuff.getText().trim());
            ps.setString(6, comboQualificationFromAddStuff.getSelectedItem().toString());
            
            
            ps.setString(7, txtAddressFromAddStuff.getText().trim());
            ps.setString(8, txtPhoneFromAddStuff.getText().trim());
          
            ps.setString(9, selectedImagePath);
            ps.setInt(10, Integer.parseInt(txtIdFromAddStuff.getText().trim()));
            ps.executeUpdate();
            ps.close();
            con.getCon().close();
            JOptionPane.showMessageDialog(rootPane, "Data Saved");
            getAllDataStuffTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Data Not Saved");
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_btnUpdateFromAddTrainee1ActionPerformed

    private void btnResetFromAddTrainee1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFromAddTrainee1ActionPerformed
        // TODO add your handling code here:
        txtIdFromAddStuff.setText(null);
        txtNameFromAddStuff.setText(null);
          buttonGroup1.clearSelection();
        
         comboDesignationFromAddStuff.setSelectedIndex(0);
        comboSpecialistFromAddStuff.setSelectedIndex(0);
        txtEmaiFromAddStuff.setText(null);
        txtAddressFromAddStuff.setText(null);
        txtPhoneFromAddStuff.setText(null);
        comboQualificationFromAddStuff.setSelectedIndex(0);
    }//GEN-LAST:event_btnResetFromAddTrainee1ActionPerformed

    private void btnTraineDetails1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraineDetails1ActionPerformed
        // TODO add your handling code here:
        stuffTab.setSelectedIndex(1);
    }//GEN-LAST:event_btnTraineDetails1ActionPerformed

    private void btnSerarvhTspWiseFromTraineeDetails1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerarvhTspWiseFromTraineeDetails1ActionPerformed
        // TODO add your handling code here:
        String columnName[] = {"ID", "Name", "Gender", "Specialist", "Email", "Qualification", "Address"};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnName);
        tblDisplayFromShowStuff.setModel(model);
        
       
         try {
              sql="select * from stuff_table_1 where designation=?";
             ps=con.getCon().prepareStatement(sql);
             
             ps.setString(1, comboDesignationFromAddStuff1.getSelectedItem().toString());
             rs=ps.executeQuery();
             
             while(rs.next()){
             int id=rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
//                String designation = rs.getString("designation");
                String specialist = rs.getString("specialist");
                String email = rs.getString("email");
     
                String qualification = rs.getString("qualification");
                String address = rs.getString("address");
             
                model.addRow(new Object[]{id, name, gender, specialist, email, qualification, address});

             }
   
            
         } catch (SQLException ex) {
             Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
              ex.printStackTrace();
         }
        
        
        
        
    }//GEN-LAST:event_btnSerarvhTspWiseFromTraineeDetails1ActionPerformed

    private void btnSearchById2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchById2ActionPerformed
        // TODO add your handling code here:
        stuffTab.setSelectedIndex(3);
    }//GEN-LAST:event_btnSearchById2ActionPerformed

    private void btnBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2ActionPerformed
        // TODO add your handling code here:
       stuffTab.setSelectedIndex(0);
    }//GEN-LAST:event_btnBack2ActionPerformed

    private void btnSearchTraineeDetails2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchTraineeDetails2ActionPerformed
        // TODO add your handling code here:
         try {
            sql = "select * from stuff_table_1 where id=?";
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdShowFromStuff.getText()));

            rs = ps.executeQuery();

            while (rs.next()) {
                int id=rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String designation = rs.getString("designation");
                String specialist = rs.getString("specialist");
                String email = rs.getString("email");
                String qualification = rs.getString("qualification");
                String address = rs.getString("address");
                String picture_path = rs.getString("picture_path");
                
                
                 txtIdStuffShowData.setText(Integer.toString(id));
                txtNameStuffShowData.setText(name);
                txtEmailStuffShowData.setText(email);
                txtGenderStuffShowData.setText(gender);
                txtDesignationStuffShowData.setText(designation);
                txtQualificationStuffShowData.setText(qualification);
                txtAddressStuffShowData.setText(address);
                txtSpecialistStuffShowData.setText(specialist);
                txtTraineeNameFromIdCard.setText(name);
                ImageIcon imageIcon2=new ImageIcon(picture_path);
            Image image2=imageIcon2.getImage().getScaledInstance(stuffDetailsS.getWidth(), stuffDetailsS.getHeight(), Image.SCALE_SMOOTH);
            stuffDetailsS.setIcon(new ImageIcon(image2));
                
                
                
                //for transfer data id card
                txtStuffdFromIdCard.setText(Integer.toString(id));
 txtStuffNameFromIdCard.setText(name);
 txtDesignationFromIdCard.setText(designation);
 
 
  ImageIcon imageIcon=new ImageIcon(picture_path);
            Image image=imageIcon.getImage().getScaledInstance(stuffDetailsSingle.getWidth(), stuffDetailsSingle.getHeight(), Image.SCALE_SMOOTH);
            stuffDetailsSingle.setIcon(new ImageIcon(image));
 
 
 
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }//GEN-LAST:event_btnSearchTraineeDetails2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
               PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("print Data");

        job.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                pf.setOrientation(PageFormat.LANDSCAPE);
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.47, 0.47);
                idCardPrintForStuff.print(g2);
                return Printable.PAGE_EXISTS;
            }

        });

        boolean ok = job.printDialog();
        if (ok) {

            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
        
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnBack3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack3ActionPerformed
        // TODO add your handling code here:
        stuffTab.setSelectedIndex(1);
    }//GEN-LAST:event_btnBack3ActionPerformed

    private void tblDisplayFromAddStuffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDisplayFromAddStuffMouseClicked
        // TODO add your handling code here:
         int row = tblDisplayFromAddStuff.getSelectedRow();

        String id = tblDisplayFromAddStuff.getModel().getValueAt(row, 0).toString();
        String name = tblDisplayFromAddStuff.getModel().getValueAt(row, 1).toString();
        String gender = tblDisplayFromAddStuff.getModel().getValueAt(row, 2).toString();
        String designation = tblDisplayFromAddStuff.getModel().getValueAt(row, 3).toString();
        String specialist = tblDisplayFromAddStuff.getModel().getValueAt(row, 4).toString();
        String email = tblDisplayFromAddStuff.getModel().getValueAt(row, 5).toString();
        String qualification = tblDisplayFromAddStuff.getModel().getValueAt(row, 6).toString();
        String address = tblDisplayFromAddStuff.getModel().getValueAt(row, 7).toString();
        String phone_number = tblDisplayFromAddStuff.getModel().getValueAt(row, 8).toString();
//        String picture_path = tblDisplayFromAddStuff.getModel().getValueAt(row, 8).toString();
       

        txtIdFromAddStuff.setText(id);
        txtNameFromAddStuff.setText(name);
        if (gender.equalsIgnoreCase("Male")) {
            radioBtnMaleFromAddStuff.setSelected(rootPaneCheckingEnabled);
        } else if (gender.equalsIgnoreCase("Female")) {
            radioBtnFemaleFromAddStuff.setSelected(rootPaneCheckingEnabled);
        }
        
        comboDesignationFromAddStuff.setSelectedItem(designation);
        comboSpecialistFromAddStuff.setSelectedItem(specialist);
        comboQualificationFromAddStuff.setSelectedItem(qualification);
        txtAddressFromAddStuff.setText(address);
        txtEmaiFromAddStuff.setText(email);
        txtPhoneFromAddStuff.setText(phone_number);
        
  

  
        
    }//GEN-LAST:event_tblDisplayFromAddStuffMouseClicked

    private void btnSerarvhTspWiseFromTraineeDetails2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerarvhTspWiseFromTraineeDetails2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSerarvhTspWiseFromTraineeDetails2ActionPerformed

    private void btnSearchById3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchById3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchById3ActionPerformed

    private void btnBack4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBack4ActionPerformed

    private void txtIdFromAddStuff3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFromAddStuff3FocusGained
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_txtIdFromAddStuff3FocusGained

    private void radioBtnBkashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioBtnBkashMouseClicked
        // TODO add your handling code here:
        getMobileBankingNumber();
    }//GEN-LAST:event_radioBtnBkashMouseClicked

    private void radioBtnNogodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioBtnNogodMouseClicked
        // TODO add your handling code here:
         getMobileBankingNumber();
    }//GEN-LAST:event_radioBtnNogodMouseClicked

    private void radioBtnOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioBtnOMouseClicked
        // TODO add your handling code here:
         getMobileBankingNumber();
    }//GEN-LAST:event_radioBtnOMouseClicked

    private void txtPhoneFromAddStuffPayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPhoneFromAddStuffPayFocusLost
     // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneFromAddStuffPayFocusLost

    private void txtNameFromAddStuffPayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFromAddStuffPayFocusGained
        // TODO add your handling code here:
          sql = "select * from stuff_table_1 where id =?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdFromAddStuff3.getText()));
            rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String phone_number = rs.getString("phone_number");
               
                txtNameFromAddStuffPay.setText(name);
//                txtCourseNameFromAddResult.setText(course);
              

            } else {
//                txtNameFromAddResult.setText("No data found for ID " + txtIdFromAddResult);
//                txtCourseNameFromAddResult.setText("");
//                txtRoundFromAddResult.setText("");

            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_txtNameFromAddStuffPayFocusGained

    private void btnPayBankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayBankActionPerformed
        // TODO add your handling code here:
           sql = "insert into payment_table_1(id,name,month,Payment_method,acount_name,acount_number,amount) values (?,?,?,?,?,?,?)";
        String paymentMethod = "";
        if (radioBtnBkash.isSelected()) {
            paymentMethod = "Bkash";
        } 
        else if (radioBtnNogod.isSelected()) {
            paymentMethod = "Nogod";
        }
        else if(radioBtnNogod.isSelected()){
        paymentMethod = "Others";
        
        }
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdFromAddStuff3.getText().trim()));
            ps.setString(2, txtNameFromAddStuffPay.getText().trim());
              ps.setString(3, comboMonthPay.getSelectedItem().toString());
               ps.setString(4, paymentMethod);
               ps.setString(5, txtNameFromAddStuffPay.getText().trim());
            
               ps.setString(6, txtPhoneFromAddStuffPay.getText().trim());
               ps.setString(7, txtAmountToPay.getText().trim());
 
            ps.executeUpdate();
            ps.close();
            con.getCon().close();
            JOptionPane.showMessageDialog(rootPane, "Payment Complete");
            getAllData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Something is Wrong");
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_btnPayBankActionPerformed

    private void txtIdFromAddStuff4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFromAddStuff4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdFromAddStuff4FocusGained

    private void txtPhoneFromAddStuffPay1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPhoneFromAddStuffPay1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneFromAddStuffPay1FocusLost

    private void txtNameFromAddStuffPay1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFromAddStuffPay1FocusGained
        // TODO add your handling code here:
            sql = "select * from stuff_table_1 where id =?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdFromAddStuff4.getText()));
            rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String phone_number = rs.getString("phone_number");
               
                txtNameFromAddStuffPay1.setText(name);
//                txtCourseNameFromAddResult.setText(course);
              

            } else {
//                txtNameFromAddResult.setText("No data found for ID " + txtIdFromAddResult);
//                txtCourseNameFromAddResult.setText("");
//                txtRoundFromAddResult.setText("");

            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_txtNameFromAddStuffPay1FocusGained

    private void btnPayBank1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayBank1ActionPerformed
        // TODO add your handling code here:
         sql = "insert into payment_table_1(id,name,month,Payment_method,acount_name,acount_number,amount) values (?,?,?,?,?,?,?)";
        String paymentMethod = "";
        if (radioBtnIdbBank.isSelected()) {
            paymentMethod = "IsDB";
        } 
        else if (radioBtnDBL.isSelected()) {
            paymentMethod = "Duch Bangla Bank";
        }
        else if(radioBtnO2.isSelected()){
        paymentMethod = "Others";
        
        }
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdFromAddStuff4.getText().trim()));
            ps.setString(2, txtNameFromAddStuffPay1.getText().trim());
              ps.setString(3, comboMonthPay1.getSelectedItem().toString());
               ps.setString(4, paymentMethod);
               ps.setString(5, txtNameFromAddStuffPay1.getText().trim());
            
               ps.setString(6, txtPhoneFromAddStuffPay1.getText().trim());
               ps.setString(7, txtAmountToPay1.getText().trim());
 
            ps.executeUpdate();
            ps.close();
            con.getCon().close();
            JOptionPane.showMessageDialog(rootPane, "Data Saved");
            getAllData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Data Not Saved");
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPayBank1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
         try {
            sql = "select * from student_table_1 where id=?";
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdFromCareerHub.getText()));

            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String fathers_name = rs.getString("fathers_name");
                String mothers_name = rs.getString("mothers_name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Date dob = rs.getDate("dob");
                String course = rs.getString("course");
                String round = rs.getString("round");
                String center = rs.getString("center");
                String batch_id = rs.getString("batch_id");
                String picture_path = rs.getString("picture_path");

                
//                txtGender2.setText(gender);
//                txtEmail2.setText(email);
//                txtFathersName2.setText(fathers_name);
//                txtMothersName5.setText(mothers_name);
//
//                txtAddress2.setText(address);
//
//
//                txtTsp2.setText(center);
//
//
//
//               
//
//                txtBatchIdFromIdCard.setText(batch_id);
//                txtRoundFromIdCard.setText(round);
//                txtTraineeIdFromIdCard.setText(Integer.toString(id));
//                txtTraineeNameFromIdCard.setText(name);
                
                cvNameTrainee.setText(name);
                cvEmail.setText(email);
                cvAddress.setText(address);
                 ImageIcon imageIcon=new ImageIcon(picture_path);
            Image image=imageIcon.getImage().getScaledInstance(cvPhotoTrainee.getWidth(), cvPhotoTrainee.getHeight(), Image.SCALE_SMOOTH);
            cvPhotoTrainee.setIcon(new ImageIcon(image));

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
        
        
        
        
        
           PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("print Data");

        job.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                pf.setOrientation(PageFormat.LANDSCAPE);
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.47, 0.47);
                cvPanel.print(g2);
                return Printable.PAGE_EXISTS;
            }

        });

        boolean ok = job.printDialog();
        if (ok) {

            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtNameFromAddResultFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFromAddResultFocusGained
        // TODO add your handling code here:
          sql = "select name, course, round,picture_path from student_table_1 where id =?";
        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdFromAddResult.getText()));
            rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String course = rs.getString("course");
                String round = rs.getString("round");
                txtNameFromAddResult.setText(name);
                txtCourseNameFromAddResult.setText(course);
                txtRoundFromAddResult.setText(round);

            } else {
//                txtNameFromAddResult.setText("No data found for ID " + txtIdFromAddResult);
//                txtCourseNameFromAddResult.setText("");
//                txtRoundFromAddResult.setText("");

            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtNameFromAddResultFocusGained

    private void btnAttachPhotoFromAddTrainee1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachPhotoFromAddTrainee1ActionPerformed
        // TODO add your handling code here:
        attachPhoto();
        ImageIcon li = new ImageIcon(selectedImagePath);
        Image image = li.getImage().getScaledInstance(photoAddFromAddTrainee.getWidth(), photoAddFromAddTrainee.getHeight(), Image.SCALE_SMOOTH);
        photoAddFromAddStuff.setIcon(new ImageIcon(image));
    }//GEN-LAST:event_btnAttachPhotoFromAddTrainee1ActionPerformed

    private void btnSearchTraineeDetails3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchTraineeDetails3ActionPerformed
        // TODO add your handling code here:
       
        // TODO add your handling code here:
         try {
            sql = "select * from student_table_1 where id=?";
            ps = con.getCon().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdShowFromTraineeDetails.getText()));

            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String fathers_name = rs.getString("fathers_name");
                String mothers_name = rs.getString("mothers_name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Date dob = rs.getDate("dob");
                String course = rs.getString("course");
                String round = rs.getString("round");
                String center = rs.getString("center");
                String batch_id = rs.getString("batch_id");
                String picture_path = rs.getString("picture_path");
                
                txtName.setText(name);
                txtGender2.setText(gender);
                txtEmail2.setText(email);
                txtAddress2.setText(address);
                txtFathersName2.setText(fathers_name);
                txtMothersName5.setText(mothers_name);
                txtCourse2.setText(course);
                txtTsp2.setText(center);
                txtRound2.setText(round);
                txtTraineeId2.setText(Integer.toString(id));
                 ImageIcon imageIcon=new ImageIcon(picture_path);
            Image image=imageIcon.getImage().getScaledInstance(stuffDetailsS1.getWidth(), stuffDetailsS1.getHeight(), Image.SCALE_SMOOTH);
            stuffDetailsS1.setIcon(new ImageIcon(image));
            
            
            cvAddress.setText(address);
            cvEmail.setText(email);
            cvNameTrainee.setText(name);
            cvPhotoTrainee.setIcon(new ImageIcon(image));
            

            txtTraineeIdFromIdCard.setText(Integer.toString(id));
            txtTraineeNameFromIdCard.setText(name);
            txtBatchIdFromIdCard.setText(batch_id);
            txtRoundFromIdCard.setText(round);
                 ImageIcon imageIcon2=new ImageIcon(picture_path);
            Image image2=imageIcon2.getImage().getScaledInstance(traineDetailsS.getWidth(), traineDetailsS.getHeight(), Image.SCALE_SMOOTH);
            traineDetailsS.setIcon(new ImageIcon(image2));
              
                 
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnSearchTraineeDetails3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
            PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("print Data");

        job.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                pf.setOrientation(PageFormat.LANDSCAPE);
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.47, 0.47);
                idCardPrint.print(g2);
                return Printable.PAGE_EXISTS;
            }

        });

        boolean ok = job.printDialog();
        if (ok) {

            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void comboMonthFromAddResult1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMonthFromAddResult1ActionPerformed
        // TODO add your handling code here:
        sql = "SELECT * FROM result_chart_1 WHERE id = ? AND month = ?";
         try {
             ps = con.getCon().prepareStatement(sql);
ps.setInt(1, Integer.parseInt(txtIdFromAddResult.getText()));
ps.setString(2, comboMonthFromAddResult1.getSelectedItem().toString());
rs = ps.executeQuery();

if (rs.next()) {
    // A record with the same student ID and month already exists in the database.
    // You can handle this situation, e.g., by updating the existing record, showing an error message, or taking any other desired action.
    JOptionPane.showMessageDialog(rootPane, "Result for this student in the selected month already exists.");
} else {
    // No record with the same student ID and month exists, so you can proceed with the insertion.
    // Your existing code for inserting the result goes here.
}
         } catch (SQLException ex) {
             Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_comboMonthFromAddResult1ActionPerformed

    private void comboMonthPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMonthPayActionPerformed
        // TODO add your handling code here:
             sql = "SELECT * FROM payment_table_1 WHERE id = ? AND month = ?";
         try {
             ps = con.getCon().prepareStatement(sql);
ps.setInt(1, Integer.parseInt(txtIdFromAddStuff3.getText()));
ps.setString(2, comboMonthPay.getSelectedItem().toString());
rs = ps.executeQuery();

if (rs.next()) {
    // A record with the same student ID and month already exists in the database.
    // You can handle this situation, e.g., by updating the existing record, showing an error message, or taking any other desired action.
    JOptionPane.showMessageDialog(rootPane, "Payment is already done");
} else {
    // No record with the same student ID and month exists, so you can proceed with the insertion.
    // Your existing code for inserting the result goes here.
}
         } catch (SQLException ex) {
             Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_comboMonthPayActionPerformed

    private void comboMonthPay1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMonthPay1ActionPerformed
        // TODO add your handling code here:
            sql = "SELECT * FROM payment_table_1 WHERE id = ? AND month = ?";
         try {
             ps = con.getCon().prepareStatement(sql);
ps.setInt(1, Integer.parseInt(txtIdFromAddStuff4.getText()));
ps.setString(2, comboMonthPay1.getSelectedItem().toString());
rs = ps.executeQuery();

if (rs.next()) {
    // A record with the same student ID and month already exists in the database.
    // You can handle this situation, e.g., by updating the existing record, showing an error message, or taking any other desired action.
    JOptionPane.showMessageDialog(rootPane, "Payment is already done");
} else {
    // No record with the same student ID and month exists, so you can proceed with the insertion.
    // Your existing code for inserting the result goes here.
}
         } catch (SQLException ex) {
             Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_comboMonthPay1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         traineeTab.setSelectedIndex(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnResetFromAddTrainee2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFromAddTrainee2ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnResetFromAddTrainee2ActionPerformed

    private void btnDeleteStuffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteStuffActionPerformed
        // TODO add your handling code here:
         String name = txtNameFromAddStuff.getText().trim();
        sql = "delete from stuff_table_1 where name=?";

        try {
            ps = con.getCon().prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();
            ps.close();
            con.getCon().close();
            JOptionPane.showMessageDialog(rootPane, "Data deleted");
            reset();
            getAllData();
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }//GEN-LAST:event_btnDeleteStuffActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addPayment;
    private javax.swing.JPanel addResult;
    private javax.swing.JPanel addStuff;
    private javax.swing.JPanel addTrainee;
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnAddTrainee;
    private javax.swing.JButton btnAttachPhotoFromAddTrainee;
    private javax.swing.JButton btnAttachPhotoFromAddTrainee1;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBack2;
    private javax.swing.JButton btnBack3;
    private javax.swing.JButton btnBack4;
    private javax.swing.JButton btnCareerHub;
    private javax.swing.JButton btnDeleteFromAddTrainee;
    private javax.swing.JButton btnDeleteStuff;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnPayBank;
    private javax.swing.JButton btnPayBank1;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton btnPrint2;
    private javax.swing.JButton btnPrint3;
    private javax.swing.JButton btnPrint4;
    private javax.swing.JButton btnResetFromAddTrainee1;
    private javax.swing.JButton btnResetFromAddTrainee2;
    private javax.swing.JButton btnSaveFromAddTrainee;
    private javax.swing.JButton btnSaveFromAddTrainee1;
    private javax.swing.JButton btnSearchById;
    private javax.swing.JButton btnSearchById1;
    private javax.swing.JButton btnSearchById2;
    private javax.swing.JButton btnSearchById3;
    private javax.swing.JButton btnSearchForAllTraineeDetails;
    private javax.swing.JButton btnSearchTraineeDetails2;
    private javax.swing.JButton btnSearchTraineeDetails3;
    private javax.swing.JButton btnSerarvhTspWiseFromTraineeDetails;
    private javax.swing.JButton btnSerarvhTspWiseFromTraineeDetails1;
    private javax.swing.JButton btnSerarvhTspWiseFromTraineeDetails2;
    private javax.swing.JButton btnStuff;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnTraineDetails;
    private javax.swing.JButton btnTraineDetails1;
    private javax.swing.JButton btnTrainee;
    private javax.swing.JButton btnUpdateFromAddTrainee;
    private javax.swing.JButton btnUpdateFromAddTrainee1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel cPassParcentageLabelHome;
    private javax.swing.JLabel cPassStudentCount;
    private javax.swing.JLabel cTotalStudentCount;
    private javax.swing.JTabbedPane careeerHubTab;
    private javax.swing.JPanel careerHome;
    private javax.swing.JPanel careerHubHomePage;
    private javax.swing.JPanel checkPayment;
    private javax.swing.JComboBox<String> comboCourseFromShowResult1;
    private javax.swing.JComboBox<String> comboDesignationFromAddStuff;
    private javax.swing.JComboBox<String> comboDesignationFromAddStuff1;
    private javax.swing.JComboBox<String> comboDesignationFromAddStuff2;
    private javax.swing.JComboBox<String> comboMonthFromAddResult1;
    private javax.swing.JComboBox<String> comboMonthPay;
    private javax.swing.JComboBox<String> comboMonthPay1;
    private javax.swing.JComboBox<String> comboQualificationFromAddStuff;
    private javax.swing.JComboBox<String> comboRoundFromAddTrainee;
    private javax.swing.JComboBox<String> comboRoundFromShowTraineeDetails;
    private javax.swing.JComboBox<String> comboRoundFromShowTraineeDetails1;
    private javax.swing.JComboBox<String> comboSpecialistFromAddStuff;
    private javax.swing.JComboBox<String> comboSubFromShowTraineeDetails;
    private javax.swing.JComboBox<String> comboSubjectFromAddTrainee;
    private javax.swing.JComboBox<String> comboTspFromAddTrainee;
    private javax.swing.JComboBox<String> comboTspFromShowTraineDatails;
    private javax.swing.JComboBox<String> comboTspFromShowTraineDatails1;
    private javax.swing.JLabel countStudent;
    private javax.swing.JLabel cvAddress;
    private javax.swing.JLabel cvEmail;
    private javax.swing.JTextField cvNameTrainee;
    private javax.swing.JTextField cvNameTrainee1;
    private javax.swing.JPanel cvPanel;
    private javax.swing.JPanel cvPanel1;
    private javax.swing.JLabel cvPhotoTrainee;
    private javax.swing.JPanel cvTab;
    private com.toedter.calendar.JDateChooser dateDobFromAddTrainee;
    private javax.swing.JLabel dateShow;
    private javax.swing.JLabel dbPassParcentageLabelHome;
    private javax.swing.JLabel dbPassStudentCount;
    private javax.swing.JLabel dbTotalStudentCount;
    private javax.swing.JLabel graphicsPassParcentageLabelHome;
    private javax.swing.JLabel graphicsPassStudentCount;
    private javax.swing.JLabel graphicsTotalStudentCount;
    private javax.swing.JTabbedPane homeTab;
    private javax.swing.JPanel idCardPrint;
    private javax.swing.JPanel idCardPrintForStuff;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel227;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel231;
    private javax.swing.JLabel jLabel232;
    private javax.swing.JLabel jLabel233;
    private javax.swing.JLabel jLabel234;
    private javax.swing.JLabel jLabel235;
    private javax.swing.JLabel jLabel236;
    private javax.swing.JLabel jLabel237;
    private javax.swing.JLabel jLabel238;
    private javax.swing.JLabel jLabel239;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel240;
    private javax.swing.JLabel jLabel241;
    private javax.swing.JLabel jLabel242;
    private javax.swing.JLabel jLabel243;
    private javax.swing.JLabel jLabel244;
    private javax.swing.JLabel jLabel245;
    private javax.swing.JLabel jLabel246;
    private javax.swing.JLabel jLabel247;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JLabel jeePassParcentageLabelHome;
    private javax.swing.JLabel jeePassStudentCount;
    private javax.swing.JLabel jeeTotalStudentCount;
    private javax.swing.JTabbedPane mainTabMother;
    private javax.swing.JLabel ntPassParcentageLabelHome;
    private javax.swing.JLabel ntPassStudentCount;
    private javax.swing.JLabel ntTotalStudentCount;
    private javax.swing.JPanel panelPieChart;
    private javax.swing.JPanel panelSChart;
    private javax.swing.JTabbedPane paymentTab;
    private javax.swing.JLabel photoAddFromAddStuff;
    private javax.swing.JLabel photoAddFromAddTrainee;
    private javax.swing.JRadioButton radioBtnBkash;
    private javax.swing.JRadioButton radioBtnDBL;
    private javax.swing.JRadioButton radioBtnFemaleFromAddStuff;
    private javax.swing.JRadioButton radioBtnFemaleFromAddTrainee;
    private javax.swing.JRadioButton radioBtnIdbBank;
    private javax.swing.JRadioButton radioBtnMaleFromAddStuff;
    private javax.swing.JRadioButton radioBtnMaleFromAddTrainee;
    private javax.swing.JRadioButton radioBtnNogod;
    private javax.swing.JRadioButton radioBtnO;
    private javax.swing.JRadioButton radioBtnO2;
    private javax.swing.JPanel raf;
    private javax.swing.JLabel resultPhoto;
    private javax.swing.JPanel searchResult;
    private javax.swing.JPanel searchShowDetailsS;
    private javax.swing.JPanel searchShowDetailsS1;
    private javax.swing.JPanel searchStuff;
    private javax.swing.JPanel searchTrainee;
    private javax.swing.JPanel showIdCard;
    private javax.swing.JPanel showIdCard1;
    private javax.swing.JLabel stuffDetailsS;
    private javax.swing.JLabel stuffDetailsS1;
    private javax.swing.JLabel stuffDetailsSingle;
    private javax.swing.JTabbedPane stuffTab;
    private javax.swing.JTable tblDisplayFromAddStuff;
    private javax.swing.JTable tblDisplayFromAddTrainee;
    private javax.swing.JTable tblDisplayFromShowResult1;
    private javax.swing.JTable tblDisplayFromShowStuff;
    private javax.swing.JTable tblDisplayFromShowStuff1;
    private javax.swing.JTable tblDisplayFromShowTrainee;
    private javax.swing.JLabel timeShow;
    private javax.swing.JPanel totalTrainee;
    private javax.swing.JLabel traineDetailsS;
    private javax.swing.JTabbedPane traineeTab;
    private javax.swing.JTextField txtAddress2;
    private javax.swing.JTextField txtAddressFromAddStuff;
    private javax.swing.JTextField txtAddressFromAddTrainee;
    private javax.swing.JTextField txtAddressStuffShowData;
    private javax.swing.JTextField txtAmountToPay;
    private javax.swing.JTextField txtAmountToPay1;
    private javax.swing.JTextField txtBatchIdFromAddTrainee;
    private javax.swing.JTextField txtBatchIdFromIdCard;
    private javax.swing.JTextField txtCourse2;
    private javax.swing.JTextField txtCourse3;
    private javax.swing.JTextField txtCourseNameFromAddResult;
    private javax.swing.JTextField txtCourseNameFromAddResult1;
    private javax.swing.JTextField txtCourseNameFromAddResult2;
    private com.toedter.calendar.JDateChooser txtDateMidterm;
    private javax.swing.JTextField txtDateMidterm1;
    private com.toedter.calendar.JDateChooser txtDateMonthly;
    private javax.swing.JTextField txtDateMonthly1;
    private javax.swing.JTextField txtDesignationFromIdCard;
    private javax.swing.JTextField txtDesignationStuffShowData;
    private javax.swing.JTextField txtEmaiFromAddStuff;
    private javax.swing.JTextField txtEmail2;
    private javax.swing.JTextField txtEmailFromAddTrainee;
    private javax.swing.JTextField txtEmailStuffShowData;
    private javax.swing.JTextField txtEvidenceMidtermResulFromAddResult;
    private javax.swing.JTextField txtEvidenceMidtermResulFromAddResult1;
    private javax.swing.JTextField txtEvidenceMonthlyResulFromAddResult;
    private javax.swing.JTextField txtEvidenceMonthlyResulFromAddResult1;
    private javax.swing.JTextField txtExternaMonthlylResulFromAddResult;
    private javax.swing.JTextField txtExternaMonthlylResulFromAddResult1;
    private javax.swing.JTextField txtExternalMidtermResulFromAddResult;
    private javax.swing.JTextField txtExternalMidtermResulFromAddResult1;
    private javax.swing.JTextField txtFathersName2;
    private javax.swing.JTextField txtFathersNameFromAddTrainee;
    private javax.swing.JTextField txtGender2;
    private javax.swing.JTextField txtGenderStuffShowData;
    private javax.swing.JTextField txtIdFromAddResult;
    private javax.swing.JTextField txtIdFromAddResult1;
    private javax.swing.JTextField txtIdFromAddStuff;
    private javax.swing.JTextField txtIdFromAddStuff3;
    private javax.swing.JTextField txtIdFromAddStuff4;
    private javax.swing.JTextField txtIdFromAddTrainee;
    private javax.swing.JTextField txtIdFromCareerHub;
    private javax.swing.JTextField txtIdFromShowResultSingle;
    private javax.swing.JTextField txtIdShowFromStuff;
    private javax.swing.JTextField txtIdShowFromTraineeDetails;
    private javax.swing.JTextField txtIdStuffShowData;
    private javax.swing.JTextField txtMonthFromAddResult1;
    private javax.swing.JTextField txtMothersName5;
    private javax.swing.JTextField txtMothersNameFromAddTrainee;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtName3;
    private javax.swing.JLabel txtName4;
    private javax.swing.JTextField txtNameFromAddResult;
    private javax.swing.JTextField txtNameFromAddResult2;
    private javax.swing.JTextField txtNameFromAddStuff;
    private javax.swing.JTextField txtNameFromAddStuffPay;
    private javax.swing.JTextField txtNameFromAddStuffPay1;
    private javax.swing.JTextField txtNameFromAddTrainee;
    private javax.swing.JLabel txtNameStuffShowData;
    private javax.swing.JLabel txtPassFail;
    private javax.swing.JTextField txtPhoneFromAddStuff;
    private javax.swing.JTextField txtPhoneFromAddStuffPay;
    private javax.swing.JTextField txtPhoneFromAddStuffPay1;
    private javax.swing.JTextField txtQualificationStuffShowData;
    private javax.swing.JTextField txtRound2;
    private javax.swing.JTextField txtRoundFromAddResult;
    private javax.swing.JTextField txtRoundFromIdCard;
    private javax.swing.JTextField txtSpecialistStuffShowData;
    private javax.swing.JTextField txtStuffNameFromIdCard;
    private javax.swing.JTextField txtStuffdFromIdCard;
    private javax.swing.JLabel txtTotalMark;
    private javax.swing.JTextField txtTraineeId2;
    private javax.swing.JTextField txtTraineeIdFromIdCard;
    private javax.swing.JTextField txtTraineeNameFromIdCard;
    private javax.swing.JTextField txtTsp2;
    private javax.swing.JTextField txtTsp3;
    private javax.swing.JLabel webPassParcentageLabelHome;
    private javax.swing.JLabel webPassStudentCount;
    private javax.swing.JLabel webTotalStudentCount;
    // End of variables declaration//GEN-END:variables
}
