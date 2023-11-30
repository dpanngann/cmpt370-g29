import employeeMana.Employee;
import employeeMana.EmployeeList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import timeMana.Project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.Duration;


public class Registration extends BasicPage {

    final double scene_width = 800;
    final double scene_height = 500;

    private Label errorLabel = new Label();
    private LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#4B1517")),
            new Stop(1, Color.web("#C49102")));

    @Override
    public void start(Stage stage) {
//        stage.getIcons().add(new Image("./resources/icon.png"));

        // Hello label message
        Label helloLabel = new Label("Registration");
        helloLabel.setTextFill(Color.WHITE);
        helloLabel.setFont(new Font(Font.getFontNames().get(1), 36));
        HBox helloCentre = new HBox(); // HBox to centre the greeting label
        helloCentre.setAlignment(Pos.CENTER);
        helloCentre.getChildren().add(helloLabel);

        // First Name label that prompts text field
        Label fnameLabel = new Label("First Name: ");
        TextField fnameField = new TextField();
        fnameField.setPromptText("Enter your First Name: ");


        // First Name label that prompts text field
        Label lnameLabel = new Label("Last Name: ");
        TextField lnameField = new TextField();
        lnameField.setPromptText("Enter your Last Name");

        // Username label that prompts text field
        Label userLabel = new Label("Username: ");
        TextField userField = new TextField();
        userField.setPromptText("Enter your username");

        // Password label that prompts password field
        Label passLabel = new Label("Password: ");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Enter your password");

        // Verify Password label that prompts password field
        Label verifyPassLabel = new Label("Confirm your Password: ");
        PasswordField verifyPassField = new PasswordField();
        verifyPassField.setPromptText("Confirm your Password");

        // Email label that prompts text field
        Label emailLabel = new Label("Email: ");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your Email");

        // Cell number label that prompts text field
        Label cellLabel = new Label("Cell: ");
        TextField cellField = new TextField();
        cellField.setPromptText("Enter your Cellphone #");

        // Log in button
        Button registerButton = new Button("Complete Registration");
        registerButton.setFont(new Font(18));
        HBox logInCentre = new HBox(); // HBox to centre button
        logInCentre.setAlignment(Pos.CENTER);
        logInCentre.getChildren().add(registerButton);



        // Set action for the register button
        registerButton.setOnAction(event -> {

            if (isDuplicate(userField.getText(),"username")){
                showAlert("Duplication Error!","Employee with this username already exist");
            }
            else if (passField.getText().length()<8){
                showAlert("Invalidation Input Error!","Password should be at least 8 digits");
            }
            else if(!passField.getText().equals(verifyPassField.getText())){
                showAlert("Invalidation Input Error!","Please confirm your password!");
            }
            else if(!emailField.getText().contains("@")||emailField.getText().length()<4||!emailField.getText().contains(".")){
                showAlert("Invalidation Input Error!","Please make sure Email is valid!");
            }
            else if(isDuplicate(emailField.getText(),"email")){
                showAlert("Duplication Error", "Employee with the email already exist!");
            }
            else if(cellField.getText().length()!= 10){
                showAlert("Invalidation Input Error!", "Please make sure Cell Number is valid North America number (10 digits)");
            }
            else if(isDuplicate(EmployeeList.formatCell(cellField.getText()),"cell")){
                showAlert("Duplication Error", "Employee with the cell number already exist!");
            }
            else {
                try {
                    register(fnameField.getText(), lnameField.getText(), userField.getText(), passField.getText(), emailField.getText(), EmployeeList.formatCell(cellField.getText()));
                    stage.close();
                    // Launch the registration page
                    try {
                        new Login().start(new Stage());
                    } catch (Exception e) {
                        showAlert("Error!",e.toString());
                    }
                } catch (IOException e) {
                    showAlert("Error!",e.toString());
                } catch (InterruptedException e) {
                    showAlert("Error!",e.toString());
                }
            }

        });

        // Registration button for new users
        Button newButton = new Button("Return to Login");

        // Set action for the registration button
        newButton.setOnAction(event -> {
            // Close the login stage
            stage.close();
            // Launch the registration page
            try {
                new Login().start(new Stage());
            } catch (Exception e) {
                System.out.println("Something went wrong when going into registration page.");
            }
        });

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(helloCentre, fnameLabel, fnameField, lnameLabel, lnameField, userLabel, userField, passLabel, passField, verifyPassLabel, verifyPassField, emailLabel, emailField, cellLabel, cellField, logInCentre, newButton, errorLabel);

        root.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));
        for (Node node: root.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setTextFill(Color.WHITE);
            }
        }

        Scene scene = new Scene(root, 500, 800);
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();


    }

    private static void register(String fname, String lname, String username, String password, String email, String cellNumber) throws IOException, InterruptedException {

        String msg = "{" +
                "\"username\":\"" + username + "\"," +
                "\"password\":\"" + password + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"cellNumber\":\"" + cellNumber + "\"," +
                "\"fname\":\"" + fname + "\"," +
                "\"lname\":\"" + lname + "\"" +
                "}";

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:5001/register"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(msg, StandardCharsets.UTF_8))
                .build();

        System.out.println("[REGISTRATION]: " + request.toString());
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("[REGISTRATION]: " +response.body());
    }

    private void displayErrorMessage(String message) {
        errorLabel.setText(message);
        errorLabel.setTextFill(Color.RED);
    }

    private void showAlert(String title, String content) {
        Alert invalidNumAlert = new Alert(Alert.AlertType.ERROR);
        invalidNumAlert.setTitle(title);
        invalidNumAlert.setHeaderText(null);
        invalidNumAlert.setContentText(content);
        invalidNumAlert.showAndWait();
    }

    private boolean isDuplicate(String input, String attributeType) {
        input = input.toLowerCase();
        if (EmployeeList.data == null){
            return false;
        }
        for (Employee employee : EmployeeList.data) {
            switch (attributeType) {
                case "cell":
                    if (employee.getCell().equals(input)) {
                        return true;
                    }
                    break;
                case "email":
                    if (employee.getEMail().toLowerCase().equals(input)) {
                        return true;
                    }
                    break;
                case "username":
                    if (employee.getUsername().toLowerCase().equals(input)) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
