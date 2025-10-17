package com.projectathena.mineservice.services;

import com.projectathena.mineservice.model.dto.requests.PublishJobRequest;
import com.projectathena.mineservice.model.entities.Job;
import com.projectathena.mineservice.model.enums.JobStatus;
import com.projectathena.mineservice.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JobService {

    private JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void publishJob(PublishJobRequest request){
        Job job = new Job();
        job.setRequestedBy(request.requestedBy());
        job.setStatus(JobStatus.PENDING);
        job.setCreatedAt(new Date());
        job.setGitRepositoryOwner(request.gitRepositoryOwner());
        job.setGitRepositoryName(request.gitRepositoryName());

        jobRepository.save(job);
    }
}
