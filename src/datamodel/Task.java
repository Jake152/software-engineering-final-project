package datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "taskname")
	private String taskname;

	@Column(name = "parentId")
	private Integer parentId;

	@Column(name = "serverId")
	private Integer serverId;
	
	@Column(name = "Archived")
	private Boolean archived;

	public Task() {
	}

	public Task(String taskname) {
		this.taskname = taskname;
		archived = false;
	}

	public Task(Integer id, Integer parentId, Integer serverId, String taskname) {
		this.id = id;
		this.parentId = parentId;
		this.serverId = serverId;
		this.taskname = taskname;
		archived = false; 
	}

	public Task(Integer id, String taskname) {
		this.id = id;
		this.taskname = taskname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public String getTaskName() {
		return this.taskname;
	}

	public void setTaskName(String taskname) {
		this.taskname = taskname;
	}
	
	public void archiveTask() {
		this.archived = true;
	}
	
	public void unArchiveTask() {
		this.archived = false;
	}
	public boolean isArchived() {
		return archived;
	}
	@Override
	public String toString() {
		return "Server: " + this.id + ", " + this.parentId + ", " + this.serverId + ", " + this.taskname +", " + (this.archived ? "Archived" : "Not Archived");
	}
}