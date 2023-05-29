package com.example.rasaAssigment.controller;

import com.example.rasaAssigment.dto.ResponseDTO;
import com.example.rasaAssigment.dto.StudentDTO;
import com.example.rasaAssigment.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.rasaAssigment.service.StudentService;
import com.example.rasaAssigment.util.VarList;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/saveStudent")
    public ResponseEntity saveStudent(@RequestBody StudentDTO studentDTO){
        try {
            String res = studentService.saveStudent(studentDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMassage("Success");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMassage("Student Registered");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMassage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }

        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/updateStudent")
    public ResponseEntity updateStudent(@RequestBody StudentDTO studentDTO){
        try {
            String res = studentService.updateStudent(studentDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMassage("Update Success");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("01")) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMassage("Student not registered");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMassage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }

        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteStudent/{stdID}")
    public ResponseEntity deleteStudent(@PathVariable int stdID){
        try {
            String res = studentService.deleteStudent(stdID);
            if(res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMassage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMassage("No student available for this ID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/searchStudent/{stdID}")
    public ResponseEntity searchStudent(@PathVariable int stdID){
        try {
           StudentDTO studentDTO = studentService.searchStudent(stdID);
            if(studentDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMassage("Success");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMassage("No student available for this ID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getAllStudent")
    public ResponseEntity getAllStudent(){
        try {
            List<StudentDTO> getAllStudent = studentService.getAllStudent();
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMassage("Success");
                responseDTO.setContent(getAllStudent);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
