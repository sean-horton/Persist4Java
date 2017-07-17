/*
 * Copyright 2017 Sean Horton
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package org.persist4java;

import org.persist4java.error.InvalidDirectoryException;

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
     * within this directory they will be loaded into memory. NOTE: Blocking call
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
     * @throws InvalidDirectoryException
     * @throws SecurityException
     */
    public static PersistenceManager load(File directory) throws InvalidDirectoryException, SecurityException {
        return new PersistenceManagerImpl(directory).initialize();
    }

}
