package jp.co.forthelocal.template.spring.test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class Bootstrap implements BeforeAllCallback {

	private static final String STORE_KEY_MIGRATION = "store_key_migration";
	private static final String STORE_KEY_MIGRATION_DONE = "done";

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		if (isExecuted(context)) {
			migrate();
			markAsExecuted(context);
		}
	}

	private boolean isExecuted(ExtensionContext context) {
		return context.getRoot().getStore(ExtensionContext.Namespace.create(Bootstrap.class)).get(STORE_KEY_MIGRATION, String.class) == null;
	}

	private void markAsExecuted(ExtensionContext context) {
		context.getRoot().getStore(ExtensionContext.Namespace.create(Bootstrap.class)).put(STORE_KEY_MIGRATION, STORE_KEY_MIGRATION_DONE);
	}

	private void migrate() throws IOException {
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		sqlSessionFactory.getConfiguration();
		DataSource ds = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();

		Flyway flyway = new Flyway();
		flyway.setDataSource(ds);
		flyway.migrate();
	}
}
