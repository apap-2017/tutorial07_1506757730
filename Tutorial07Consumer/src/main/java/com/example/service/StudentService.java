package com.example.apaptutorial05.service;

import java.util.List;

import com.example.apaptutorial05.model.CourseModel;
import com.example.apaptutorial05.model.StudentModel;

public interface StudentService
{
    StudentModel selectStudent (String npm);

    List<StudentModel> selectAllStudents ();

    void addStudent (StudentModel student);

    void deleteStudent (String npm);

    void updateStudent (StudentModel student);

    CourseModel selectCourse (String id);

}
