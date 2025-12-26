# Flowable BPMN POC

A simple Proof of Concept demonstrating Flowable BPMN process engine with Spring Boot.

## Overview

This POC demonstrates a basic BPMN workflow:
```
Start → Service Task → End
```

When triggered, the process executes a Java delegate that prints output to the console, demonstrating how Flowable orchestrates service calls.

## Tech Stack

- **Spring Boot 3.5.9**
- **Flowable 7.0.1**
- **MySQL** (database)
- **Java 17**

## Setup

### 1. Configure Environment Variables

Copy the environment template and set your database credentials:

```bash
cp .env.example .env
```

Edit `.env` with your MySQL credentials:
```properties
DB_URL=jdbc:mysql://localhost:3306/flowable?createDatabaseIfNotExist=true
DB_USERNAME=root
DB_PASSWORD=your_password
```

### 2. Run the Application

```bash
./mvnw spring-boot:run
```

The application runs on **port 8081**.

## Usage

### Start a Process

```bash
curl -X POST http://localhost:8081/api/process/start \
  -H "Content-Type: application/json"
```

With custom input data:
```bash
curl -X POST http://localhost:8081/api/process/start \
  -H "Content-Type: application/json" \
  -d '{"inputData": "Hello from Flowable!"}'
```

### Health Check

```bash
curl http://localhost:8081/api/process/health
```

## Console Output

When the process runs, you'll see:
```
============================================
🚀 SERVICE TRIGGERED FROM FLOWABLE!
============================================
Process Instance ID: xxx
Process Definition ID: simpleProcess:1:xxx
...
✅ Service Task Completed!
============================================
```

## Project Structure

```
src/main/
├── java/com/silly/flowable/
│   ├── FlowableApplication.java      # Main application
│   ├── controller/
│   │   └── ProcessController.java    # REST API
│   └── delegate/
│       └── SimpleServiceDelegate.java # Service task implementation
└── resources/
    ├── application.properties         # Configuration
    └── processes/
        └── simple-process.bpmn        # BPMN process definition
```

## Editing the BPMN Diagram

Open `simple-process.bpmn` in [bpmn.io](https://demo.bpmn.io/) to visually edit the workflow.
