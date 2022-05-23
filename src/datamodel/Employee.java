package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "name")
   private String name;
   
   @Column(name = "")
   private Boolean archived;

   public Employee() {
   }

   public Employee(Integer id, String name) {
      this.id = id;
      this.name = name;
   }

   public Employee(String name) {
      this.name = name;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
   public void archiveEmployee() {
      this.archived = true;
   } 

   public void unArchiveEmployee() {
      this.archived = false;
   }
	public boolean isArchived() {
		return archived;
	}
   @Override
   public String toString() {
      return "Employee: " + this.id + ", " + this.name;
   }
}
