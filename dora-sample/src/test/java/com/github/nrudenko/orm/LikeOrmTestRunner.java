package com.github.nrudenko.orm;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.res.Fs;
import org.robolectric.res.FsFile;

import java.io.File;
import java.util.Properties;

public class LikeOrmTestRunner extends RobolectricTestRunner {

    public LikeOrmTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected Properties getConfigProperties() {
        Properties configProperties = super.getConfigProperties();
        if (configProperties == null) {
            configProperties = new Properties();
        }
        String manifestPath = "src/main/AndroidManifest.xml";
        FsFile manifestFile = Fs.newFile(new File(manifestPath));
        if (manifestFile.exists()) {
            configProperties.put("manifest", manifestPath);
        } else {
            configProperties.put("manifest", "LikeOrmExample/" + manifestPath);
        }
        configProperties.put("emulateSdk", "16");
        return configProperties;
    }
}
