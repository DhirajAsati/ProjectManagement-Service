package com.project.management.resources;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.management.utils.CustomDateDeserializer;

import lombok.Data;

@Data
@Document(collection="TASK_RESOURCE")
public class TaskResource {
	
	@Id
	private String taskId;
	
	@Size(min=1, max=70, message="Task Name is required")
	private String taskName;
	
	private String projectId;
	
	private String parentId;
	private String parentTask;
	
	private String priority;
	
	@NotNull(message="Date is required")
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endDate;

	private String userId;
	
	private String status;
}
