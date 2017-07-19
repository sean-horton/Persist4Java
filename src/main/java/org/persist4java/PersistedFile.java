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

import java.io.File;

/**
 * File specific methods for a file saved to disk.
 */
public interface PersistedFile {

    /**
     * Save a value using a key value pair. Be sure to call {@link #flush()} to save the values to disk
     *
     * @param key   the key to use
     * @param value the value to save
     */
    void put(Object key, Object value);

    /**
     * Get a value for the specified key
     *
     * @param key the key
     */
    Object get(String key);

    /**
     * The value to remove using the specified key.
     *
     * @param key the key value pair to remove
     * @return the value (may be null) of the key that was deleted
     */
    Object remove(String key);

    /**
     * Flush state for this specific file to the disk. NOTE: Blocking call.
     *
     * @return true if the flush was successfully saved to disk.
     */
    boolean flush();

    /**
     * Get the system file associated with this Persisted file
     *
     * @return a {@link File}
     */
    File getFile();

}
