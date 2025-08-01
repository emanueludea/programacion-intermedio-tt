# Java PostgreSQL Database Connection Example

This project demonstrates how to connect to a PostgreSQL database using Java and JDBC, with Docker containerization support.

## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains the following folders:

- `src`: the folder to maintain Java source files
- `lib`: the folder to maintain dependencies (PostgreSQL JDBC driver)
- `bin`: compiled output files (generated automatically)

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Prerequisites

- Java 21 (Amazon Corretto)
- PostgreSQL database
- PostgreSQL JDBC driver (included in `lib/` folder)
- Docker (optional, for containerized deployment)

## Environment Variables

The application requires the following environment variables:

- `DB_HOST` (required): PostgreSQL server hostname or IP address
- `DB_PASSWORD` (required): Database password
- `DB_PORT` (optional): Database port (default: 5432)
- `DB_NAME` (optional): Database name (default: postgres)
- `DB_USER` (optional): Database username (default: postgres)

## Running Locally

1. Set the required environment variables:
```bash
export DB_HOST=localhost
export DB_PASSWORD=your_password
export DB_PORT=5432
export DB_NAME=your_database
export DB_USER=your_username
```

2. Compile and run:
```bash
javac -d bin -cp "lib/*" src/*.java
java -cp "bin:lib/*" App
```

## Docker Deployment

1. Build the Docker image:
```bash
docker build -t examplesql .
```

2. Run the container with environment variables:
```bash
docker run --rm \
  -e DB_HOST=your_db_host \
  -e DB_PORT=5432 \
  -e DB_NAME=your_database_name \
  -e DB_USER=your_username \
  -e DB_PASSWORD=your_password \
  examplesql
```

## Database Setup

The application expects a table named `alumno` in your PostgreSQL database. Example table structure:

```sql
CREATE TABLE alumno (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100),
    email VARCHAR(100)
);
```

## Security Notes

- Never hardcode passwords in your source code or Dockerfile
- Always pass sensitive data through environment variables at runtime
- The application masks passwords in console output for security

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
