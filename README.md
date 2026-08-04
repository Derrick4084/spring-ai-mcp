# Spring AI MCP Server

A **Spring AI Model Context Protocol (MCP) Server** designed to be used by a Spring AI application.

This application exposes MCP tools and prompts that allow a Spring AI client application to access product data, retrieve the current date and time, and perform Retrieval-Augmented Generation (RAG) queries against a Qdrant vector database.

## Overview

The application provides an MCP server using **synchronous transport** and enables both **MCP Tools** and **MCP Prompts**.

The server is intended to work as a backend MCP service for a Spring AI application, allowing the client application to discover and invoke tools and prompts exposed by this server.

### Architecture

```text
                    Spring AI Application
                            |
                            | MCP Sync Transport
                            |
                            v
                 +----------------------+
                 |  Spring AI MCP Server |
                 +----------------------+
                    |        |        |
                    |        |        |
                    v        v        v
                PostgreSQL  Qdrant   MCP Prompts
                Product DB  Vector   Greetings
                            Store
```

## Features

* Spring AI MCP Server
* MCP synchronous transport
* MCP Tool capabilities enabled
* MCP Prompt capabilities enabled
* PostgreSQL integration
* Qdrant vector database integration
* Product information retrieval
* Current date and time retrieval
* RAG document retrieval
* MCP prompt support
* Docker Compose environment for PostgreSQL and Qdrant

## MCP Tools

The server exposes the following MCP tools.

### DateTime Tool

Retrieves the current date and time.

**Purpose:**

Allows the Spring AI client application to request the current date and time through the MCP server.

### Product Tool

Retrieves product information from PostgreSQL.

**Purpose:**

Provides product information stored in the PostgreSQL database to the Spring AI client application.

The database contains sample product data for demonstration and development purposes.

### RAG Tool

Performs Retrieval-Augmented Generation (RAG) document queries using the Qdrant vector database.

**Purpose:**

Allows the Spring AI client application to search the vector store for relevant document content.

The RAG tool can be used to retrieve information from documents that have been ingested and stored as vector embeddings in Qdrant.



### Weather Tool

Performs retrieval of weather information for requested city.

**Purpose:**

Allows the Spring AI client application to get temperature, description, feels like temperature and humidity of requested city.


## MCP Prompts

The server exposes the following MCP prompt.

### Greetings Prompt

Provides a friendly greeting prompt that can be retrieved and used by the Spring AI client application.

The prompt demonstrates how MCP prompts can be defined and consumed by an MCP client.

## MCP Capabilities

The MCP server has the following capabilities enabled:

| Capability  | Status   |
| ----------- | -------- |
| Tools       | Enabled  |
| Prompts     | Enabled  |
| Resources   | Disabled |
| Completions | Disabled |

## Data Stores

### PostgreSQL

PostgreSQL is used to store sample product data.

The Product MCP Tool queries PostgreSQL to retrieve product information.

### Qdrant

Qdrant is used as the vector database for the RAG functionality.

Documents are converted into vector embeddings and stored in Qdrant. The RAG MCP Tool queries Qdrant to retrieve relevant document information.

## Docker Compose

The application includes a Docker Compose configuration that starts the required infrastructure services.

The Docker Compose environment runs:

* PostgreSQL
* Qdrant

### Docker Architecture

```text
+-------------------------+
|     Docker Compose      |
+-------------------------+
          |
          +------------------+
          |                  |
          v                  v
+------------------+  +------------------+
|   PostgreSQL     |  |     Qdrant       |
|                  |  |                  |
| Sample Products  |  |  Vector Store    |
+------------------+  +------------------+
          ^                  ^
          |                  |
          +--------+---------+
                   |
                   |
          Spring AI MCP Server
```

## Getting Started

### Prerequisites

Before running the application, make sure you have the following installed:

* Java
* Maven
* Docker
* Docker Compose



### Run the Application

Build and run the application:

* Postgres and Qdrant will start via Spring's compose.yml


```bash
./mvnw clean package
java -jar target/*.jar
```

## Connecting from a Spring AI Application

The MCP server is designed to be consumed by a separate Spring AI application.

The Spring AI application connects to this server using the **MCP synchronous transport** and can discover the available MCP tools and prompts.

Once connected, the client application can access:

```text
MCP Server
    |
    +-- Tools
    |    +-- DateTime Tool
    |    +-- Product Tool
    |    +-- RAG Tool
    |    +-- Weather Tool
    |
    +-- Prompts
         +-- Greetings Prompt
```

The Spring AI application can then use the tools as part of an AI-powered workflow.

For example:

```text
User
  |
  | "What is the current time?"
  v
Spring AI Application
  |
  | MCP Tool Call
  v
DateTime Tool
  |
  v
Current Date and Time
```

Another example:

```text
User
  |
  | "Tell me about product X"
  v
Spring AI Application
  |
  | MCP Tool Call
  v
Product Tool
  |
  v
PostgreSQL
  |
  v
Product Information
```


You need to supply an OpenWeather api key for the next example:

```text
User
  |
  | "Give me the current weather in Atlanta Ga"
  v
Spring AI Application
  |
  | MCP Tool Call
  v
Weather Tool
  |
  v
OpenWeather.org
  |
  v
Weather Information
```

For RAG queries:

```text
User
  |
  | "Find information about X"
  v
Spring AI Application
  |
  | MCP Tool Call
  v
RAG Tool
  |
  v
Qdrant
  |
  v
Relevant Documents
```

## Project Purpose

This project demonstrates how to build a **Spring AI MCP Server** that centralizes access to application capabilities and data sources.

Instead of implementing these capabilities directly inside the Spring AI client application, the MCP server exposes them through a standardized MCP interface.

This allows the Spring AI application to dynamically discover and use:

* Tools
* Prompts
* Data from PostgreSQL
* Vector search through Qdrant
* RAG capabilities

## Technology Stack

* Java
* Spring Boot
* Spring AI
* Model Context Protocol (MCP)
* PostgreSQL
* Qdrant
* Docker
* Docker Compose
* Maven

## Summary

This project provides a Spring AI MCP Server with synchronous transport and support for MCP tools and prompts.

The available tools include:

* **DateTime Tool** — Retrieves the current date and time.
* **Product Tool** — Retrieves product information from PostgreSQL.
* **RAG Tool** — Queries Qdrant for relevant document information.

The server also exposes a **Greetings Prompt** that can be retrieved by the Spring AI MCP client.

PostgreSQL and Qdrant are provided through Docker Compose, making it easy to run the application's required infrastructure locally.
