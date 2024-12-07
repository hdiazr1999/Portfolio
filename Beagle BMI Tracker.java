import java.util.ArrayList;
import java.util.Scanner;

// Beagle Class
class Beagle {
    private static int totalBeagles = 0; // Shared among all instances
    private String name;
    private double heightInInches;
    private double weightInPounds;
    private String gender;

    // Default Constructor
    public Beagle() {
        this.name = "";
        this.heightInInches = 0.0;
        this.weightInPounds = 0.0;
        this.gender = "";
    }

    // Parameterized Constructor
    public Beagle(String name, double heightInInches, double weightInPounds, String gender) {
        this.name = name;
        this.heightInInches = heightInInches;
        this.weightInPounds = weightInPounds;
        this.gender = gender;
        totalBeagles++;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeightInInches() {
        return heightInInches;
    }

    public void setHeightInInches(double heightInInches) {
        this.heightInInches = heightInInches;
    }

    public double getWeightInPounds() {
        return weightInPounds;
    }

    public void setWeightInPounds(double weightInPounds) {
        this.weightInPounds = weightInPounds;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static int getTotalBeagles() {
        return totalBeagles;
    }

    // Convert height to meters
    public String calcHeightMeters() {
        double meters = heightInInches / 39.3701;
        return String.format("Height (m): %.2f", meters);
    }

    // Convert weight to kilograms
    public String calcWeightKilograms() {
        double kilograms = weightInPounds / 2.20462;
        return String.format("Weight (kg): %.2f", kilograms);
    }

    @Override
    public String toString() {
        return String.format(
            "Beagle Name: %s\nHeight (in): %.2f\n%s\nWeight (lbs): %.2f\n%s\nGender: %s",
            name,
            heightInInches,
            calcHeightMeters(),
            weightInPounds,
            calcWeightKilograms(),
            gender
        );
    }
}

// BeagleBMI Class
class BeagleBMI {
    private Beagle beagle;

    // Constructor
    public BeagleBMI(Beagle beagle) {
        this.beagle = beagle;
    }

    // Calculate BMI
    public double calcBMI() {
        return beagle.getWeightInPounds() / beagle.getHeightInInches();
    }

    // Determine weight status
    public String weightStatus() {
        double bmi = calcBMI();
        String gender = beagle.getGender().toLowerCase();
        if (gender.equals("male")) {
            if (bmi < 1.54) return "Underweight";
            else if (bmi <= 2.31) return "Healthy";
            else return "Overweight";
        } else if (gender.equals("female")) {
            if (bmi < 1.49) return "Underweight";
            else if (bmi <= 2.25) return "Healthy";
            else return "Overweight";
        }
        return "Unknown";
    }

    @Override
    public String toString() {
        return String.format(
            "%s\nBMI: %.2f\nStatus: %s",
            beagle.toString(),
            calcBMI(),
            weightStatus()
        );
    }
}

// Main Class
public class DemoDiaz { // Class name matches the file name
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<BeagleBMI> beagleList = new ArrayList<>();
        boolean continueInput = true;

        while (continueInput) {
            System.out.print("Enter Beagle's Name: ");
            String name = scanner.nextLine();
            while (name.isEmpty()) {
                System.out.print("Name cannot be empty. Enter Beagle's Name: ");
                name = scanner.nextLine();
            }

            System.out.print("Enter Beagle's Height in Inches (9-22): ");
            double height = scanner.nextDouble();
            while (height < 9 || height > 22) {
                System.out.print("Height must be between 9 and 22 inches. Enter again: ");
                height = scanner.nextDouble();
            }

            System.out.print("Enter Beagle's Weight in Pounds (7-60): ");
            double weight = scanner.nextDouble();
            while (weight < 7 || weight > 60) {
                System.out.print("Weight must be between 7 and 60 pounds. Enter again: ");
                weight = scanner.nextDouble();
            }

            scanner.nextLine(); // Consume newline
            System.out.print("Enter Beagle's Gender (Male/Female): ");
            String gender = scanner.nextLine();
            while (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) {
                System.out.print("Gender must be Male or Female. Enter again: ");
                gender = scanner.nextLine();
            }

            // Create Beagle and BeagleBMI objects
            Beagle beagle = new Beagle(name, height, weight, gender);
            BeagleBMI beagleBMI = new BeagleBMI(beagle);
            beagleList.add(beagleBMI);

            // Ask if the user wants to continue
            String response;
            while (true) {
                System.out.print("Do you want to add another Beagle? (yes/no): ");
                response = scanner.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    break;
                } else if (response.equalsIgnoreCase("no")) {
                    continueInput = false;
                    break;
                } else {
                    System.out.println("Answer must be 'yes' or 'no'. Please try again.");
                }
            }
        }

        // Display Beagle Information
        int underweightCount = 0, healthyCount = 0, overweightCount = 0;
        double totalBMI = 0;
        System.out.println("\n--- Beagle Information ---");
        for (BeagleBMI beagleBMI : beagleList) {
            System.out.println(beagleBMI);
            totalBMI += beagleBMI.calcBMI();
            String status = beagleBMI.weightStatus();
            if (status.equals("Underweight")) underweightCount++;
            else if (status.equals("Healthy")) healthyCount++;
            else if (status.equals("Overweight")) overweightCount++;
        }

        // Display Summary
        System.out.println("\n--- Summary ---");
        System.out.println("Total Beagles: " + Beagle.getTotalBeagles());
        System.out.println("Underweight Beagles: " + underweightCount);
        System.out.println("Healthy Beagles: " + healthyCount);
        System.out.println("Overweight Beagles: " + overweightCount);
        System.out.printf("Average BMI: %.2f\n", totalBMI / Beagle.getTotalBeagles());

        scanner.close();
    }
}
