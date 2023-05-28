package repo;

import entity.Student;
import entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository <Teacher,Integer>{
}
