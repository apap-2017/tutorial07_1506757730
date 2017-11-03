package com.example.rest;

import com.example.model.CourseModel;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class CourseRestController {
    @Autowired
    StudentService studentService;
    @RequestMapping("/course/view/{id}")
    public CourseModel view (@PathVariable("id") String id){
        CourseModel course = studentService.selectCourse(id);
        return course;
    }

    @RequestMapping("/course/viewall")
    public List<CourseModel> viewall (){
        List<CourseModel> courses = studentService.selectAllCourses();
        return courses;
    }

}
