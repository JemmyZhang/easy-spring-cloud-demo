package pers.jz.common.concurrent;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jemmyzhang on 2018/3/26.
 */
public class SimpleExecutorService {

    private static final SimpleExecutorService instance = new SimpleExecutorService();

    private static final int THREAD_IDLE = 10;

    private static final int THREAD_CAPACITY = 1024;

    private volatile ExecutorService cpuBusyService = null;

    private volatile ExecutorService ioBusyService = null;

    private static SimpleExecutorService get() {
        return instance;
    }

    public ExecutorService getCpuBusyService() {
        if (Objects.isNull(cpuBusyService)) {
            synchronized (this) {
                if (Objects.isNull(cpuBusyService)) {
                    final int coreSize = coreSize();
                    final int maxSize = coreSize() * 2;
                    cpuBusyService = buildThreadPoolExecutor(coreSize, maxSize, "cpu-busy-thread");
                }
            }
        }
        return cpuBusyService;
    }

    public ExecutorService getIoBusyService() {
        if (Objects.isNull(ioBusyService)) {
            synchronized (this) {
                if (Objects.isNull(ioBusyService)) {
                    final int coreSize = coreSize() * 2;
                    final int maxSize = coreSize() * 5;
                    ioBusyService = buildThreadPoolExecutor(coreSize, maxSize, "io-busy-thread");
                }
            }
        }
        return ioBusyService;
    }

    public int coreSize() {
        return Runtime.getRuntime().availableProcessors();
    }

    private ExecutorService buildThreadPoolExecutor(int coreSize, int maxSize, String threadName) {
        return new ThreadPoolExecutor(coreSize,
                maxSize,
                THREAD_IDLE,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(THREAD_CAPACITY),
                new DaemonThreadFactory(threadName),
                (r, executor) -> {
                });
    }

}
