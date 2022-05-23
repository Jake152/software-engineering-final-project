/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.Employee;
import datamodel.Task;
import datamodel.Server;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
	static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory != null) {
			return sessionFactory;
		}
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static void ArchiveEmployee(Integer id) {
		Employee employee;
		Session session = getSessionFactory().openSession();
		Transaction tx = null; 
		try {
			tx = session.beginTransaction();
			employee = (Employee)session.createQuery("FROM Server WHERE id="+id).uniqueResult();				
			employee.archiveEmployee();
			session.save(employee);
			tx.commit();
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}	
	public static void UnArchiveEmployee(Integer id) {
		Employee employee;
		Session session = getSessionFactory().openSession();
		Transaction tx = null; 
		try {
			tx = session.beginTransaction();
			employee = (Employee)session.createQuery("FROM Server WHERE id="+id).uniqueResult();				
			employee.unArchiveEmployee();
			session.save(employee);
			tx.commit();
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}	
	public static List<Employee> listEmployees() {
		List<Employee> resultList = new ArrayList<Employee>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> employees = session.createQuery("FROM Employee").list();
			for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
				Employee employee = (Employee) iterator.next();
				if(employee.equals(employee)==false) 
				{
					resultList.add(employee);
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}
	public static List<Employee> listAllEmployees() {
		List<Employee> resultList = new ArrayList<Employee>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> employees = session.createQuery("FROM Employee WHERE archived=false").list();
			for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
				Employee employee = (Employee) iterator.next();
				resultList.add(employee);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}

	public static List<Employee> listEmployees(String keyword) {
		List<Employee> resultList = new ArrayList<Employee>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> employees = session.createQuery("FROM Employee WHERE archived=false").list();
			for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
				Employee employee = (Employee) iterator.next();
				if (employee.getName().startsWith(keyword)) {
					resultList.add(employee);
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}

	public static void createEmployees(String userName) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Employee(userName));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	public static void ArchiveServer(Integer id) {
		Server server;
		Session session = getSessionFactory().openSession();
		Transaction tx = null; 
		try {
			tx = session.beginTransaction();
			server = (Server)session.createQuery("FROM Server WHERE id="+id).uniqueResult();				
			server.archiveServer();
			session.save(server);
			tx.commit();
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}	
	public static void UnArchiveServer(Integer id) {
		Server server;
		Session session = getSessionFactory().openSession();
		Transaction tx = null; 
		try {
			tx = session.beginTransaction();
			server = (Server)session.createQuery("FROM Server WHERE id="+id).uniqueResult();				
			server.unArchiveServer();
			session.save(server);
			tx.commit();
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}	
	public static List<Server> listAllServers() {
		List<Server> resultList = new ArrayList<Server>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> servers = session.createQuery("FROM Server WHERE archived=false").list();
			for (Iterator<?> iterator = servers.iterator(); iterator.hasNext();) {
				Server server = (Server) iterator.next();
				resultList.add(server);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}
	public static List<Server> listServers() {
		List<Server> resultList = new ArrayList<Server>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> servers = session.createQuery("FROM Server WHERE archived=false").list();
			for (Iterator<?> iterator = servers.iterator(); iterator.hasNext();) {
				Server server = (Server) iterator.next();
				if(server.isArchived()==false) {
					resultList.add(server);
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}

	public static List<Server> listServers(String keyword) {
		List<Server> resultList = new ArrayList<Server>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> servers = session.createQuery("FROM Server").list();
			for (Iterator<?> iterator = servers.iterator(); iterator.hasNext();) {
				Server server = (Server) iterator.next();
				if (server.getHostname().startsWith(keyword)) {
					resultList.add(server);
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}

	public static void createServers(String hostName) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Server(hostName));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void ArchiveTask(Integer id) {
			Task task;
			Session session = getSessionFactory().openSession();
			Transaction tx = null; 
			try {
				tx = session.beginTransaction();
				task = (Task)session.createQuery("FROM task WHERE id="+id).uniqueResult();				
				task.archiveTask();
				session.save(task);
				tx.commit();
			}catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			}finally {
				session.close();
			}
	}	
	public static void UnArchiveTask(Integer id) {
		Task task;
		Session session = getSessionFactory().openSession();
		Transaction tx = null; 
		try {
			tx = session.beginTransaction();
			task = (Task)session.createQuery("FROM task WHERE id="+id).uniqueResult();				
			task.unArchiveTask();
			session.save(task);
			tx.commit();
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}	
	public static List<Task> listAllTasks() {
		List<Task> resultList = new ArrayList<Task>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> tasks = session.createQuery("FROM Task WHERE archived=false").list();
			for (Iterator<?> iterator = tasks.iterator(); iterator.hasNext();) {
				Task task = (Task) iterator.next();
				resultList.add(task);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}
	public static List<Task> listTasks() {
		List<Task> resultList = new ArrayList<Task>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> tasks = session.createQuery("FROM Task WHERE archived=false").list();
			for (Iterator<?> iterator = tasks.iterator(); iterator.hasNext();) {
				Task task = (Task) iterator.next();
				if(task.isArchived()==false) {
					resultList.add(task);
				}	
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}
	public static List<Task> listTasks(String keyword) {
		List<Task> resultList = new ArrayList<Task>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> tasks = session.createQuery("FROM Task WHERE archived=false").list();
			for (Iterator<?> iterator = tasks.iterator(); iterator.hasNext();) {
				Task task = (Task) iterator.next();
				if (task.getTaskName().startsWith(keyword)) {
					resultList.add(task);
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}

	public static void createTasks(String taskName) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Task(taskName));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
