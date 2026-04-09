

# Freja eID Demo

A Spring Boot demo application integrating [Freja eID](https://frejaeid.com) authentication.

## Prerequisites

- Java 17+
- Maven
- A valid Freja eID keystore (`.pfx`) and the Freja test root CA certificate

## Configuration

The application requires two environment variables:

| Variable | Description |
|--------|-----------|
| `KEYSTORE_PASS` | Password for the `.pfx` keystore file |
| `KEYSTORE_PATH` | Absolute path to the `.pfx` keystore file |

You can set them in a `.env` file:

```
export KEYSTORE_PASS=your_password
export KEYSTORE_PATH=/absolute/path/to/keystore.pfx
```

## Running the Application

```bash
source .env && ./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

## Logging

The application uses [Log4j2](https://logging.apache.org/log4j/2.x/) configured via `src/main/resources/log4j2.xml`.

Logs are written to two destinations:

- **Console** — all output visible in the terminal
- **`app.log`** — authentication events only, written to the project root.

### What gets logged

| Level | Event |
|-------|-------|
| `INFO` | Authentication initiated (includes transaction reference) |
| `INFO` | Authentication approved |
| `WARN` | Authentication ended with a non-approved status (e.g. `CANCELED`, `REJECTED`, `EXPIRED`) |
| `ERROR` | Unexpected failure during authentication, with full stack trace |
