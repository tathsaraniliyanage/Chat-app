package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * @author Praboda Thathsarani
 * @Project Chat-app
 */
public class LoginController {


    public TextField txtUserName;

    public void btnLoginOnAction(ActionEvent actionEvent) {

        Thread thread =new Thread(() -> {

        });

            try {
                URL resource = LoginController.class.getResource("/view/ChatRoomController.fxml");
                Parent parent = FXMLLoader.load(resource);
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setAlwaysOnTop(true);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ChatRoomController.getController().txtUserText.setText(txtUserName.getText());
    }
}
