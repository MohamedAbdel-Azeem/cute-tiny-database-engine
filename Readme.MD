# Cute Tiny Database Engine

A lightweight, educational database engine implementation in Java that supports basic SQL operations with a focus on understanding database internals.

## Features

- SQL-like query language support
- Basic CRUD operations (Create, Read, Update, Delete)
- Table creation with primary key support
- B+ tree indexing for efficient data retrieval
- Page-based storage system
- Support for multiple data types (INT, DOUBLE, STRING)
- Configurable page size and index node size

## Supported SQL Operations

1. **CREATE TABLE**
   ```sql
   CREATE TABLE tableName (columnType:columnName PRIMARY KEY, columnType:columnName, ...);
   ```

2. **CREATE INDEX**
   ```sql
   CREATE INDEX indexName ON tableName (columnName);
   ```

3. **INSERT**
   ```sql
   INSERT INTO tableName (columnName, ...) VALUES (value, ...);
   ```

4. **UPDATE**
   ```sql
   UPDATE tableName SET (columnName = value, ...) WHERE clusteringColumnName = value;
   ```

5. **DELETE**
   ```sql
   DELETE FROM tableName WHERE (columnName = value, ...);
   ```

6. **SELECT**
   ```sql
   SELECT * FROM tableName WHERE condition (AND|OR|XOR condition)*;
   ```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── DBMain/           # Core database engine implementation
│   │   ├── Structures/       # Data structures (Table, Tuple, Page)
│   │   ├── Utils/           # Utility classes and helper functions
│   │   ├── MyCuteSQLParser/ # SQL parser implementation
│   │   └── gen/             # Generated ANTLR parser code
│   └── resources/           # Configuration files
```

## Dependencies

- Java 21
- ANTLR 4.13.1 (for SQL parsing)
- Apache Commons CSV 1.8
- B+ Tree implementation (com.github.davidmoten:bplustree:0.1.4)

## Configuration

The database engine can be configured through `src/main/resources/DBApp.config`:

- `MaximumRowsCountinPage`: Maximum number of rows per page
- `IndexNodeSize`: Size of B+ tree index nodes

## Usage

1. **Compile the Project**
   ```bash
   mvn clean install
   ```

2. **Run Queries**
   - Through CLI:
     ```bash
     java -cp target/cute-tiny-database-engine-1.0-SNAPSHOT.jar MyCuteSQLParser.Main
     ```
   - Through file:
     ```bash
     java -cp target/cute-tiny-database-engine-1.0-SNAPSHOT.jar MyCuteSQLParser.Main path/to/queries.txt
     ```

## Example Queries

```sql
-- Create a table
CREATE TABLE Students (INT:id PRIMARY KEY, STRING:name, DOUBLE:gpa);

-- Create an index
CREATE INDEX nameIndex ON Students (name);

-- Insert data
INSERT INTO Students (id, name, gpa) VALUES (1, 'John Doe', 3.8);

-- Update data
UPDATE Students SET (gpa = 3.9) WHERE id = 1;

-- Select data
SELECT * FROM Students WHERE gpa > 3.5;

-- Delete data
DELETE FROM Students WHERE (id = 1);
```

## Implementation Details

- Uses a page-based storage system for efficient data management
- Implements B+ tree indexing for faster data retrieval
- Supports clustering key-based operations
- Uses ANTLR for SQL parsing and validation
- Implements serialization for persistent storage

## Contributing

Feel free to submit issues and enhancement requests!
