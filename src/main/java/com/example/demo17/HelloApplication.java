package com.example.demo17;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ScrollPane;

public class HelloApplication extends Application {

    private int currentImageIndex = 0;
    private ImageView fullImageView = new ImageView();
    private Button btnNext = new Button("Next");
    private Button btnBack = new Button("Back");
    private Button btnMainBack = new Button("Back");
    private HBox navigationButtons;
    private ImageView[] currentImages;
    private BorderPane mainLayout;
    private HBox defaultLayout;

    @Override
    public void start(Stage stage) {
        var btnDessert = new Button("DESSERT");
        btnDessert.setStyle("-fx-background-color:purple; -fx-text-fill:white");
        var btnHorizon = new Button("FLOWERS");
        btnHorizon.setStyle("-fx-background-color:purple; -fx-text-fill:white");

        btnBack.setStyle("-fx-background-color:purple; -fx-text-fill:white");
        btnNext.setStyle("-fx-background-color:purple; -fx-text-fill:white");
        btnMainBack.setStyle("-fx-background-color:purple; -fx-text-fill:white");

        Label headerText = new Label("GALLERY");
        headerText.setStyle("-fx-text-fill: purple; -fx-font-size: 30px; -fx-font-weight: bold;");

        Label footerText = new Label("© copyright 😊");
        footerText.setStyle("-fx-text-fill: purple; -fx-font-size: 20px; -fx-font-weight: bold;");

        HBox buttonLayout = new HBox(10, btnDessert, btnHorizon, btnMainBack);
        buttonLayout.setAlignment(Pos.CENTER_RIGHT);

        HBox header = new HBox(10, headerText);
        header.setStyle("-fx-background-color: violet; padding: 10px;");
        header.setPrefHeight(70);
        header.setAlignment(Pos.CENTER_LEFT);
        header.getChildren().add(buttonLayout);
        HBox.setHgrow(buttonLayout, javafx.scene.layout.Priority.ALWAYS);

        VBox topLayout = new VBox(header);

        HBox footer = new HBox(footerText);
        footer.setStyle("-fx-background-color: violet;");
        footer.setPrefHeight(50);
        footer.setAlignment(Pos.CENTER);

        mainLayout = new BorderPane();
        mainLayout.setTop(topLayout);
        mainLayout.setBottom(footer);
        mainLayout.setStyle("-fx-background-color: pink;");

        String[] dessertPaths = {
                "/New_folder/dessert/desert.jpg",
                "/New_folder/dessert/brownie.jpg",
                "/New_folder/dessert/cake.jpg",
                "/New_folder/dessert/pie.jpg",
                "/New_folder/dessert/croissant.jpg",
                "/New_folder/dessert/roll.jpg"
        };

        String[] horizonPaths = {
                "/New_folder/flower/blueViolet.jpg",
                "/New_folder/flower/yellow.jpg",
                "/New_folder/flower/rose.jpg",
                "/New_folder/flower/whitePurple.jpg",
                "/New_folder/flower/violet.jpg",
                "/New_folder/flower/flower.jpg"
        };

        ImageView[] dessertImages = createImageViews(dessertPaths, true);
        ImageView[] horizonImages = createImageViews(horizonPaths, true);
        ImageView[] defaultImages = createDefaultImageViews(new String[]{dessertPaths[0], horizonPaths[0]});

        defaultLayout = new HBox(10, defaultImages);
        defaultLayout.setAlignment(Pos.CENTER);
        mainLayout.setCenter(defaultLayout);

        HBox dessertLayout = new HBox(10, dessertImages);
        dessertLayout.setAlignment(Pos.CENTER);

        HBox horizonLayout = new HBox(10, horizonImages);
        horizonLayout.setAlignment(Pos.CENTER);

        ScrollPane dessertScrollPane = new ScrollPane(dessertLayout);
        dessertScrollPane.setFitToWidth(true);
        dessertScrollPane.setPannable(true);

        ScrollPane horizonScrollPane = new ScrollPane(horizonLayout);
        horizonScrollPane.setFitToWidth(true);
        horizonScrollPane.setPannable(true);

        btnDessert.setOnAction(e -> {
            currentImages = dessertImages;
            currentImageIndex = 0;
            mainLayout.setCenter(dessertScrollPane);
        });

        btnHorizon.setOnAction(e -> {
            currentImages = horizonImages;
            currentImageIndex = 0;
            mainLayout.setCenter(horizonScrollPane);
        });

        btnMainBack.setOnAction(e -> mainLayout.setCenter(defaultLayout));

        navigationButtons = new HBox(20, btnBack, btnNext);
        navigationButtons.setAlignment(Pos.CENTER);

        btnNext.setOnAction(e -> showNextImage());
        btnBack.setOnAction(e -> showPreviousImage());

        Scene scene = new Scene(mainLayout, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Paddy's Gallery");
        stage.show();
    }

    private ImageView[] createImageViews(String[] paths, boolean enableHover) {
        ImageView[] images = new ImageView[paths.length];
        for (int i = 0; i < paths.length; i++) {
            String resourcePath = paths[i];
            System.out.println("Loading image from: " + resourcePath); // Debugging
            Image image = new Image(getClass().getResource(resourcePath).toExternalForm(), 500, 500, true, true);
            ImageView imgView = new ImageView(image);
            if (enableHover) {
                imgView.setOnMouseEntered(e -> {
                    imgView.setScaleX(1.1);
                    imgView.setScaleY(1.1);
                    imgView.setStyle("-fx-effect: dropshadow(gaussian, gray, 10, 0.5, 0, 0);");
                });
                imgView.setOnMouseExited(e -> {
                    imgView.setScaleX(1.0);
                    imgView.setScaleY(1.0);
                    imgView.setStyle("");
                });
                imgView.setOnMouseClicked(e -> showFullImage(imgView));
            }
            images[i] = imgView;
        }
        return images;
    }

    private ImageView[] createDefaultImageViews(String[] paths) {
        ImageView[] images = new ImageView[paths.length];
        for (int i = 0; i < paths.length; i++) {
            String resourcePath = paths[i];
            System.out.println("Loading default image from: " + resourcePath); // Debugging
            Image image = new Image(getClass().getResource(resourcePath).toExternalForm(), 500, 500, true, true);
            images[i] = new ImageView(image);
        }
        return images;
    }

    private void showFullImage(ImageView imageView) {
        if (imageView.getImage() != null) {
            fullImageView.setImage(imageView.getImage());
            fullImageView.setFitWidth(700);
            fullImageView.setFitHeight(600);
            VBox fullImageContainer = new VBox(10, fullImageView, navigationButtons);
            fullImageContainer.setAlignment(Pos.CENTER);
            mainLayout.setCenter(fullImageContainer);
        }
    }

    private void showNextImage() {
        if (currentImages != null && currentImages.length > 0) {
            currentImageIndex = (currentImageIndex + 1) % currentImages.length;
            fullImageView.setImage(currentImages[currentImageIndex].getImage());
        }
    }

    private void showPreviousImage() {
        if (currentImages != null && currentImages.length > 0) {
            currentImageIndex = (currentImageIndex - 1 + currentImages.length) % currentImages.length;
            fullImageView.setImage(currentImages[currentImageIndex].getImage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}