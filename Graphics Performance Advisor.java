import java.util.Scanner;

public class GraphicsQualityRecommendationTool {

    // Constants
    private static final double CLOCK_SPEED_GPU_LOW_THRESHOLD = 800;
    private static final double CLOCK_SPEED_GPU_HI_THRESHOLD = 2000;
    private static final double CLOCK_SPEED_CPU_LOW_THRESHOLD = 1000;
    private static final double CLOCK_SPEED_CPU_HI_THRESHOLD = 5500;
    private static final int CORES_LOW_THRESHOLD = 1;
    private static final int CORES_HI_THRESHOLD = 20;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char repeat;
        int computerNum = 0;
        double totalPerformanceScore = 0;

        do {
            displayTitle();

            // Get user input
            System.out.print("Please enter the type of processor: ");
            String processor = scanner.nextLine();

            double gpuClockSpeed = getValidDoubleInput(scanner, "Please enter the clock speed (in Megahertz) of your graphics card: ", CLOCK_SPEED_GPU_LOW_THRESHOLD, CLOCK_SPEED_GPU_HI_THRESHOLD);
            double cpuClockSpeed = getValidDoubleInput(scanner, "Please enter the clock speed (in Megahertz) of your processor: ", CLOCK_SPEED_CPU_LOW_THRESHOLD, CLOCK_SPEED_CPU_HI_THRESHOLD);
            int cpuCoreNumber = getValidIntInput(scanner, "Please enter the number of cores of your processor: ", CORES_LOW_THRESHOLD, CORES_HI_THRESHOLD);

            System.out.print("Is the hardware overclock-friendly? (Enter y for yes or n for no): ");
            char overclock = scanner.next().toLowerCase().charAt(0);
            while (overclock != 'y' && overclock != 'n') {
                System.out.print("Error: the response should be y for yes or n for no: ");
                overclock = scanner.next().toLowerCase().charAt(0);
            }

            int monitorResolution = getMenuChoice(scanner);
            double multiplier = getMultiplierValue(monitorResolution);
            String monitorResolutionAsString = getResolutionString(monitorResolution);

            double performanceScore = calculatePerformanceScore(gpuClockSpeed, cpuClockSpeed, cpuCoreNumber, multiplier);
            String recommendedQuality = getRecommendedQuality(performanceScore);

            displayInformation(processor, monitorResolutionAsString, gpuClockSpeed, cpuClockSpeed, cpuCoreNumber, performanceScore, recommendedQuality, overclock);

            System.out.print("Would you like to check another recommendation? Enter y for yes or n for no: ");
            repeat = scanner.next().toLowerCase().charAt(0);
            scanner.nextLine();  // Consume the newline character

            computerNum++;
            totalPerformanceScore += performanceScore;

        } while (repeat == 'y');

        double average = totalPerformanceScore / computerNum;
        System.out.println("Average Performance Score: " + average);

        scanner.close();
    }

    public static void displayTitle() {
        String title = "Graphics Quality Recommendation Tool";
        System.out.println(title);
    }

    private static double getValidDoubleInput(Scanner scanner, String message, double lowThreshold, double highThreshold) {
        double input;
        do {
            System.out.print(message);
            input = scanner.nextDouble();
            if (input < lowThreshold || input > highThreshold) {
                System.out.println("The value should be between " + lowThreshold + " and " + highThreshold + " Megahertz.");
            }
        } while (input < lowThreshold || input > highThreshold);
        return input;
    }

    private static int getValidIntInput(Scanner scanner, String message, int lowThreshold, int highThreshold) {
        int input;
        do {
            System.out.print(message);
            input = scanner.nextInt();
            if (input < lowThreshold || input > highThreshold) {
                System.out.println("The value should be between " + lowThreshold + " and " + highThreshold + " cores.");
            }
        } while (input < lowThreshold || input > highThreshold);
        return input;
    }

    private static int getMenuChoice(Scanner scanner) {
        System.out.println("What is the resolution of your monitor?");
        System.out.println("1. 1280 x 720");
        System.out.println("2. 1920 x 1080");
        System.out.println("3. 2560 x 1440");
        System.out.println("4. 3840 x 2160");
        int choice;
        do {
            System.out.print("Please select from the options above (1, 2, 3 or 4): ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character
            if (choice < 1 || choice > 4) {
                System.out.println("Invalid menu selection. Please select from the options above.");
            }
        } while (choice < 1 || choice > 4);
        return choice;
    }

    private static double getMultiplierValue(int monitorResolution) {
        final int RESOLUTION_1280_1024 = 1;
        final int RESOLUTION_1366_768 = 2;
        final int RESOLUTION_1600_900 = 3;
        final int RESOLUTION_1920_1080 = 4;

        final double MULTIPLIER_1280_1024 = 1.0;
        final double MULTIPLIER_1366_768 = 0.75;
        final double MULTIPLIER_1600_900 = 0.55;
        final double MULTIPLIER_1920_1080 = 0.35;

        switch (monitorResolution) {
            case RESOLUTION_1280_1024: return MULTIPLIER_1280_1024;
            case RESOLUTION_1366_768: return MULTIPLIER_1366_768;
            case RESOLUTION_1600_900: return MULTIPLIER_1600_900;
            case RESOLUTION_1920_1080: return MULTIPLIER_1920_1080;
            default: return 1.0;
        }
    }

    private static String getResolutionString(int monitorResolution) {
        final int RESOLUTION_1280_1024 = 1;
        final int RESOLUTION_1366_768 = 2;
        final int RESOLUTION_1600_900 = 3;
        final int RESOLUTION_1920_1080 = 4;

        switch (monitorResolution) {
            case RESOLUTION_1280_1024: return "1280 x 1024";
            case RESOLUTION_1366_768: return "1366 x 768";
            case RESOLUTION_1600_900: return "1600 x 900";
            case RESOLUTION_1920_1080: return "1920 x 1080";
            default: return "Unknown";
        }
    }

    private static double calculatePerformanceScore(double gpuClockSpeed, double cpuClockSpeed, int cpuCoreNumber, double multiplier) {
        final int ATTRIBUTE = 6;
        return (ATTRIBUTE * gpuClockSpeed + (cpuCoreNumber * cpuClockSpeed)) * multiplier;
    }

    private static String getRecommendedQuality(double performanceScore) {
        final double PERFORMANCE_THRESHOLD_HD_PLUS = 17000;
        final double PERFORMANCE_THRESHOLD_HD = 15000;
        final double PERFORMANCE_THRESHOLD_FHD = 13000;
        final double PERFORMANCE_THRESHOLD_WUXGA = 11000;

        if (performanceScore > PERFORMANCE_THRESHOLD_HD_PLUS) {
            return "Ultra Extended";
        } else if (performanceScore > PERFORMANCE_THRESHOLD_HD) {
            return "Ultra";
        } else if (performanceScore > PERFORMANCE_THRESHOLD_FHD) {
            return "Medium";
        } else if (performanceScore > PERFORMANCE_THRESHOLD_WUXGA) {
            return "Low";
        } else {
            return "Unable to Play";
        }
    }

    private static void displayInformation(String processor, String monitorResolutionAsString, double gpuClockSpeed, double cpuClockSpeed, int cpuCoreNumber, double performanceScore, String recommendedQuality, char overclock) {
        System.out.println("\nHardware Information:");
        System.out.println("Processor: " + processor);
        System.out.println("Monitor Resolution: " + monitorResolutionAsString);
        System.out.println("GPU Clock Speed: " + gpuClockSpeed + " MHz");
        System.out.println("CPU Clock Speed: " + cpuClockSpeed + " MHz");
        System.out.println("Number of Cores: " + cpuCoreNumber);
        System.out.println("Performance Score: " + performanceScore);
        System.out.println("Recommended Graphics Quality: " + recommendedQuality);
        if (overclock == 'n') {
            System.out.println("Warning, your cooling system may work harder. Consider upgrading to overclock-friendly components.");
        }
    }
}
