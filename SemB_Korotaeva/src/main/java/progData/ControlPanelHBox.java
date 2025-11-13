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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import praceZSoubory.CteniZText;
import praceZSoubory.ZapisDoText;
import spravaOblasti.Oblast;
import spravaOblasti.SpravaOblasti;
import spravaZaznamu.Zaznam;

public class ControlPanelHBox {

    private final ListView<String> listOblasti;
    private final ListView<String> listZaznamy;
    AnchorPane rootPane;
    private final Group group;
    private final HBox hboxPanel1 = new HBox();
    private final HBox hboxPanel2 = new HBox();
    private ObservableList<String> obsListOblast = FXCollections.observableArrayList();
    private ObservableList<String> obsListZaznam = FXCollections.observableArrayList();

    private final Generator generator = new Generator();

    private final Button btnGenerateAll = new Button("Generate all");
    private final Button btnImport = new Button("Import");
    private final Button btnSave = new Button("Save");
    private final Button btnNovyZaznam = new Button("Novy zaznam");
    private final Button btnZrus = new Button("Vymazat");
    private final Button btnOdeberZaznam = new Button("Odeber zaznam");

    private final ChoiceBox<EnumPozice> odeberOblast = new ChoiceBox<>();
    private final ChoiceBox<EnumPozice> zpristupniOblast = new ChoiceBox<>();

    private final Label labelZpristupniOblast = new Label("Zpristupni oblast:");
    private final Label labelSeznamOblasti = new Label("Seznam oblasti:");
    private final Label labelZaznamy = new Label("");

    private static final int ROOT_WIDTH = 900;
    private static final int ROOT_HEIGHT = 430;
    private static final int HBOX_HEIGHT = 80;
    private static final int NOVY_ZAZNAM_WIDTH = 150;
    private static final int VELKY_WIDTH = 100;
    private static final int IMPORT_WIDTH = 65;
    private static final int SAVE_WIDTH = 60;
    private static final int GENERATE_ALL_WIDTH = 95;
    private static final int SPACING = 10;

    private SpravaOblasti spravaOblasti;
    private Oblast aktualniOblast = null;
    private Zaznam aktualniZaznam = null;

    private final ObservableList<EnumPozice> pozice = FXCollections.observableArrayList(EnumPozice.values());

    public ControlPanelHBox(HBox hbox, SpravaOblasti spravaOblasti) {

        this.spravaOblasti = spravaOblasti;

        odeberOblast.setItems(pozice);
        odeberOblast.getSelectionModel().clearSelection();
        zpristupniOblast.setItems(pozice);
        zpristupniOblast.getSelectionModel().clearSelection();

        this.btnGenerateAll.setPrefWidth(GENERATE_ALL_WIDTH);
        this.btnImport.setPrefWidth(IMPORT_WIDTH);
        this.btnSave.setPrefWidth(SAVE_WIDTH);
        this.btnZrus.setPrefWidth(VELKY_WIDTH);
        this.btnNovyZaznam.setPrefWidth(VELKY_WIDTH);
        this.zpristupniOblast.setPrefWidth(VELKY_WIDTH);

        this.listOblasti = new ListView();
        this.listZaznamy = new ListView();
        this.rootPane = new AnchorPane();
        this.group = new Group();
        rootPane.setMaxSize(ROOT_WIDTH, ROOT_HEIGHT - HBOX_HEIGHT);
        listOblasti.setMaxSize(100, ROOT_HEIGHT);
        listOblasti.setItems(obsListOblast);
        listOblasti.setItems(obsListZaznam);

        btnGenerateAllAction();
        btnImportAction();
        btnSaveAction();
        btnNovyZaznamAction();
        odeberZaznamAction();
        zpristupniOblastAction();
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
        grid.setMinSize(900, 450);
        grid.setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
        
        rootPane.setMinSize(400, 400);

        rootPane.getChildren().add(group);

        hboxPanel1.getChildren().addAll(btnGenerateAll, btnImport, btnSave,
                btnNovyZaznam, btnZrus);
        hboxPanel2.getChildren().addAll(labelZpristupniOblast, zpristupniOblast,
                btnOdeberZaznam);

        hboxPanel1.setMaxSize(ROOT_WIDTH, HBOX_HEIGHT);
        hboxPanel1.setSpacing(SPACING);
        hboxPanel1.setAlignment(Pos.CENTER);
        hboxPanel2.setMaxSize(ROOT_WIDTH, HBOX_HEIGHT);
        hboxPanel2.setSpacing(SPACING);
        hboxPanel2.setAlignment(Pos.CENTER);
        clickedOnList();

        labelSeznamOblasti.setMaxSize(350, 30);
        listOblasti.setMaxSize(350, 420);
        labelZaznamy.setMaxSize(350, 30);
        listZaznamy.setMaxSize(350, 420);
        
        VBox vboxRight = new VBox();
        vboxRight.setMaxSize(250, 450);
        vboxRight.setAlignment(Pos.CENTER);
        vboxRight.getChildren().addAll(labelSeznamOblasti,listOblasti);
        
        
        VBox vboxCenter = new VBox();
        vboxCenter.setMaxSize(400, 450);
        
        GridPane gridCenter = new GridPane();
        gridCenter.setAlignment(Pos.CENTER);
        gridCenter.add(rootPane, 0, 0);
        gridCenter.add(hboxPanel1, 0, 1);
        gridCenter.add(hboxPanel2, 0, 2);
        gridCenter.setVgap(SPACING);
        gridCenter.setHgap(SPACING);
        gridCenter.setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
        vboxCenter.getChildren().add(gridCenter);
        
        
        VBox vboxLeft = new VBox();
        vboxLeft.setMaxSize(250, 450);
        vboxLeft.setAlignment(Pos.CENTER);
        vboxLeft.getChildren().addAll(labelZaznamy,listZaznamy);
        
        
        grid.add(vboxRight, 0, 0);
        grid.add(vboxCenter, 1, 0);
        grid.add(vboxLeft, 2, 0);
        
        hbox.setSpacing(SPACING);

        hbox.getChildren().addAll(grid);
    }

    private void btnGenerateAllAction() {
        btnGenerateAll.setOnAction((event) -> {
            
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
            refreshVypis();
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
                            spravaOblasti.vlozZaznam(zaznam);
                            refreshVypis();
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
                grid.add(btnOk, 0, 2);
                grid.add(btnCancel, 1, 2);
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
                            if (aktualniZaznam != null) {
                                aktualniZaznam = null;
                            }

//                            for (TreeItem<String> child : defaultItem.getChildren()) {
//                                if (oblast.toString().equals(child.getValue())) {
//                                    listOblasti.getSelectionModel().select(child);
//                                }
//                            }
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
        });
    }

    private void odeberZaznamAction() {
        btnOdeberZaznam.setOnAction((event) -> {
            if (!obsListOblast.isEmpty() && aktualniOblast != null && aktualniZaznam != null) {

//                Zaznam zaznam = spravaOblasti.odeberZaznam(odeberZaznam.getSelectionModel().getSelectedItem());
//                if (spravaOblasti.getID_oblast() != 1 && zaznam != null) {
//                    if (zaznam == aktualniZaznam) {
//                        aktualniZaznam = spravaOblasti.zpristupniZaznam(EnumPozice.PRVNI);
//                    }
//                    Oblast aktualniSave = aktualniOblast;
//                    aktualniOblast = spravaOblasti.zpristupniOblast(EnumPozice.AKTUALNI);
//                    if (aktualniSave != aktualniOblast) {
//                        aktualniZaznam = null;
//                    }
//                }
                refreshVypis();
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
            } else if (aktualniZaznam == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Chyba při odebírání záznamu.");
                alert.setContentText("Aktualní záznam nenastaven.");
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

    private void clickedOnList() {
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

    private void refreshVypis() {
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

    private Group kresleni(Group group) {
        if (aktualniOblast == null) {
            return null;
        }
        Iterator<Zaznam> iterator = aktualniOblast.getZaznamy().vytvorIterator(ETypProhl.SIRKA);

        int level = 0;             // уровень узла
        int nodesInLevel = 1;      // количество узлов на текущем уровне
        int nextLevelCount = 0;    // количество узлов на следующем уровне
        double ySpacing = 80;      // вертикальное расстояние между уровнями
        double xSpacing = 60;      // горизонтальное расстояние между узлами
        double margin = 50;        // отступ от края окна
        int i = 0;

        while (iterator.hasNext()) {
            Zaznam zaznam = iterator.next();

            double x = i * xSpacing + margin;
            double y = level * ySpacing + margin;

            Circle c = new Circle(x, y, 20, Color.LIGHTGREEN);
            c.setStroke(Color.BLACK);
            Label l = new Label("" + zaznam.getID());
            l.setLayoutX(x - 10);
            l.setLayoutY(y - 10);

            group.getChildren().addAll(c, l);

            i++;
            nextLevelCount++;

            if (i == nodesInLevel) {
                i = 0;
                level++;
                nodesInLevel = nextLevelCount * 2;
                nextLevelCount = 0;
            }
        }

        return group;
    }
}
