package com.projectathena.minedispatcherservice.model.dto.requests;

import com.projectathena.minedispatcherservice.model.enums.JobStatus;

public record JobSubmissionResponse(
        String jobId,
        JobStatus status,
        String statusUrl
) {
}
