// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField pool = new JTextField(30);
    private JTextField reps = new JTextField(4);
    private JTextField rec = new JTextField(4);
    private JTextField ter = new JTextField(30);
    private JTextField temp = new JTextField(4);

    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JLabel labPool = new JLabel("Pool type:");
    private JLabel labReps = new JLabel("Repetitions:");
    private JLabel labRec = new JLabel("Recovery (min):");
    private JLabel labTer = new JLabel("Terrain:");
    private JLabel labTemp = new JLabel("Tempo (min):");

    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton findAllByDate = new JButton("Find all");
    private JButton removeButton = new JButton("Remove Entry");
    private JRadioButton isCycleButton = new JRadioButton("isCycleEntry");
    private JRadioButton isSprintButton = new JRadioButton("isSprintEntry");
    private JRadioButton isSwimButton = new JRadioButton("isSwimEntry");


    private boolean isCycleEntry = false;
    private boolean isSprintEntry = false;
    private boolean isSwimEntry = false;



    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        //Radio buttons for Entry type selection
        add(isCycleButton);
        add(isSprintButton);
        add(isSwimButton);
        isCycleButton.addActionListener(this);
        isSprintButton.addActionListener(this);
        isSwimButton.addActionListener(this);
        //
        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(findAllByDate);
        findAllByDate.addActionListener(this);
        add(removeButton);
        removeButton.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);
        setSize(1080, 720);
        setVisible(true);
        blankDisplay();

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry("generic");
            blankDisplay();
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
            blankDisplay();
        }
        if (event.getSource() == findAllByDate) {
            message = lookupEntries();
            blankDisplay();
        }
        if (event.getSource() == removeButton) {
            message = removeEntry();
            blankDisplay();
        }
        if(event.getSource() == isSprintButton)
        {
            isCycleButton.setSelected(false);
            isSwimButton.setSelected(false);
            displaySprintOptions();
            message = "sprint true";
        }
        if(event.getSource() == isCycleButton)
        {
            isSprintButton.setSelected(false);
            isSwimButton.setSelected(false);
            displayCycleOptions();
            message = "cycle true";
        }
        if(event.getSource() == isSwimButton)
        {
            isCycleButton.setSelected(false);
            isSprintButton.setSelected(false);
            displaySwimOptions();
            message = "swim true";
        }
        outputArea.setText(message);

    } // actionPerformed

    public String addEntry(String what) {
        String message = "Cannot add entry when one or more fields are empty.";

        try {
            String n = name.getText();
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            float km = java.lang.Float.parseFloat(dist.getText());
            int h = Integer.parseInt(hours.getText());
            int mm = Integer.parseInt(mins.getText());
            int s = Integer.parseInt(secs.getText());

            Entry e;
            if (isSprintEntry) {
                System.out.println("reps: " + reps);
                System.out.println("reps: " + rec);
                int rps = Integer.parseInt(reps.getText());
                int rvy = Integer.parseInt(rec.getText());
                e = new SprintEntry(n, d, m, y, h, mm, s, km, rps, rvy);
            } else if (isSwimEntry) {
                String poolType = pool.getText();
                e = new SwimEntry(n, d, m, y, h, mm, s, km, poolType);
            } else if (isCycleEntry) {
                String terrain = ter.getText();
                String tempo = temp.getText();
                e = new CycleEntry(n, d, m, y, h, mm, s, km, terrain, tempo);
            }else
            {
                e = new Entry(n, d, m, y, h, mm, s, km);
            }
            message = myAthletes.addEntry(e);
            System.out.println("Adding " + what + " entry to the records");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return message;
    }
    
    public String lookupEntry() {
        String message = "Cannot lookup when fields are empty.";
        try {
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            outputArea.setText("looking up record ...");
            message = myAthletes.lookupEntry(d, m, y);
        }
        catch(Exception e)
        {
            //
        }
        return message;
    }

    public String lookupEntries()
    {
        String message = "Cannot lookup when fields are empty.";
        try {
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            outputArea.setText("looking up records ...");
            message = myAthletes.lookupEntries(d, m, y);
        }
        catch(Exception e){
            //
        }
        return message;
    }

    public String removeEntry()
    {
        String message = "Cannot lookup when fields are empty.";
        try {
            String n = name.getText();
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            outputArea.setText("deleting entry ...");
            message = myAthletes.removeEntry(n, d, m, y);
        }
        catch(Exception e)
        {
            //
        }
        return message;
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        pool.setText("");
        reps.setText("");
        rec.setText("");
        ter.setText("");
        temp.setText("");

    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }

    public void displayCycleOptions()
    {
        removeOptional();
        isCycleEntry = true;
        add(labTer);
        add(ter);
        ter.setEditable(true);
        ter.setText("");
        add(labTemp);
        add(temp);
        temp.setEditable(true);
        temp.setText("");
    }

    public void displaySprintOptions()
    {
        removeOptional();
        isSprintEntry = true;
        add(labReps);
        add(reps);
        reps.setEditable(true);
        reps.setText("");
        add(labRec);
        add(rec);
        rec.setEditable(true);
        rec.setText("");
    }

    public void displaySwimOptions()
    {
        removeOptional();
        isSwimEntry = true;
        add(labPool);
        add(pool);
        pool.setEditable(true);
        pool.setText("");
    }

    public void removeOptional()
    {
        remove(labPool);
        remove(pool);
        remove(labReps);
        remove(reps);
        remove(labRec);
        remove(rec);
        remove(labTer);
        remove(ter);
        remove(labTemp);
        remove(temp);
        isSwimEntry = false;
        isSprintEntry = false;
        isCycleEntry = false;
    }

} // TrainingRecordGUI

