package lk.ijse.controller;

import javafx.scene.text.Text;

/**
 * @author Sasindu Malshan
 * @project Chat-app
 * @date 1/25/2024
 */

public class ContactUserController {
    public Text userName;
    public void setData(String user) {
        userName.setText(user);
    }
}
