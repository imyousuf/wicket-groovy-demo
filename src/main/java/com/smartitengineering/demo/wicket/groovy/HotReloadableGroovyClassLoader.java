package com.smartitengineering.demo.wicket.groovy;

import groovy.lang.GroovyClassLoader;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author imyousuf
 */
public class HotReloadableGroovyClassLoader
    implements Runnable {

  private ClassLoader parentClassLoader;
  private GroovyClassLoader groovyClassLoader;
  private final Map<URI, Long> watchedResourceMap;
  private long pollInterval;

  public HotReloadableGroovyClassLoader(ClassLoader parent) {
    this(parent, 10);
  }

  public HotReloadableGroovyClassLoader(ClassLoader parent,
                                        int pollIntervalInSeconds) {
    parentClassLoader = parent;
    watchedResourceMap = Collections.synchronizedMap(new HashMap<URI, Long>());
    this.pollInterval = pollIntervalInSeconds * 1000;
    startPoller();
    init();
  }

  protected synchronized void init() {
    groovyClassLoader = new GroovyClassLoader(parentClassLoader) {

      @Override
      public Class<?> loadClass(String name)
          throws ClassNotFoundException {
        final Class<?> loadClass = super.loadClass(name);
        if (loadClass != null) {
          try {
            String resourceName = new StringBuilder(name.replaceAll("\\.", "/")).append(".groovy").
                toString();
            final URL resource = getResource(resourceName);
            if (resource != null) {
              File groovySourceFile = new File(resource.toURI());
              if (groovySourceFile.exists()) {
                watchedResourceMap.put(groovySourceFile.toURI(),
                    groovySourceFile.lastModified());
              }
            }
          }
          catch (Exception ex) {
          }
        }
        return loadClass;
      }
    };
  }

  public long getPollIntervalInSecond() {
    return pollInterval;
  }

  public void setPollIntervalInSecond(long pollIntervalInSecond) {
    this.pollInterval = pollIntervalInSecond;
  }

  public void run() {
    Set<File> changedFiles = new HashSet();
    for (URI fileUri : watchedResourceMap.keySet()) {
      File file = new File(fileUri);
      if (file.lastModified() != watchedResourceMap.get(fileUri)) {
        changedFiles.add(file);
      }
    }
    if (!changedFiles.isEmpty()) {
      synchronized (watchedResourceMap) {
        for (File changedFile : changedFiles) {
          watchedResourceMap.put(changedFile.toURI(), changedFile.lastModified());
        }
      }
      init();
    }
  }

  private void startPoller() {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {

      @Override
      public void run() {
        HotReloadableGroovyClassLoader.this.run();
      }
    }, this.pollInterval, this.pollInterval);
  }

  public synchronized GroovyClassLoader getGroovyClassLoader() {
    return groovyClassLoader;
  }
}
