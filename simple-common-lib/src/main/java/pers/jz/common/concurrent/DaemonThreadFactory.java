package pers.jz.common.concurrent;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ThreadFactory;

/**
 * @author jemmyzhang on 2018/3/26.
 */
public class DaemonThreadFactory implements ThreadFactory {

    private String name = "default-daemon-thread";

    public DaemonThreadFactory(String name) {
        if (StringUtils.isNotBlank(name)) {
            this.name = name;
        }
    }

    public DaemonThreadFactory() {
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        thread.setName(this.name + "-" + thread.getId());
        return null;
    }
}
