### What is it?
Persist4Java provides an easy way to store preference files to disk 
that can be loaded upon JVM starts. The API is as follows:

1) Initialize a directory for persisted state. The following code will
create a PersistenceManager for /"UserDir"/Desktop/Persist4Java/. 
All files will be stored here for this PersistenceManager instance.
```
PersistenceManager persistenceManager = PersistenceFactory
                    .load(new File(System.getProperty("user.home") 
                    + "/Desktop/Persist4Java"));
```

2) Manage `PersistedFile`'s using the `PersistenceManager`:
```
// Create a new persisted file with the specified 'name'
PersistedFile persistedFile = persistenceManager.createFile(name);

// Load a file with the specified name
PersistedFile persistedFile = persistenceManager.getFile(name);

// Get a List of ALL persisted files
List<PersistedFile> persistedFileList = persistenceManager.getFiles();
```

3) Add, update, get and remove preferences for a `PersistedFile`
```
// Update or Add a property
persistedFile.put("key", "value");

// Remove a preference
String example = (String) persistedFile.remove("key");

// get a key
String example = (String) persistedFile.get("key");
```


4) Be sure to flush all state to disk when you are done. All
data is stored in memory until `flush()` is called.
```
// Flush only a specific file to disk (Blocking call)
persistedFile.flush();

// Flush all persisted files to disk (Blocking call)
persistenceManager.flush();
```

### What this is not
This is not intended to replace a database, but provides a 
simple API for saving simple preferences or state to the 
file system.

### Building
Run `./gradlew jar`. There are no external libraries required.