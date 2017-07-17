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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * A concrete implementation of a {@link PersistedFile}.
 */
class PersistedFileDefault implements PersistedFile {

    private final File mFile;
    private Properties mProperties;

    public PersistedFileDefault(File file) {
        mFile = file;
    }

    public PersistedFile initialize() {
        mProperties = new Properties();
        try {
            mProperties.load(new FileInputStream(mFile));
        } catch (IOException e) {

        }

        return this;
    }

    //////////////////////////////
    // PersistedFile
    //////////////////////////////
    @Override
    public void put(Object key, Object value) {
        mProperties.put(key, value);
    }

    @Override
    public Object get(String key) {
        return mProperties.get(key);
    }

    @Override
    public Object delete(String key) {
        return mProperties.remove(key);
    }

    @Override
    public boolean flush() {
        boolean flag = false;
        try {
            mProperties.store(new FileOutputStream(mFile), null);
            flag = true;
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        return flag;
    }

    @Override
    public File getFile() {
        return mFile;
    }

}
