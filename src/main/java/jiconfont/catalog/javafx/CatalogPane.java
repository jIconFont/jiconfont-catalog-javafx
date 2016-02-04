package jiconfont.catalog.javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontSmoothingType;
import jiconfont.IconCode;
import jiconfont.javafx.IconNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2016 jIconFont <BR>
 * <BR>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:<BR>
 * <BR>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.<BR>
 * <BR>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class CatalogPane extends BorderPane {

    private Label iconUnderMouse;
    private HBox configPane;
    private ColorPicker iconColorPicker;
    private ColorPicker backgroundColorPicker;

    private Spinner<Integer> spinner;
    private CheckBox checkBox;
    private Map<String, IconCollection> icons = new HashMap<>();

    public CatalogPane() {
        setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        BorderPane.setMargin(getConfigPane(), new Insets(5, 5, 5, 5));
        BorderPane.setMargin(getIconUnderMouse(), new Insets(0, 5, 5, 5));
        setTop(getConfigPane());
        setBottom(getIconUnderMouse());
        update();
    }

    public Label getIconUnderMouse() {
        if (iconUnderMouse == null) {
            iconUnderMouse = new Label();
        }
        return iconUnderMouse;
    }

    public ColorPicker getIconColorPicker() {
        if (iconColorPicker == null) {
            iconColorPicker = new ColorPicker();
            iconColorPicker.setValue(Color.BLACK);
            iconColorPicker.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    update();
                }
            });
        }
        return iconColorPicker;
    }

    public ColorPicker getBackgroundColorPicker() {
        if (backgroundColorPicker == null) {
            backgroundColorPicker = new ColorPicker();
            backgroundColorPicker.setValue(Color.WHITE);
            backgroundColorPicker.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    update();
                }
            });
        }
        return backgroundColorPicker;
    }

    public Spinner<Integer> getSpinner() {
        if (spinner == null) {
            spinner = new Spinner<>();
            spinner.setEditable(true);
            SpinnerValueFactory svf =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(12, 48);
            svf.setValue(new Integer(25));
            spinner.setValueFactory(svf);

            spinner.valueProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue<? extends Integer> observable,
                                    Integer oldValue, Integer newValue) {
                    update();
                }
            });
        }
        return spinner;
    }

    public CheckBox getCheckBox() {
        if (checkBox == null) {
            checkBox = new CheckBox("Show icon name");
            checkBox.setSelected(true);
            checkBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    update();
                }
            });
        }
        return checkBox;
    }

    public HBox getConfigPane() {
        if (configPane == null) {
            configPane = new HBox();
            configPane.setSpacing(3);
            configPane.setAlignment(Pos.CENTER_LEFT);
            configPane.getChildren().addAll(getCheckBox(), new Label("Icon size:"),
                    getSpinner(), new Label("Icon color:"), getIconColorPicker(),
                    new Label("Background:"), getBackgroundColorPicker());
            HBox.setMargin(getCheckBox(), new Insets(0, 10, 0, 0));
            HBox.setMargin(getSpinner(), new Insets(0, 10, 0, 0));
            HBox.setMargin(getIconColorPicker(), new Insets(0, 10, 0, 0));
        }
        return configPane;
    }


    public final void update() {
        VBox vBox = new VBox();
        vBox.setFillWidth(true);

        vBox.setBackground(new Background(
                new BackgroundFill(getBackgroundColorPicker().getValue(), null, null)));

        for (IconCollection iconCollection : getIcons().values()) {
            Label fontFamilyTitle = new Label(iconCollection.getName());
            fontFamilyTitle.setTextFill(getIconColorPicker().getValue());
            fontFamilyTitle.setStyle("-fx-font-size: 22px;");
            VBox.setMargin(fontFamilyTitle, new Insets(10, 0, 10, 20));
            vBox.getChildren().addAll(fontFamilyTitle);

            TilePane pane = new TilePane();
            pane.setHgap(5);
            pane.setVgap(10);

            pane.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                    BorderStrokeStyle.SOLID, null, new BorderWidths(5, 5, 5, 5))));

            for (final IconCode icon : iconCollection.getIcons()) {
                IconNode iconNode = new IconNode();
                iconNode.setIconCode(icon);
                iconNode.setIconSize(getSpinner().getValue());
                iconNode.setFill(getIconColorPicker().getValue());
                iconNode.setFontSmoothingType(FontSmoothingType.LCD);

                Node node = null;
                if (getCheckBox().isSelected()) {
                    Label label = new Label(icon.name());
                    label.setTextFill(getIconColorPicker().getValue());
                    label.setStyle("-fx-font-size: 9px;");
                    label.setContentDisplay(ContentDisplay.TOP);
                    label.setGraphic(iconNode);
                    node = label;
                } else {
                    node = iconNode;
                }

                node.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        getIconUnderMouse().setText("Icon: " + icon.name());
                    }
                });
                node.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        getIconUnderMouse().setText("");
                    }
                });
                // node.setCache(true);
                //   node.setCacheHint(CacheHint.QUALITY);
                pane.getChildren().add(node);
            }

            vBox.getChildren().addAll(pane);
        }

        ScrollPane sp = new ScrollPane(vBox);


        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        BorderPane.setMargin(sp, new Insets(5, 5, 5, 5));
        setCenter(sp);
    }

    public void register(IconCode icon) {
        IconCollection iconCollection = icons.get(icon.getFontFamily());
        if (iconCollection == null) {
            iconCollection = new IconCollection(icon.getClass().getSimpleName());
            icons.put(icon.getFontFamily(), iconCollection);
        }
        iconCollection.add(icon);
    }

    public Map<String, IconCollection> getIcons() {
        return icons;
    }

    private static class IconCollection {
        private String name;
        private List<IconCode> icons;

        public IconCollection(String name) {
            this.name = name;
            this.icons = new ArrayList<>();
        }

        public void add(IconCode icon) {
            this.icons.add(icon);
        }

        public List<IconCode> getIcons() {
            return icons;
        }

        public String getName() {
            return name;
        }
    }

}
