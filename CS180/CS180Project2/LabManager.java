package CS180Project2;

/**
 * LabManager
 *
 * Project 2 
 *
 * @author Lucas 
 *
 * @version 3/3/2022
 *
 */
public class LabManager {
    private Lab labOne;
    private Lab labTwo;
    private Lab labThree;

    public LabManager(Lab labOne, Lab labTwo, Lab labThree) {
        this.labOne = labOne;
        this.labTwo = labTwo;
        this.labThree = labThree;

    } //end of constructer

    public Lab getLabOne() {
        return labOne;
    } //end getLabOne

    public Lab getLabTwo() {
        return labTwo;
    } //end getLabTwo

    public Lab getLabThree() {
        return labThree;
    } //end getLabThree

    public void setLabOne(Lab labOne) {
        this.labOne = labOne;
    } //end setLabOne

    public void setLabTwo(Lab labTwo) {
        this.labTwo = labTwo;
    } //end setLabTwo

    public void setLabThree(Lab labThree) {
        this.labThree = labThree;
    } //end setLabThree

    public int calculateTotalCapacity() {
        int labOneCapacity;
        labOneCapacity = labOne.getCapacity() * 2;

        int labTwoCapacity;
        labTwoCapacity = labTwo.getCapacity() * 2;

        int labThreeCapacity;
        labThreeCapacity = labThree.getCapacity() * 2;

        int totalCapacity;
        totalCapacity = labOneCapacity + labTwoCapacity + labThreeCapacity;
        return totalCapacity;


    } //end calculateTotalCapacity

    public double calculateTotalUtilization() {
        double capacity = calculateTotalCapacity();
        double usageOne = labOne.getMorning().getEnrollment() + labOne.getAfternoon().getEnrollment();
        double usageTwo = labTwo.getMorning().getEnrollment() + labTwo.getAfternoon().getEnrollment();
        double usageThree = labThree.getMorning().getEnrollment() + labThree.getAfternoon().getEnrollment();
        double totalUsage = usageOne + usageTwo + usageThree;
        double percentUsage = totalUsage / capacity;
        return percentUsage;


    } //end calculateTotalUtilization

    public int calculateAvailableSeats() {
        int capacity = calculateTotalCapacity();
        int usageOne = labOne.getMorning().getEnrollment() + labOne.getAfternoon().getEnrollment();
        int usageTwo = labTwo.getMorning().getEnrollment() + labTwo.getAfternoon().getEnrollment();
        int usageThree = labThree.getMorning().getEnrollment() + labThree.getAfternoon().getEnrollment();
        int totalUsage = usageOne + usageTwo + usageThree;
        return capacity - totalUsage;
    } //end calculateAvailableSeats

    public String listReservedLabs() {
        String reservedLabs = "Lab One\n" + labOne.listReservations() + "\n";
        reservedLabs = reservedLabs + "Lab Two\n" + labTwo.listReservations() + "\n";
        reservedLabs = reservedLabs + "Lab Three\n" + labThree.listReservations();

        return reservedLabs;


    } //end listReservedLabs

    public String listAvailableLabs() {
        String availableLabs = "Lab One\n" + labOne.listAvailabilities() + "\n";
        availableLabs = availableLabs + "Lab Two\n" + labTwo.listAvailabilities() + "\n";
        availableLabs = availableLabs + "Lab Three\n" + labThree.listAvailabilities();

        return availableLabs;




    } //end listAvailableLabs

    public String addReservation(String location, String time, String name, int enrollment) {
        String reservation = "";
        if (labOne.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labOne.isMorningAvailable()) {
                    if (labOne.getCapacity() >= enrollment) {
                        Session add = new Session(name, enrollment);
                        labOne.setMorning(add);
                        return "Reservation added!";
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time.";
                } //end of is moringAvaliable
            } else if (time.equals("afternoon")) {
                if (labOne.isAfternoonAvailable()) {
                    if (labOne.getCapacity() >= enrollment) {
                        Session add = new Session(name, enrollment);
                        labOne.setAfternoon(add);
                        return "Reservation added!";
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time.";
                }
            } else {
                return "Error. Invalid time.";
            } //end of else equals morning
        } else if (labTwo.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labTwo.isMorningAvailable()) {
                    if (labTwo.getCapacity() >= enrollment) {
                        Session add = new Session(name, enrollment);
                        labTwo.setMorning(add);
                        return "Reservation added!";
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time.";
                } //end of is moringAvaliable
            } else if (time.equals("afternoon")) {
                if (labTwo.isAfternoonAvailable()) {
                    if (labTwo.getCapacity() >= enrollment) {
                        Session add = new Session(name, enrollment);
                        labTwo.setAfternoon(add);
                        return "Reservation added!";
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time.";
                }
            } else {
                return "Error. Invalid time.";
            } //end of else equals morning
        } else if (labThree.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labThree.isMorningAvailable()) {
                    if (labThree.getCapacity() >= enrollment) {
                        Session add = new Session(name, enrollment);
                        labThree.setMorning(add);
                        return "Reservation added!";
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time.";
                } //end of is moringAvaliable
            } else if (time.equals("afternoon")) {
                if (labThree.isAfternoonAvailable()) {
                    if (labThree.getCapacity() >= enrollment) {
                        Session add = new Session(name, enrollment);
                        labThree.setAfternoon(add);
                        return "Reservation added!";
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time.";
                }
            } else {
                return "Error. Invalid time.";
            } //end of else equals morning
        } else { //end of getLocation
            return "Error. Invalid location";
        } //end of if else getLocation 
    } //end addReservation

    public String removeReservation(String location, String time) {
        if (labOne.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labOne.isMorningAvailable()) {
                    return "Error. Invalid time.";
                } else {
                    labOne.getMorning().setName("");
                    labOne.getMorning().setEnrollment(0);
                    return "Reservation removed!";
                } //end of is moringAvaliable
            } else if (time.equals("afternoon")) {
                if (labOne.isAfternoonAvailable()) {
                    return "Error. Invalid time.";
                } else {
                    labOne.getAfternoon().setName("");
                    labOne.getAfternoon().setEnrollment(0);
                    return "Reservation removed!";
                }
            } else {
                return "Error. Invalid time.";
            } //end of else equals morning
        } else if (labTwo.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labTwo.isMorningAvailable()) {
                    return "Error. Invalid time.";
                } else {
                    labTwo.getMorning().setName("");
                    labTwo.getMorning().setEnrollment(0);
                    return "Reservation removed!";
                } //end of is moringAvaliable
            } else if (time.equals("afternoon")) {
                if (labTwo.isAfternoonAvailable()) {
                    return "Error. Invalid time.";
                } else {
                    labTwo.getAfternoon().setName("");
                    labTwo.getAfternoon().setEnrollment(0);
                    return "Reservation removed!";
                }
            } else {
                return "Error. Invalid time.";
            } //end of else equals morning
        } else if (labThree.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labThree.isMorningAvailable()) {
                    return "Error. Invalid time.";
                } else {
                    labThree.getMorning().setName("");
                    labThree.getMorning().setEnrollment(0);
                    return "Reservation removed!";
                } //end of is moringAvaliable
            } else if (time.equals("afternoon")) {
                if (labThree.isAfternoonAvailable()) {
                    return "Error. Invalid time.";
                } else {
                    labThree.getAfternoon().setName("");
                    labThree.getAfternoon().setEnrollment(0);
                    return "Reservation removed!";
                }
            } else {
                return "Error. Invalid time.";
            } //end of else equals morning
        } else { //end of getLocation
            return "Error. Invalid location";
        } //end of if else getLocation 
    } //end addReservation

    public String modifyReservation(String location, String time, String name, int enrollment) {

        if (labOne.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labOne.isMorningAvailable()) {
                    return "Error. Invalid time.";
                } else {
                    if (labOne.getCapacity() >= enrollment) {
                        Session add = new Session(name, enrollment);
                        labOne.setMorning(add);
                        return "Reservation modified!";
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } //end of is moringAvaliable
            } else if (time.equals("afternoon")) {
                if (labOne.isAfternoonAvailable()) {
                    return "Error. Invalid time.";
                } else {
                    if (labOne.getCapacity() >= enrollment) {
                        Session add = new Session(name, enrollment);
                        labOne.setAfternoon(add);
                        return "Reservation modified!";
                    } else {
                        return "Error. Capacity exceeded";
                    }
                }
            } else {
                return "Error. Invalid time.";
            } //end of else equals morning
        } else if (labTwo.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labTwo.isMorningAvailable()) {
                    return "Error. Invalid time.";
                } else {
                    if (labTwo.getCapacity() >= enrollment) {
                        Session add = new Session(name, enrollment);
                        labTwo.setMorning(add);
                        return "Reservation modified!";
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } //end of is moringAvaliable
            } else if (time.equals("afternoon")) {
                if (labTwo.isAfternoonAvailable()) {
                    return "Error. Invalid time.";
                } else {
                    if (labTwo.getCapacity() >= enrollment) {
                        Session add = new Session(name, enrollment);
                        labTwo.setAfternoon(add);
                        return "Reservation modified!";
                    } else {
                        return "Error. Capacity exceeded";
                    }
                }
            } else {
                return "Error. Invalid time.";
            } //end of else equals morning
        } else if (labThree.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labThree.isMorningAvailable()) {
                    return "Error. Invalid time.";
                } else {
                    if (labThree.getCapacity() >= enrollment) {
                        Session add = new Session(name, enrollment);
                        labThree.setMorning(add);
                        return "Reservation modified!";
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } //end of is moringAvaliable
            } else if (time.equals("afternoon")) {
                if (labThree.isAfternoonAvailable()) {
                    return "Error. Invalid time.";
                } else {
                    if (labThree.getCapacity() >= enrollment) {
                        Session add = new Session(name, enrollment);
                        labThree.setAfternoon(add);
                        return "Reservation modified!";
                    } else {
                        return "Error. Capacity exceeded";
                    }
                }
            } else {
                return "Error. Invalid time.";
            } //end of else equals morning
        } else { //end of getLocation
            return "Error. Invalid location";
        }
    }
    public String toString() {
        String managerString;
        managerString = "LabManager{" + labOne.toString() + labTwo.toString() + labThree.toString() + "}";

        return managerString;
    } //end toString









}
