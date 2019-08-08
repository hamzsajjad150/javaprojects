package hamzmuha;

/* Name: [Muhammad Hamza] 
Assignment: [Assignment 5] 
Program: [Ice Cream Inventory] 
Date: [02-04-2019] 
--------------------------------------------------
Description: [this class is a javafx class in create an interface that allows 
user to add icecreams with there price and quantity. is also shows the record 
for an exting icecream info entered] 
 */
import java.io.IOException;
import java.util.InputMismatchException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Store extends Application {
// instance variable used in this class    

    private IceCreamList iceInventory;
    private IceCream iceCream;

    //this is where GUI is created
    @Override
    public void start(Stage primaryStage) throws IOException {
        // creating objects of instance variable
        iceInventory = new IceCreamList();
        iceCream = new IceCream();
        //creating menubar and its menus
        MenuBar menubar = new MenuBar();
        Menu fileMenu = new Menu("_File");
        MenuItem exitItem = new MenuItem("_Exit");
        // seting the exit menu on shortcut and on action
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.E,
                KeyCodeCombination.SHORTCUT_DOWN));
        exitItem.setOnAction(e -> eventCode(e, exitItem));
        //adding the menu together
        fileMenu.getItems().add(exitItem);
        menubar.getMenus().add(fileMenu);

        // panses objects
        BorderPane root = new BorderPane();
        VBox slectionArea = new VBox(15);
        VBox ioBox = new VBox(10);

        // label of title
        Label lblTitle = new Label("Ice Cream Inventory");
        lblTitle.setId("label");
        //creating combobox and adding its items 
        ComboBox<String> cmbFlv = new ComboBox<String>();
        cmbFlv.getItems().addAll(IceCreamFlavour.VANILLA.getFlavourName(),
                IceCreamFlavour.CHOCOLATE.getFlavourName(),
                IceCreamFlavour.MANGO.getFlavourName(),
                IceCreamFlavour.STRAWBERRY.getFlavourName(),
                IceCreamFlavour.BUTTER_PERCAN.getFlavourName(),
                IceCreamFlavour.MOOSE.getFlavourName(),
                IceCreamFlavour.RASBERRY.getFlavourName());
        cmbFlv.setPromptText("Select a flavour");
        cmbFlv.setPrefSize(149, 25);

        // this is a hbox for price
        HBox priceBox = new HBox(5);
        Label lblPrice = new Label("Price: ");
        lblPrice.setId("lblPrice");
        TextField txtPrice = new TextField();
        priceBox.getChildren().addAll(lblPrice, txtPrice);
        priceBox.setAlignment(Pos.CENTER_RIGHT);

        // this is a Hbox for price
        HBox quantBox = new HBox(5);
        Label lblQuant = new Label("Quantity");
        lblQuant.setId("lblQuant");
        TextField txtQuant = new TextField();
        quantBox.getChildren().addAll(lblQuant, txtQuant);
       quantBox.setAlignment(Pos.CENTER_RIGHT);

        // this is setting the position of the selectionarea 
        slectionArea.setPrefSize(100, 50);
        slectionArea.setAlignment(Pos.TOP_CENTER);

        // text area and setting its dimension
        TextArea txtOput = new TextArea();
        txtOput.setPadding(new Insets(5));
        txtOput.setPrefWidth(200);
        txtOput.setPrefHeight(500);
        txtOput.setId("txtOput");

        // HBox for btns 
        HBox btnSelection = new HBox(15);
        Button btnRest = new Button("_Reset");
        btnRest.setOnAction(e -> clearEvery(e, btnRest, txtOput, txtPrice,
                txtQuant, cmbFlv, lblPrice, lblQuant));
        Button btnSave = new Button("_Save");
        //setting btnsave on action its in try and catch because the methods 
        //throws exceptions
        btnSave.setOnAction(e -> {
            try {
                procees(e, cmbFlv, txtOput, checkPrice(txtPrice, lblPrice),
                        checkQunt(txtQuant, lblQuant), lblPrice, lblQuant);
            } catch (Exception ex) {
                txtOput.setText(ex.getMessage());
            }
        });
        Button btnInven = new Button("_Show Inventory");
        //setting btnInven to action to show the inventory
        btnInven.setOnAction(e -> printing(e, txtOput));
        // adding btns to btns HBox
        btnSelection.getChildren().addAll(btnRest, btnSave, btnInven);
        btnSelection.setSpacing(10);
        btnSelection.setPadding(new Insets(20, 15, 15, 15));
        btnSelection.setAlignment(Pos.CENTER);
        btnSelection.setId("btnSelection");
        
        //creating a vbox
        VBox selectbox = new VBox(5, cmbFlv, priceBox, quantBox);
        selectbox.setAlignment(Pos.CENTER_RIGHT);
        selectbox.setMaxWidth(250);
        // adding eeverthing except bottton btn to selection Vbox
        slectionArea.getChildren().addAll(lblTitle, selectbox, txtOput);
        slectionArea.setId("slectionArea");
        ioBox.getChildren().addAll(btnSelection);
        ioBox.setPrefSize(130, 25);
        ioBox.setAlignment(Pos.BOTTOM_CENTER);

        // setting the position od the vbox in the main pane 
        root.setTop(menubar);
        root.setCenter(slectionArea);
        root.setBottom(ioBox);
        Scene scene = new Scene(root, 500, 500);
        root.getStylesheets().add("store.css");
        primaryStage.setTitle("Assignment 5");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    // all the methods that are being used are below

    /**
     * this methods exits the program if the right nenuBar is selected
     *
     * @param e object of actionevent class
     * @param exit object of menuItem
     */
    private void eventCode(ActionEvent e, MenuItem exit) {
        if (e.getSource() == exit) {
            System.exit(0);
        }
    }

    /**
     * this methods clear every Input field on the GUI
     *
     * @param e the Object of ActionEvent use to set the btn in GUI to method
     * @param btnre object of Button class that will be used to clear the inputs
     * @param txtOut object of the textarea that is used to clear what ever is
     * on it
     * @param txtP object of textfield that will be used to erase any input
     * entered
     * @param txtQ object of textfield that will be used to erase any input
     * entered
     * @param comb object of combobox used to reset the combobox in the gui
     */
    private void clearEvery(ActionEvent e, Button btnre, TextArea txtOut,
            TextField txtP, TextField txtQ, ComboBox comb, 
                                                Label price, Label quan) {
        if (e.getSource() == btnre) {
            txtOut.clear();
            txtP.clear();
            txtQ.clear();
            comb.setValue(null);
            price.setId("good");
            quan.setId("good2");
            
        }
    }

    /**
     * this method is used to print the record which is uses iceCream object to
     * calls its methods that hold the records
     *
     * @param e object of the ActionEvent that is used to get the sources of the
     * event
     * @param text object of textarea that where the record will be printed
     */
    private void printing(ActionEvent e, TextArea text) {
        text.setText(iceInventory.printRecord());
    }

    /**
     * this methods gets the info that is need for the icecream to be added in
     * the icecream list
     *
     * @param flv object of iceCreamFlavour class that is used to determinted
     * what flavour is selected by the user
     * @param pricing the pricing of the icecream input by the user
     * @param qunatity the quantity of icecream input by the user
     * @throws IOException
     */
    private void buildingRecord(IceCreamFlavour flv, double pricing,
            int qunatity) throws IOException {
        iceCream.setFlavour(flv);
        iceCream.setPrice(pricing);
        iceCream.setQuantity(qunatity);
        iceInventory.writeRecord(iceCream);
    }

    /**
     * check if the price that user enter is valid double or no changes the
     * price label accordingly
     *
     * @param txtPrice textarea object where any error will be input
     * @return the double the user entered
     */
    private double checkPrice(TextField txtPrice, Label lblprice) {
        double checking = 0;
        try {
            checking = Double.parseDouble(txtPrice.getText());
        } catch (Exception e) {
            lblprice.setId("error");
            throw new InputMismatchException("invalid.......");
        }
        if (checking <= 0) {
            lblprice.setId("error");
            throw new InputMismatchException("negative Not excepted........");
        } else {
            return checking;
        }
    }

    /**
     * check if the quantity that user enter is valid double or no changes the
     * price label accordingly
     *
     * @param txtQun textarea object where any error will be input
     * @return the integer the user entered
     */
    private int checkQunt(TextField txtQun, Label lblquan) {
        int checking = 0;
        try {
            checking = Integer.parseInt(txtQun.getText());
        } catch (Exception e) {
            lblquan.setId("errors");
            throw new InputMismatchException("invalid.......");
        }
        if (checking <= 0) {
            lblquan.setId("errors");
            throw new InputMismatchException("negative Not excepted........");
        } else {
            return checking;
        }
    }

    /**
     * this is the method the process the info entered by the user
     *
     * @param e object of the ActionEvent that is used to find the source of the
     * actionEvent
     * @param combo checks if any flavour is selected if not sends an error
     * message
     * @param txt this is where the error or notificaation of the event are
     * printed
     * @param priceing this is used to set the price of the icecream
     * @param quant this is used to set the quantity of the icecream
     * @throws IOException if any checkbox is not selected
     */
    private void procees(ActionEvent e, ComboBox combo, TextArea txt,
            double priceing, int quant, Label price, Label quan) 
                                                           throws IOException {
        int checker = combo.getSelectionModel().getSelectedIndex();
        
        if (checker == -1) {
            txt.setText("no Flavour selected");
        } else {
            switch (checker) {
                case 0:
                    buildingRecord(IceCreamFlavour.VANILLA, priceing, quant);
                    break;
                case 1:
                    buildingRecord(IceCreamFlavour.CHOCOLATE, priceing, quant);
                    break;
                case 2:
                    buildingRecord(IceCreamFlavour.MANGO, priceing, quant);
                    break;
                case 3:
                    buildingRecord(IceCreamFlavour.STRAWBERRY, priceing, quant);
                    break;
                case 4:
                buildingRecord(IceCreamFlavour.BUTTER_PERCAN, priceing, quant);
                    break;
                case 5:
                    buildingRecord(IceCreamFlavour.MOOSE, priceing, quant);
                    break;
                case 6:
                    buildingRecord(IceCreamFlavour.RASBERRY, priceing, quant);
                    break;
            }
            txt.setText("record saved....");
            price.setId("good");
            quan.setId("good2");
        }
    }

}
