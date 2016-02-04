package jiconfont.catalog.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jiconfont.icons.*;
import jiconfont.javafx.IconFontFX;

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
public class ShowCatalogFX extends Application {

    @Override
    public void start(Stage stage) {
        IconFontFX.register(FontAwesome.getIconFont());
        IconFontFX.register(GoogleMaterialDesignIcons.getIconFont());
        IconFontFX.register(Iconic.getIconFont());
        IconFontFX.register(Elusive.getIconFont());
        IconFontFX.register(Entypo.getIconFont());
        CatalogPane catalogPane = new CatalogPane();
        for (FontAwesome icon : FontAwesome.values()) {
            catalogPane.register(icon);
        }
        for (GoogleMaterialDesignIcons icon : GoogleMaterialDesignIcons.values()) {
            catalogPane.register(icon);
        }
        for (Iconic icon : Iconic.values()) {
            catalogPane.register(icon);
        }
        for (Elusive icon : Elusive.values()) {
            catalogPane.register(icon);
        }
        for (Entypo icon : Entypo.values()) {
            catalogPane.register(icon);
        }
        catalogPane.update();
        Scene scene = new Scene(catalogPane, 950, 650);
        stage.setTitle("jIconFont - JavaFX - Catalog");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
