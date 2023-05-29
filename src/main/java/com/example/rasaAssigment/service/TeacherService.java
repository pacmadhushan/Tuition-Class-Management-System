package com.example.rasaAssigment.service;

import com.example.rasaAssigment.dto.StudentDTO;
import com.example.rasaAssigment.dto.TeacherDTO;
import com.example.rasaAssigment.entity.Student;
import com.example.rasaAssigment.entity.Teacher;
import com.example.rasaAssigment.repo.StudentRepo;
import com.example.rasaAssigment.repo.TeacherRepo;
import com.example.rasaAssigment.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

     @Autowired
     private ModelMapper modelMapper;
    public String saveTeacher(TeacherDTO teacherDTO){
        if(teacherRepo.existsById(teacherDTO.getTechID())){
            return VarList.RSP_DUPLICATED;
        }else {
            teacherRepo.save(modelMapper.map(teacherDTO, Teacher.class));
            return VarList.RSP_SUCCESS;

        }

    }
    public String updateTeacher(TeacherDTO teacherDTO){
        if (teacherRepo.existsById(teacherDTO.getTechID())){
            teacherRepo.save(modelMapper.map(teacherDTO,Teacher.class));
            return VarList.RSP_SUCCESS;


        }else {
            return VarList.RSP_NO_DATA_FOUND;

        }
    }

    public String deleteTeacher(int techID){
        if (teacherRepo.existsById(techID)){
//            Student student = studentRepo.findById(stdID).orElse(null);
            teacherRepo.deleteById(techID);
//            studentRepo.delete(student);
            return VarList.RSP_SUCCESS;

        }else {
            return VarList.RSP_NO_DATA_FOUND;

        }
    }

    public TeacherDTO searchTeacher(int techID){
        if (teacherRepo.existsById(techID)){
          Teacher teacher= teacherRepo.findById(techID).orElse(null);
          return modelMapper.map(teacher,TeacherDTO.class);

        }else {
            return null;

        }
    }

    public List<TeacherDTO> getAllTeacher(){
        List<Teacher> teacherList = teacherRepo.findAll();
        return modelMapper.map(teacherList,new TypeToken<ArrayList<TeacherDTO>>(){}.getType());

    }
}
