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
import java.util.ArrayList;
import java.util.List;

/**
 * The concrete implementation of a {@link PersistenceManager}. Provides methods for managing persisted state.
 */
class PersistenceManagerImpl implements PersistenceManager {

    private static final String FILE_EXT = ".pref";

    private final File mDirectory;
    private final List<PersistedFile> mPersistedFiles;

    public PersistenceManagerImpl(File directory) {
        mDirectory = directory;
        mPersistedFiles = new ArrayList<>();
    }

    public PersistenceManager initialize() throws InvalidDirectoryException, SecurityException {

        // Throw exception if invalid directory
        if (mDirectory.exists() && !mDirectory.isDirectory()) {
            throw new InvalidDirectoryException(mDirectory.getAbsolutePath() + "is not a valid directory");
        } else if (!mDirectory.exists() && !mDirectory.mkdir()) {
            throw new InvalidDirectoryException("Unable to make directory " + mDirectory.getAbsolutePath());
        }

        // Create PersistedFile for each file with our extension
        File[] files = mDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getAbsolutePath().endsWith(FILE_EXT)) {
                    mPersistedFiles.add(new PersistedFileDefault(file).initialize());
                }
            }
        }

        return this;
    }

    ////////////////////////////////
    // PersistenceManager
    ////////////////////////////////
    @Override
    public List<PersistedFile> getFiles() {
        return mPersistedFiles;
    }

    @Override
    public PersistedFile getFile(String fileName) {
        PersistedFile persistedFile = null;
        for (PersistedFile temp : mPersistedFiles) {
            String name = temp.getFile().getName();
            String substring = name.substring(0, name.length() - FILE_EXT.length());
            if (name.length() > FILE_EXT.length()
                    && substring.equals(fileName)) {
                persistedFile = temp;
                break;
            }
        }
        return persistedFile;
    }

    @Override
    public PersistedFile createFile(String name) {
        PersistedFile persistedFile = null;
        File file = new File(mDirectory.getAbsolutePath() + File.separator + name + FILE_EXT);
        persistedFile = new PersistedFileDefault(file).initialize();
        mPersistedFiles.add(persistedFile);

        return persistedFile;
    }

    @Override
    public boolean flush() {
        boolean flag = true;
        for (PersistedFile persistedFile : mPersistedFiles) {
            flag = flag && persistedFile.flush();
        }

        return flag;
    }

    ////////////////////////////////
    // Helper methods
    ////////////////////////////////
}
