package com.practice.kylin;

import org.apache.kylin.common.KylinConfig;

/**
 * @author ysong1
 *
 */
public abstract class AbstractKylinTestCase {

    static {
        System.setProperty("needCheckCC", "true");
    }

    public abstract void createTestMetadata(String... overlayMetadataDirs) throws Exception;

    public abstract void cleanupTestMetadata() throws Exception;

    public static KylinConfig getTestConfig() {
        return KylinConfig.getInstanceFromEnv();
    }

    public static void staticCleanupTestMetadata() {
        System.clearProperty(KylinConfig.KYLIN_CONF);
        KylinConfig.destroyInstance();
    }

}