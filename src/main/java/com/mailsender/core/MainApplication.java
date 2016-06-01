package com.mailsender.core;

import com.mailsender.MainApp;
import javafx.stage.Stage;

public class MainApplication {

    private static MainApplication application;

    private MainApp mainApp;
    private Stage settingsDialogStage;

    public static MainApplication getInstance() {
        if (application == null) {
            application = new MainApplication();
        }
        return application;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void closeSettingsDialog() {
        if (settingsDialogStage != null) {
            settingsDialogStage.close();
        }
    }

    public void setSettingsDialogStage(Stage settingsDialogStage) {
        this.settingsDialogStage = settingsDialogStage;
    }
}
