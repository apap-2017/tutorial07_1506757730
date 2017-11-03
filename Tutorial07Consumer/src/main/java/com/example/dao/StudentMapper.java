package com.example.apaptutorial05.dao;

import java.util.List;
import com.example.apaptutorial05.model.CourseModel;
import org.apache.ibatis.annotations.*;
import com.example.apaptutorial05.model.StudentModel;

@Mapper
public interface StudentMapper
{
    @Select("select npm, name, gpa from student where npm = #{npm}")
    @Results(value = {
            @Result(property = "npm", column = "npm"),
            @Result(property = "name", column = "name"),
            @Result(property = "gpa", column = "gpa"),
            @Result(property = "courses", column = "npm", javaType = List.class, many = @Many(select="selectCourses"))
    })
    StudentModel selectStudent (@Param("npm") String npm);

    @Select("select npm, name, gpa from student")
    @Results(value = {
            @Result(property = "npm", column = "npm"),
            @Result(property = "name", column = "name"),
            @Result(property = "gpa", column = "gpa"),
            @Result(property = "courses", column = "npm", javaType = List.class, many = @Many(select="selectCourses"))
    })
    List<StudentModel> selectAllStudents ();

    @Insert("INSERT INTO student (npm, name, gpa) VALUES (#{npm}, #{name}, #{gpa})")
    void addStudent (StudentModel student);

    @Delete("DELETE FROM STUDENT WHERE npm = #{npm}")
    void deleteStudent(String npm);

    @Update("UPDATE STUDENT SET name = #{name}, gpa = #{gpa} where npm = #{npm}")
    void updateStudent(StudentModel student);

    @Select("select course.id_course, name, credits " +
            "from studentcourse join course " +
            "on studentcourse.id_course = course.id_course " +
            "where studentcourse.npm = #{npm}")
    List<CourseModel> selectCourses (@Param("npm") String npm);

    @Select("select id_course, name, credits from course where id_course = #{id_course}")
    @Results(value = {
            @Result(property = "id_course", column = "id_course"),
            @Result(property = "name", column = "name"),
            @Result(property = "credit", column = "credit"),
            @Result(property = "students", column = "id_course", javaType = List.class, many = @Many(select="selectAllStudentInCourse"))
    })
    CourseModel selectCourse (@Param("id_course") String id_course);

    @Select("select course.id_course, student.npm, student.name from course " +
            "join studentcourse on studentcourse.id_course = course.id_course " +
            "join student on studentcourse.npm = student.npm " +
            "where course.id_course = #{id_course}")
    List<StudentModel> selectAllStudentInCourse(@Param("id_course") String id_course);

}
