package com.example.service;

import com.example.dao.CourseDAO;
import com.example.model.CourseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CourseServiceRest implements CourseService{
    @Autowired
    private CourseDAO courseDAO;

    @Override
    public CourseModel selectCourse(String id){
        log.info("REST - select course with id {}", id);
        CourseModel course = courseDAO.selectCourse(id);
        return course;
    }
}
