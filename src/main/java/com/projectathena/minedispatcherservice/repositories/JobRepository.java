package com.projectathena.minedispatcherservice.repositories;

import com.projectathena.minedispatcherservice.model.entities.Job;
import com.projectathena.minedispatcherservice.model.enums.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,String> {
    List<Job> findByJobStatus(JobStatus jobStatus);
}
