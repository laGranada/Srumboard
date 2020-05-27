/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scrumboard;

import de.scrumboard.entity.Employee;
import de.scrumboard.entity.Project;
import de.scrumboard.entity.Status;
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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

/**
 *
 * @author MaggieSaurusRex
 */
public class FXMLDocumentController implements Initializable {
    
    ScrumboardDaoStub dao = new ScrumboardDaoStub();
    
    @FXML
    private ChoiceBox<Project> projects;
    
    @FXML
    private Button selectBtn;
    //TableView for employee
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    
    //TreeTable for toDo
    @FXML
    private TableView<Task> toDoTable;
    @FXML
    private TableColumn<Task, String> toDoColumnDescription;
    @FXML
    private TableColumn<Task, String> toDoColumnEditor;


    //TreeTable for inProgress
    @FXML
    private TableView<Task> inProgressTable;
    @FXML
    private TableColumn<Task, String> inProgressColumnDescription;
    @FXML
    private TableColumn<Task, String> inProgressColumnEditor;

    
    //TreeTable for toVerify
    @FXML
    private TableView<Task> toVerifyTable;
    @FXML
    private TableColumn<Task, String> toVerifyColumnDescription;
    @FXML
    private TableColumn<Task, String> toVerifyColumnEditor;

    
    //TreeTable for done
    @FXML
    private TableView<Task> doneTable;
    @FXML
    private TableColumn<Task, String> doneColumnDescription;
    private TableColumn<Task, String> doneColumnEditor;
    
    
    private List<Project> projectList;
    
    private Project selectedProject;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");

    }
    
    @FXML //loadData
    private void handleChoiceBoxAction(ActionEvent event){
        selectedProject = projects.getValue();
        initiateCols();
        
        //for employeeTable
        employeeTable.getItems().addAll(FXCollections.observableArrayList(selectedProject.getEmployees()));
        
        //for toDoTable
        toDoTable.getItems().addAll(FXCollections.observableArrayList(selectedProject.findTasksByStatus(Status.TO_DO)));
        
        //for inProgressTable
        inProgressTable.getItems().addAll(FXCollections.observableArrayList(selectedProject.findTasksByStatus(Status.IN_PROGRESS)));
        
        //for toVerifyTable
        toVerifyTable.getItems().addAll(FXCollections.observableArrayList(selectedProject.findTasksByStatus(Status.TO_VERIFY)));
        
        //for doneTable
        doneTable.getItems().addAll(FXCollections.observableArrayList(selectedProject.findTasksByStatus(Status.DONE)));
    }
    
    private void initiateCols(){
        //for employeeTable
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));   
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));   
        
        //for toDoTable
        toDoColumnDescription.setCellValueFactory(new PropertyValueFactory<>("stringForTable"));
        toDoColumnEditor.setCellValueFactory(new PropertyValueFactory<>("editor"));
        
        //for inProgressTable
        inProgressColumnDescription.setCellValueFactory(new PropertyValueFactory<>("stringForTable"));
        inProgressColumnEditor.setCellValueFactory(new PropertyValueFactory<>("editor"));
        
        //for toVerifyTable
        toVerifyColumnDescription.setCellValueFactory(new PropertyValueFactory<>("stringForTable"));
        toVerifyColumnEditor.setCellValueFactory(new PropertyValueFactory<>("editor"));

        //for doneTable
        doneColumnDescription.setCellValueFactory(new PropertyValueFactory<>("stringForTable"));
        doneColumnEditor.setCellValueFactory(new PropertyValueFactory<>("editor"));

        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        projectList = dao.findAllProjects();
        projects.setItems(FXCollections.observableArrayList(projectList));
    }    
    
}
