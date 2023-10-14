package inventoryMana;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
public class Inventory extends VBox {

    private TableView<InventoryItem> inventoryTable;
    private ObservableList<InventoryItem> data;

     public Inventory(){
         // Setting up the table
         inventoryTable = new TableView<>();
         inventoryTable.prefWidthProperty().bind(this.widthProperty());
         data = FXCollections.observableArrayList();

         // Adding column names
         TableColumn<InventoryItem, String> toolNameCol = new TableColumn<>("Tool Name");
         toolNameCol.setCellValueFactory(cellData -> cellData.getValue().toolNameProperty());
         toolNameCol.prefWidthProperty().bind(inventoryTable.widthProperty().multiply(0.3)); // 40% width


         TableColumn<InventoryItem, Integer> quantityCol = new TableColumn<>("Total Quantity");
         quantityCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
         quantityCol.prefWidthProperty().bind(inventoryTable.widthProperty().multiply(0.3)); // 30% width


         TableColumn<InventoryItem, Integer> estCol = new TableColumn<>("Estimation for Project");
         estCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
         estCol.prefWidthProperty().bind(inventoryTable.widthProperty().multiply(0.3)); // 30% width


         inventoryTable.getColumns().addAll(toolNameCol, quantityCol, estCol);
         inventoryTable.setItems(data);


         // Setting up the button options for things user can do in this tab
         Button addItem = new Button("Add");
         addItem.setOnAction(actionEvent -> addInventoryItem());

         Button deleteItem = new Button("Delete");
         deleteItem.setOnAction(actionEvent -> deleteInventoryItem());

         Button modifyItem = new Button("Modify");
         modifyItem.setOnAction(actionEvent -> {modifyInventoryItem();});

         Button importFile = new Button("Import");
         modifyItem.setOnAction(actionEvent -> {importInventoryFile();});

         Button exportFile = new Button("Export");
         exportFile.setOnAction(actionEvent -> {exportInventoryFile();});


         HBox optButton = new HBox(10, addItem, deleteItem, modifyItem, importFile, exportFile);
         optButton.setPadding(new Insets(10, 0, 10, 0)); // top, right, bottom, left padding


         this.getChildren().addAll(inventoryTable, optButton);
         VBox.setVgrow(inventoryTable, Priority.ALWAYS);
     }

    private void exportInventoryFile() {
    }

    private void importInventoryFile() {
    }

    private void modifyInventoryItem() {
    }

    private void deleteInventoryItem() {
    }

    private void addInventoryItem() {
         // Gather user input for tool, quantity, and estimation values
        TextInputDialog toolInput = new TextInputDialog();
        toolInput.setTitle("Add New Tool");
        toolInput.setHeaderText("Enter Tool Name");
        String toolName = toolInput.showAndWait().orElse("");

        // Error Handling: If tool name is already in the table
        for (InventoryItem item : this.data) {
            if (item.getToolName().equals(toolName)){
                Alert duplicateAlert = new Alert(Alert.AlertType.ERROR);
                duplicateAlert.setTitle("Error!");
                duplicateAlert.setHeaderText("Tool already exists!");
                duplicateAlert.setContentText("Please use the modify button to change the existing tool's " +
                        "details instead!");
                duplicateAlert.showAndWait();
                return;
            }
        }

        int quantity = 0;
        int estimation = 0;

        try {
            TextInputDialog quantityInput = new TextInputDialog("0");
            quantityInput.setHeaderText("Enter Quality");
            quantity = Integer.parseInt(quantityInput.showAndWait().orElse("Invalid Input!"));

            if (quantity < 0) {
                throw new NumberFormatException();
            }

            TextInputDialog estimateInput = new TextInputDialog("0");
            estimateInput.setHeaderText("Enter Estimate");
            estimation = Integer.parseInt(estimateInput.showAndWait().orElse("Invalid Input!"));

            if (estimation < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Alert invalidNum = new Alert(Alert.AlertType.ERROR);
            invalidNum.setTitle("Error!");
            invalidNum.setHeaderText("Invalid Input!");
            invalidNum.setContentText("Please enter a valid positive integer for quantity and estimation.");
            invalidNum.showAndWait();
            return;
        }

        // Create the InventoryItem
        InventoryItem newItem = new InventoryItem(new SimpleStringProperty(toolName),
                new SimpleIntegerProperty(quantity), new SimpleIntegerProperty(estimation));

        this.data.add(newItem);
        inventoryTable.refresh();

    }

}
