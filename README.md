# Demo App

Technology assessment assignment

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application

1- To build the jar

```shell
mvn spring-boot:run
```

## Application Structure

On start-up, DummyDataCreator class is responsible to save dummy data e.g Products, Customers

* Endpoint to get product with filters and pagination

```shell
Method: GET
URI: http://localhost:8080/product/all
Request Params: [name, brand, color, page]
Response Format: {
    "content" : [], // List of products
    "totalPages": 3,
    "totalElements": 30
}
```

* Endpoint to save/update cart

```shell
Method: POST
URI: http://localhost:8080/cart/add 
     OR
     http://localhost:8080/cart/update

Request Fromat: 
{
    "customerId": 2, 
    "productId":22,
    "quantity":1
}

Response Format: 
{
    "id": 34,
    "customer": {
        "id": 2,
        "name": "Qasim"
    },
    "cartItems": [
        {
            "id": 35,
            "product": {
                "id": 11,
                "name": "Microsoft Surface Laptop 4",
                "type": null,
                "color": "Black",
                "brand": "Microsoft Surface",
                "price": 1499.0
            },
            "quantity": 1
        }
    ],
    "totalCost": 1499.0
}
```


* Endpoint to delete cart item

```shell
Method: DELETE
URI: http://cart/remove?itemId=36
 
```

* Endpoint to place an order
```shell
Method: POST
URI: http://localhost:8080/order/save/{customerId}

Response Format: 
{
    "id": 37,
    "customer": {
        "id": 2,
        "name": "Qasim"
    },
    "orderDetail": [
        {
            "id": 38,
            "product": {
                "id": 11,
                "name": "Microsoft Surface Laptop 4",
                "type": null,
                "color": "Black",
                "brand": "Microsoft Surface",
                "price": 1499.0
            },
            "quantity": 1
        },
        {
            "id": 39,
            "product": {
                "id": 22,
                "name": "Skin Beauty Serum.",
                "type": null,
                "color": "white",
                "brand": "ROREC White Rice",
                "price": 46.0
            },
            "quantity": 1
        }
    ],
    "totalCost": 1545.0,
    "status": "READY_TO_DELIVER"
}
```
