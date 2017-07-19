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
 * Gives an example for how to use Persist4Java
 */
public class UsageExample {

    private static final String FILE_USER_PREF = "UserPreferences";
    private static final String USERNAME_KEY = "username";

    public static void main(String[] args) {
        UsageExample usageExample = new UsageExample();
        usageExample.start();
    }

    /**
     * After running example code check out the directory and the saved files contents
     */
    public void start() {
        try {
            // Initialize the persisted state (loads or starts a new persisted state dir)
            PersistenceManager manager = PersistenceFactory
                    .load(new File(System.getProperty("user.home") + "/Desktop/Persist4Java"));

            // Add a new preference
            PersistedFile persistedFile = manager.createFile(FILE_USER_PREF);
            persistedFile.put(USERNAME_KEY, "Some User");

            System.out.println("State stored to preference: " + persistedFile.get(USERNAME_KEY));

            // Save preference to disk
            persistedFile.flush();
        } catch (InvalidDirectoryException e) {
            System.out.println("Unable to initialize: " + e.getMessage());
        }
    }

}
