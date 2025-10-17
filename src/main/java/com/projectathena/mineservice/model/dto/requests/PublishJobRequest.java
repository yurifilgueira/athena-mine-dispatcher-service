package com.projectathena.mineservice.model.dto.requests;

public record PublishJobRequest (
         String requestedBy,
         String gitRepositoryName,
         String gitRepositoryOwner
){
}
