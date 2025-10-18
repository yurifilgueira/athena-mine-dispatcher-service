package com.projectathena.minedispatcherservice.services;

import com.projectathena.minedispatcherservice.model.dto.requests.JobSubmissionResponse;
import com.projectathena.minedispatcherservice.model.dto.requests.PublishJobRequest;
import com.projectathena.minedispatcherservice.model.entities.Job;
import com.projectathena.minedispatcherservice.model.enums.JobStatus;
import com.projectathena.minedispatcherservice.repositories.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final Logger logger = LoggerFactory.getLogger(JobService.class);
    @Value(value = "${spring.application.name}")
    private String applicationName;
    private final static String BASE_URL_VERIFY_JOB_STATUS = "http://localhost:8080";

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public JobSubmissionResponse publishJob(PublishJobRequest request){
        Job job = new Job();
        job.setRequestedBy(request.requestedBy());
        job.setJobStatus(JobStatus.PENDING);
        job.setCreatedAt(new Date());
        job.setGitRepositoryOwner(request.gitRepositoryOwner());
        job.setGitRepositoryName(request.gitRepositoryName());

        var jobEntity =jobRepository.save(job);

        String urlJobStatus = BASE_URL_VERIFY_JOB_STATUS + "/" + applicationName + "/jobs/status/" + jobEntity.getId();
        return new JobSubmissionResponse(jobEntity.getId(), jobEntity.getJobStatus(), urlJobStatus);
    }

    public List<Job> findJobsByStatus(JobStatus jobStatus) {
        return jobRepository.findByJobStatus(jobStatus);
    }

    public void updateJobToPending(Job job) {
        job.setJobStatus(JobStatus.PENDING);
        job.setLastUpdated(new Date());
        job.setWorkerId(null);

        jobRepository.save(job);

        logger.info("Job updated to PENDING: " + job.getId());
    }

    public Optional<Job> findById(String id) {
        return jobRepository.findById(id);
    }
}