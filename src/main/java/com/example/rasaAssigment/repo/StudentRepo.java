package com.example.rasaAssigment.repo;

import com.example.rasaAssigment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository <Student,Integer>{
}
