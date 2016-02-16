package jiconfont.catalog.javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
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
public class CatalogPane extends GridPane {

    private Label iconUnderMouse;
    private HBox tabPane1;
    private HBox tabPane2;
    private ColorPicker iconColorPicker;
    private ColorPicker backgroundColorPicker;
    private Spinner<Integer> sizeSpinner;
    private CheckBox showNameCheckBox;
    private TextField filterField;
    private Map<String, IconCollection> icons = new HashMap<>();
    private ScrollPane scrollPane;

    public CatalogPane() {
        setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        add(getTabPane1(), 0, 0);
        add(getTabPane2(), 0, 1);
        add(getScrollPane(), 0, 2);
        add(getIconUnderMouse(), 0, 3);

        GridPane.setHgrow(getTabPane1(), Priority.ALWAYS);
        GridPane.setHgrow(getTabPane2(), Priority.ALWAYS);
        GridPane.setHgrow(getScrollPane(), Priority.ALWAYS);
        GridPane.setHgrow(getIconUnderMouse(), Priority.ALWAYS);

        setVgap(5);

        GridPane.setVgrow(getScrollPane(), Priority.ALWAYS);

        GridPane.setMargin(getTabPane1(), new Insets(5, 5, 0, 5));
        GridPane.setMargin(getTabPane2(), new Insets(0, 5, 0, 5));
        GridPane.setMargin(getScrollPane(), new Insets(0, 5, 0, 5));
        GridPane.setMargin(getIconUnderMouse(), new Insets(0, 5, 5, 5));

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


    public TextField getFilterField() {
        if (filterField == null) {
            filterField = new TextField();
            filterField.setPrefColumnCount(30);
            filterField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    update();
                }
            });
        }
        return filterField;
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

    public Spinner<Integer> getSizeSpinner() {
        if (sizeSpinner == null) {
            sizeSpinner = new Spinner<>();
            sizeSpinner.setEditable(true);
            SpinnerValueFactory svf =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(12, 48);
            svf.setValue(new Integer(25));
            sizeSpinner.setValueFactory(svf);

            sizeSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue<? extends Integer> observable,
                                    Integer oldValue, Integer newValue) {
                    update();
                }
            });
        }
        return sizeSpinner;
    }

    public CheckBox getShowNameCheckBox() {
        if (showNameCheckBox == null) {
            showNameCheckBox = new CheckBox("Show icon name");
            showNameCheckBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    update();
                }
            });
        }
        return showNameCheckBox;
    }

    public HBox getTabPane1() {
        if (tabPane1 == null) {
            tabPane1 = new HBox();
            tabPane1.setSpacing(3);
            tabPane1.setAlignment(Pos.CENTER_LEFT);
            tabPane1.getChildren().addAll(getShowNameCheckBox(), new Label("Icon size:"),
                    getSizeSpinner(), new Label("Filter:"),
                    getFilterField());
            HBox.setMargin(getShowNameCheckBox(), new Insets(0, 20, 0, 0));
            HBox.setMargin(getSizeSpinner(), new Insets(0, 20, 0, 0));
        }
        return tabPane1;
    }

    public HBox getTabPane2() {
        if (tabPane2 == null) {
            tabPane2 = new HBox();
            tabPane2.setSpacing(3);
            tabPane2.setAlignment(Pos.CENTER_LEFT);
            tabPane2.getChildren().addAll(new Label("Icon color:"), getIconColorPicker(),
                    new Label("Background:"), getBackgroundColorPicker());
            HBox.setMargin(getIconColorPicker(), new Insets(0, 20, 0, 0));
            tabPane2.setMinHeight(Region.USE_PREF_SIZE);
            tabPane2.setFillHeight(true);
        }
        return tabPane2;
    }

    public final void update() {
        TilePane pane = new TilePane();
        pane.setHgap(5);
        pane.setVgap(10);

        pane.setBackground(new Background(new BackgroundFill(getBackgroundColorPicker().getValue(), null, null)));
        pane.setBorder(new Border(new BorderStroke(getBackgroundColorPicker().getValue(), BorderStrokeStyle.SOLID, null, new BorderWidths(10, 5, 10, 5))));

        String text = getFilterField().getText().toLowerCase();

        for (final IconCollection iconCollection : icons.values()) {

            for (final IconCode icon : iconCollection.getIcons()) {
                if (icon.name().toLowerCase().contains(text)) {
                    IconNode iconNode = new IconNode();
                    iconNode.setIconCode(icon);
                    iconNode.setIconSize(getSizeSpinner().getValue());
                    iconNode.setFill(getIconColorPicker().getValue());

                    Node node = null;
                    if (getShowNameCheckBox().isSelected()) {
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
                            getIconUnderMouse().setText(iconCollection.getName() + ": " + icon.name());
                        }
                    });
                    node.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            getIconUnderMouse().setText("");
                        }
                    });
                    //   node.setCache(true);
                    //      node.setCacheHint(CacheHint.QUALITY);
                    pane.getChildren().add(node);
                }
            }
        }

        getScrollPane().setContent(pane);
    }

    public ScrollPane getScrollPane() {
        if (scrollPane == null) {
            scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
        }
        return scrollPane;
    }

    public void register(IconCode icon) {
        IconCollection iconCollection = icons.get(icon.getFontFamily());
        if (iconCollection == null) {
            iconCollection = new IconCollection(icon.getClass().getSimpleName());
            icons.put(icon.getFontFamily(), iconCollection);
        }
        iconCollection.add(icon);
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
