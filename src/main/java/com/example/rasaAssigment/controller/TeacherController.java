package com.example.rasaAssigment.controller;

import com.example.rasaAssigment.dto.ResponseDTO;
import com.example.rasaAssigment.dto.TeacherDTO;
import com.example.rasaAssigment.service.TeacherService;
import com.example.rasaAssigment.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Teacher")
public class TeacherController {


    @Autowired
    private TeacherService TeacherService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/saveTeacher")
    public ResponseEntity saveTeacher(@RequestBody TeacherDTO TeacherDTO){
        try {
            String res = TeacherService.saveTeacher(TeacherDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMassage("Success");
                responseDTO.setContent(TeacherDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMassage("Teacher Registered");
                responseDTO.setContent(TeacherDTO);
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
    @PutMapping("/updateTeacher")
    public ResponseEntity updateTeacher(@RequestBody TeacherDTO TeacherDTO){
        try {
            String res = TeacherService.updateTeacher(TeacherDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMassage("Update Success");
                responseDTO.setContent(TeacherDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("01")) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMassage("Teacher not registered");
                responseDTO.setContent(TeacherDTO);
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

    @DeleteMapping("/deleteTeacher/{techID}")
    public ResponseEntity deleteTeacher(@PathVariable int techID){
        try {
            String res = TeacherService.deleteTeacher(techID);
            if(res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMassage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMassage("No Teacher available for this ID");
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
    @GetMapping("/searchTeacher/{techID}")
    public ResponseEntity searchTeacher(@PathVariable int techID){
        try {
           TeacherDTO TeacherDTO = TeacherService.searchTeacher(techID);
            if(TeacherDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMassage("Success");
                responseDTO.setContent(TeacherDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMassage("No Teacher available for this ID");
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
    @GetMapping("/getAllTeacher")
    public ResponseEntity getAllTeacher(){
        try {
            List<TeacherDTO> getAllTeacher = TeacherService.getAllTeacher();
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMassage("Success");
                responseDTO.setContent(getAllTeacher);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMassage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
