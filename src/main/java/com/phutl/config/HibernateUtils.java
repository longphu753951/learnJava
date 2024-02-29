package com.phutl.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
    // Chỉ được tao 1 lần duy nhất và cho phép sử dụng chung
    private static final SessionFactory FACTORY;

    // Khối static sẽ được gọi lần đầu tiên khi lớp này được khởi tạo và chỉ được
    // gọi 1 lần duy nhất
    static {
        try {
            // Tạo ra đối tượng Configuration
            Configuration cfg = new Configuration();
            // Thêm properties vào cfg
            Properties props = new Properties();
            // Cấu hình kết nối tới database
            props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            props.put(Environment.URL, "jdbc:mysql://127.0.0.1:3306/LearnJDBC");
            props.put(Environment.USER, "root");
            props.put(Environment.PASS, "qwpo1234");
            cfg.setProperties(props);
            ServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties())
                    .build();
            // Tạo đối tượng SessionFactory từ hibernate.cfg.xml
            FACTORY = cfg.buildSessionFactory(registry);
        } catch (Throwable ex) {
            // Log exception!
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

}
