# CalculatorKafka
## Building the Application
### Step 1: Navigate to the Project Directory
Open your terminal and navigate to the directory where the `docker-compose.yml` file is located.
### Step 2: Build the Docker Containers
Run the following command to build the necessary Docker containers:\
`docker compose up –build`
### Step 3: Sending a Request
Once the containers are up and running, you can interact with the API using a tool such as Postman. Send a request to the following endpoint:\
`localhost:8080/api/calculator/multiplication?a=10&b=400`\
In the above example:
- Replace `"multiplication"` with one of the available operators: `"sum"`, `"subtraction"`, or `"division"`, depending on the operation you want to perform.
- Adjust the values for `a` and `b` in the URL (`a=10` and `b=400`) to the numbers you wish to use in the calculation.
