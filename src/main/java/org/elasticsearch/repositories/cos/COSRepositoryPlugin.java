package org.elasticsearch.repositories.cos;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import org.elasticsearch.cluster.metadata.RepositoryMetaData;
import org.elasticsearch.common.xcontent.NamedXContentRegistry;
import org.elasticsearch.env.Environment;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.plugins.RepositoryPlugin;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.repositories.Repository;
import org.elasticsearch.threadpool.ThreadPool;

/**
 * Created by Ethan-Zhang on 30/03/2018.
 */
public class COSRepositoryPlugin extends Plugin implements RepositoryPlugin {

    protected COSService createStorageService(RepositoryMetaData metaData) {
        return new COSService(metaData);
    }

    @Override
    public Map<String, Repository.Factory> getRepositories(final Environment env,
                                                           final NamedXContentRegistry namedXContentRegistry,
                                                            final ThreadPool threadPool) {
        return Collections.singletonMap(COSRepository.TYPE,
                (metadata) -> new COSRepository(metadata, namedXContentRegistry,
                        createStorageService(metadata), threadPool));
    }

    @Override
    public List<Setting<?>> getSettings() {
        return Arrays.asList(COSClientSettings.REGION, COSClientSettings.ACCESS_KEY_ID, COSClientSettings.ACCESS_KEY_SECRET,
                COSClientSettings.APP_ID, COSClientSettings.BUCKET,
                COSClientSettings.BASE_PATH, COSClientSettings.COMPRESS, COSClientSettings.CHUNK_SIZE, COSClientSettings.CosEndPoint);
    }
}
