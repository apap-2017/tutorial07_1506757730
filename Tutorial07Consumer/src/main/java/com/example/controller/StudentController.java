package com.example.apaptutorial05.controller;

import java.util.List;

import com.example.apaptutorial05.model.CourseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.apaptutorial05.model.StudentModel;
import com.example.apaptutorial05.service.StudentService;
import javax.websocket.server.PathParam;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;

    @RequestMapping("/")
    public String index (Model model)
    {
        model.addAttribute("page", "index");
        return "index";
    }


    @RequestMapping("/student/add")
    public String add (Model model)
    {
        model.addAttribute("page", "form-tambah-student");
        return "form-add";
    }

    @RequestMapping("/student/add/submit")
    public String addSubmit (
            Model model,
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        model.addAttribute("page", "berhasil-add");
        StudentModel student = new StudentModel (npm, name, gpa, null);
        studentDAO.addStudent (student);

        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        model.addAttribute("page", "view-student");
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        model.addAttribute("page", "view-student");
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        model.addAttribute("page", "view-all");
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);

        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
        model.addAttribute("page", "berhasil-delete");
        StudentModel student = studentDAO.selectStudent(npm);
        if(student != null){
            studentDAO.deleteStudent (npm);
            return "delete";
        }
        else{
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }

    @RequestMapping("/student/update/{npm}")
    public String update (Model model, @PathVariable(value = "npm") String npm)
    {
        model.addAttribute("page", "ubah-student");
        StudentModel student = studentDAO.selectStudent(npm);
        if(student != null){
            model.addAttribute("student", student);
            return "form-update";
        }
        else{
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }

    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
    public String updateSubmit (@ModelAttribute StudentModel student, Model model)
    {
        model.addAttribute("page", "berhasil-update");
        studentDAO.updateStudent(student);
        return "success-update";
    }

    @RequestMapping("/course/view/{id}")
    public String viewCourse(Model model, @PathVariable("id") String id){
        model.addAttribute("page", "view-course");
        CourseModel course = studentDAO.selectCourse(id);
        if(course != null){
            model.addAttribute("course", course);
            return "view-course";
        }
        else {
            model.addAttribute("npm", id);
            return "not-found";
        }

    }
}
