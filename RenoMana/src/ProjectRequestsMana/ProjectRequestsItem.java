//package ProjectRequestsMana;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import javafx.beans.property.SimpleStringProperty;
//
//public class ProjectRequestsItem {
//    private SimpleStringProperty customerName;
//    private SimpleStringProperty customerEmail;
//    private SimpleStringProperty customerCell;
//    private SimpleStringProperty company;
//    private SimpleStringProperty startDate;
//    private SimpleStringProperty endDate;
//    private SimpleStringProperty projectDesc;
//    private SimpleStringProperty projectInq;
//
//
//    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
//    public ProjectRequestsItem(@JsonProperty("customerName") String customerName,
//                               @JsonProperty("customerEmail") String customerEmail,
//                               @JsonProperty("customerCell") String customerCell,
//                               @JsonProperty("company") String company,
//                               @JsonProperty("startDate") String startDate,
//                               @JsonProperty("endDate") String endDate,
//                               @JsonProperty("projectDesc") String projectDesc,
//                               @JsonProperty("projectInq") String projectInq)
//    {
//        this.customerName =new SimpleStringProperty(customerName);
//        this.customerEmail = new SimpleStringProperty(customerEmail);
//        this.customerCell = new SimpleStringProperty(customerCell);
//        this.company = new SimpleStringProperty(company);
//        this.startDate = new SimpleStringProperty(startDate);
//        this.endDate = new SimpleStringProperty(endDate);
//        this.projectDesc = new SimpleStringProperty(projectDesc);
//        this.projectInq = new SimpleStringProperty(projectInq);
//    }
//
//    public ProjectRequestsItem() {}
//    public SimpleStringProperty Name() { return customerName; }
//
//    public SimpleStringProperty Email() {
//        return customerEmail;
//    }
//
//    public SimpleStringProperty Contact() {
//        return customerCell;
//    }
//
//    public SimpleStringProperty Company() {
//        return company;
//    }
//
//    public SimpleStringProperty Start_date() {return startDate;}
//
//    public SimpleStringProperty End_date() {
//        return endDate;
//    }
//
//    public SimpleStringProperty Description() {
//        return projectDesc;
//    }
//
//    public SimpleStringProperty Inquiry() {
//        return projectInq;
//    }
//}

package ProjectRequestsMana;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
public class ProjectRequestsItem {
    private SimpleStringProperty customerName;
    private SimpleStringProperty customerEmail;
    private SimpleStringProperty customerCell;
    private SimpleStringProperty company;
    private SimpleStringProperty startDate;
    private SimpleStringProperty endDate;
    private SimpleStringProperty projectDesc;
    private SimpleStringProperty projectInq;
    private BooleanProperty accepted;
    private int id;
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProjectRequestsItem(@JsonProperty("customerName") String customerName,
                               @JsonProperty("customerEmail") String customerEmail,
                               @JsonProperty("customerCell") String customerCell,
                               @JsonProperty("company") String company,
                               @JsonProperty("startDate") String startDate,
                               @JsonProperty("endDate") String endDate,
                               @JsonProperty("projectDesc") String projectDesc,
                               @JsonProperty("projectInq") String projectInq,
                               @JsonProperty("ID") int id)
    {
        this.customerName =new SimpleStringProperty(customerName);
        this.customerEmail = new SimpleStringProperty(customerEmail);
        this.customerCell = new SimpleStringProperty(customerCell);
        this.company = new SimpleStringProperty(company);
        this.startDate = new SimpleStringProperty(startDate);
        this.endDate = new SimpleStringProperty(endDate);
        this.projectDesc = new SimpleStringProperty(projectDesc);
        this.projectInq = new SimpleStringProperty(projectInq);
        this.accepted = new SimpleBooleanProperty(false);
        this.id = id;
    }
    public ProjectRequestsItem() {}
    public SimpleStringProperty Name() { return customerName; }
    public SimpleStringProperty Email() {
        return customerEmail;
    }
    public SimpleStringProperty Contact() {
        return customerCell;
    }
    public SimpleStringProperty Company() {
        return company;
    }
    public SimpleStringProperty Start_date() {return startDate;}
    public SimpleStringProperty End_date() {
        return endDate;
    }
    public SimpleStringProperty Description() {
        return projectDesc;
    }
    public SimpleStringProperty Inquiry() {
        return projectInq;
    }

    public BooleanProperty acceptedProperty() {
        return accepted;
    }

    public boolean isAccepted() {
        return accepted.get();
    }

    public void setAccepted(boolean accepted) {
        this.accepted.set(accepted);
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id; }

}
