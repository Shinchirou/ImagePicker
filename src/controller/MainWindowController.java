package controller;


import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MainWindowController {
    private Main main;
    private String imagesNames = "image.png";
    private Image img;
    private List<Image> pickedImages = new ArrayList<>();

    @FXML
    private ImageView imageView = new ImageView();
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane anchorPane;


    @FXML
    public void readButton() {
    }

    @FXML
    public void clearButton() {
        gridPane.getChildren().clear();
        gridPane.setGridLinesVisible(true);
        pickedImages.clear();
    }

    public void start() {
        img = new Image(getClass().getResourceAsStream(imagesNames));
        gridPane.setGridLinesVisible(true);
        anchorPane.setCursor(createCursor());
        imageView.setImage(img);
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    Image subImage = getSubImage(e.getX(), e.getY());
                    pickedImages.add(subImage);
                    fillGridPane(pickedImages);
                }
        );
    }

    public ImageCursor createCursor() {
        Image cursor = new Image(this.getClass().getResource("") + "cursor.png");
        return new ImageCursor(cursor);
    }

    public Image getSubImage(double x, double y) {
        PixelReader pr = img.getPixelReader();
        return new WritableImage(pr, (int) x, (int) y, 32, 32);
    }

    public void fillGridPane(List<Image> pickedImages) {
        if (pickedImages == null) {
            throw new AppException("pickedImages list is null", LocalDateTime.now());
        }
        int idx = 0;
        ImageView imgToAdd;
        for (int i = 0; i < gridPane.getColumnCount(); i++) {
            for (int j = 0; j < gridPane.getRowCount(); j++) {
                if (idx < pickedImages.size()) {
                    imgToAdd = new ImageView(pickedImages.get(idx));
                } else {
                    imgToAdd = new ImageView();
                }
                gridPane.add(imgToAdd, j, i);
                idx++;
            }
        }
    }

    public void setMain(Main main) {
        this.main = main;
        start();
    }

}
