package CS180Project2;

import java.util.Scanner;
/**
 * TimeKeeper
 *
 * Project 2
 *
 * @author Lucas 
 *
 * @version 3/3/2022
 *
 */
public class TimeKeeper {
    
    private static String welcomePrompt = "Welcome to the TimeKeeper application!";
    private static String invalidInput = "Invalid input. Please try again.";
    private static String enterLabCapacity = "Enter the capacity for Lab ";
    private static String enterLabLocation = "Enter the location for Lab "; 
    private static String labLocationPrompt = "Enter the location of the lab:"; 
    private static String reservationTimePrompt = "Enter the time of the reservation:"; 
    private static String reservationNamePrompt = "Enter the name of the reservation:";
    private static String reservationEnrollmentPrompt = "Enter the expected enrollment:"; 
    private static String reservationNameUpdate = "Enter the updated name of the reservation:";
    private static String reservationEnrollmentUpdate = "Enter the updated enrollment:"; 
    private static String totalCapacity = "Total Capacity: ";
    private static String totalUtilization = "Total Utilization: ";
    private static String availableSeats = "Available seats: "; 
    
    
    private static String initializeMenu = "1. Initialize Application\n" +
                                        "2. Exit";
    private static String ongoingMenu = "1. View Current Lab Schedule\n" +
                                "2. List Labs by Availability\n" +
                                "3. List Labs by Reservation\n" +
                                "4. Add a Reservation\n" +
                                "5. Remove a Reservation\n" +
                                "6. Modify a Reservation\n" +
                                "7. Calculate Statistics\n" +
                                "8. Exit";
    private static String statisticsMenu = "1. Total Capacity\n" +
                                            "2. Total Utilization\n" +
                                            "3. Available seats\n" +
                                            "4. Return to main menu";
    private static String exitMessage = "Thank you for using TimeKeeper!";

    // public void runMenuOne(){
    //     System.out.println("results for menu one");
    // }//end of RunMenuOne

    //main
    public static void main(String[] args){
        //start menu
        Scanner scanner = new Scanner(System.in);
        int ongoingMenuSelection;
        int statisticsMenuSelection;
        Lab labOne;
        Lab labTwo;
        Lab labThree;
        int initializeInput;
        System.out.println(welcomePrompt);
            do {
            
            System.out.println(initializeMenu);
            initializeInput = scanner.nextInt();
            scanner.nextLine();
            //Lab labOne = new Lab(morning, afternoon, capacity, location)
            //LabManager lm = new LabManager(labOne, labTwo, labThree)


            //if they press 1

            if (initializeInput == 1){
                //lab one
                
                System.out.println(enterLabCapacity + "1:");
                int labOneCapacity = scanner.nextInt();
                scanner.nextLine();
                

                System.out.println(enterLabLocation + "1:");
                String labOneLocation = scanner.nextLine();
                labOne = new Lab(labOneCapacity, labOneLocation);
                //lab two

                System.out.println(enterLabCapacity + "2:");
                int labTwoCapacity = scanner.nextInt();
                scanner.nextLine();

                System.out.println(enterLabLocation + "2:");
                String labTwoLocation = scanner.nextLine();
                labTwo = new Lab(labTwoCapacity, labTwoLocation);
                //lab three

                System.out.println(enterLabCapacity + "3:");
                int labThreeCapacity = scanner.nextInt();
                scanner.nextLine();

                System.out.println(enterLabLocation + "3:");
                String labThreeLocation = scanner.nextLine();
                labThree = new Lab(labThreeCapacity, labThreeLocation);
                LabManager lm = new LabManager(labOne, labTwo, labThree);
                
                
                do {
                    System.out.println(ongoingMenu);
                    ongoingMenuSelection = scanner.nextInt();
                    scanner.nextLine();
                    if (ongoingMenuSelection == 1){
                        System.out.println();
                        System.out.println(labOne.toString());
                        System.out.println(labTwo.toString());
                        System.out.println(labThree.toString());
                        
                        



                    }//end of if for one


                    if (ongoingMenuSelection == 2){
                        System.out.println(lm.listAvailableLabs());
                        

                    }//end of if for two


                    if (ongoingMenuSelection == 3){
                        System.out.println(lm.listReservedLabs());
                    }//end of if for three


                    if (ongoingMenuSelection == 4){
                        String enterLocation;
                        String enterTimeReservation;
                        String enterNameReservation;
                        int enterEnrollment;
                        
                        System.out.println(labLocationPrompt);
                        enterLocation = scanner.nextLine();

                        System.out.println(reservationTimePrompt);
                        enterTimeReservation = scanner.nextLine();

                        System.out.println(reservationNamePrompt);
                        enterNameReservation = scanner.nextLine();

                        System.out.println(reservationEnrollmentPrompt);
                        enterEnrollment = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println(lm.addReservation(enterLocation, enterTimeReservation, enterNameReservation, enterEnrollment));

                    }//end of if for four


                    if (ongoingMenuSelection == 5){
                        System.out.println(labLocationPrompt);
                        String enterLocation = scanner.nextLine();

                        System.out.println(reservationTimePrompt);
                        String enterTimeReservation = scanner.nextLine();
                        System.out.println(lm.removeReservation(enterLocation, enterTimeReservation));

                        
                    }//end of if for five



                    if (ongoingMenuSelection == 6){
                        System.out.println(labLocationPrompt);
                        String enterLocation = scanner.nextLine();

                        System.out.println(reservationTimePrompt);
                        String enterTimeReservation = scanner.nextLine();

                        System.out.println(reservationNameUpdate);
                        String enterNameReservation = scanner.nextLine();

                        System.out.println(reservationEnrollmentUpdate);
                        int enterEnrollment = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println(lm.modifyReservation(enterLocation, enterTimeReservation, enterNameReservation, enterEnrollment));
                    }//end of if for six


                    if (ongoingMenuSelection == 7){
                        do {
                            System.out.println(statisticsMenu);
                            statisticsMenuSelection = scanner.nextInt();
                            scanner.nextLine();
                            if (statisticsMenuSelection ==1){
                                System.out.println(totalCapacity + lm.calculateTotalCapacity());
                            }
                            if (statisticsMenuSelection == 2){
                                System.out.printf(totalUtilization + "%.2f",lm.calculateTotalUtilization());
                                System.out.println();
                            }
                            if (statisticsMenuSelection == 3){
                                System.out.println(availableSeats + lm.calculateAvailableSeats());
                            }
                        }while(statisticsMenuSelection == 1 || statisticsMenuSelection == 2 || statisticsMenuSelection == 3);//end of internal while for 7
                    }//end of if for seven

                if (ongoingMenuSelection > 8){
                    System.out.println(invalidInput);
                }

                }while(ongoingMenuSelection == 1 || ongoingMenuSelection == 2 || ongoingMenuSelection == 3 ||
                ongoingMenuSelection == 4 || ongoingMenuSelection == 5 || ongoingMenuSelection == 6 ||
                ongoingMenuSelection == 7 || ongoingMenuSelection > 8);

                if (ongoingMenuSelection == 8){
                    System.out.println(exitMessage);
                }//end of if for eight
                }//end of if

                if (initializeInput == 2){
                    System.out.println(exitMessage);
                }//end of if exitmessage

            if (initializeInput > 2){
                System.out.println(invalidInput);
            }
            

        }while(initializeInput > 2);








            

        }

        
    }
