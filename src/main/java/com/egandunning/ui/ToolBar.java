package com.egandunning.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.egandunning.runner.Runner;

public class ToolBar extends JMenuBar {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager
            .getLogger(com.egandunning.ui.ToolBar.class);

    public ToolBar() {

        logger.traceEntry();

        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        initFileMenu();
        initEditMenu();
        initViewMenu();
        initRunMenu();
        initHelpMenu();

        logger.traceExit();
    }

    /**
     * Create and add menu items to the File menu.
     */
    private void initFileMenu() {

        logger.traceEntry();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openProject = new JMenuItem("Open Project");
        JMenuItem openRecent = new JMenuItem("Open Recent");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAll = new JMenuItem("Save All");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem exportJar = new JMenuItem("Export JAR");
        JMenuItem exportSql = new JMenuItem("Export SQL");
        JMenuItem properties = new JMenuItem("Project Properties");

        // action listeners
        newMenuItem.addActionListener(e -> newMenuItemListener());
        openProject.addActionListener(e -> openProjectListener());
        openRecent.addActionListener(e -> openRecentListener());
        save.addActionListener(e -> saveListener());
        saveAll.addActionListener(e -> saveAllListener());
        saveAs.addActionListener(e -> saveAsListener());
        exportJar.addActionListener(e -> exportJarListener());
        exportSql.addActionListener(e -> exportSqlListener());
        properties.addActionListener(e -> propertiesListener());

        // keyboard shortcuts
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));

        save.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        saveAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));

        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK | ActionEvent.ALT_MASK));

        fileMenu.add(newMenuItem);
        fileMenu.add(openProject);
        fileMenu.add(openRecent);
        fileMenu.add(save);
        fileMenu.add(saveAll);
        fileMenu.add(saveAs);
        fileMenu.add(exportJar);
        fileMenu.add(exportSql);
        fileMenu.add(properties);
        add(fileMenu);

        logger.traceExit();
    }

    private void newMenuItemListener() {

    }

    private void openProjectListener() {

    }

    private void openRecentListener() {

    }

    private void saveListener() {

    }

    private void saveAllListener() {

    }

    private void saveAsListener() {

    }

    private void exportJarListener() {

    }

    private void exportSqlListener() {

    }

    private void propertiesListener() {

    }

    /**
     * Create and add menu items to the Edit menu.
     */
    private void initEditMenu() {

        logger.traceEntry();

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);

        // TODO: add menu items

        add(editMenu);

        logger.traceExit();
    }

    /**
     * Create and add menu items to the View menu.
     */
    private void initViewMenu() {

        logger.traceEntry();

        JMenu viewMenu = new JMenu("View");
        viewMenu.setMnemonic(KeyEvent.VK_V);

        // TODO: add menu items

        add(viewMenu);

        logger.traceExit();
    }

    private void initRunMenu() {

        logger.traceEntry();

        JMenu runMenu = new JMenu("Run");
        runMenu.setMnemonic(KeyEvent.VK_R);

        JMenuItem runSelected = new JMenuItem("Run Selected");
        JMenuItem run = new JMenuItem("Run");
        JMenuItem runnerVars = new JMenuItem("View Runtime Variables");

        run.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));

        runSelected.addActionListener(e -> runSelectedListener());
        run.addActionListener(e -> runListener());
        runnerVars.addActionListener(e -> runnerVarsListener());

        runMenu.add(runSelected);
        runMenu.add(run);
        runMenu.add(runnerVars);

        add(runMenu);

        logger.traceExit();
    }

    private void runSelectedListener() {

        logger.traceEntry();
        
        Runner.getInstance().executeSelected();
        
        logger.traceExit();
    }

    private void runListener() {

        logger.traceEntry();

        Runner.getInstance().execute();

        logger.traceExit();
    }

    private void runnerVarsListener() {

        logger.traceEntry();

        //Shows variables that can be used in scripts
        JOptionPane.showOptionDialog(this, Runner.getInstance().getVarSummary(),
                "Scripting Variables", JOptionPane.OK_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, new String[] {"OK"}, null);

        logger.traceExit();
    }

    /**
     * Create and add menu items to the Help menu.
     */
    private void initHelpMenu() {

        logger.traceEntry();

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        // TODO: add menu items
        JMenuItem website = new JMenuItem("Online Help");
        JMenuItem about = new JMenuItem("About");

        helpMenu.add(website);
        helpMenu.add(about);

        add(helpMenu);

        logger.traceExit();
    }
}
