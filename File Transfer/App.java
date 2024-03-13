import javafx.application.Application;
import javafx.application.HostServices;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Stage pStage;

    @Override
    public void start(Stage primaryStage) {
        pStage = primaryStage;

        // Set up the main application window
        primaryStage.setTitle("Share Sphere");
        primaryStage.getIcons().add(new Image("file_transfer.png"));
        primaryStage.setResizable(false);

        Group root = new Group();
        Scene scene = new Scene(root, 600, 600, Color.LIGHTBLUE);

        // Create and configure Text objects for the welcome message
        Text text = new Text();
        text.setText("Welcome to ShareSphere..");
        text.setX(20);
        text.setY(60);
        text.setFont(Font.font("Times New Roman", 40));
        text.setFill(Color.ORANGERED);

        Text text1 = new Text();
        text1.setText("Connecting Your Files Globally..");
        text1.setX(220);
        text1.setY(100);
        text1.setFont(Font.font("Comic Sans MS", 25));
        text1.setFill(Color.LAVENDERBLUSH);

        Text text2 = new Text();
        text2.setText("© Copyright Reserved By: ShareSphere , Core2Web || (The 5AM's CLUB)");
        text2.setX(80);
        text2.setY(530);
        text2.setFont(Font.font("Verdana", 12));
        text2.setFill(Color.LIGHTCORAL);

        Image img1 = new Image("group.png");
        ImageView aboutUs = new ImageView(img1);

        Image img2 = new Image("recieved.png");
        ImageView recieveImage = new ImageView(img2);

        Image img3 = new Image("core.png");
        ImageView infoImage = new ImageView(img3);

        Image img4 = new Image("send.png");
        ImageView sendImage = new ImageView(img4);

        // Create buttons and images
        Button sendButton = new Button("Send File",sendImage);
        sendButton.setPrefSize(150, 15);

        Button receiveButton = new Button("Receive File",recieveImage);
        receiveButton.setPrefSize(150, 15);

        Button infoButton = new Button("Know More!!");
        Button devButton = new Button("About Us!" , aboutUs);

        Image image = new Image("file.png");
        ImageView imageView = new ImageView(image);
        imageView.setX(50);
        imageView.setY(200);
        imageView.setFitHeight(350);
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);

        // Create and configure StackPanels to organize buttons
        StackPane stackpane1 = new StackPane(sendButton);
        StackPane stackpane2 = new StackPane(receiveButton);
        StackPane stackpane3 = new StackPane(infoButton);
        StackPane stackpane4 = new StackPane(devButton);
        stackpane1.setLayoutX(80);
        stackpane1.setLayoutY(150);
        stackpane2.setLayoutX(380);
        stackpane2.setLayoutY(150);
        stackpane3.setLayoutX(50);
        stackpane3.setLayoutY(560);
        stackpane4.setLayoutX(450);
        stackpane4.setLayoutY(560);

        // Add elements to the root Group
        root.getChildren().addAll(text, text1, text2, stackpane1, stackpane2, stackpane3, stackpane4, imageView);

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();

        // Button actions
        infoButton.setOnAction(e -> showWebsite());
        sendButton.setOnAction(e -> sendFileAction());
        devButton.setOnAction(e -> aboutUs());
        receiveButton.setOnAction(e -> receiveFileAction());
    }

    // Method to get an IP address from the user
    private String getIpAddress() {
        Stage s = new Stage();
        s.setResizable(false);
        s.setTitle("Enter IP Address");

        TextField input = new TextField();
        input.setPrefSize(330, 10);
        
        Button enter = new Button("Enter");

        StackPane sp1 = new StackPane(input);
        StackPane sp2 = new StackPane(enter);

        sp1.setLayoutX(10);
        sp1.setLayoutY(10);
        sp2.setLayoutX(150);
        sp2.setLayoutY(80);

        Group g = new Group();
        g.getChildren().addAll(sp1, sp2);

        StringBuilder abc = new StringBuilder();
        enter.setOnAction(e -> {
            String str = input.getText();

            if (str.equals("") || str == null)
                popUp("Please Enter the IP Address");
            else {
                abc.append(str);
                s.close();
            }
        });

        Scene scene = new Scene(g, 350, 150, Color.LIGHTBLUE);
        s.setScene(scene);
        s.showAndWait();

        return abc.toString();
    }

    // Method to display the "About Us" information
    private void aboutUs() {
        Stage s = new Stage();
        s.getIcons().add(new Image("group.png"));
        s.setResizable(false);
        s.setTitle("About Us");

        Image img = new Image("back_button.png");
        ImageView backImg = new ImageView(img);

        Button backButton = new Button("Back" , backImg);
        backButton.setOnAction(e -> {
            s.close();
            pStage.show();
        });

        Group g = new Group();
        Image image = new Image("about.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setX(10);
        imageView.setY(10);
        imageView.setFitHeight(500);
        imageView.setFitWidth(790);
        imageView.setPreserveRatio(false);
        StackPane sp1 = new StackPane(imageView);
        StackPane sp2 = new StackPane(backButton);
        sp1.setLayoutX(1);
        sp1.setLayoutY(1);
        sp2.setLayoutX(400);
        sp2.setLayoutY(520);

        g.getChildren().addAll(imageView, sp2);

        Scene scene = new Scene(g, 800, 550, Color.LIGHTBLUE);
        s.setScene(scene);
        s.show();
    }

    // Method to choose a directory for file downloads
    private File chooseDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a Directory");

        File selectedDirectory = directoryChooser.showDialog(pStage);

        while (selectedDirectory == null) {
            popUp("Please Select Directory To Save Downloads");
            selectedDirectory = directoryChooser.showDialog(pStage);
        }

        return selectedDirectory;
    }

    // Method to display a pop-up window with a message
    public static void popUp(String msg) {
        Stage popupStage = new Stage();
        popupStage.getIcons().add(new Image("warning.png"));

        popupStage.setResizable(false);
        popupStage.setTitle("Warning");

        Label popupLabel = new Label(msg);
        StackPane popupLayout = new StackPane();
        popupLayout.getChildren().add(popupLabel);

        StackPane.setAlignment(popupLabel, Pos.CENTER);
        Scene popupScene = new Scene(popupLayout, 300, 150 , Color.LIGHTBLUE);
        popupStage.setScene(popupScene);

        popupStage.showAndWait();
    }

    // Method to open a core2web website using the default web browser
    private void showWebsite() {
        HostServices hostServices = getHostServices();
        String url = "https://www.core2web.in";
        hostServices.showDocument(url);
    }

    // Method to select a file for sending
    private File selectFile() {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(pStage);

        while (f == null) {
            popUp("Please Select File To Send");
            f = fc.showOpenDialog(pStage);
        }
        return f;
    }

    // Method to handle the Send File button action
    private void sendFileAction() {
        String ipAddress = getIpAddress();
        while (ipAddress == null || ipAddress.equals("")) {
            popUp("Enter IP Address");
            ipAddress = getIpAddress();
        }
        File fileToSend = selectFile();
        sendFile(fileToSend, ipAddress);
    }

    // Method to handle the Receive File button action
    private void receiveFileAction() {
        File dirFile = chooseDirectory();
        showWaitingWindow(dirFile);
    }

    ProgressBar progressBar2;

    // Method to display sending progress
    void showSendingProgress(String fileName) {
        Stage s = new Stage();

        s.setTitle("Sending File");
        s.setResizable(false);

        Text text;
        text = new Text("' " + fileName + " ' sending ....");
        text.setFont(Font.font("Verdana", 18));
        text.setFill(Color.BLACK);

        progressBar2 = new ProgressBar(0);
        progressBar2.setPrefSize(360, 20);

        StackPane sp1 = new StackPane(text);
        sp1.setLayoutX(15);
        sp1.setLayoutY(50);

        StackPane sp2 = new StackPane(progressBar2);
        sp2.setLayoutX(20);
        sp2.setLayoutY(150);

        Group group = new Group(sp1, sp2);

        Scene scene = new Scene(group, 400, 200, Color.LIGHTBLUE);

        s.setScene(scene);

        s.show();
    }

    // Method to send a file to a specified IP address
    private void sendFile(File fileToSend, String ipAddress) {

        showSendingProgress(fileToSend.getName());

        try {
            Socket socket = new Socket(ipAddress, 2001);
            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os);

            FileInputStream fis = new FileInputStream(fileToSend);
            long sizeOfFile = fileToSend.length();
            sizeOfFile = sizeOfFile / 1024;

            ps.println(fileToSend.getName());
            ps.println(sizeOfFile);

            byte[] data = new byte[1024];
            int len = 0;
            long totalSend = 0;

            while ((len = fis.read(data)) > 0) {
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                totalSend += 1;
                double per = (totalSend / sizeOfFile) * 100 / 100;
                progressBar2.setProgress(per);

                os.write(data, 0, len);
            }

            fis.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            popUp("An error occurred");
        }
    }

    // Global variables for tracking progress
    public static Text text;
    public static ProgressBar progressBar = new ProgressBar();
    public static File storeDirFile;

    // Method to display a waiting window for receiving files
    private void showWaitingWindow(File file) {
        storeDirFile = file;
        Stage s = new Stage();
        s.setResizable(false);
        s.setTitle("Sending File");

        progressBar.setProgress(0);
        progressBar.setPrefSize(360, 20);

        InetAddress ipAddress = null;
        try {
            ipAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException uException) {
        }

        Text ip = new Text("Your IP Address: " + ipAddress.getHostAddress());
        ip.setFont(Font.font("Comic Sans MS", 20));
        ip.setFill(Color.BLACK);

        text = new Text();
        text.setText("Send Files From Sender Side");
        text.setFont(Font.font("Comic Sans MS", 18));
        text.setFill(Color.BLACK);

        RecieveFile r = new RecieveFile();
        r.start();

        Button close = new Button("Close");
        close.setOnAction(e -> {
            s.close();
            r.interrupt();
        });

        StackPane sp1 = new StackPane(progressBar);
        StackPane sp2 = new StackPane(text);
        StackPane sp3 = new StackPane(close);
        StackPane sp4 = new StackPane(ip);

        sp1.setLayoutX(20);
        sp1.setLayoutY(230);
        sp2.setLayoutX(80);
        sp2.setLayoutY(130);
        sp3.setLayoutX(190);
        sp3.setLayoutY(270);
        sp4.setLayoutX(20);
        sp4.setLayoutY(10);

        Group g = new Group();
        g.getChildren().addAll(sp2, sp1, sp3, sp4);

        Scene scene = new Scene(g, 400, 300, Color.LIGHTBLUE);
        s.setScene(scene);
        s.showAndWait();
    }
}
