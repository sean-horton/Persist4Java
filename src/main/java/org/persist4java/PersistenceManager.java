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

import java.util.List;

/**
 * Provides
 */
public interface PersistenceManager {

    /**
     * Get all {@link PersistedFile}'s within this {@link PersistenceManager}'s control.
     *
     * @return a {@link List} of {@link PersistedFile}'s
     */
    List<PersistedFile> getFiles();

    /**
     * Get a {@link PersistedFile} with the specified name. The name is the same name that was passed to
     * {@link #createFile(String)}.
     *
     * @param name the name of the file to get
     * @return the {@link PersistedFile} or null if no file exists
     */
    PersistedFile getFile(String name);

    /**
     * Creates a new Persisted file. If the file has already been created the created file will be returned.
     *
     * @param name the name of the file
     * @return a new {@link PersistedFile}
     */
    PersistedFile createFile(String name);

    /**
     * Save all persisted state to the disk. If flush is not called before the JVM exists your state may not be
     * persisted.
     *
     * @return true on successful save to disk
     */
    boolean flush();

}
