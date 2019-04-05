package com.egandunning.runner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.egandunning.ui.Editor;
import com.egandunning.ui.MainWindow;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * This class is responsible for running Groovy scripts and storing variables
 * that can be used in user scripts.
 * @author dunning
 *
 */
public class Runner {

    //testing
    public static void main(String[] args) {
        Binding binding = new Binding();
        binding.setVariable("myVar", "k");
        GroovyShell gs = new GroovyShell(binding);
        gs.evaluate("myVar = 'aaaa'");
        System.out.println(binding.getVariable("myVar"));
    }

    private static final Logger logger = LogManager.getLogger(com.egandunning.runner.Runner.class);
    
    //Variables to be used in Groovy Scripts
    
    private Map<String, ResultSet> dataSets;
    private Map<String, String> vars;
    private Map<String, Object> customVars;
    
    public static Runner instance;
    
    /**
     * Get the instance of the runner class.
     * @return an instance of this class
     */
    public static Runner getInstance() {
        if(instance == null) {
            instance = new Runner();
        }
        return instance;
    }
    
    private Runner() {
        //initialize Maps used in bindings
        vars = new HashMap<>();
        dataSets = new HashMap<>();
        customVars = new HashMap<>();
    }
    
    /**
     * Initializes the Binding to different variables. Bound variables can be
     * accessed from the user's Groovy script.
     * 
     * Bound variable names: {@link Runner#vars vars},
     * {@link Runner#dataSets dataSets},
     * {@link Runner#customVars customVars},
     * {@link com.egandunning.ui.MainWindow#dataView dataView},
     * {@link Runner#logger log}
     * 
     * @return Binding object to be passed to GroovyShell constructor
     */
    private Binding initBinding() {
        
        Binding binding = new Binding();
        
        //String variables
        binding.setVariable("vars", vars);
        
        //Results from DB
        binding.setVariable("dataSets", dataSets);
        
        //Any custom variables
        binding.setVariable("customVars", customVars);
        
        //DataView binding
        binding.setVariable("dataView", MainWindow.getInstance().getDataView());
        
        //Logger
        binding.setVariable("log", logger);
        
        return binding;
    }
    
    public void execute() {
        
        logger.traceEntry();
        
        MainWindow mw = MainWindow.getInstance();
        Editor e = mw.getEditor();
        String groovyScript = e.getTabText();
        
        execute(groovyScript);
        
        dataSets.keySet().forEach(title -> {
            try {
                MainWindow.getInstance().getDataView().addDataTab(title, dataSets.get(title));
            } catch (SQLException e1) {
                logger.catching(e1);
                logger.warn("Unable to display query results.");
            }
        });
        
        logger.traceExit();
    }
    
    public void executeSelected() {
        logger.traceEntry();
        
        MainWindow mw = MainWindow.getInstance();
        Editor e = mw.getEditor();
        String groovyScript = e.getSelectedTabText();
        
        if(groovyScript == null) {
            groovyScript = e.getTabText();
        }
        execute(groovyScript);
        
        logger.traceExit();
    }

    public void execute(String groovyScript) {
        logger.traceEntry();
        
        GroovyShell gs = new GroovyShell(initBinding());
        gs.evaluate(groovyScript);
        
        logger.traceExit();
    }
    
    /**
     * Get a printable representation of vars and customVars variables
     * @return
     */
    public String getVarSummary() {
        
        StringBuilder output = new StringBuilder();
        
        output.append("Variable name: vars\nValues:\n");
        vars.keySet().forEach(key -> {
            output.append(key).append(" = ").append(vars.get(key)).append("\n");
        });
     
        output.append("\nVariable name: customVars\nValues:\n");
        customVars.keySet().forEach(key -> {
            output.append(key).append(" = ").append(customVars.get(key).toString()).append("\n");
        });

        return output.toString();
    }
    
}
