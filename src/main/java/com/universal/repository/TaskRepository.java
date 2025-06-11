package com.universal.repository;

import com.universal.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByStatus(String status);


    //    @Query("SELECT c FROM Cart c WHERE c.user.email = ?1 AND c.id = ?2")
    @Query("SELECT c FROM Task c WHERE c.user.id = ?1")
    List<Task> findAllByUserId(Long userId);
}
