package com.egandunning.main;

import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.egandunning.ui.MainWindow;

public class Main {

    private static final Logger logger = LogManager.getLogger(com.egandunning.main.Main.class);
    
    /**
     * Create and show the window
     * @param args
     */
    public static void main(String[] args) {

        logger.traceEntry();
        
        //Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            logger.warn("Unable to set system look and feel");
            logger.catching(e);
        }
        
        //Show main window
        MainWindow mw = MainWindow.getInstance();
        
        mw.setVisible(true);
        
        //debug
        try {
            mw.getEditor().addTextTab("test", "import org.postgresql.Driver;\r\n" + 
                    "import java.sql.Connection;\r\n" + 
                    "import java.sql.DriverManager;\r\n" + 
                    "import java.sql.PreparedStatement;\r\n" + 
                    "import java.sql.ResultSet;\r\n" + 
                    "\r\n" + 
                    "Connection conn = null;\r\n" + 
                    "Properties connectionProps = new Properties();\r\n" + 
                    "connectionProps.put(\"user\", \"postgres\");\r\n" + 
                    "connectionProps.put(\"password\", \"dabbad00.\");\r\n" + 
                    "\r\n" + 
                    "conn = DriverManager.getConnection(\"jdbc:postgresql://localhost:5432/northwind\", connectionProps);\r\n" + 
                    "\r\n" + 
                    "PreparedStatement ps = conn.prepareStatement(\"SELECT * FROM CUSTOMERS\", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);\r\n" + 
                    "ResultSet rs = ps.executeQuery();\r\n" + 
                    "dataSets.put(\"customers\", rs);");
            mw.getDataView().addDataTab("Test", new String[]{"id", "name"}, new Object[][] {new Object[] {"123", "egan"}, new Object[] {"456", "john"}});
        } catch(Exception e) {
            logger.throwing(e);
        }
        
        logger.traceExit();
    }

}
