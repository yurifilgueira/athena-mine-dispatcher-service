package com.projectathena.mineservice.services;

import com.projectathena.mineservice.model.dto.requests.PublishJobRequest;
import com.projectathena.mineservice.model.entities.Job;
import com.projectathena.mineservice.model.enums.JobStatus;
import com.projectathena.mineservice.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final Logger logger = Logger.getLogger(JobTimeoutScanner.class.getName());

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void publishJob(PublishJobRequest request){
        Job job = new Job();
        job.setRequestedBy(request.requestedBy());
        job.setJobStatus(JobStatus.PENDING);
        job.setCreatedAt(new Date());
        job.setGitRepositoryOwner(request.gitRepositoryOwner());
        job.setGitRepositoryName(request.gitRepositoryName());

        jobRepository.save(job);
    }

    public List<Job> findJobsByStatus(JobStatus jobStatus) {
        return jobRepository.findByJobStatus(jobStatus);
    }

    public void updateJobToPending(Job job) {
        job.setJobStatus(JobStatus.PENDING);
        job.setLastUpdated(new Date());

        jobRepository.save(job);

        logger.info("Job updated to PENDING: " + job.getId());
    }
}