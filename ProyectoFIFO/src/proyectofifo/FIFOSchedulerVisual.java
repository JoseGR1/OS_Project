/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofifo;

/**
 *
 * @author jose5
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

class Process {
    String name;
    int burstTime;

    public Process(String name, int burstTime) {
        this.name = name;
        this.burstTime = burstTime;
    }
}

public class FIFOSchedulerVisual {
    private Queue<Process> readyQueue = new LinkedList<>();
    private int totalWaitTime = 0;

    private JFrame frame;
    private JTextField numProcessesField;
    private JPanel processPanel;
    private JTextArea outputTextArea;

    public FIFOSchedulerVisual() {
        frame = new JFrame("Simulador FIFO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Simulador de Algoritmo FIFO");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        numProcessesField = new JTextField(10);
        numProcessesField.setToolTipText("Número de procesos");

        JButton submitButton = new JButton("Iniciar simulación");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSimulation(Integer.parseInt(numProcessesField.getText()));
            }
        });

        topPanel.add(titleLabel);
        topPanel.add(numProcessesField);
        topPanel.add(submitButton);

        processPanel = new JPanel();
        processPanel.setLayout(new FlowLayout());

        //outputTextArea = new JTextArea();
        //outputTextArea.setEditable(false);
        //outputTextArea.setPreferredSize(new Dimension(550, 200));

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(processPanel, BorderLayout.CENTER);
        frame.add(new JScrollPane(outputTextArea), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void startSimulation(int numProcesses) {
        readyQueue.clear();
        totalWaitTime = 0;

        processPanel.removeAll();
        processPanel.revalidate();
       processPanel.repaint();

        for (int i = 0; i < numProcesses; i++) {
            Process process = new Process("P" + (i + 1), (int) (Math.random() * 20) + 1); // Tiempo de ráfaga aleatorio
            readyQueue.add(process);

            JPanel processSquare = new JPanel();
            processSquare.setPreferredSize(new Dimension(100, 100));
            processSquare.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            processSquare.setBackground(Color.WHITE);
            processSquare.setLayout(new BorderLayout());

            JLabel nameLabel = new JLabel(process.name, SwingConstants.LEFT);
            JLabel burstLabel = new JLabel("B: " + process.burstTime, SwingConstants.CENTER);

            processSquare.add(nameLabel, BorderLayout.NORTH);
            processSquare.add(burstLabel, BorderLayout.CENTER);

            processPanel.add(processSquare);
        }

        frame.revalidate();
        frame.repaint();

        /*String simulationOutput = "Ejecución de procesos:\n";
        simulationOutput += "Proceso\tTiempo de Ráfaga\tTiempo de Llegada\tTiempo de Espera\n";
        simulationOutput += "--------------------------------------------------------------\n";

        int currentTime = 0;
        while (!readyQueue.isEmpty()) {
            Process currentProcess = readyQueue.poll();

            simulationOutput += currentProcess.name + "\t\t" + currentProcess.burstTime + "\t\t\t" + currentTime + "\t\t\t" + (currentTime - currentProcess.burstTime) + "\n";

            totalWaitTime += currentTime - currentProcess.burstTime;
            currentTime += currentProcess.burstTime;
        }

        double avgWaitTime = (double) totalWaitTime / numProcesses;
        simulationOutput += "\nMétricas:\n";
        simulationOutput += "Total de Procesos: " + numProcesses + "\n";
        simulationOutput += "Tiempo de Espera Total: " + totalWaitTime + "\n";
        simulationOutput += "Tiempo de Espera Promedio: " + avgWaitTime + "\n";

        outputTextArea.setText(simulationOutput);*/
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FIFOSchedulerVisual();
            }
        });
    }
}

