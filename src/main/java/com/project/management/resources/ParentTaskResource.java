package com.project.management.resources;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="PARENT_TASK_RESOURCE")
public class ParentTaskResource {
	
	@Id
	private String parentId;
	
	private String parentTask;
}
