package application;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentManagementApp extends Application {
    private StudentDAO studentDAO = new StudentDAO();
    private TableView<Student> table = new TableView<>();
    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Management System");

        // Setup TableView
        TableColumn<Student, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));

        TableColumn<Student, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFirstName()));

        TableColumn<Student, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLastName()));

        TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmail()));

        TableColumn<Student, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPhone()));

        table.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, emailColumn, phoneColumn);
        refreshTable();

        // Form Fields
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        firstNameField.getStyleClass().add("form-field");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        lastNameField.getStyleClass().add("form-field");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.getStyleClass().add("form-field");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");
        phoneField.getStyleClass().add("form-field");

        Button addButton = new Button("Add     ");
        addButton.getStyleClass().add("button");
        addButton.setOnAction(e -> {
            Student student = new Student(0, firstNameField.getText(), lastNameField.getText(), emailField.getText(), phoneField.getText());
            studentDAO.addStudent(student);
            refreshTable();
        });

        Button updateButton = new Button("Update");
        updateButton.getStyleClass().add("button");
        updateButton.setOnAction(e -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                selectedStudent.setFirstName(firstNameField.getText());
                selectedStudent.setLastName(lastNameField.getText());
                selectedStudent.setEmail(emailField.getText());
                selectedStudent.setPhone(phoneField.getText());
                studentDAO.updateStudent(selectedStudent);
                refreshTable();
            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("button");
        deleteButton.setOnAction(e -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                studentDAO.deleteStudent(selectedStudent.getId());
                refreshTable();
            }
        });

        // Layout
        GridPane form = new GridPane();
        form.add(new Label("First Name:"), 0, 0);
        form.add(firstNameField, 1, 0);
        form.add(new Label("Last Name:"), 0, 1);
        form.add(lastNameField, 1, 1);
        form.add(new Label("Email:"), 0, 2);
        form.add(emailField, 1, 2);
        form.add(new Label("Phone:"), 0, 3);
        form.add(phoneField, 1, 3);
        form.add(addButton, 0, 4);
        form.add(updateButton, 1, 4);
        form.add(deleteButton, 2, 4);

        form.setVgap(10);
        form.setHgap(10);

        GridPane layout = new GridPane();
        layout.add(table, 0, 0);
        layout.add(form, 1, 0);

        layout.setHgap(20);
        layout.setVgap(20);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout, 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void refreshTable() {
        studentList.setAll(studentDAO.getAllStudents());
        table.setItems(studentList);
    }
}
