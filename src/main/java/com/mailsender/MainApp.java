package com.mailsender;

import com.google.gson.Gson;
import com.mailsender.model.User;
import com.mailsender.model.Users;
import com.mailsender.service.MailSender;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.soap.SOAPBinding;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static final String HEADER_MAIN_PAGE = "Mail Sender";

    public static final String MENU_BAR_SELECTOR = "#menuBar";
    public static final String OPEN_FILE_SELECTOR = "#fileOpen";
    public static final String FILE_SELECTOR = "#file";

    public static final String UI_MAIN_PAGE = "/fxml/main.fxml";
    public static final String CSS_MAIN_PAGE = "/styles/styles.css";
    public static final Integer WIDTH = 820;
    public static final Integer HEIGHT = 600;

    private Users users;

    private Stage currentStage;
    @FXML
    private MenuBar menuBar;
    @FXML
    private ListView listViewMails;
    @FXML
    private HTMLEditor htmlEditor;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private void initStyles(Scene scene) {
        scene.getStylesheets().add(CSS_MAIN_PAGE);
    }

    public void start(Stage stage) throws Exception {
        currentStage = stage;
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(UI_MAIN_PAGE));

        Scene scene = new Scene(rootNode, WIDTH, HEIGHT);
        initStyles(scene);

        stage.setTitle(HEADER_MAIN_PAGE);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    @FXML
    public void openFileAction(ActionEvent event) {
        showFileOpenDialog(currentStage);
        if (users != null) {
            updateListOfMails(users);
        }
    }

    @FXML
    public void sendMessageButton(ActionEvent event) {
        for (int i = 0 ; i < listViewMails.getItems().size() ; ++i) {
            final User user = (User) listViewMails.getItems().get(i);
            final int finalI = i;
            final int finalI1 = i;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(3000);
                        MailSender.sendMail(user.getEmail(), htmlEditor.getHtmlText());
                        //user.setEmail(user.getEmail() + " DONE");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("didn't send " + user.getEmail());
                    }
                }
            }).start();
        }

    }

    final ObservableList<User> items = FXCollections.observableArrayList();
    private void updateListOfMails(Users users) {
        items.addAll(users.getUsers());
        listViewMails.setItems(items);

        listViewMails.setCellFactory(new Callback<ListView<User>, ListCell<User>>(){

            public ListCell<User> call(ListView<User> param) {
                ListCell<User> listCell = new ListCell<User>() {
                    @Override
                    protected void updateItem(User t, boolean bln) {
                        if (t != null) {
                            setText(t.getEmail());
                        }
                    }
                };
                return listCell;
            }
        });
    }

    private void showFileOpenDialog(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        try {
            Users users = parseFile(fileChooser.showOpenDialog(stage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Users parseFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder sbFile = new StringBuilder();
        String line = StringUtils.EMPTY;
        while ((line = bufferedReader.readLine()) != null) {
            sbFile.append(line);
        }

        users = new Gson().fromJson(sbFile.toString(), Users.class);
        return users;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public ListView getListViewMails() {
        return listViewMails;
    }

    public void setListViewMails(ListView listViewMails) {
        this.listViewMails = listViewMails;
    }

    public HTMLEditor getHtmlEditor() {
        return htmlEditor;
    }

    public void setHtmlEditor(HTMLEditor htmlEditor) {
        this.htmlEditor = htmlEditor;
    }
}
