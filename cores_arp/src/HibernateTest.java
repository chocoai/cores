import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.lanen.model.Employee;
import com.lanen.model.Role;


public class HibernateTest {

	@Test
	public void testMany2Many(){
		//TODO 创建配置对象
		Configuration config=new Configuration().configure();
		//TODO 创建服务注册对象
		//org.hibernate.seServiceRegistry serviceRegistry=new
		
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		Employee e=new Employee();
		e.setEmployeeid("03");
		Role r1=new Role();
		r1.setId(Long.valueOf("5"));
		
		Role r2=new Role();
		r2.setId(Long.valueOf("6"));
		Role r3=new Role();
		r3.setId(Long.valueOf("7"));
		Set<Role> s=new HashSet<Role>();
		s.add(r1);
		s.add(r2);
		s.add(r3);
		e.setRoles1(s);
		session.update(e);
	}
}
