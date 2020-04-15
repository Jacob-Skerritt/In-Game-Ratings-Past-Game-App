package Database;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author Jacob Skerritt
 * 
 * Class to manage database connection
 * 
*/
public class Config {
    
    private String url;
    private String username;
    private String password;
    private ComboPooledDataSource db;
    
    public Config(){
        
    }
    
    public Config(String url, String username, String password) throws SQLException, PropertyVetoException{
        this.url = url;
        this.username = username;
        this.password = password;
        this.db = new ComboPooledDataSource();
        this.db.setDriverClass("com.mysql.jdbc.Driver"); 
        this.db.setJdbcUrl(this.url);
        this.db.setUser(this.username);
        this.db.setPassword(this.password);
        this.db.setMinPoolSize(50);
        this.db.setMaxIdleTime(3600);
        this.db.setMaxConnectionAge(3600);
        
    }
    
    public Connection getDatabaseConnection () throws SQLException{
        return db.getConnection();
    }
    
    
    
}
