package com.egandunning.ui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This JFrame is the application's main window
 * @author dunning
 *
 */
public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = LogManager.getLogger(com.egandunning.ui.MainWindow.class);
    
    private static MainWindow mw;
    private Editor editor;
    private DataView dataView;
    private JSplitPane centerPanel;
    
    /**
     * Get the instance of MainWindow. This ensures that other classes
     * can access the same instance.
     * @return
     */
    public static MainWindow getInstance() {
        
        if(mw != null) {
            return mw;
        }
        
        mw = new MainWindow();
        return mw;
    }
    
    /** 
     * Set the Center Pane divider such that the Editor and DataView use
     * equal horizontal space.
     */
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        mw.centerPanel.setDividerLocation(0.5);
    }

    /**
     * Initialize the window:
     * 
     * Retrieve and set icons
     * 
     * Set title, default close operation, default size
     * 
     * 
     */
    private MainWindow() {
        
        logger.traceEntry();
        
        //Get list of icons, set frame icon images if list exists.
        List<Image> icons = getIconList();
        
        if(icons != null) {
            setIconImages(icons);
        }

        //Initialize the on-screen components
        JPanel mainPanel = new JPanel(new BorderLayout());
        ToolBar toolBar = new ToolBar();
        StatusBar statusBar = new StatusBar();
        ProjectNavigator projectNavigator = new ProjectNavigator();
        
        centerPanel = new JSplitPane(); 
        
        
        //Initialize the components in the center panel
        editor = new Editor();
        dataView = new DataView();
        
        centerPanel.setLeftComponent(editor);
        centerPanel.setRightComponent(dataView);
        
        
        //Add components to the main panel
        //Layout Docs:
        //https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
        mainPanel.add(toolBar, BorderLayout.PAGE_START);
        mainPanel.add(projectNavigator, BorderLayout.LINE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(statusBar, BorderLayout.PAGE_END);
        
        //Add main panel to frame
        setContentPane(mainPanel);
        
        setTitle("ETL Test Tool POC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        
        logger.traceExit();
    }
    
    /**
     * Returns a list of Images to be used as icons for the main JFrame. Use
     * multiple image sizes to avoid blurry images.
     * 
     * Images should be placed in the src/main/resources folder.
     * 
     * Returns null if an icon isn't found.
     * 
     * @return List of Images.
     */
    public List<Image> getIconList() {
        
        logger.traceEntry();
        
        //Get icons (runnable JAR version)
        logger.info("fetching icon resources");
        InputStream is16 = getClass().getClassLoader().getResourceAsStream("resources/star16.png");
        InputStream is32 = getClass().getClassLoader().getResourceAsStream("resources/star32.png");
        InputStream is64 = getClass().getClassLoader().getResourceAsStream("resources/star64.png");
        
        //Get icons (Eclipse version)
        if(is16 == null || is32 == null || is64 == null) {
            logger.info("using debug icon location");
            is16 = getClass().getClassLoader().getResourceAsStream("star16.png");
            is32 = getClass().getClassLoader().getResourceAsStream("star32.png");
            is64 = getClass().getClassLoader().getResourceAsStream("star64.png");
        }
        
        //If any images are not found, return null
        if(is16 == null || is32 == null || is64 == null) {
            logger.warn("icons not found");
            logger.traceExit();
            return null;
        }
        
        BufferedImage bi16;
        BufferedImage bi32;
        BufferedImage bi64;
        
        try {
            logger.trace("Creating BufferedImage objects");
            bi16 = ImageIO.read(is16);
            bi32 = ImageIO.read(is32);
            bi64 = ImageIO.read(is64);
        } catch(IOException e) {
            logger.catching(e);
            logger.traceExit();            
            return null;
        }
        
        List<Image> icons = new ArrayList<>();
        
        icons.add(bi16);
        icons.add(bi32);
        icons.add(bi64);
        
        logger.traceExit();
        
        return icons;
    }
    
    
    public Editor getEditor() {
        return editor;
    }
    
    public DataView getDataView() {
        return dataView;
    }
}
