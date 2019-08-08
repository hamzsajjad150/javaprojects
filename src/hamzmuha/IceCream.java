package hamzmuha;

/*[IceCream] 
Name: [Muhammad Hamza]
Date: [28-03-2019] 
-------------------------------------------
Description [this is a class that contains the variable and methods of setting 
and getting the price quantity and the cost of the iceream flavour. it also 
create a instance of IceCreamFlavour to use it] 
 */
public class IceCream {

    // these are the instance variable used in this class
    private IceCreamFlavour Flavour;
    private double price;
    private int quantity;

    /**
     * default constructor of this Icecream class
     */
    public IceCream() {
    }

    /**
     * this return the flavour which as methods to get the id and the name of
     * the flavours
     *
     * @return flavour
     */
    public IceCreamFlavour getFlavour() {
        return Flavour;
    }

    /**
     * this method is used to set the icecreamflavour of the icecream
     *
     * @param Flavour object to set the flavour
     */
    public void setFlavour(IceCreamFlavour Flavour) {
        this.Flavour = Flavour;
    }

    /**
     * this get the price of the icecream
     *
     * @return a double that is the price of the icecream
     */
    public double getPrice() {
        return price;
    }

    /**
     * this method is used to set the price of the icecream
     *
     * @param price takes in the double and sets it to the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * this method is used to get the quantity of the icecream
     *
     * @return integer that is the quantity of the icecream
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * this method is used to sett the quantity of the icecream
     *
     * @param quantyty takes int and sets it to the quantity of the icecream
     */
    public void setQuantity(int quantyty) {
        this.quantity = quantyty;
    }

    /**
     * this methods is used get the cost of the icecream which is the price *
     * quantity
     *
     * @return double which is the icecream cost
     */
    public double getCost() {
        return price * quantity;
    }

    /**
     * this is to get the info of the icecream that includes flavour, price,
     * quanity and the cost of the icecream
     *
     * @return a string that has all the info of the icecream
     */
    @Override
    public String toString() {
        return "IceCream: " + "\n Flavour = " + Flavour
                + "\n price = " + price
                + "\n quantity = " + quantity;
    }

}
