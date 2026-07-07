package es.upm.da.etsisi.DABiblioteca.data.model;

public class Student extends Entity {

    private String name;
    private String studentId;
    private String phone;
    private String mail;

    public Student(String name, String studentId, String phone, String mail) {
        super(-1);
        this.name = name;
        setStudentId(studentId);
        this.phone = phone;
        setMail(mail);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        String STUDENT_ID_REGEX = "^[A-Za-z]{2}\\d{4}$";
        if (!studentId.matches(STUDENT_ID_REGEX)) {
            throw new IllegalArgumentException("Student Id is not valid");
        }
        this.studentId = studentId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        if (!mail.endsWith("@alumnos.upm.es")) {
            throw new IllegalArgumentException("Mail must be from @alumnos.upm.es");
        }
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
