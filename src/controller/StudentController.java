package controller;

import model.Alumno;

import java.util.HashMap;
import java.util.Map;

public class StudentController {

    private Map<String, Alumno> studentMap;

    public StudentController() {
        this.studentMap = new HashMap<>();
    }

    public Map<String, Alumno> getStudentMap() {
        return studentMap;
    }

    public Alumno getStudent(String matricula) {
        return studentMap.get(matricula);
    }


}
