package progData;

import enumClass.ETypProhl;
import enumClass.EnumPozice;
import generator.Generator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import praceZSoubory.CteniZText;
import praceZSoubory.ZapisDoText;
import spravaOblasti.Oblast;
import spravaOblasti.SpravaOblasti;
import spravaZaznamu.Zaznam;

public class ControlPanelHBox {

    private final ListView<String> listOblasti;
    private final ListView<String> listZaznamy;
    private final HBox hboxPanel1 = new HBox();
    private final HBox hboxPanel2 = new HBox();
    private final HBox hboxPanel3 = new HBox();
    private final HBox hboxPanel4 = new HBox();
    private final ObservableList<String> obsListOblast = FXCollections.observableArrayList();
    private final ObservableList<String> obsListZaznam = FXCollections.observableArrayList();

    private final Generator generator = new Generator();

    private final Button btnGenerateAll = new Button("Generate all");
    private final Button btnImport = new Button("Import");
    private final Button btnSave = new Button("Save");
    private final Button btnNovyZaznam = new Button("Novy zaznam");
    private final Button btnZrus = new Button("Vymazat");
    private final Button btnOdeberZaznam = new Button("Odeber zaznam");

    private final ChoiceBox<EnumPozice> odeberOblast = new ChoiceBox<>();
    private final ChoiceBox<EnumPozice> zpristupniOblast = new ChoiceBox<>();
    private final ChoiceBox<ETypProhl> typProhl = new ChoiceBox<>();

    private final Label labelZpristupniOblast = new Label("Zpristupni oblast:");
    private final Label labelSeznamOblasti = new Label("Seznam oblasti:");
    private final Label labelZaznamy = new Label("Prochazeni stromu podle typu:");

    private static final int ROOT_WIDTH = 900;
    private static final int ROOT_HEIGHT = 430;
    private static final int HBOX_HEIGHT = 80;
    private static final int VELKY_WIDTH = 100;
    private static final int WIDTH = 80;
    private static final int IMPORT_WIDTH = 65;
    private static final int SAVE_WIDTH = 60;
    private static final int GENERATE_ALL_WIDTH = 95;
    private static final int SPACING = 10;

    private SpravaOblasti spravaOblasti;
    private Oblast aktualniOblast = null;

    private final ObservableList<EnumPozice> pozice = FXCollections.observableArrayList(EnumPozice.values());
    private final ObservableList<ETypProhl> typProhlObsList = FXCollections.observableArrayList(ETypProhl.values());

    public ControlPanelHBox(HBox hbox, SpravaOblasti spravaOblasti) {

        this.spravaOblasti = spravaOblasti;

        odeberOblast.setItems(pozice);
        odeberOblast.getSelectionModel().clearSelection();
        zpristupniOblast.setItems(pozice);
        zpristupniOblast.getSelectionModel().clearSelection();
        typProhl.setItems(typProhlObsList);
        typProhl.getSelectionModel().clearSelection();

        this.btnGenerateAll.setPrefWidth(GENERATE_ALL_WIDTH);
        this.btnImport.setPrefWidth(IMPORT_WIDTH);
        this.btnSave.setPrefWidth(SAVE_WIDTH);
        this.btnZrus.setPrefWidth(WIDTH);
        this.btnNovyZaznam.setPrefWidth(VELKY_WIDTH);
        this.zpristupniOblast.setPrefWidth(VELKY_WIDTH);

        this.listOblasti = new ListView();
        this.listZaznamy = new ListView();
        listOblasti.setMaxSize(VELKY_WIDTH, ROOT_HEIGHT);
        listOblasti.setItems(obsListOblast);
        listOblasti.setItems(obsListZaznam);

        btnGenerateAllAction();
        btnImportAction();
        btnSaveAction();
        btnNovyZaznamAction();
        odeberZaznamAction();
        zpristupniOblastAction();
        typProhlAction();
        btnZrusAction();

        GridPane grid = new GridPane();

        ColumnConstraints col = new ColumnConstraints();
        col.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().add(col);

        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);
        grid.getRowConstraints().addAll(row1, new RowConstraints(), new RowConstraints());
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(SPACING);
        grid.setHgap(SPACING);
        grid.setMinSize(700, 480);
        grid.setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));

        hboxPanel1.getChildren().addAll(btnGenerateAll, btnNovyZaznam, btnOdeberZaznam, btnZrus);
        hboxPanel2.getChildren().addAll(labelZpristupniOblast, zpristupniOblast);
        hboxPanel3.getChildren().addAll(labelZaznamy, typProhl);
        hboxPanel4.getChildren().addAll(btnImport, btnSave);

        hboxPanel1.setMaxSize(ROOT_WIDTH, HBOX_HEIGHT);
        hboxPanel1.setSpacing(SPACING);
        hboxPanel1.setAlignment(Pos.CENTER);
        hboxPanel2.setMaxSize(ROOT_WIDTH, HBOX_HEIGHT);
        hboxPanel2.setSpacing(SPACING);
        hboxPanel2.setAlignment(Pos.CENTER);
        hboxPanel3.setMaxSize(ROOT_WIDTH, HBOX_HEIGHT);
        hboxPanel3.setSpacing(SPACING);
        hboxPanel3.setAlignment(Pos.CENTER);
        hboxPanel4.setMaxSize(ROOT_WIDTH, HBOX_HEIGHT);
        hboxPanel4.setSpacing(SPACING);
        hboxPanel4.setAlignment(Pos.CENTER);
        clickedOnListOblasti();
        clickedOnListZaznamu();

        labelSeznamOblasti.setMaxSize(350, 30);
        listOblasti.setMaxSize(350, 420);
        labelZaznamy.setMaxSize(350, 30);
        listZaznamy.setMaxSize(380, 400);

        VBox vboxRight = new VBox();
        vboxRight.setMaxSize(250, 450);
        vboxRight.setAlignment(Pos.CENTER);
        vboxRight.getChildren().addAll(labelSeznamOblasti, listOblasti);

        VBox vboxCenter = new VBox();
        vboxCenter.setMaxSize(450, 450);

        GridPane gridCenter = new GridPane();
        gridCenter.setAlignment(Pos.CENTER);
        gridCenter.add(listZaznamy, 0, 0);
        gridCenter.add(hboxPanel1, 0, 1);
        gridCenter.add(hboxPanel2, 0, 2);
        gridCenter.add(hboxPanel3, 0, 3);
        gridCenter.add(hboxPanel4, 0, 4);
        gridCenter.setVgap(SPACING);
        gridCenter.setHgap(SPACING);
        gridCenter.setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
        vboxCenter.getChildren().add(gridCenter);

        grid.add(vboxRight, 0, 0);
        grid.add(vboxCenter, 1, 0);

        hbox.setSpacing(SPACING);

        hbox.getChildren().addAll(grid);
    }

    private void btnGenerateAllAction() {
        btnGenerateAll.setOnAction((event) -> {
            obsListZaznam.clear();
            listZaznamy.setItems(obsListZaznam);
            spravaOblasti = generator.generatePole(spravaOblasti);

            vyplneniListu();
        });
    }

    private void btnImportAction() {
        btnImport.setOnAction((event) -> {
            obsListOblast.clear();
            obsListZaznam.clear();
            spravaOblasti.zrus();
            aktualniOblast = null;
            String soubor = "data.txt";
            spravaOblasti = new SpravaOblasti();
            CteniZText cteni = new CteniZText();
            cteni.ObnovovaniZTextSoubor(soubor, spravaOblasti);
            vyplneniListu();
        });
    }

    private void btnSaveAction() {
        btnSave.setOnAction((event) -> {
            if (!obsListOblast.isEmpty()) {
                try {
                    String soubor = "data.txt";
                    ZapisDoText zapis = new ZapisDoText();
                    zapis.zapisDoTextSouboru(soubor, spravaOblasti);
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Seznam je prazdný!");
                alert.showAndWait();
            }
        });
    }

    private void btnNovyZaznamAction() {
        btnNovyZaznam.setOnAction((event) -> {
            try {
                Stage stage = new Stage();
                GridPane grid = new GridPane();
                Label labelID = new Label("ID: ");
                labelID.setPrefWidth(90);
                Label labelJmeno = new Label("Jmeno: ");
                labelJmeno.setPrefWidth(90);

                TextField tfID = new TextField();
                TextField tfJmeno = new TextField();
                ChoiceBox<String> box = new ChoiceBox<>();
                box.setItems(FXCollections.observableArrayList("First-fit", "Do akt. oblasti"));
                Button btnOk = new Button("Ok");
                Button btnCancel = new Button("Cancel");
                tfID.setPrefWidth(90);
                tfJmeno.setPrefWidth(90);
                btnOk.setPrefWidth(90);
                btnCancel.setPrefWidth(90);

                btnOk.setOnAction((eventOk) -> {
                    if (!tfJmeno.getText().isEmpty()) {
                        try {
                            Zaznam zaznam = new Zaznam(Integer.parseInt(tfID.getText()), tfJmeno.getText());
                            switch (box.getValue()) {
                                case "First-fit" -> {
                                    spravaOblasti.vlozZaznam(zaznam);
                                    vyplneniListu();
                                    vyplneniZaznamu();
                                }
                                case "Do akt. oblasti" -> {
                                    if (aktualniOblast == null) {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Aktualní oblast nenastavena.");
                                        alert.showAndWait();
                                    } else {
                                        if (aktualniOblast.getAktKapacita() < aktualniOblast.getMaxKapacita()) {
                                            spravaOblasti.vlozZaznamPozice(zaznam);
                                            vyplneniListu();
                                            vyplneniZaznamu();
                                        } else {
                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                            alert.setTitle("Error");
                                            alert.setHeaderText("Chyba pri vlozeni zaznamu.");
                                            alert.setContentText("Aktualni oblast vypylnena!");
                                            alert.showAndWait();
                                        }
                                    }
                                }
                            }
                        } catch (NullPointerException | NumberFormatException | NoSuchElementException ex) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Chyba při vytvoření zaznamu.");
                            alert.setContentText(ex.getMessage());
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Špatně zadané údaje.");
                        alert.showAndWait();
                    }
                    stage.close();
                }
                );

                btnCancel.setOnAction((eventCance) -> {
                    stage.close();
                });

                grid.setPadding(
                        new Insets(SPACING, SPACING, SPACING, SPACING));
                grid.setHgap(SPACING);

                grid.setVgap(SPACING);

                grid.setMaxSize(
                        400, 600);
                grid.add(labelID, 0, 0);
                grid.add(tfID, 1, 0);
                grid.add(labelJmeno, 0, 1);
                grid.add(tfJmeno, 1, 1);
                grid.add(   box, 0, 2);
                grid.add(btnOk, 0, 3);
                grid.add(btnCancel, 1, 3);
                Scene scene = new Scene(grid);

                stage.setScene(scene);

                stage.setResizable(false);
                stage.show();

            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        });
    }

    private void zpristupniOblastAction() {
        zpristupniOblast.setOnAction((event) -> {
            if (!obsListOblast.isEmpty()) {
                try {
                    if (zpristupniOblast.getSelectionModel().getSelectedItem() == EnumPozice.AKTUALNI && aktualniOblast == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Aktualní oblast nenastavena.");
                        alert.showAndWait();
                    } else {
                        if (zpristupniOblast.getSelectionModel().getSelectedItem() != EnumPozice.AKTUALNI) {
                            Oblast oblast = spravaOblasti.zpristupniOblast(zpristupniOblast.getSelectionModel().getSelectedItem());
                            aktualniOblast = oblast;
                            zpristupniOblast.getSelectionModel().clearSelection();
                            listOblasti.getSelectionModel().select(aktualniOblast.toString());
                            obsListZaznam.clear();
                            listZaznamy.setItems(obsListZaznam);

                            typProhl.getSelectionModel().select(ETypProhl.SIRKA);
                            vyplneniZaznamu();
                        }
                    }

                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Seznam je prazdný.");
                alert.showAndWait();
            }
            zpristupniOblast.getSelectionModel().clearSelection();
        });
    }

    private void odeberZaznamAction() {
        btnOdeberZaznam.setOnAction((event) -> {
            if (!obsListOblast.isEmpty() && aktualniOblast != null) {
                Stage stage = new Stage();
                GridPane grid = new GridPane();
                Label labelID = new Label("Zadejte ID: ");
                labelID.setPrefWidth(90);

                TextField tfID = new TextField();
                Button btnOk = new Button("Ok");
                Button btnCancel = new Button("Cancel");
                tfID.setPrefWidth(90);
                btnOk.setPrefWidth(90);
                btnCancel.setPrefWidth(90);

                btnOk.setOnAction((eventOk) -> {
                    if (!tfID.getText().isEmpty()) {
                        try {
                            Zaznam zaznam = aktualniOblast.getZaznamy().najdi(Integer.parseInt(tfID.getText()));
                            if (zaznam == null) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("Zaznam neexistuje.");
                                alert.setContentText(null);
                                alert.showAndWait();
                            } else {
                                spravaOblasti.odeberZaznam(zaznam);
                                aktualniOblast = spravaOblasti.zpristupniOblast(EnumPozice.AKTUALNI);
                                vyplneniListu();
                                vyplneniZaznamu();
                            }
                        } catch (NullPointerException | NumberFormatException | NoSuchElementException ex) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Chyba při odebirani zaznamu.");
                            alert.setContentText(ex.getMessage());
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Špatně zadané údaje.");
                        alert.showAndWait();
                    }
                    stage.close();
                }
                );

                btnCancel.setOnAction((eventCance) -> {
                    stage.close();
                });

                grid.setPadding(
                        new Insets(SPACING, SPACING, SPACING, SPACING));
                grid.setHgap(SPACING);

                grid.setVgap(SPACING);

                grid.setMaxSize(
                        400, 600);
                grid.add(labelID, 0, 0);
                grid.add(tfID, 1, 0);
                grid.add(btnOk, 0, 1);
                grid.add(btnCancel, 1, 1);
                Scene scene = new Scene(grid);

                stage.setTitle("Odebirani zaznamu.");
                stage.setScene(scene);

                stage.setResizable(false);
                stage.show();

                vyplneniListu();
            } else if (obsListOblast.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Chyba při odebírání záznamu.");
                alert.setContentText("Seznam je prazdný.");
                alert.showAndWait();
            } else if (aktualniOblast == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Chyba při odebírání záznamu.");
                alert.setContentText("Aktualní oblast nenastavena.");
                alert.showAndWait();
            }
        });
    }

    private void typProhlAction() {
        typProhl.setOnAction((event) -> {
            if (!obsListOblast.isEmpty()) {
                try {
                    if (aktualniOblast == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Aktualní oblast nenastavena.");
                        alert.showAndWait();
                    } else {
                        vyplneniZaznamu();
                    }

                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Seznam je prazdný.");
                alert.showAndWait();
            }
        });
    }

    private void btnZrusAction() {
        btnZrus.setOnAction((event) -> {
            obsListOblast.clear();
            obsListZaznam.clear();
            spravaOblasti.zrus();
            aktualniOblast = null;
            listOblasti.setItems(obsListOblast);
            listZaznamy.setItems(obsListZaznam);
        });
    }

    private void clickedOnListOblasti() {
        listOblasti.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (listOblasti.getSelectionModel().getSelectedItem() != null) {
                listOblasti.getSelectionModel().clearSelection();
            }
            if (aktualniOblast != null) {
                Iterator<Oblast> iterator = spravaOblasti.iterator();

                while (iterator.hasNext()) {
                    Oblast oblast = iterator.next();
                    if (aktualniOblast == oblast) {
                        listOblasti.getSelectionModel().select(oblast.toString());
                        break;
                    }
                }
            }
        });
    }

    private void clickedOnListZaznamu() {
        listZaznamy.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (listZaznamy.getSelectionModel().getSelectedItem() != null) {
                listZaznamy.getSelectionModel().clearSelection();
            }
        });
    }

    private void vyplneniZaznamu() {
        obsListZaznam.clear();
        if (typProhl.getSelectionModel().getSelectedItem() == ETypProhl.SIRKA) {
            Iterator<Zaznam> it = aktualniOblast.getZaznamy().vytvorIterator(ETypProhl.SIRKA);
            Zaznam zaznam = null;
            while (it.hasNext()) {
                zaznam = it.next();
                obsListZaznam.add(zaznam.toString());
            }
            listZaznamy.setItems(obsListZaznam);
        } else if (typProhl.getSelectionModel().getSelectedItem() == ETypProhl.HLOUBKA) {
            Iterator it = aktualniOblast.getZaznamy().vytvorIterator(ETypProhl.HLOUBKA);

            while (it.hasNext()) {
                obsListZaznam.add(it.next().toString());
            }
            listZaznamy.setItems(obsListZaznam);
        }
    }

    private void vyplneniListu() {
        obsListOblast.clear();

        Iterator<Oblast> iteratorOblasti = spravaOblasti.iterator();
        Oblast oblast = null;
        while (iteratorOblasti.hasNext()) {
            oblast = iteratorOblasti.next();
            obsListOblast.add(oblast.toString());
        }
        listOblasti.setItems(obsListOblast);

        if (aktualniOblast != null) {
            Iterator<Oblast> iterator = spravaOblasti.iterator();

            while (iterator.hasNext()) {
                Oblast obl = iterator.next();
                if (aktualniOblast == obl) {
                    listOblasti.getSelectionModel().select(obl.toString());
                    break;
                }
            }
        }
    }
}
