package it.mybatis.configuration;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {

    private static SqlSessionFactory factory;

    static {
        String myBatisConfig = "mybatis-config.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(myBatisConfig);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        factory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return factory;
    }
}
