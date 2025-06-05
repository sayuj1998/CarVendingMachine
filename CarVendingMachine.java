import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * This program simulates a car vending machine with multiple floors and spaces.
 * It allows loading car data from a file, displaying the vending machine, retrieving cars,
 * sorting the inventory, searching cars, processing a car wash queue, and selling cars.
 */

public class CarVendingMachine {
    /**
     * @param args The main method to execute the car vending machine
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of floors for the car vending machine: ");
        int floors = scanner.nextInt();
        System.out.print("Enter the number of spaces for the car vending machine: ");
        int spaces = scanner.nextInt();

        VendingMachine vendingMachine = new VendingMachine(floors, spaces);

        boolean runningProgram = true;
        while (runningProgram) {
            printMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter the file name: ");
                    String filename = scanner.next();
                    readCarFromFile(filename, vendingMachine);
                    break;
                case 2:
                    vendingMachine.displayInventory();
                    break;
                case 3:
                    System.out.print("\nEnter floor: ");
                    int floor = scanner.nextInt();
                    System.out.print("Enter space: ");
                    int space = scanner.nextInt();
                    vendingMachine.retrieveCar(floor, space);
                    break;
                case 4:
                    vendingMachine.printSortedInventory("price");
                    break;
                case 5:
                    vendingMachine.printSortedInventory("year");
                    break;
                case 6:
                    System.out.print("\nEnter manufacturer: ");
                    String manufacturer = scanner.next();
                    System.out.print("Enter car type (Basic/Premium): ");
                    String type = scanner.next();
                    vendingMachine.searchCars(manufacturer, type);
                    break;
                case 7:
                    System.out.print("\nEnter floor: ");
                    floor = scanner.nextInt();
                    System.out.print("Enter space: ");
                    space = scanner.nextInt();
                    vendingMachine.addToCarWashQueue(floor, space);
                    break;
                case 8:
                    vendingMachine.processCarWashQueue();
                    break;
                case 9:
                    System.out.print("\nEnter floor of the car to sell: ");
                    floor = scanner.nextInt();
                    System.out.print("Enter space of the car to sell: ");
                    space = scanner.nextInt();
                    vendingMachine.sellCar(floor, space);
                    break;
                case 10:
                    System.out.print("\nExiting program. Goodbye!");
                    runningProgram = false;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
        scanner.close();
    }//end main method

    /**
     * Prints the vending machine menu
     */
    private static void printMenu() {
        System.out.println("\n=== Car Vending System Menu ===");
        System.out.println("1. Load Car Data from File");
        System.out.println("2. Display Vending Machine");
        System.out.println("3. Retrieve a Car by Location (Floor & Space)");
        System.out.println("4. Print Sorted Inventory (Price)");
        System.out.println("5. Print Sorted Inventory (Year)");
        System.out.println("6. Search for Cars (Manufacturer & Type)");
        System.out.println("7. Add Car to Wash Queue");
        System.out.println("8. Process Car Wash Queue");
        System.out.println("9. Sell a Car");
        System.out.println("10. Exit");
        System.out.print("\nEnter your choice: ");
    }

    /**
     * Reads car data from a file and adds it to the vending machine
     * @param fileName name of file containing car data
     * @param vendingMachine vending machine to add cars to
     */
    static void readCarFromFile(String fileName, VendingMachine vendingMachine) {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNext()) {
                String type = fileScanner.next();
                int floor = fileScanner.nextInt();
                int space = fileScanner.nextInt();
                int year = fileScanner.nextInt();
                double price = fileScanner.nextDouble();
                String manufacturer = fileScanner.next();
                String model = fileScanner.next();

                Car car;
                if (type.equalsIgnoreCase("B")) {
                    car = new BasicCar(floor, space, year, price, manufacturer, model);
                } else {
                    car = new PremiumCar(floor, space, year, price, manufacturer, model);
                }

                vendingMachine.addCar(car);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File " + fileName + " not found.");
        }
    }//end readCarFromFile method
}//end Driver class

/**
 * Car class represents a car with its details
 */
abstract class Car {
    protected int floor;
    protected int space;
    protected int year;
    protected double price;
    protected String manufacturer;
    protected String model;

    /**
     * @param floor floor of where the car if located
     * @param space space of where the car is located
     * @param year manufacturing year of the car
     * @param price price of the car
     * @param manufacturer make of the car
     * @param model model of the car
     */
    public Car(int floor, int space, int year, double price, String manufacturer, String model) {
        this.floor = floor;
        this.space = space;
        this.year = year;
        this.price = price;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public abstract String getType();

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String locationKey() {
        return floor + "-" + space;
    }

    @Override
    public String toString() {
        return getType() + " Car: " + manufacturer + " " + model + " " + year + " - $" + price + " (Floor: " + (floor)
                + ", Space: " + (space) + ")";
    }
}//end Car class

/**
 * Constructs a BasicCar object.
 */
class BasicCar extends Car {
    public BasicCar(int floor, int space, int year, double price, String manufacturer, String model) {
        super(floor, space, year, price, manufacturer, model);
    }

    @Override
    public String getType() {
        return "Basic";
    }
}//end BasicCar class

/**
 * Constructs a PremiumCar object.
 */
class PremiumCar extends Car {
    public PremiumCar(int floor, int space, int year, double price, String manufacturer, String model) {
        super(floor, space, year, price, manufacturer, model);
    }

    @Override
    public String getType() {
        return "Premium";
    }
}//end PremiumCar

/**
 * Vending machine class represents a car vending machine with multiple floors and spaces
 */
class VendingMachine {
    private final int floors;
    private final int spaces;
    private final LinkedList<Car> inventory;
    private final Map<String, Car> carPositions;
    private final Queue<Car> carWashQueue;

    /**
     * @param floors number of floors in the vending machine
     * @param spaces number of spaces on each floor
     */
    public VendingMachine(int floors, int spaces) {
        this.floors = floors;
        this.spaces = spaces;
        inventory = new LinkedList<>();
        carPositions = new HashMap<>();
        carWashQueue = new LinkedList<>();
    }

    /**
     * Adds a car to the vending machine
     *
     * @param car the car to add
     * @return True if the car was added successfully, false otherwise
     */
    public boolean addCar(Car car) {
        boolean addCar = true;
        String key = car.locationKey();
        if (car.floor < 1 || car.floor > floors || car.space < 1 || car.space > spaces) {
            System.out.println("\nError: Invalid position at Floor " + (car.floor) + " Space " + (car.space));
            System.out.println("Cannot place Car: " + car);
            addCar = false;
        } else if (carPositions.containsKey(key)) {
            System.out.println("\nError: Slot at Floor " + (car.floor) + " Space " + (car.space) + " is already occupied.");
            System.out.println(car.toString() + " cannot be placed.");
            addCar = false;
        } else {
            inventory.add(car);
            carPositions.put(key, car);
        }
        return addCar;
    }//end addCar method

    /**
     * Sells a car by removing it from the vending machine
     *
     * @param floor Floor where the car is located
     * @param space Space where the car is located
     */
    public boolean sellCar(int floor, int space) {
        boolean sold = true;
        String key = floor + "-" + space;

        if (!carPositions.containsKey(key)) {
            System.out.println("\nNo car found at Floor " + (floor) + " Space " + (space) + ".");
            sold = false;
        } else {
            Car car = carPositions.remove(key);
            inventory.remove(car);
            System.out.println("\nCar Sold: " + car);
        }
        return sold;
    }//end of sellCar method

    /**
     * Displays all cars in the vending machine inventory.
     */
    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("\nNo cars in the inventory.");
        } else {
            for (Car car : inventory) {
                System.out.println(car);
            }
        }
    }//end displayInventory method

    /**
     * Retrieves a car from the vending machine
     *
     * @param floor the floor to retrieve the car from
     * @param space the space to retrieve the car from
     */
    public Car retrieveCar(int floor, int space) {
        Car car = null;
        String key = floor + "-" + space;
        if (!carPositions.containsKey(key)) {
            System.out.println("\nCar not found at this location.");
        } else {
            car = carPositions.remove(key);
            System.out.println("\nCar Retrieved: " + car);
        }
        return car;
    }//end retrieveCar method

    /**
     * Prints the sorted inventory based on a given sort criterion.
     *
     * @param sortBy Criterion to sort by (price or year)
     */
    public void printSortedInventory(String sortBy) {
        if (inventory.isEmpty()) {
            System.out.println("\nNo cars available.");
        } else {
            List<Car> sortedInvetory = new ArrayList<>(inventory);
            if (sortBy.equalsIgnoreCase("price")) {
                sortedInvetory.sort(Comparator.comparingDouble(Car::getPrice));
                System.out.println("\nSorted Inventory by Price:");
            } else if (sortBy.equalsIgnoreCase("year")) {
                sortedInvetory.sort(Comparator.comparingInt(Car::getYear));
                System.out.println("\nSorted Inventory by Year:");
            }

            for (Car car : sortedInvetory) {
                System.out.println(car);
            }
        }
    }//end printInventory method

    /**
     * Searches for cars based on manufacturer and type.
     *
     * @param manufacturer Manufacturer of the car
     * @param type Type of the car (Basic or Premium)
     */
    public void searchCars(String manufacturer, String type) {
        List<Car> results = new ArrayList<>();

        for (Car car : inventory) {
            if (car.getManufacturer().equals(manufacturer) && car.getType().equals(type)) {
                results.add(car);
            }
        }

        if (results.isEmpty()) {
            System.out.println("\nNo cars available.");
        } else {
            results.sort(Comparator.comparing(Car::getManufacturer));
            for (Car car : results) {
                System.out.println(car);
            }
        }
    }//end searchCars

    /**
     * Adds a car to the car wash queue.
     *
     * @param floor Floor where the car is located
     * @param space Space where the car is located
     */
    public void addToCarWashQueue(int floor, int space) {
        Car car = retrieveCar(floor, space);
        if (car != null) {
            carWashQueue.add(car);
            System.out.println("Car added to wash queue.");
        }
    }//end addToCarWashQueue method

    /**
     * Processes all cars in the car wash queue.
     */
    public void processCarWashQueue() {
        if (carWashQueue.isEmpty()) {
            System.out.println("\nNo cars in the wash queue.");
        } else {
            while (!carWashQueue.isEmpty()) {
                System.out.println("Washing: " + carWashQueue.remove());
            }
        }
    }//end processCarWashQueue method
}//end VendingMachine class
