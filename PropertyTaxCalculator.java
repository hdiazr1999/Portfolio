import java.util.ArrayList;
import java.util.Scanner;

// Building Class
class Building {
    private String streetAddress;
    private double sizeInSquareFeet;

    // Default Constructor
    public Building() {
        this.streetAddress = "";
        this.sizeInSquareFeet = 0.0;
    }

    // Parameterized Constructor
    public Building(String streetAddress, double sizeInSquareFeet) {
        this.streetAddress = streetAddress;
        this.sizeInSquareFeet = sizeInSquareFeet;
    }

    // Getters and Setters
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public double getSizeInSquareFeet() {
        return sizeInSquareFeet;
    }

    public void setSizeInSquareFeet(double sizeInSquareFeet) {
        if (sizeInSquareFeet > 0) {
            this.sizeInSquareFeet = sizeInSquareFeet;
        } else {
            throw new IllegalArgumentException("Size in square feet must be greater than 0.");
        }
    }

    // toString Method
    @Override
    public String toString() {
        return "Building Address: " + streetAddress + ", Size: " + sizeInSquareFeet + " square feet";
    }
}

// ParcelOfLand Class
class ParcelOfLand {
    private String parcelID;
    private double sizeInAcres;
    private String zoningType; // Residential or Commercial
    private Building building;

    // Default Constructor
    public ParcelOfLand() {
        this.parcelID = "";
        this.sizeInAcres = 0.0;
        this.zoningType = "";
        this.building = new Building();
    }

    // Parameterized Constructor
    public ParcelOfLand(String parcelID, double sizeInAcres, String zoningType, Building building) {
        this.parcelID = parcelID;
        this.sizeInAcres = sizeInAcres;
        this.zoningType = zoningType;
        this.building = building;
    }

    // Getters and Setters
    public String getParcelID() {
        return parcelID;
    }

    public void setParcelID(String parcelID) {
        this.parcelID = parcelID;
    }

    public double getSizeInAcres() {
        return sizeInAcres;
    }

    public void setSizeInAcres(double sizeInAcres) {
        if (sizeInAcres > 0) {
            this.sizeInAcres = sizeInAcres;
        } else {
            throw new IllegalArgumentException("Size in acres must be greater than 0.");
        }
    }

    public String getZoningType() {
        return zoningType;
    }

    public void setZoningType(String zoningType) {
        this.zoningType = zoningType;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    // Method to Calculate Property Tax
    public double calculatePropertyTax() {
        double baseTax = zoningType.equalsIgnoreCase("Residential") ? 500 : 2000;
        double additionalTaxPerSqFt = 0;

        if (zoningType.equalsIgnoreCase("Commercial")) {
            if (building.getSizeInSquareFeet() < 4000) {
                additionalTaxPerSqFt = 1.0;
            } else {
                additionalTaxPerSqFt = 1.5;
            }
        } else if (zoningType.equalsIgnoreCase("Residential")) {
            if (building.getSizeInSquareFeet() < 2000) {
                additionalTaxPerSqFt = 0.5;
            } else {
                additionalTaxPerSqFt = 0.75;
            }
        }

        return (sizeInAcres * baseTax) + (building.getSizeInSquareFeet() * additionalTaxPerSqFt);
    }

    // toString Method
    @Override
    public String toString() {
        return "Parcel ID: " + parcelID + ", Size: " + sizeInAcres + " acres, Zoning Type: " + zoningType + "\n" +
                building.toString() + "\n" +
                "Property Tax: $" + String.format("%.2f", calculatePropertyTax());
    }
}

// Main Class
public class PropertyTaxApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ParcelOfLand> parcels = new ArrayList<>();
        boolean continueInput = true;

        while (continueInput) {
            // Input for Parcel of Land
            System.out.print("Enter Parcel ID: ");
            String parcelID = scanner.nextLine();

            System.out.print("Enter Size of Parcel (in acres): ");
            double sizeInAcres = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter Zoning Type (Residential/Commercial): ");
            String zoningType = scanner.nextLine();

            // Input for Building
            System.out.print("Enter Building Address: ");
            String streetAddress = scanner.nextLine();

            System.out.print("Enter Building Size (in square feet): ");
            double sizeInSquareFeet = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            // Create Building and ParcelOfLand objects
            Building building = new Building(streetAddress, sizeInSquareFeet);
            ParcelOfLand parcel = new ParcelOfLand(parcelID, sizeInAcres, zoningType, building);

            // Add parcel to the list
            parcels.add(parcel);

            // Ask user if they want to continue
            System.out.print("Do you want to add another parcel? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                continueInput = false;
            }
        }

        // Display all parcels
        System.out.println("\n--- Parcel Information ---");
        double totalTax = 0;
        for (ParcelOfLand parcel : parcels) {
            System.out.println(parcel);
            System.out.println();
            totalTax += parcel.calculatePropertyTax();
        }

        // Calculate and display average property tax
        double averageTax = totalTax / parcels.size();
        System.out.printf("Average Property Tax: $%.2f\n", averageTax);

        scanner.close();
    }
}
