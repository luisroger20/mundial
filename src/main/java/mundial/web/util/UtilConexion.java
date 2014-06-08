package mundial.web.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

public class UtilConexion implements Serializable {
	private static UtilConexion factory;
	protected Logger log;
	protected GenericObjectPool pool = null;
    protected DriverManagerConnectionFactory dmcf = null;
    protected PoolableConnectionFactory pcf = null;
	
    public static UtilConexion getInstance() throws ClassNotFoundException {
        if (factory == null) {
            factory = new  UtilConexion();
        }
        return factory;
    }
    
    private UtilConexion() throws ClassNotFoundException 
    {
		log = Logger.getLogger(this.getClass());
		java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("mundial.resources.jdbc");
        
		String url = bundle.getString("jdbc.url");
        String driverClassName = bundle.getString("jdbc.driverClassName");
        String username = bundle.getString("jdbc.username");
        String password = bundle.getString("jdbc.password");
		
        //log.debug("Instanciando el UtilConexion");
        //log.debug("Pool url " + url);
        //log.debug("Pool driver class name " + driverClassName);
        //log.debug("Pool username " + username);
		
        pool = new GenericObjectPool(null);
        Class.forName(driverClassName);
        dmcf = new DriverManagerConnectionFactory(url, username, password);
        
        /*try {
            int maxActive = Integer.parseInt(bundle.getString("maxActive"));
            log.debug("Pool maxActive " + maxActive);
            pool.setMaxActive(maxActive);
        }
        catch(java.util.MissingResourceException mre) {
			log.error("Error al instanciar el UtilConexion. maxActive. " + mre.getMessage());
        }

        try {
            int maxIdle = Integer.parseInt(bundle.getString("maxIdle"));
            log.debug("Pool maxIdle " + maxIdle);
            pool.setMaxIdle(maxIdle);
        }
        catch(java.util.MissingResourceException mre) {
			log.error("Error al instanciar el UtilConexion. maxIdle. " + mre.getMessage());
        }
        
        try {
            int maxWait = Integer.parseInt(bundle.getString("maxWait"));
            log.debug("Pool maxWait " + maxWait );
            pool.setMaxWait(maxWait);
        }
        catch(java.util.MissingResourceException mre) {
			log.error("Error al instanciar el UtilConexion. maxWait. " + mre.getMessage());
        }

        try {
            int minIdle = Integer.parseInt(bundle.getString("minIdle"));
            log.debug("Pool minIdle " + minIdle );
            pool.setMinIdle(minIdle);
        }
        catch(java.util.MissingResourceException mre) {
			log.error("Error al instanciar el UtilConexion. minIdle. " + mre.getMessage());
        }*/

        pcf = new PoolableConnectionFactory(
        dmcf, pool, null, null, false, true);

        // register our pool and give it a name
        new PoolingDriver().registerPool("pool", pool);
    }
    
    public Connection getConnection() throws java.sql.SQLException {
        // get a connection from the pool
        //log.debug("Getting a connection from the pool");
        Connection con = DriverManager.getConnection("jdbc:apache:commons:dbcp:pool");
        
		//log.debug("Connection retrieved from the pool");
        //log.debug("numActive " + pool.getNumActive());
        //log.debug("numIdle " + pool.getNumIdle());
		
        return con;
    }	
}