services to run 


 1.customer-service port:9001  crud operations on users 


a basic prototype of user crud operations 

customer service apis for given functionalities
 1. add customer   		 url : http://localhost:9001/customer/saveCustomer
 2. get customer details         url : http://localhost:9001/customer/getCustomer/{customerId}  
 3. get all customers            url : http://localhost:9001/customer/allCustomers
 4. update customer details      url : http://localhost:9001/customer/updateCustomer/{customerId}
 5. delete customer 		 url : http://localhost:9001/customer/deleteCustomer/{customerId}
 
docker-compose.yml
 -contains customer service , MySQL and redis confirmation 
 - docker-compose up --build -d  will build the 
