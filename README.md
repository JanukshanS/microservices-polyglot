# Microservices Polyglot Project

This is a polyglot microservices application consisting of several interconnected services that handle different parts of an e-commerce workflow (Items, Orders, and Payments). It includes an API Gateway to properly route requests to the respective microservices.

## Architecture

The project contains the following components:

1. **API Gateway** (`api-gateway`) 
   - Runs on port `8080`
   - Acts as the main entry point for the application.
   - Routes incoming requests to the appropriate downstream microservice.

2. **Item Service** (`item-service`)
   - Runs on port `8081`
   - Manages operations related to items/inventory.

3. **Order Service** (`order-service`)
   - Runs on port `8082`
   - Handles the creation and management of orders.

4. **Payment Service** (`payment-service`)
   - Runs on port `8083`
   - Processes simulated payments and manages payment states.

## Prerequisites
- **Docker** and **Docker Compose** installed on your system.
- *(Optional)* Postman (you can import the provided `Postman_Collection.json` file to test the APIs).

## How to Run

The easiest way to run the entire cluster of microservices is by using Docker Compose.

1. **Open a terminal** in the root directory of this project (`microservices-polyglot`).

2. **Start the services**:
   ```bash
   docker compose up --build
   ```
   *(Add `-d` to run it in detached mode in the background).*

3. **Verify it is running**:
   - The services will spin up in their respective containers.
   - You can access the API Gateway at: `http://localhost:8080`
   - `docker ps` will show the four running containers (`api-gateway`, `item-service`, `order-service`, and `payment-service`) connected via the `microservices-net` bridge network.

4. **Stop the services**:
   ```bash
   docker compose down
   ```

## Testing the APIs

A Postman collection (`Postman_Collection.json`) is included in the root directory. 

1. Open Postman.
2. Click **Import** and select the `Postman_Collection.json` file.
3. This collection contains predefined requests configured to hit the API Gateway (`http://localhost:8080`). The gateway will seamlessly route your requests to the correct underlying microservice.

## Development

If you wish to run a specific service independently without Docker, you can navigate into its specific directory and run it locally based on its underlying technology stack (e.g., Python/Flask, Java/Spring Boot, Node.js, etc.). Be sure to check each individual directory for the specific files and configuration requirements.
