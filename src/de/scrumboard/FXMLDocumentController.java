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
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

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
    private TableColumn<Task, String> toDoColumnTask;
    @FXML
    private TableColumn<Task, String> toDoColumnDescription;
    @FXML
    private TableColumn<Task, String> toDoColumnEditor;


    //TreeTable for inProgress
    @FXML
    private TableView<Task> inProgressTable;
    @FXML
    private TableColumn<Task, String> inProgressColumnTask;
    @FXML
    private TableColumn<Task, String> inProgressColumnDescription;
    @FXML
    private TableColumn<Task, String> inProgressColumnEditor;

    
    //TreeTable for toVerify
    @FXML
    private TableView<Task> toVerifyTable;
    @FXML
    private TableColumn<Task, String> toVerifyColumnTask;
    @FXML
    private TableColumn<Task, String> toVerifyColumnDescription;
    @FXML
    private TableColumn<Task, String> toVerifyColumnEditor;

    
    //TreeTable for done
    @FXML
    private TableView<Task> doneTable;
    @FXML
    private TableColumn<Task, String> doneColumnTask;
    @FXML
    private TableColumn<Task, String> doneColumnDescription;
//    @FXML
//    private TableColumn<Task, String> doneColumnEditor;
    @FXML
    private TableColumn<Task, ChoiceBox<Employee>> doneColumnEditor;
    
    
    private List<Project> projectList;
    
    private Project selectedProject;
    
    private static final DataFormat TaskDataFormat = new DataFormat("de.scrumboard.entity.Task");
    private static final DataFormat EmployeeDataFormat = new DataFormat("de.scrumboard.entity.Employee");
    
    @FXML 
    private void loadTableData(ActionEvent event){
        selectedProject = projects.getValue();
        initiateCols();
        
        //for employeeTable
        employeeTable.getItems().clear();
        employeeTable.getItems().addAll(FXCollections.observableArrayList(selectedProject.getEmployees()));
        
        //for toDoTable
        toDoTable.getItems().clear();
        toDoTable.getItems().addAll(FXCollections.observableArrayList(selectedProject.findTasksByStatus(Status.TO_DO)));
        
        //for inProgressTable
        inProgressTable.getItems().clear();
        inProgressTable.getItems().addAll(FXCollections.observableArrayList(selectedProject.findTasksByStatus(Status.IN_PROGRESS)));
        
        //for toVerifyTable
        toVerifyTable.getItems().clear();
        toVerifyTable.getItems().addAll(FXCollections.observableArrayList(selectedProject.findTasksByStatus(Status.TO_VERIFY)));
        
        //for doneTable
        doneTable.getItems().clear();
        doneTable.getItems().addAll(FXCollections.observableArrayList(selectedProject.findTasksByStatus(Status.DONE)));
    }
    
    private void initiateCols(){
        //for employeeTable
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));   
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));   
        
        //for toDoTable
        toDoColumnTask.setCellValueFactory(new PropertyValueFactory<>("name"));
        toDoColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        toDoColumnEditor.setCellValueFactory(new PropertyValueFactory<>("editor"));
        
        //for inProgressTable
        inProgressColumnTask.setCellValueFactory(new PropertyValueFactory<>("name"));
        inProgressColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        inProgressColumnEditor.setCellValueFactory(new PropertyValueFactory<>("editor"));
        
        //for toVerifyTable
        toVerifyColumnTask.setCellValueFactory(new PropertyValueFactory<>("name"));
        toVerifyColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        toVerifyColumnEditor.setCellValueFactory(new PropertyValueFactory<>("editor"));

        //for doneTable
        doneColumnTask.setCellValueFactory(new PropertyValueFactory<>("name"));
        doneColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        doneColumnEditor.setCellValueFactory(new PropertyValueFactory<>("editor"));  
    }
    
    //<editor-fold defaultstate="collapsed" desc="Methods for Drag and Drop">
    @FXML
    private void handleDragDetectedEmployeeTable(MouseEvent event){
        Dragboard db = toDoTable.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        //save the selected object as ClipboardContent
        Employee e = employeeTable.focusModelProperty().get().getFocusedItem();
        cb.put(EmployeeDataFormat, e);

        //set the dragboard content
        db.setContent(cb);
    }
    
    @FXML
    private void handleDragDetectedToDoTable(MouseEvent event){
        Dragboard db = toDoTable.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        //save the selected object as ClipboardContent
        Task t = toDoTable.focusModelProperty().get().getFocusedItem();
        cb.put(TaskDataFormat, t);

        //set the dragboard content
        db.setContent(cb);
        
        //remove the selected task from itemlist
        toDoTable.getItems().remove(t);
        toDoTable.refresh();
    }
    @FXML
    private void handleDragDetectedInProgressTable(MouseEvent event){
        Dragboard db = inProgressTable.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        //save the selected object as ClipboardContent
        Task t = inProgressTable.focusModelProperty().get().getFocusedItem();
        cb.put(TaskDataFormat, t);

        //ste the dragboard content
        db.setContent(cb);
        
        //remove the selected task from itemlist
        inProgressTable.getItems().remove(t);
        inProgressTable.refresh();
    }
    @FXML
    private void handleDragDetectedToVerifyTable(MouseEvent event){
        Dragboard db = toVerifyTable.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        //save the selected object as ClipboardContent
        Task t = toVerifyTable.focusModelProperty().get().getFocusedItem();
        cb.put(TaskDataFormat, t);

        //ste the dragboard content
        db.setContent(cb);
        
        //remove the selected task from itemlist
        toVerifyTable.getItems().remove(t);
        toVerifyTable.refresh();
    }
    @FXML
    private void handleDragDetectedDoneTable(MouseEvent event){
        Dragboard db = doneTable.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        //save the selected object as ClipboardContent
        Task t = doneTable.focusModelProperty().get().getFocusedItem();
        cb.put(TaskDataFormat, t);

        //ste the dragboard content
        db.setContent(cb);
        
        //remove the selected task from itemlist
        doneTable.getItems().remove(t);
        doneTable.refresh();
    }
    
    @FXML
    private void handleDragOver(DragEvent event){
        if(event.getDragboard().hasContent(TaskDataFormat)) event.acceptTransferModes(TransferMode.ANY);
    }
    
    @FXML
    private void handleDragDroppedToDoTable(DragEvent event){
        if(event.getDragboard().hasContent(TaskDataFormat)){
            //get the Task from dragboard
            Task t = (Task) event.getDragboard().getContent(TaskDataFormat);

            //Change Task status to TO_DO and update task
            t.setStatus(Status.TO_DO);
            dao.updateTask(t);

            //Add Task to table
            toDoTable.getItems().add(t);
            toDoTable.refresh();
        }
    }
    
    @FXML
    private void handleDragDroppedInProgressTable(DragEvent event){
        if(event.getDragboard().hasContent(TaskDataFormat)){
            //get the Task from dragboard
            Task t = (Task) event.getDragboard().getContent(TaskDataFormat);

            //Change Task status to IN_PROGRESS and update task
            t.setStatus(Status.IN_PROGRESS);
            dao.updateTask(t);

            //Add Task to table
            inProgressTable.getItems().add(t);
            inProgressTable.refresh();
        }
    }
    @FXML
    private void handleDragDroppedToVerifyTable(DragEvent event){
        if(event.getDragboard().hasContent(TaskDataFormat)){
            //get the Task from dragboard
            Task t = (Task) event.getDragboard().getContent(TaskDataFormat);

            //Change Task status to TO_VERIFY and update task
            t.setStatus(Status.TO_VERIFY);
            dao.updateTask(t);

            //Add Task to table
            toVerifyTable.getItems().add(t);
            toVerifyTable.refresh();
        }
    }
    @FXML
    private void handleDragDroppedDoneTable(DragEvent event){
        if(event.getDragboard().hasContent(TaskDataFormat)){
            //get the Task from dragboard
            Task t = (Task) event.getDragboard().getContent(TaskDataFormat);

            //Change Task status to Done and update task
            t.setStatus(Status.DONE);
            dao.updateTask(t);

            //Add Task to table
            doneTable.getItems().add(t);
            doneTable.refresh();
        }
    }
    //</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Methods for table edit">
    /**
     * This method will allow the user to double click on a cell and update
     * the first name of the person
     */
    public void changeFirstNameCellEvent(CellEditEvent edittedCell){
        Employee employeeSelected =  employeeTable.getSelectionModel().getSelectedItem();
        employeeSelected.setFirstName(edittedCell.getNewValue().toString());
        dao.updateEmployee(employeeSelected);
    }
    /**
     * This method will allow the user to double click on a cell and update
     * the last name of the person
     */
    public void changeLastNameCellEvent(CellEditEvent edittedCell){
        Employee employeeSelected =  employeeTable.getSelectionModel().getSelectedItem();
        employeeSelected.setLastName(edittedCell.getNewValue().toString());
        dao.updateEmployee(employeeSelected);
    }

    //</editor-fold> 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        projectList = dao.findAllProjects();
        System.out.println("Size of project list = " + projectList.size());
        projects.setItems(FXCollections.observableArrayList(projectList));
        System.out.println("Size of project list = " + projects.getItems().toString());
        
        //allow the employeeTable and all his columns to be editable
        employeeTable.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
        //allow the toDoTable to be editable
        toDoTable.setEditable(true);
        toDoColumnTask.setCellFactory(TextFieldTableCell.forTableColumn());
        toDoColumnDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        
        //allow the inProgressTable to be editable
        inProgressTable.setEditable(true);
        inProgressColumnTask.setCellFactory(TextFieldTableCell.forTableColumn());
        inProgressColumnDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        
        //allow the toVerifyTable to be editable
        toVerifyTable.setEditable(true);
        toVerifyColumnTask.setCellFactory(TextFieldTableCell.forTableColumn());
        toVerifyColumnDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        
        //allow the doneTable to be editable
        doneTable.setEditable(true);
        doneColumnTask.setCellFactory(TextFieldTableCell.forTableColumn());
        doneColumnDescription.setCellFactory(TextFieldTableCell.forTableColumn());
    }    
    
}
