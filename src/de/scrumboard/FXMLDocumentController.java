/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scrumboard;

import de.scrumboard.entity.Employee;
import de.scrumboard.entity.Project;
import de.scrumboard.entity.Task;
import de.scrumboard.service.stub.ScrumboardDaoStub;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author MaggieSaurusRex
 */
public class FXMLDocumentController implements Initializable {
    
    ScrumboardDaoStub dao = new ScrumboardDaoStub();
    
    @FXML
    private ChoiceBox<Project> projects;
    
    @FXML
    private Button selectBtn = new Button();
    
    @FXML
    private TableView<Employee> employeeTable = new TableView<>();
    @FXML
    private TableColumn<Employee, String> employeeColumn = new TableColumn<>();
    
    @FXML
    private TreeTableView<Task> toDoTable = new TreeTableView<>();
    @FXML
    private TreeTableView<Task> inProgressTable = new TreeTableView<>();
    @FXML
    private TreeTableView<Task> toVerifyTable = new TreeTableView<>();
    @FXML
    private TreeTableView<Task> doneTable = new TreeTableView<>();
    
    private List<Project> projectList;
    
    private Project selectedProject;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");

    }
    
    @FXML
    private void handleChoiceBoxAction(ActionEvent event){
        selectedProject = projects.getValue();
        System.out.println("Size of employees = " + selectedProject.getEmployees().size());
        employeeTable.setItems(FXCollections.observableArrayList(selectedProject.getEmployees()));
        //employeeTable.getItems().addAll(FXCollections.observableArrayList(selectedProject.getEmployees()));
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("firstName" + "lastName"));
       
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        projectList = dao.findAllProjects();
        projects.setItems(FXCollections.observableArrayList(projectList));
        employeeTable.getColumns().add(employeeColumn);
    }    
    
}
