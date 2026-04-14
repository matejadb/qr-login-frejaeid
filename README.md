


# Freja eID Demo

A Spring Boot demo application integrating [Freja eID](https://frejaeid.com) authentication.

## Prerequisites

- Java 21+
- A valid Freja eID keystore (`.pfx`) and the Freja test root CA certificate

## Configuration

The application requires two environment variables:

| Variable | Description |
|--------|-----------|
| `KEYSTORE_PASS` | Password for the `.pfx` keystore file |
| `KEYSTORE_PATH` | Absolute path to the `.pfx` keystore file |

## Running the Application

> **Note:** The instructions below have been tested on Linux. The macOS and Windows instructions should work based on standard Spring Boot and Maven conventions, but have not been verified.

### Linux and macOS

Create a `.env` file:

```bash
export KEYSTORE_PASS=your_password
export KEYSTORE_PATH=/absolute/path/to/keystore.pfx
```

Then run:

```bash
source .env && ./mvnw spring-boot:run
```

### Windows

Set the environment variables in Command Prompt:

```cmd
set KEYSTORE_PASS=your_password
set KEYSTORE_PATH=C:\absolute\path\to\keystore.pfx
mvnw.cmd spring-boot:run
```

Or in PowerShell:

```powershell
$env:KEYSTORE_PASS="your_password"
$env:KEYSTORE_PATH="C:\absolute\path\to\keystore.pfx"
.\mvnw.cmd spring-boot:run
```

---

The application will start on `http://localhost:8080`.

## Logging

The application uses [Log4j2](https://logging.apache.org/log4j/2.x/) configured via `src/main/resources/log4j2.xml`.

Logs are written to two destinations:

- **`Console`**
- **`app.log`**

### What gets logged

| Level | Event |
|-------|-------|
| `INFO` | Authentication initiated (includes transaction reference) |
| `INFO` | Authentication approved |
| `WARN` | Authentication ended with a non-approved status (e.g. `CANCELED`, `REJECTED`, `EXPIRED`) |
| `ERROR` | Unexpected failure during authentication, with full stack trace |
