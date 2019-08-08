package hamzmuha;

/*[IceCreamList] 
Name: [Muhammad Hamza]
Date: [28-03-2019] 
-------------------------------------------
Description [this is the class that create icecream by extending arraylist 
class it also create file and scanner that will read and write on the file. 
also creating icream instance object the access icecream class methods] 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class IceCreamList extends ArrayList<IceCream> {
// this is the instance used in this class

    private File file;
    private Scanner input;
    private IceCream item;

    /**
     * default constructor of icecreamList
     *
     * @throws FileNotFoundException if the file is not found
     * @throws IOException if scanner cannot find file
     */
    public IceCreamList() throws FileNotFoundException, IOException {
        file = new File("tester.txt");
        input = new Scanner(file);
        readFile();
    }

    /**
     * this is the method that readfile and loads the array with the info on the
     * text file
     *
     * @throws IOException if the scanner cannot find the file
     */
    public void readFile() throws IOException {
        while (input.hasNext()) {
            int id = input.nextInt();
            String flavor = input.next();
            double price = input.nextDouble();
            int qty = input.nextInt();
            double cost = input.nextDouble();
            item = new IceCream();

            loadingArray(item, id, price, qty);
        }
    }

    /**
     * this is the method that write the new Icecream on the text file and calls
     * other method to check if the flavoured icecream already exits if it does
     * that it just update the array and then write the text file again
     *
     * @param newItem object of iceCream class
     * @throws IOException if the fileWrite cannot find the file
     */
    public void writeRecord(IceCream newItem) throws IOException {
        int ixt = searchIceCream(newItem.getFlavour());
        if (ixt == -1) {
            this.add(newItem);
        } else {
            get(ixt).setPrice(newItem.getPrice());
            get(ixt).setQuantity(newItem.getQuantity());
        }
        PrintWriter write = new PrintWriter(file);
        for (int i = 0; i < size(); i++) {
            write.print(get(i).getFlavour().getId());
            write.print(" " + get(i).getFlavour().getFlavourName() + " ");
            write.print(get(i).getPrice() + " ");
            write.print(get(i).getQuantity() + " ");
            write.print(get(i).getCost() + " ");
            write.print("\r\n");
        }
        write.close();
    }

    /**
     * this methods checks if the new flavour that user is trying to add already
     * exits or not
     *
     * @param flv an object of the iceCreamFlavour class and this method can
     * access flv id and name
     * @return the index at which if the flavour that is trying to be added or
     * -1 if the flavour doesn't exites
     */
    public int searchIceCream(IceCreamFlavour flv) {
        int index = -1;
        for (IceCream itemCream : this) {
            if (itemCream.getFlavour() == flv) {
                index = this.indexOf(itemCream);
            }
        }
        return index;
    }

    /**
     * this load the array from the text file and uses the flavour id to find
     * the flavour name in the switch statement
     *
     * @param cream this is the object of the icecream and it is the object that
     * is added in the array
     * @param id int that is the id of the flavour
     * @param price double price of the icecream thats being added to the array
     * @param qty int the quantity of the icecream that being added
     */
    public void loadingArray(IceCream cream, int id, double price, int qty) {
        switch (id) {
            case 1:
                cream.setFlavour(IceCreamFlavour.VANILLA);
                cream.setPrice(price);
                cream.setQuantity(qty);
                add(cream);
                break;
            case 2:
                cream.setFlavour(IceCreamFlavour.CHOCOLATE);
                cream.setPrice(price);
                cream.setQuantity(qty);
                add(cream);
                break;
            case 3:
                cream.setFlavour(IceCreamFlavour.MANGO);
                cream.setPrice(price);
                cream.setQuantity(qty);
                add(cream);
                break;
            case 4:
                cream.setFlavour(IceCreamFlavour.STRAWBERRY);
                cream.setPrice(price);
                cream.setQuantity(qty);
                add(cream);
                break;
            case 5:
                cream.setFlavour(IceCreamFlavour.BUTTER_PERCAN);
                cream.setPrice(price);
                cream.setQuantity(qty);
                add(cream);
                break;
            case 6:
                cream.setFlavour(IceCreamFlavour.MOOSE);
                cream.setPrice(price);
                cream.setQuantity(qty);
                add(cream);
                break;
            case 7:
                cream.setFlavour(IceCreamFlavour.RASBERRY);
                cream.setPrice(price);
                cream.setQuantity(qty);
                add(cream);
                break;
        }
    }

    /**
     * this methods formats the records in the text file as a string. the
     * records are taken from the array as it holds everything the text file has
     *
     * @return string that holds all the records from the text file in formated
     */
    public String printRecord() {
        String outputRec = "ID      Falvour     Price       Qty     cost\n"
                + "==============================================\n";
        for (IceCream itemCream : this) {

            outputRec += String.format("%d%15s%10.2f%10d%10.2f\n",
                    itemCream.getFlavour().getId(), itemCream.getFlavour().
                    getFlavourName(), itemCream.getPrice(),
                    itemCream.getQuantity(), itemCream.getCost());
        }
        return outputRec;
    }
}
