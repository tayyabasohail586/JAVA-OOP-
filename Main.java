
package com.mycompany.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

abstract class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract String getRole();
    public abstract double calculateSalary();

    public String getDetails() {
        return "Name: " + name + "\nAge: " + age + "\nRole: " + getRole() + "\nSalary: $" + calculateSalary();
    }
}

class Doctor extends Person {
    private int patientsTreated;
    private double feePerPatient;

    public Doctor(String name, int age, int patientsTreated, double feePerPatient) {
        super(name, age);
        this.patientsTreated = patientsTreated;
        this.feePerPatient = feePerPatient;
    }

    public String getRole() {
        return "Doctor";
    }

    public double calculateSalary() {
        return patientsTreated * feePerPatient;
    }
}

class Nurse extends Person {
    private int hoursWorked;
    private double hourlyRate;

    public Nurse(String name, int age, int hoursWorked, double hourlyRate) {
        super(name, age);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public String getRole() {
        return "Nurse";
    }

    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class Patient extends Person {
    private String disease;

    public Patient(String name, int age, String disease) {
        super(name, age);
        this.disease = disease;
    }

    public String getRole() {
        return "Patient";
    }

    public double calculateSalary() {
        return 0.0;
    }

    public String getDetails() {
        return super.getDetails() + "\nDisease: " + disease;
    }
}

public class Main {
    private static final ArrayList<Person> records = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hospital Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));

        JButton btnAddDoctor = new JButton("Add Doctor");
        JButton btnAddNurse = new JButton("Add Nurse");
        JButton btnAddPatient = new JButton("Add Patient");
        JButton btnShowRecords = new JButton("Show All Records");
        JButton btnExit = new JButton("Exit");

        btnAddDoctor.addActionListener(e -> addDoctor());
        btnAddNurse.addActionListener(e -> addNurse());
        btnAddPatient.addActionListener(e -> addPatient());
        btnShowRecords.addActionListener(e -> showAllRecords());
        btnExit.addActionListener(e -> System.exit(0));

        frame.add(btnAddDoctor);
        frame.add(btnAddNurse);
        frame.add(btnAddPatient);
        frame.add(btnShowRecords);
        frame.add(btnExit);

        frame.setVisible(true);
    }

    private static void addDoctor() {
        try {
            String name = JOptionPane.showInputDialog("Enter Doctor's Name:");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age:"));
            int patients = Integer.parseInt(JOptionPane.showInputDialog("Patients Treated:"));
            double fee = Double.parseDouble(JOptionPane.showInputDialog("Fee per Patient:"));
            records.add(new Doctor(name, age, patients, fee));
            JOptionPane.showMessageDialog(null, "Doctor added successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input.");
        }
    }

    private static void addNurse() {
        try {
            String name = JOptionPane.showInputDialog("Enter Nurse's Name:");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age:"));
            int hours = Integer.parseInt(JOptionPane.showInputDialog("Hours Worked:"));
            double rate = Double.parseDouble(JOptionPane.showInputDialog("Hourly Rate:"));
            records.add(new Nurse(name, age, hours, rate));
            JOptionPane.showMessageDialog(null, "Nurse added successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input.");
        }
    }

    private static void addPatient() {
        try {
            String name = JOptionPane.showInputDialog("Enter Patient's Name:");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age:"));
            String disease = JOptionPane.showInputDialog("Enter Disease:");
            records.add(new Patient(name, age, disease));
            JOptionPane.showMessageDialog(null, "Patient added successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input.");
        }
    }

    private static void showAllRecords() {
        if (records.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No records found!");
            return;
        }

        StringBuilder all = new StringBuilder();
        for (Person p : records) {
            all.append(p.getDetails()).append("\n----------------\n");
        }

        JTextArea textArea = new JTextArea(all.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 200));
        JOptionPane.showMessageDialog(null, scrollPane, "All Records", JOptionPane.INFORMATION_MESSAGE);
    }
}
