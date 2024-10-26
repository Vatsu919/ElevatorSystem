package org.example;

import org.example.entities.Building;
import org.example.entities.FloorManager;
import org.example.entities.elevator.ElevatorManager;
import org.example.enums.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElevatorSystemUI extends JFrame {

    private FloorManager floorManager = FloorManager.getInstance();
    private ElevatorManager elevatorManager = ElevatorManager.getInstance();
    private Building building;

    public ElevatorSystemUI() {
        setTitle("Elevator System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize FloorManager and ElevatorManager
        initializeBuilding();

        // Add Floors Panel
        add(createFloorsPanel(), BorderLayout.CENTER);

        // Add Control Panel (for simulating people requests)
        add(createControlPanel(), BorderLayout.SOUTH);

        // Add Internal Display Panel (for manual elevator control)
        add(createInternalDisplayPanel(), BorderLayout.EAST);
    }

    private void initializeBuilding() {
        System.out.println("Enter the number of floors in the building");
        building = new Building(floorManager, elevatorManager);
        building.addFloors();        // Add floors to the building
        building.addElevators();     // Add elevators to the building
        building.startElevators();   // Start elevator movement
    }

    private JPanel createFloorsPanel() {
        JPanel floorsPanel = new JPanel();
        floorsPanel.setLayout(new GridLayout(floorManager.getFloorCount(), 1));

        for (int i = floorManager.getFloorCount() - 1; i >= 0; i--) {
            int floorNumber = i;
            JPanel floorPanel = new JPanel();
            floorPanel.setBorder(BorderFactory.createTitledBorder("Floor " + floorNumber));

            JButton upButton = new JButton("Up");
            upButton.addActionListener(e -> {
                floorManager.getFloor(floorNumber).pressButton(Direction.UP);
                System.out.println("Up button pressed on floor " + floorNumber);
            });

            JButton downButton = new JButton("Down");
            downButton.addActionListener(e -> {
                floorManager.getFloor(floorNumber).pressButton(Direction.DOWN);
                System.out.println("Down button pressed on floor " + floorNumber);
            });

            floorPanel.add(upButton);
            floorPanel.add(downButton);

            // Create individual elevator position labels
            for (int j = 0; j < elevatorManager.getElevatorCount(); j++) {
                JLabel elevatorPosition = new JLabel("Elevator " + j + " here");
                elevatorPosition.setVisible(false); // Initially hidden

                floorPanel.add(elevatorPosition);

                // Track each elevator’s position and update label visibility
                final int elevatorIndex = j;
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (elevatorManager.getElevatorController(elevatorIndex).getElevatorCart().getCurrentFloor() == floorNumber) {
                            elevatorPosition.setVisible(true);
                        } else {
                            elevatorPosition.setVisible(false);
                        }
                    }
                });
                timer.start();
            }

            floorsPanel.add(floorPanel);
        }
        return floorsPanel;
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JLabel personLabel = new JLabel("Number of people:");
        JTextField personInput = new JTextField(5);
        controlPanel.add(personLabel);
        controlPanel.add(personInput);

        JButton simulateButton = new JButton("Simulate Elevator");
        simulateButton.addActionListener(e -> {
            int numPeople = Integer.parseInt(personInput.getText());
            System.out.println("Simulating " + numPeople + " people in the building.");

            floorManager.getFloor(6).pressButton(Direction.UP);
            floorManager.getFloor(3).pressButton(Direction.DOWN);
            elevatorManager.getElevatorController(0).getElevatorCart().pressButton(8);

            try {
                Thread.sleep(7000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        controlPanel.add(simulateButton);
        return controlPanel;
    }

    private JPanel createInternalDisplayPanel() {
        JPanel internalDisplayPanel = new JPanel();
        internalDisplayPanel.setLayout(new GridLayout(3, 1));
        internalDisplayPanel.setBorder(BorderFactory.createTitledBorder("Internal Display"));

        // Dropdown for selecting the elevator
        JLabel elevatorLabel = new JLabel("Select Elevator:");
        JComboBox<Integer> elevatorDropdown = new JComboBox<>();
        for (int i = 0; i < elevatorManager.getElevatorCount(); i++) {
            elevatorDropdown.addItem(i);
        }

        // Dropdown for selecting the destination floor
        JLabel floorLabel = new JLabel("Select Floor:");
        JComboBox<Integer> floorDropdown = new JComboBox<>();
        for (int i = 0; i < floorManager.getFloorCount(); i++) {
            floorDropdown.addItem(i);
        }

        // GO button to send the elevator to the selected floor
        JButton goButton = new JButton("GO");
        goButton.addActionListener(e -> {
            int selectedElevator = (int) elevatorDropdown.getSelectedItem();
            int destinationFloor = (int) floorDropdown.getSelectedItem();

            // Move the selected elevator to the destination floor
            System.out.println("Moving Elevator " + selectedElevator + " to Floor " + destinationFloor);
            elevatorManager.getElevatorController(selectedElevator).getElevatorCart().pressButton(destinationFloor);
        });

        // Adding components to the panel
        internalDisplayPanel.add(elevatorLabel);
        internalDisplayPanel.add(elevatorDropdown);
        internalDisplayPanel.add(floorLabel);
        internalDisplayPanel.add(floorDropdown);
        internalDisplayPanel.add(goButton);

        return internalDisplayPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ElevatorSystemUI ui = new ElevatorSystemUI();
            ui.setVisible(true);
        });
    }
}
