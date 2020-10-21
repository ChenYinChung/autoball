package com.nexio.autoball.config;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Register entity bean & create Jdbi bean for autowired
 */
@Configuration
public class PersistenceConfig {
    private static Logger logger = LoggerFactory.getLogger(PersistenceConfig.class);

    private static final String ENTITY_BASE_PACKAGE = "com.nexio.autoball.model";

    /**
     * JDBI wants to control the Connection wrap the datasource in a proxy
     * That is aware of the Spring managed transaction
     * @param dataSource
     * @return
     */
    @Bean
    public Jdbi jdbi(DataSource dataSource) {
        TransactionAwareDataSourceProxy dataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
        Jdbi jdbi = Jdbi.create(dataSourceProxy);
        jdbi.installPlugins();
        jdbi.installPlugin(new PostgresPlugin());
        registerPojo(jdbi);
        return jdbi;
    }

    /**
     * auto register entity bean for jdbi
     *
     * @param jdbi
     */
    private void registerPojo(Jdbi jdbi) {

        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));

        final Set<BeanDefinition> classes = provider.findCandidateComponents(ENTITY_BASE_PACKAGE);

        for (BeanDefinition bean : classes) {
            try {
                Class<?> clazz = Class.forName(bean.getBeanClassName());
                jdbi.registerRowMapper(FieldMapper.factory(clazz));

                logger.info("register entity class[{}] into JBDI", clazz.getName());
            } catch (ClassNotFoundException e) {
                logger.error("Register JDBI failure", e);
            }
        }
    }
}
