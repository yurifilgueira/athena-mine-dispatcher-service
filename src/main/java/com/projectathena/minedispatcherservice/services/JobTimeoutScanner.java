package com.projectathena.minedispatcherservice.services;

import com.projectathena.minedispatcherservice.model.entities.Job;
import com.projectathena.minedispatcherservice.model.enums.JobStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

@Service
public class JobTimeoutScanner {

    private final JobService jobService;
    private final Logger logger = Logger.getLogger(JobTimeoutScanner.class.getName());

    public JobTimeoutScanner(JobService jobService) {
        this.jobService = jobService;
    }

    @Scheduled(fixedRate = 30000)
    @Transactional
    public void scan() {
        List<Job> jobs = jobService.findJobsByStatus(JobStatus.MINING);

        for (Job job : jobs) {
            Instant jobLastUpdatedAt = job.getLastUpdated().toInstant();
            Instant now = Instant.now();
            Duration duration = Duration.between(jobLastUpdatedAt, now);

            if (duration.toSeconds() > 60) {
                logger.warning("Job Timeout Scanner: " + job.getId());
                logger.info("Job Timeout Scanner Update Status: " + job.getJobStatus());
                jobService.updateJobToPending(job);
            }
        }
    }

}
