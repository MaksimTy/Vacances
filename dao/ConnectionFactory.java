package com.tymkovskiy.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Класс предоставляет объект Connection.
 */
public final class ConnectionFactory {
    /**путь к файлу "Properties"*/
    private static final String PROPERTIESPATH = "src/com/tymkovskiy/res/properties.properties";
    private static Connection connection;
    private static ConnectionFactory instance;
    private static Properties props;

    /**
     * Constructor.
     * Устанавливает соединение с базой данных в соответствии со свойствами, перечисленными
     * в файле по адресу, определенном в "PROPERTIESPATH".
     * Инициализирует переменную "connection".
     * @throws IOException
     * @throws SQLException
     */
    private ConnectionFactory() throws IOException, SQLException {
        props = getProperties();
        try {
            Class.forName(props.get("driver").toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
      //  connection = DriverManager.getConnection(props.get("url").toString(), props);
    }

    /**
     * Метод возвращает объект Connection .
     *
     * @return connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Метод доступа к объекту ConnectionFactory.
     * @return ConnectionFactory.
     * @throws SQLException
     * @throws IOException
     */
    public static ConnectionFactory getInstance() throws SQLException, IOException {
        ConnectionFactory localInstance = instance;
        if (localInstance == null) {
            localInstance = instance;
            if (instance == null) {
                instance = new ConnectionFactory();
                localInstance = instance;
            }
        }
        connection = DriverManager.getConnection(props.get("url").toString(), props);
        return localInstance;
    }

    /**
     * Метод возвращает объект properties для подключения Connection.
     * @return properties.
     * @throws IOException
     */
    private static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(PROPERTIESPATH)));
        return properties;
    }

}
