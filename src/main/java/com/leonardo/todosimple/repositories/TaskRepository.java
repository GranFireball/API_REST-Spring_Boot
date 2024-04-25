package com.leonardo.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.todosimple.models.Task;
import com.leonardo.todosimple.models.projections.TaskProjection;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

  List<TaskProjection> findByUser_Id(Long id);

  // @Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
  // List<Task> findByUser_Id(@Param("id") Long id);

  // @Query(value = "SELECT * FROM Task t WHERE t.user_id = :id", nativeQuery = true)
  // List<Task> findByUser_Id(@Param("id") Long id);
}
