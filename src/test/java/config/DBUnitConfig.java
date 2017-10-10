/*
package config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.epam.dao.impl.ArticleDAOImpl;
import com.epam.entity.Article;
import org.apache.log4j.Logger;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.transaction.Transactional;


public class DBUnitConfig extends DBTestCase {
    private static final Logger logger = Logger.getLogger(DBUnitConfig.class);
    protected IDatabaseTester tester;
    private Properties prop;
    protected IDataSet beforeData;

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

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }

    @Before
    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester(prop.getProperty("jdbc.driverClassName"),
                prop.getProperty("jdbc.url"),
                prop.getProperty("jdbc.username"),
                prop.getProperty("jdbc.password"));
    }

    public DBUnitConfig(String name) {
        super(name);
        prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db/db.test.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, prop.getProperty("jdbc.driverClassName"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, prop.getProperty("jdbc.url"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, prop.getProperty("jdbc.username"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, prop.getProperty("jdbc.password"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "");
    }


    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-test-config.xml");
    ArticleDAOImpl articleDAO = (ArticleDAOImpl) applicationContext.getBean("articleDAOTest");

    @Test
    public void testById() throws Exception {
        List<Article> apartments = articleDAO.findAll();

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader().getResourceAsStream("com.epam.entity.article/article-data.xml"));
        Assert.assertEquals(expectedData.getTable("TESTARTICLE").getRowCount(), apartments.size());
        logger.debug(expectedData.getTable("TESTARTICLE").getRowCount());
        assertThat(expectedData.getTable("TESTARTICLE").getRowCount(), is(apartments.size()));
    }

}

*/
