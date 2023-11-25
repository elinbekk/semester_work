module com.example.semester_work2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.semester_work2 to javafx.fxml;
    exports com.example.semester_work2;
}