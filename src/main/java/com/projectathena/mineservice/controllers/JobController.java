package com.projectathena.mineservice.controllers;

import com.projectathena.mineservice.model.dto.requests.PublishJobRequest;
import com.projectathena.mineservice.model.entities.Job;
import com.projectathena.mineservice.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping(value = "/publish")
    public ResponseEntity<?> publishJob(@RequestBody PublishJobRequest request){

        jobService.publishJob(request);

        return ResponseEntity.accepted().build();
    }

}
