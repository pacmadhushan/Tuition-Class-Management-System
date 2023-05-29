package com.example.rasaAssigment.service;

import com.example.rasaAssigment.dto.StudentDTO;
import com.example.rasaAssigment.entity.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.rasaAssigment.repo.StudentRepo;
import com.example.rasaAssigment.util.VarList;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

     @Autowired
     private ModelMapper modelMapper;
    public String saveStudent(StudentDTO studentDTO){
        if(studentRepo.existsById(studentDTO.getStdID())){
            return VarList.RSP_DUPLICATED;
        }else {
            studentRepo.save(modelMapper.map(studentDTO, Student.class));
            return VarList.RSP_SUCCESS;

        }

    }
    public String updateStudent(StudentDTO studentDTO){
        if (studentRepo.existsById(studentDTO.getStdID())){
            studentRepo.save(modelMapper.map(studentDTO,Student.class));
            return VarList.RSP_SUCCESS;


        }else {
            return VarList.RSP_NO_DATA_FOUND;

        }
    }

    public String deleteStudent(int stdID){
        if (studentRepo.existsById(stdID)){
            Student student = studentRepo.findById(stdID).orElse(null);
            studentRepo.delete(student);
            return VarList.RSP_SUCCESS;

        }else {
            return VarList.RSP_NO_DATA_FOUND;

        }
    }

    public StudentDTO searchStudent(int stdID){
        if (studentRepo.existsById(stdID)){
          Student student= studentRepo.findById(stdID).orElse(null);
          return modelMapper.map(student,StudentDTO.class);

        }else {
            return null;

        }
    }

    public List<StudentDTO> getAllStudent(){
        List<Student> studentList = studentRepo.findAll();
        return modelMapper.map(studentList,new TypeToken<ArrayList<StudentDTO>>(){}.getType());

    }
}
