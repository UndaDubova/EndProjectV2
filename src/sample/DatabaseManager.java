package sample;
//this is what I mean about the stupid versions, he said to always use "maven project" for these things to work, but whe choosing
//javaFX we didn't take maven project
// thats why it didn't work for the other girl too she said she had to wirte a new different code for everything
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class DatabaseManager {
    PropertiesConfiguration databaseProperties = new PropertiesConfiguration();
    private static Connection connection;

    public DatabaseManager(){
        try {
            databaseProperties.load("resources/database.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        this.setupDB();
    }
//it doesnt have the yellow thingy
    private void setupDB() {

        String userName = databaseProperties.getString("database.userName");
        String password = databaseProperties.getString("database.password");
        String host = databaseProperties.getString("database.host");
        String port = databaseProperties.getString("database.port");
        String dbName = databaseProperties.getString("database.dbName");
        String connectionUrl = host + ":" + port + "/" + dbName;

        try {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
        }catch (SQLException ex){
            System.out.println("unable to connect to database");
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
