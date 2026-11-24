package com.university;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StudentController {
    @FXML private TextField regField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> colReg;
    @FXML private TableColumn<Student, String> colFirst;
    @FXML private TableColumn<Student, String> colLast;
    @FXML private Label statusLabel;
    @FXML private Button saveButton;

    private final ObservableList<Student> studentList = FXCollections.observableArrayList();
    private BooleanBinding fieldsEmptyBinding;

    @FXML
    public void initialize() {
        colReg.setCellValueFactory(cellData -> cellData.getValue().regNumberProperty());
        colFirst.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        colLast.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        studentTable.setItems(studentList);

        fieldsEmptyBinding = regField.textProperty().isEmpty()
            .or(firstNameField.textProperty().isEmpty())
            .or(lastNameField.textProperty().isEmpty());
        saveButton.disableProperty().bind(fieldsEmptyBinding);

        loadStudents();
    }

    @FXML
    private void handleSaveStudent() {
        String reg = regField.getText().trim();
        String fName = firstNameField.getText().trim();
        String lName = lastNameField.getText().trim();

        if (reg.isEmpty() || fName.isEmpty() || lName.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        saveButton.disableProperty().unbind();
        saveButton.setDisable(true);
        statusLabel.setText("Saving student...");

        Task<Void> saveTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                String sql = "INSERT INTO students (registration_number, first_name, last_name, email) VALUES (?, ?, ?, ?)";
                try (Connection conn = DatabaseUtil.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, reg);
                    stmt.setString(2, fName);
                    stmt.setString(3, lName);
                    stmt.setString(4, reg.toLowerCase() + "@university.edu");
                    stmt.executeUpdate();
                }
                return null;
            }
        };

        saveTask.setOnSucceeded(e -> Platform.runLater(() -> {
            statusLabel.setText("Student saved successfully!");
            regField.clear();
            firstNameField.clear();
            lastNameField.clear();
            saveButton.disableProperty().bind(fieldsEmptyBinding);
            loadStudents();
        }));

        saveTask.setOnFailed(e -> Platform.runLater(() -> {
            Throwable ex = saveTask.getException();
            statusLabel.setText("Save failed: " + (ex != null ? ex.getMessage() : "Unknown error"));
            saveButton.disableProperty().bind(fieldsEmptyBinding);
        }));

        new Thread(saveTask).start();
    }

    private void loadStudents() {
        LoadStudentsTask task = new LoadStudentsTask();

        task.setOnSucceeded(e -> Platform.runLater(() -> {
            studentList.setAll(task.getValue());
            statusLabel.setText("Loaded " + studentList.size() + " students from database.");
        }));

        task.setOnFailed(e -> Platform.runLater(() -> {
            Throwable ex = task.getException();
            statusLabel.setText("Load failed: " + (ex != null ? ex.getMessage() : "Check DB password/server"));
        }));

        new Thread(task).start();
    }
}