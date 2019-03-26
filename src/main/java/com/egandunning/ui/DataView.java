package com.egandunning.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataView extends JTabbedPane {

    private static final Logger logger = LogManager.getLogger(com.egandunning.ui.DataView.class);
    
    private static final long serialVersionUID = 1L;

    private Map<String, Object[][]> dataSets;
    private boolean allowOverwrite = true;
    private int rowLimit = Integer.MAX_VALUE;
    
    public DataView() {
        logger.traceEntry();
        
        dataSets = new HashMap<>();
        
        logger.traceExit();
    }
    
    public void addDataTab(String title, ResultSet rs) throws SQLException {
        logger.traceEntry();
        
        int colCount = rs.getMetaData().getColumnCount();
        int rowCount = 0;
        
        //get column labels
        String[] headers = new String[colCount];
        for(int i = 1; i <= colCount; i++) {
            
            headers[i - 1] = rs.getMetaData().getColumnLabel(i);
            //logger.info("header " + i + ": " + headers[i]);
            
        }
        
        //get row count
        while(rs.next()) {
            rowCount++;
            
            if(rowCount == rowLimit - 1) {
                logger.warn("ResultSet row limit (", rowLimit, ") reached");
                break;
            }
        }
        rs.beforeFirst();
        
        
        
        Object[][] data = new Object[rowCount][colCount];
        
        
        for(int i = 1; i <= rowCount; i++) {
            
            if(rs.next()) {
                for(int j = 1; j <= colCount; j++) {
                    data[i-1][j-1] = rs.getObject(j);
                    //logger.info(i + "," + j + " " + data[i][j]);
                }
            }
        }
        rs.close();
        addDataTab(title, headers, data);
        
        logger.traceExit();
    }
    
    public void addDataTab(String title, String[] headers, Object[][] data) {
        logger.traceEntry();
        
        if(dataSets.containsKey(title)) {
            if(allowOverwrite) {
                logger.warn("data set: " + title + " overwritten with new result");
            } else {
                logger.warn("data set: " + title + " already exists. not overwritten");
                logger.traceExit();
                return;
            }
        }
        
        dataSets.put(title, data);
        
        JTable table = new JTable(data, headers);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        JScrollPane scrollPane = new JScrollPane(table);
        addTab(title, scrollPane);
        logger.traceExit();
    }
    
    /**
     * Get the number of rows to read from the ResultSet
     * @return rowLimit
     */
    public int getRowLimit() {
        return rowLimit;
    }
    
    /**
     * Set the number of row to read from the ResultSet
     * @param rowLimit
     */
    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }
    
}
