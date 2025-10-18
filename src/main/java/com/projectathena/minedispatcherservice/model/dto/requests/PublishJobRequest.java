package com.projectathena.minedispatcherservice.model.dto.requests;

public record PublishJobRequest (
         String requestedBy,
         String gitRepositoryName,
         String gitRepositoryOwner
){
}
