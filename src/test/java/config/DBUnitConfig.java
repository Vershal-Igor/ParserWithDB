
package config;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Properties;

public class DBUnitConfig extends DBTestCase {
    protected IDatabaseTester tester; //Объект с помощью которого идёт сравнение табличек;
    private Properties prop; // Настройки подключения;
    protected IDataSet beforeData; // Данные для инициализации БД перд выполнением тестов;

    @Before
    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester(prop.getProperty("db.driver"),
                prop.getProperty("db.url"),
                prop.getProperty("db.username"),
                prop.getProperty("db.password"));
    }

    public DBUnitConfig(String name) {
        super(name);
        prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db/db.test.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, prop.getProperty("db.driver"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, prop.getProperty("db.url"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, prop.getProperty("db.username"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, prop.getProperty("db.password"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "");
    }

    @Transactional
    @Override
    protected IDataSet getDataSet() throws Exception {
        return beforeData;
    }

    @Transactional
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

}
