/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.scrumboard;

import de.scrumboard.entity.Employee;
import de.scrumboard.service.stub.ScrumboardDaoStub;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MaggieSaurusRex
 */
public class FXML_LoginController implements Initializable {
    
    ScrumboardDaoStub dao = new ScrumboardDaoStub();

    @FXML
    private Label errorLabel;
    
    @FXML
    private TextField usernameTxtField;
    
    @FXML
    private PasswordField pwField;
    
    @FXML
    private Button loginBtn;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception{
        //TODO errorLabel if username or password empty
        Employee emp = dao.findEmployeeByUsername(usernameTxtField.getText());
        if(emp.getPassword().equals(pwField.getText())){
            Parent homePageParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene homePageScene = new Scene(homePageParent);

            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
        }else{
            errorLabel.setText("Incorrect username or password!");
        }    
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
