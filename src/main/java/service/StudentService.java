package service;

import dto.StudentDTO;
import entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import repo.StudentRepo;
import util.VarList;

import javax.transaction.Transactional;

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
}
