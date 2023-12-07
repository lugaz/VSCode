package CS180Project1;

import java.util.Scanner;

/**
 * A simple SnowAccumulation class
 *
 * @author Purdue CS
 * @version January 10, 2022
 */
public class SnowAccumulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.println("Enter Resort One Name:");
        String resortOne = scanner.nextLine();
        System.out.println("Enter Resort Two Name:");
        String resortTwo = scanner.nextLine();
        System.out.println("Enter Snow Accumulations:");
        String accumulations = scanner.nextLine();
        System.out.println("Enter Report type:" +
            "\n1. Full" +
            "\n2. Summary");
        int reportType = scanner.nextInt();

        scanner.close();

        // The values of each of the accumulations are defined below, 
        // you should use these double variables to make your calculations.
        // Each string has 7 paired values so the format of the string is resortOneDayOne
        // - resortTwoDayOne, resortOneDayTwo - resortTwoDayTwo, ...
        int currentStringIndex = 0;
        double resortOneDayOne = Double.parseDouble(accumulations.substring(currentStringIndex,
            accumulations.indexOf("-")));
        currentStringIndex += accumulations.indexOf("-") + 1;
        double resortTwoDayOne = Double.parseDouble(accumulations.substring(currentStringIndex,
            accumulations.indexOf(",")));
        accumulations = accumulations.substring(accumulations.indexOf(",") + 1);

        currentStringIndex = 0;
        double resortOneDayTwo = Double.parseDouble(accumulations.substring(currentStringIndex,
            accumulations.indexOf("-")));
        currentStringIndex += accumulations.indexOf("-") + 1;
        double resortTwoDayTwo = Double.parseDouble(accumulations.substring(currentStringIndex,
            accumulations.indexOf(",")));
        accumulations = accumulations.substring(accumulations.indexOf(",") + 1);

        currentStringIndex = 0;
        double resortOneDayThree = Double.parseDouble(accumulations.substring(currentStringIndex,
            accumulations.indexOf("-")));
        currentStringIndex += accumulations.indexOf("-") + 1;
        double resortTwoDayThree = Double.parseDouble(accumulations.substring(currentStringIndex,
            accumulations.indexOf(",")));
        accumulations = accumulations.substring(accumulations.indexOf(",") + 1);

        currentStringIndex = 0;
        double resortOneDayFour = Double.parseDouble(accumulations.substring(currentStringIndex,
            accumulations.indexOf("-")));
        currentStringIndex += accumulations.indexOf("-") + 1;
        double resortTwoDayFour = Double.parseDouble(accumulations.substring(currentStringIndex,
            accumulations.indexOf(",")));
        accumulations = accumulations.substring(accumulations.indexOf(",") + 1);

        currentStringIndex = 0;
        double resortOneDayFive = Double.parseDouble(accumulations.substring(currentStringIndex,
            accumulations.indexOf("-")));
        currentStringIndex += accumulations.indexOf("-") + 1;
        double resortTwoDayFive = Double.parseDouble(accumulations.substring(currentStringIndex,
            accumulations.indexOf(",")));
        accumulations = accumulations.substring(accumulations.indexOf(",") + 1);

        currentStringIndex = 0;
        double resortOneDaySix = Double.parseDouble(accumulations.substring(currentStringIndex,
            accumulations.indexOf("-")));
        currentStringIndex += accumulations.indexOf("-") + 1;
        double resortTwoDaySix = Double.parseDouble(accumulations.substring(currentStringIndex,
            accumulations.indexOf(",")));
        accumulations = accumulations.substring(accumulations.indexOf(",") + 1);

        currentStringIndex = 0;
        double resortOneDaySeven = Double.parseDouble(accumulations.substring(currentStringIndex,
            accumulations.indexOf("-")));
        currentStringIndex += accumulations.indexOf("-") + 1;
        double resortTwoDaySeven = Double.parseDouble(accumulations.substring(currentStringIndex));

        // Do not modify above


        //total accumlation
        double totalAccumlationResortOne = resortOneDayOne + resortOneDayTwo + resortOneDayThree + resortOneDayFour + resortOneDayFive + resortOneDaySix + resortOneDaySeven;
        double totalAccumlationResortTwo = resortTwoDayOne + resortTwoDayTwo + resortTwoDayThree + resortTwoDayFour + resortTwoDayFive + resortTwoDaySix + resortTwoDaySeven;
        double totalAccumlation = totalAccumlationResortOne + totalAccumlationResortTwo;
        //avg accumlation
        double averageAccumulationResortOne = totalAccumlationResortOne / 7;
        double averageAccumulationResortTwo = totalAccumlationResortTwo / 7;
        // counter for snow days
        int resortOneCounter = 0;
        int resortTwoCounter = 0;
        //full or summary report 
        if (reportType == 1) {
            //resort one
            System.out.printf("%s Full Report: \n", resortOne);

            System.out.printf("Monday: %.2f\n", resortOneDayOne);

            System.out.printf("Tuesday: %.2f\n", resortOneDayTwo);

            System.out.printf("Wednesday: %.2f\n", resortOneDayThree);

            System.out.printf("Thursday: %.2f\n", resortOneDayFour);

            System.out.printf("Friday: %.2f\n", resortOneDayFive);

            System.out.printf("Saturday: %.2f\n", resortOneDaySix);

            System.out.printf("Sunday: %.2f\n", resortOneDaySeven);
            //resort two
            System.out.printf("%s Full Report: \n", resortTwo);

            System.out.printf("Monday: %.2f\n", resortTwoDayOne);

            System.out.printf("Tuesday: %.2f\n", resortTwoDayTwo);

            System.out.printf("Wednesday: %.2f\n", resortTwoDayThree);

            System.out.printf("Thursday: %.2f\n", resortTwoDayFour);

            System.out.printf("Friday: %.2f\n", resortTwoDayFive);

            System.out.printf("Saturday: %.2f\n", resortTwoDaySix);

            System.out.printf("Sunday: %.2f\n", resortTwoDaySeven);

            System.out.printf("Total Accumulation: %.2f\n", totalAccumlation);

            System.out.printf("%s Total Accumulation: %.2f\n", resortOne, totalAccumlationResortOne);

            System.out.printf("%s Total Accumulation: %.2f\n", resortTwo, totalAccumlationResortTwo);

            System.out.printf("%s Average Accumulation: %.2f\n", resortOne, averageAccumulationResortOne);

            System.out.printf("%s Average Accumulation: %.2f\n", resortTwo, averageAccumulationResortTwo);




        } //end of if
        if (reportType == 2) {
            System.out.printf("Total Accumulation: %.2f\n", totalAccumlation); //add number

            System.out.printf("%s Total Accumulation: %.2f\n", resortOne, totalAccumlationResortOne);

            System.out.printf("%s Total Accumulation: %.2f\n", resortTwo, totalAccumlationResortTwo);

            System.out.printf("%s Average Accumulation: %.2f\n", resortOne, averageAccumulationResortOne);

            System.out.printf("%s Average Accumulation: %.2f\n", resortTwo, averageAccumulationResortTwo);


        } //end of if
        if (resortOneDayOne > resortTwoDayOne) {
            resortOneCounter++;
        } //end of if
        else if (resortTwoDayOne > resortOneDayOne) {
            resortTwoCounter++;
        } //end of else if 

        if (resortOneDayTwo > resortTwoDayTwo) {
            resortOneCounter++;
        } //end of if 
        else if (resortTwoDayTwo > resortOneDayTwo) {
            resortTwoCounter++;
        } //end of else if 

        if (resortOneDayThree > resortTwoDayThree) {
            resortOneCounter++;
        } //end of if 
        else if (resortTwoDayThree > resortOneDayThree) {
            resortTwoCounter++;
        } //end of else if 

        if (resortOneDayFour > resortTwoDayFour) {
            resortOneCounter++;
        } //end of if 
        else if (resortTwoDayFour > resortOneDayFour) {
            resortTwoCounter++;
        } //end of else if 

        if (resortOneDayFive > resortTwoDayFive) {
            resortOneCounter++;
        } //end of if 
        else if (resortTwoDayFive > resortOneDayFive) {
            resortTwoCounter++;
        } //end of else if 

        if (resortOneDaySix > resortTwoDaySix) {
            resortOneCounter++;
        } //end of if 
        else if (resortTwoDaySix > resortOneDaySix) {
            resortTwoCounter++;
        } //end of else if 

        if (resortOneDaySeven > resortTwoDaySeven) {
            resortOneCounter++;
        } //end of if 
        else if (resortTwoDaySeven > resortOneDaySeven) {
            resortTwoCounter++;
        } //end of else if

        if (resortOneCounter > resortTwoCounter) {
            System.out.printf("%s had greater snowfall on more days than %s!\n", resortOne, resortTwo);
        } //end of if 

        if (resortOneCounter < resortTwoCounter) {
            System.out.printf("%s had greater snowfall on more days than %s!\n", resortTwo, resortOne);
        } //end of if 

        if (resortOneCounter == 7) {
            System.out.printf("%s is the undisputed winner!\n", resortOne);
        } //end of if 

        if (resortTwoCounter == 7) {
            System.out.printf("%s is the undisputed winner!\n", resortTwo);
        } //end of if 
    }
}
