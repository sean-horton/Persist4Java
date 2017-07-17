package org.persist4java;

import java.io.File;

/**
 * Main entry point into using/creating a persistent data store. This is not a database, but uses functionality from
 * {@link java.util.prefs.Preferences}.
 */
public final class PersistenceFactory {

    private PersistenceFactory() {

    }

    /**
     * Load/initialize a directory to be used for persisted state. If there are already preference files saved
     * within this directory they will be loaded into memory.
     * <p>
     * For example, a use could be to store state in the user directory:
     *
     * <pre>
     *  {@code
     *  PersistenceFactory.load(new File(System.getProperty("user.home")
     *              + File.pathSeparator + "<your save path>"));
     *  }
     * </pre>
     *
     * @param directory The directory to be used for persisted state
     * @return A {@link PersistenceManager} for the provided directory
     */
    public static PersistenceManager load(File directory) {
        return new PersistenceManagerImpl();
    }

}
