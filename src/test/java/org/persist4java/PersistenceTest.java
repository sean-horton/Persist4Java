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

import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

/**
 * Created by shorton on 7/18/17.
 */
public class PersistenceTest {

    private static final String FILE_1 = "Test File 1";

    @Test
    public void testAddDeleteState() throws Exception {
        File file = Files.createTempDirectory("directory").toFile();
        file.deleteOnExit();

        PersistenceManager manager = PersistenceFactory.load(file);
        PersistedFile persistedFile = manager.createFile(FILE_1);

        // Test basic add and deletes
        persistedFile.put("item1", "value");
        assertThat(persistedFile.get("item1"), equalTo("value"));
        assertThat(persistedFile.delete("item1"), equalTo("value"));
        assertThat(persistedFile.get("item1"), equalTo(null));
        assertThat(manager.getFiles(), hasItem(persistedFile));
        assertThat(manager.getFile(FILE_1), equalTo(persistedFile));

        // put value back and flush to disk
        persistedFile.put("item1", "value");
        manager.flush();

        // Load from disk again and verify state
        manager = PersistenceFactory.load(file);
        assertThat(manager.getFile(FILE_1).get("item1"), equalTo("value"));
    }


}
