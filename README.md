﻿# Car Vending Machine Simulator

This is a Java-based simulation of a multi-floor car vending machine. It supports adding, retrieving, washing, and selling cars, as well as loading data from files, sorting, and searching the inventory.

## Features
- Load car inventory from files
- Display vending machine inventory
- Retrieve or sell a car by floor and space
- Sort cars by price or year
- Search cars by manufacturer and type (Basic or Premium)
- Add cars to a car wash queue and process them

## Technologies
- Java
- Object-Oriented Programming (OOP)
- File I/O
- Collections API (List, Map, Queue)

## Getting Started
1. Clone the repo:
```
https://github.com/sayuj1998/CarVendingMachine
```
2. Compile and run:
```
javac CarVendingMachine.java
java CarVendingMachine
```

## Customization

After compiling and running the program, you can:

* Use `cars1.txt` or `cars2.txt` as sample input files, or create your own.
* Add more cars by editing the file format:
```
<Type> <Floor> <Space> <Year> <Price> <Manufacturer> <Model>
```
```
Example:
    P 3 1 2024 48000.00 Tesla Model Y
    B 2 2 2015 18000.00 Honda Civic
```

## Example Output
```
Enter the number of floors for the car vending machine: 4
Enter the number of spaces for the car vending machine: 4

=== Car Vending System Menu ===
1. Load Car Data from File
2. Display Vending Machine
3. Retrieve a Car by Location (Floor & Space)
4. Print Sorted Inventory (Price)
5. Print Sorted Inventory (Year)
6. Search for Cars (Manufacturer & Type)
7. Add Car to Wash Queue
8. Process Car Wash Queue
9. Sell a Car
10. Exit

Enter your choice: 1

Enter the file name: cars1.txt

=== Car Vending System Menu ===
1. Load Car Data from File
2. Display Vending Machine
3. Retrieve a Car by Location (Floor & Space)
4. Print Sorted Inventory (Price)
5. Print Sorted Inventory (Year)
6. Search for Cars (Manufacturer & Type)
7. Add Car to Wash Queue
8. Process Car Wash Queue
9. Sell a Car
10. Exit

Enter your choice: 1

Enter the file name: cars2.txt

Error: Slot at Floor 3 Space 3 is already occupied.
Premium Car: Honda Accord 2016 - $28000.0 (Floor: 3, Space: 3) cannot be placed.

=== Car Vending System Menu ===
1. Load Car Data from File
2. Display Vending Machine
3. Retrieve a Car by Location (Floor & Space)
4. Print Sorted Inventory (Price)
5. Print Sorted Inventory (Year)
6. Search for Cars (Manufacturer & Type)
7. Add Car to Wash Queue
8. Process Car Wash Queue
9. Sell a Car
10. Exit

Enter your choice: 2
Basic Car: Toyota Prius 2017 - $28000.0 (Floor: 1, Space: 2)
Basic Car: Toyota Camry 2005 - $8000.0 (Floor: 2, Space: 2)
Premium Car: Chevrolet Traverse 2022 - $53000.0 (Floor: 3, Space: 3)
Basic Car: Ford F150 2022 - $26000.0 (Floor: 2, Space: 4)
Basic Car: Toyota Corolla 2018 - $24000.0 (Floor: 1, Space: 1)
Basic Car: Ford Mustang 2019 - $26000.0 (Floor: 2, Space: 3)
Premium Car: Lexus RZ 2025 - $45000.0 (Floor: 3, Space: 2)
Premium Car: Hyundai Ioniq5 2021 - $35000.0 (Floor: 3, Space: 1)
Basic Car: Nissan Altima 2017 - $20000.0 (Floor: 4, Space: 2)

=== Car Vending System Menu ===
1. Load Car Data from File
2. Display Vending Machine
3. Retrieve a Car by Location (Floor & Space)
4. Print Sorted Inventory (Price)
5. Print Sorted Inventory (Year)
6. Search for Cars (Manufacturer & Type)
7. Add Car to Wash Queue
8. Process Car Wash Queue
9. Sell a Car
10. Exit

Enter your choice: 6

Enter manufacturer: Lexus
Enter car type (Basic/Premium): Premium
Premium Car: Lexus RZ 2025 - $45000.0 (Floor: 3, Space: 2)

=== Car Vending System Menu ===
1. Load Car Data from File
2. Display Vending Machine
3. Retrieve a Car by Location (Floor & Space)
4. Print Sorted Inventory (Price)
5. Print Sorted Inventory (Year)
6. Search for Cars (Manufacturer & Type)
7. Add Car to Wash Queue
8. Process Car Wash Queue
9. Sell a Car
10. Exit

Enter your choice: 7

Enter floor: 3
Enter space: 2

Car Retrieved: Premium Car: Lexus RZ 2025 - $45000.0 (Floor: 3, Space: 2)
Car added to wash queue.

=== Car Vending System Menu ===
1. Load Car Data from File
2. Display Vending Machine
3. Retrieve a Car by Location (Floor & Space)
4. Print Sorted Inventory (Price)
5. Print Sorted Inventory (Year)
6. Search for Cars (Manufacturer & Type)
7. Add Car to Wash Queue
8. Process Car Wash Queue
9. Sell a Car
10. Exit

Enter your choice: 8
Washing: Premium Car: Lexus RZ 2025 - $45000.0 (Floor: 3, Space: 2)

=== Car Vending System Menu ===
1. Load Car Data from File
2. Display Vending Machine
3. Retrieve a Car by Location (Floor & Space)
4. Print Sorted Inventory (Price)
5. Print Sorted Inventory (Year)
6. Search for Cars (Manufacturer & Type)
7. Add Car to Wash Queue
8. Process Car Wash Queue
9. Sell a Car
10. Exit

Enter your choice: 9

Enter floor of the car to sell: 1
Enter space of the car to sell: 2

Car Sold: Basic Car: Toyota Prius 2017 - $28000.0 (Floor: 1, Space: 2)

=== Car Vending System Menu ===
1. Load Car Data from File
2. Display Vending Machine
3. Retrieve a Car by Location (Floor & Space)
4. Print Sorted Inventory (Price)
5. Print Sorted Inventory (Year)
6. Search for Cars (Manufacturer & Type)
7. Add Car to Wash Queue
8. Process Car Wash Queue
9. Sell a Car
10. Exit

Enter your choice: 10

Exiting program. Goodbye!
```
