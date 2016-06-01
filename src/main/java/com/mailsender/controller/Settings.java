package com.mailsender.controller;

import com.mailsender.MainApp;
import com.mailsender.core.MainApplication;
import com.mailsender.settings.EmailSettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Settings {

    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private TextField smtp;
    @FXML
    private TextField port;
    @FXML
    private Button saveBtn;

    @FXML
    public void saveAction(ActionEvent event) {
        EmailSettings emailSettings = new EmailSettings();
        emailSettings.login = login.getText();
        emailSettings.password = password.getText();
        emailSettings.smtp = smtp.getText();
        emailSettings.port = Integer.parseInt(port.getText());
        MainApp.saveSettings(emailSettings);
        MainApplication.getInstance().closeSettingsDialog();
    }

    @FXML
    public void cancellAction(ActionEvent event) {
        MainApplication.getInstance().closeSettingsDialog();
    }

    public TextField getLogin() {
        return login;
    }

    public void setLogin(TextField login) {
        this.login = login;
    }

    public TextField getPassword() {
        return password;
    }

    public void setPassword(TextField password) {
        this.password = password;
    }

    public TextField getSmtp() {
        return smtp;
    }

    public void setSmtp(TextField smtp) {
        this.smtp = smtp;
    }

    public TextField getPort() {
        return port;
    }

    public void setPort(TextField port) {
        this.port = port;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public void setSaveBtn(Button saveBtn) {
        this.saveBtn = saveBtn;
    }
}
