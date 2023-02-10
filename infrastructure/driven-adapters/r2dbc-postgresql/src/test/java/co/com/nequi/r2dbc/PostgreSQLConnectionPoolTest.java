package co.com.nequi.r2dbc.config;

import co.com.nequi.r2dbc.config.PostgreSQLConnectionPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PostgreSQLConnectionPoolTest {

    // TODO: change four you own tests
    @Test
    void getConnectionConfig() {
        PostgresqlConnectionProperties properties = new PostgresqlConnectionProperties("sample", "public", "user", "password", "local", 1111);
        PostgreSQLConnectionPool postgreSQLConnectionPool= new PostgreSQLConnectionPool();
        Assertions.assertNotNull(postgreSQLConnectionPool.getConnectionConfig(properties));
    }
}
