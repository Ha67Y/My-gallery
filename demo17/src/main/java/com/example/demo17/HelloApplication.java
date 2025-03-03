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


    // Index to keep track of the currently displayed image in the gallery
    private int currentImageIndex = 0;

    // ImageView to display the full-sized image when clicked
    private ImageView fullImageView = new ImageView();

    // Button to navigate to the next image in the gallery
    private Button btnNext = new Button("Next");

    // Button to navigate to the previous image in the gallery
    private Button btnBack = new Button("Back");

    // Button to return to the main gallery view
    private Button btnMainBack = new Button("Back");

    // HBox to hold the navigation buttons (Next and Back)
    private HBox navigationButtons;

    // Array to store the currently displayed images (either desserts or flowers)
    private ImageView[] currentImages;

    // BorderPane to serve as the main layout of the application
    private BorderPane mainLayout;

    // HBox to store and display the default images when the application starts
    private HBox defaultLayout;

    @Override
    public void start(Stage stage) {

        // Creating buttons for different categories and navigation, setting their styles
        var btnDessert = new Button("DESSERT");
        btnDessert.setStyle("-fx-background-color:purple; -fx-text-fill:white");
        var btnHorizon = new Button("FLOWERS");
        btnHorizon.setStyle("-fx-background-color:purple; -fx-text-fill:white");

        // Styling navigation buttons
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

        // Initializing the main layout as a BorderPane
        mainLayout = new BorderPane();
        // Setting the top section of the layout to contain the header
        mainLayout.setTop(topLayout);
        // Setting the bottom section of the layout to contain the footer
        mainLayout.setBottom(footer);
        // Applying a pink background color to the main layout
        mainLayout.setStyle("-fx-background-color: pink;");

        //array to load the pictures
        String[] dessertPaths = {
                "file:C:/Users/X260/IdeaProjects/demo17/src/main/resources/New folder/dessert/desert.jpg",
                "file:C:/Users/X260/IdeaProjects/demo17/src/main/resources/New folder/dessert/brownie.jpg",
                "file:C:/Users/X260/IdeaProjects/demo17/src/main/resources/New folder/dessert/cake.jpg",
                "file:C:/Users/X260/IdeaProjects/demo17/src/main/resources/New folder/dessert/pie.jpg",
                "file:C:/Users/X260/IdeaProjects/demo17/src/main/resources/New folder/dessert/croissant.jpg",
                "file:C:/Users/X260/IdeaProjects/demo17/src/main/resources/New folder/dessert/roll.jpg"
        };

        String[] horizonPaths = {
                "file:C:/Users/X260/IdeaProjects/demo17/src/main/resources/New folder/flower/blueViolet.jpg",
                "file:C:/Users/X260/IdeaProjects/demo17/src/main/resources/New folder/flower/yellow.jpg",
                "file:C:/Users/X260/IdeaProjects/demo17/src/main/resources/New folder/flower/rose.jpg",
                "file:C:/Users/X260/IdeaProjects/demo17/src/main/resources/New folder/flower/whitePurple.jpg",
                "file:C:/Users/X260/IdeaProjects/demo17/src/main/resources/New folder/flower/violet.jpg",
                "file:C:/Users/X260/IdeaProjects/demo17/src/main/resources/New folder/flower/flower.jpg"
        };

        // Creating an array of ImageViews for dessert images using the provided paths
        ImageView[] dessertImages = createImageViews(dessertPaths, true);
        // Creating an array of ImageViews for flower images using the provided paths
        ImageView[] horizonImages = createImageViews(horizonPaths, true);

        ImageView[] defaultImages = createDefaultImageViews(new String[]{dessertPaths[0], horizonPaths[0]});

        // Creating an HBox layout with a spacing of 10 pixels to display default images
        defaultLayout = new HBox(10, defaultImages);
        // Aligning the default layout to the center
        defaultLayout.setAlignment(Pos.CENTER);
        // Setting the default layout as the center content of the main layout
        mainLayout.setCenter(defaultLayout);

        HBox dessertLayout = new HBox(10, dessertImages);
        dessertLayout.setAlignment(Pos.CENTER);

        HBox horizonLayout = new HBox(10, horizonImages);
        horizonLayout.setAlignment(Pos.CENTER);

        // Creating a scrollable pane to contain the dessert images layout
        ScrollPane dessertScrollPane = new ScrollPane(dessertLayout);
        // Ensuring the scroll pane adjusts to the width of its parent container
        dessertScrollPane.setFitToWidth(true);
        // Allowing the user to scroll through the images using the mouse
        dessertScrollPane.setPannable(true);

        ScrollPane horizonScrollPane = new ScrollPane(horizonLayout);
        horizonScrollPane.setFitToWidth(true);
        horizonScrollPane.setPannable(true);

        // Setting an action event for the "DESSERT" button
        btnDessert.setOnAction(e -> {
            // Assigning the dessert images to the current image array
            currentImages = dessertImages;
            // Resetting the image index to start from the first image
            currentImageIndex = 0;
            // Displaying the dessert images in the main layout by setting the center to the dessert scroll pane
            mainLayout.setCenter(dessertScrollPane);
        });

        btnHorizon.setOnAction(e -> {
            currentImages = horizonImages;
            currentImageIndex = 0;
            mainLayout.setCenter(horizonScrollPane);
        });

        // Setting an action event for the "Back" button on the main screen
        // Restoring the default layout  in the center of the main layout
        btnMainBack.setOnAction(e -> mainLayout.setCenter(defaultLayout));

        // Setting action events for navigation buttons
        // Moves to the next image when "Next" button is clicked
        btnNext.setOnAction(e -> showNextImage());
        // Moves to the previous image when "Back" button is clicked
        btnBack.setOnAction(e -> showPreviousImage());

        // Creating an HBox layout to hold the navigation buttons with a spacing of 20 pixels
        navigationButtons = new HBox(20, btnBack, btnNext);
        navigationButtons.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainLayout, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Paddy's Gallery");
        stage.show();
    }

    // Method to create an array of ImageViews from given image file paths
    private ImageView[] createImageViews(String[] paths, boolean allowFullSize) {
        // Array to store ImageView objects
        ImageView[] images = new ImageView[paths.length];

        // Loop through each image path to create and configure ImageView objects
        for (int i = 0; i < paths.length; i++) {
            // Create an ImageView with the specified image, setting width and height to 500px
            ImageView imgView = new ImageView(new Image(paths[i], 500, 500, true, true));

            // If allowFullSize is true, enable click event to show image in full size
            if (allowFullSize) {
                imgView.setOnMouseClicked(e -> showFullImage(imgView));
            }

            // Add a mouse hover effect to the image
            imgView.setOnMouseEntered(e -> {
                // Slightly increase the image width when the picture hovers
                imgView.setScaleX(1.1);
                // Slightly increase the image height when the picture hovers
                imgView.setScaleY(1.1);

                // Applying a shadow effect when the picture hovers
                imgView.setStyle("-fx-effect: dropShadow(gaussian, gray, 10, 0.5, 0, 0);");
            });

            imgView.setOnMouseExited(e -> {
                imgView.setScaleX(1.0);
                imgView.setScaleY(1.0);
                imgView.setStyle("");
            });

            images[i] = imgView;
        }

        return images;
    }

    private ImageView[] createDefaultImageViews(String[] paths) {
        ImageView[] images = new ImageView[paths.length];

        for (int i = 0; i < paths.length; i++) {
            images[i] = new ImageView(new Image(paths[i], 500, 500, true, true));
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
