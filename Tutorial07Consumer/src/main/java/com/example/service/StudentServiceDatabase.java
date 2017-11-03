package com.example.apaptutorial05.service;

import java.util.List;

import com.example.apaptutorial05.model.CourseModel;
import jdk.management.resource.internal.inst.InitInstrumentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apaptutorial05.dao.StudentMapper;
import com.example.apaptutorial05.model.StudentModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceDatabase implements StudentService
{
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public StudentModel selectStudent (String npm)
    {
        log.info ("select student with npm {}", npm);
        return studentMapper.selectStudent (npm);
    }


    @Override
    public List<StudentModel> selectAllStudents ()
    {
        log.info ("select all students");
        return studentMapper.selectAllStudents ();
    }


    @Override
    public void addStudent (StudentModel student)
    {
        studentMapper.addStudent (student);
    }


    @Override
    public void deleteStudent (String npm)
    {
        log.info("student " + npm + "deleted");
        studentMapper.deleteStudent(npm);
    }

    @Override
    public void updateStudent(StudentModel student) {
        log.info("data student " + "berhasil diubah");
        studentMapper.updateStudent(student);
    }

    @Override
    public CourseModel selectCourse(String id){
        log.info("masuk ke select course");
        return studentMapper.selectCourse(id);
    }

}
