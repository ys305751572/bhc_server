package com.gcs.sysmgr.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 系统初始化监听器
 * 
 * @author zhoub
 * @date 2014年6月10日12:44:08
 */
public class SystemInitListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
//		ServletContext servletContext = sce.getServletContext();
//		EntityManagerFactory emf = SpringContextHolder.getBean("entityManagerFactory");
//		 EntityManager em = emf.createEntityManager();
//		   String sql = "from Sjzd order by to_number(dm)";
//		   Query query = em.createQuery(sql);
//		   List<Sjzd> list = query.getResultList();
//		   StaticVales.sjzdList = list;
//		   em.close();
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

}
