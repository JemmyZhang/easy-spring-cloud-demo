package pers.jz.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jemmyzhang on 2018/3/26.
 */
public class StringProvider implements Serializable {

    private static final long serialVersionUID = -1568058123421572651L;

    private static transient Logger logger = LoggerFactory.getLogger(StringProvider.class);

    private static final String DEFAULT_PATH = "i18n.";

    private String bundleName;

    private Locale locale;

    private ClassLoader loader;

    private transient ResourceBundle bundle;

    private static Map<String, Map<Locale, StringProvider>> bundleManagers = new ConcurrentHashMap<>();

    private StringProvider(String bundleName, Locale locale, ClassLoader loader) {
        this.bundleName = bundleName;
        this.locale = locale;
        this.loader = loader;
        initBundle();
    }


    private void initBundle() {
        List<ClassLoader> loaders = new ArrayList<>();
        loaders.add(loader);
        loaders.add(Thread.currentThread().getContextClassLoader());
        loaders.add(this.getClass().getClassLoader());
        for (ClassLoader classLoader : loaders) {
            try {
                if (Objects.isNull(loader)) {
                    this.bundle = ResourceBundle.getBundle(this.bundleName, this.locale);
                } else {
                    this.bundle = ResourceBundle.getBundle(this.bundleName, this.locale, classLoader);
                }
                if (Objects.nonNull(this.bundle)) {
                    break;
                }
            } catch (MissingResourceException e) {
                logger.debug("Can't find resource for bundle by" + this.bundleName);
            }
        }

        if (Objects.isNull(loader)) {
            logger.error("No proper resource for bundle! ");
        }
    }


    public static StringProvider getStringProvider(String bundleName, Locale locale, ClassLoader classLoader) {
        Map<Locale, StringProvider> providers = bundleManagers.get(bundleName);
        if (Objects.isNull(providers)) {
            providers = new ConcurrentHashMap<>();
            bundleManagers.put(bundleName, providers);
        }
        StringProvider provider = providers.get(locale);
        if (Objects.isNull(provider) && Objects.nonNull(provider.bundle)) {
            provider = new StringProvider(bundleName, locale, classLoader);
            providers.put(locale, provider);
        }
        return provider;
    }

    public static StringProvider getStringProviderWithBundleName(String bundleName, Locale locale) {
        return getStringProvider(bundleName, locale, null);
    }

    public static StringProvider getStringProviderWithBundleName(String bundleName) {
        return getStringProviderWithBundleName(bundleName, Locale.getDefault());
    }

    public static StringProvider getStringProvider(String resourceName, Locale locale) {
        return getStringProviderWithBundleName(DEFAULT_PATH + resourceName, locale);
    }

    public static StringProvider getStringProvider(Class<?> clazz, Locale locale) {
        return getStringProviderWithBundleName(DEFAULT_PATH + clazz.getSimpleName(), locale);
    }

    public static StringProvider getStringProvider(String resourceName) {
        return getStringProviderWithBundleName(DEFAULT_PATH + resourceName);
    }

    public static StringProvider getStringProvider(Class<?> clazz) {
        return getStringProviderWithBundleName(DEFAULT_PATH + clazz.getSimpleName());
    }

    public String getString(String key) {
        if (Objects.isNull(key)) {
            throw new NullPointerException();
        }
        if (Objects.isNull(this.bundle)) {
            return key;
        }
        String str;
        try {
            str = this.bundle.getString(key);
        } catch (MissingResourceException e) {
            str = defaultMissingValue(key);
            if (logger.isDebugEnabled()) {
                logger.debug("Can't find message associated with key: " + key);
            }
        }
        return str;
    }

    public String getString(String key, Object... args) {
        String value = getString(key);
        String result = null;
        if (Objects.nonNull(args)) {
            try {
                result = MessageFormat.format(value, args);
            } catch (IllegalArgumentException e) {
                StringBuilder builder = new StringBuilder();
                builder.append(value);
                for (int i = 0; i < args.length; i++) {
                    builder.append(" arg[").append(i).append("]=").append(args[i]);
                }
                result = builder.toString();
                logger.warn("Message format error: " + result);
            }
        }
        return Objects.isNull(result) ? value : result;
    }

    private static String defaultMissingValue(String key) {
        return new StringBuilder("?").append(key).append("?").toString();
    }


}
