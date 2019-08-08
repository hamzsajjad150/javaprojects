package hamzmuha;

/*[IceCreamFlavour] 
Name: [Muhammad Hamza]
Date: [28-03-2019] 
-------------------------------------------
Description [this is enum class that contains the id and the flavours of 
different types of icecreams it also has method to get the id and flavour name] 
 */
public enum IceCreamFlavour {
    VANILLA(1, "Vanilla"),
    CHOCOLATE(2, "Chocolate"),
    MANGO(3, "Mango"),
    STRAWBERRY(4, "Strawberry"),
    BUTTER_PERCAN(5, "Butter_Peacan"),
    MOOSE(6, "Moose"),
    RASBERRY(7, "Rasberry");
    // these are the variable that are private and have getter to work them
    private int id;
    private String flavourName;

    // this is a private constructor as enum class requres
    private IceCreamFlavour(int idNum, String iceName) {
        id = idNum;
        flavourName = iceName;
    }

    /**
     * this is to get getting the flavour name of the enum selected
     *
     * @return a string the will be the name of the flavours
     */
    public String getFlavourName() {
        return flavourName;
    }

    /**
     * this is to get the id of the flavour which is also same when printing the
     * info
     *
     * @return a int that is the flavour id
     */
    public int getId() {
        return id;
    }
/**
 * the is to get the info of the flavour as well as there id
 * @return return a string which is the info of a choosen flavour
 */
    @Override
    public String toString() {
        return "IceCreamFlavour id = " + id
                + "\n flavourName = " + flavourName;
    }

}
