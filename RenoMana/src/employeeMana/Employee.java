package employeeMana;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import timeMana.Project;

import java.util.List;

public class Employee {

    private SimpleStringProperty employeeID;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty cellNumber;
    private SimpleStringProperty eMail;
    private ObservableList<Project> projects;
    private SimpleStringProperty title;

    public Employee(SimpleStringProperty employeeID, SimpleStringProperty firstName,
                    SimpleStringProperty lastName, ObservableList<Project> projects,
                    SimpleStringProperty cellNumber,SimpleStringProperty eMail,SimpleStringProperty title){
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellNumber = cellNumber;
        this.eMail = eMail;
        this.projects = projects;
        this.title = title;
    }

    public String getEmployeeFirstName() {

        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {

        return firstName;
    }

    public String getEmployeeLastName() {

        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {

        return lastName;
    }

    public String getEmployeeID() {
        return employeeID.get();
    }

    public SimpleStringProperty employeeIDProperty() {
        return employeeID;
    }

    public ObservableList<Project> getProjects() {
        return projects;
    }

    public void addProject2Employee(Project project){
        projects.add(project);
    }
    public void removeProject2Employee(Project project){
        projects.remove(project);
    }

    public String getCell() {return cellNumber.get();}

    public SimpleStringProperty cellProperty() {
        return cellNumber;
    }

    public String getEMail() {return eMail.get();}

    public SimpleStringProperty eMailProperty() {
        return eMail;
    }

    public String getTitle() {return title.get();}

    public SimpleStringProperty titleProperty() {
        return title;
    }


}

