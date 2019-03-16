package com.egandunning.ui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Editor extends JTabbedPane {
    
    private static final Logger logger = LogManager.getLogger(com.egandunning.ui.Editor.class);
    
    private static final long serialVersionUID = 1L;

    private boolean lineWrap = false;
    private Map<String, JEditorPane> textPanes;
    
    public Editor() {
        logger.traceEntry();        
        
        textPanes = new HashMap<>();
        
        logger.traceExit();
    }
    
    public void addTextTab(String title, String text) throws Exception {
        
        logger.traceEntry();
        
        JEditorPane textArea = new JEditorPane("text/plain", text);
        
        //check for duplicate tab
        if(textPanes.containsKey(title)) {
            throw new Exception("File names must be unique");
        }
        
        //add text area to list of text panes
        textPanes.put(title, textArea);
        
        //TODO: line numbers
        
        JScrollPane scrollPane = new JScrollPane(textPanes.get(title));
        
        addTab(title, scrollPane);
        
        logger.traceExit();
    }
    
    public String getTabText() {
        return textPanes.get(getTitleAt(getSelectedIndex())).getText();
    }
    
    public String getSelectedTabText() {
        return textPanes.get(getTitleAt(getSelectedIndex())).getSelectedText();
    }
    
    public void setLineWrap(boolean allowLineWrapping) {
        logger.traceEntry();
        
        lineWrap = allowLineWrapping;
        
        //TODO: update text areas
        
        logger.traceExit();
    }
}
