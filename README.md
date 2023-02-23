# Reward-Service

#A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every

dollar spent between $50 and $100 in each transaction.
(e.g., a $120 purchase = 2x$20 + 1x$50 = 90 points).

#How run application
- Step 1 uzip program and import project into IDE sts or eclipse
- Step 2 mvn clean install
- Step 3 run as spring boot application

H2 in-memory database is used and their data.sql, schema.sql is added into the source resoure folder.

#Curl Request

#Fetch all customer and its transaction record

curl --location 'localhost:8081/reward-service/customer/list'

```
[
    {
        "customerId": 2251,
        "customerName": "Jaykumar",
        "transcations": [
            {
                "transactionId": 2254,
                "transactionAmount": 120.0,
                "transactionDate": "2022-12-12"
            },
            {
                "transactionId": 2255,
                "transactionAmount": 85.0,
                "transactionDate": "2022-12-01"
            },
            {
                "transactionId": 2256,
                "transactionAmount": 160.0,
                "transactionDate": "2023-01-04"
            },
            {
                "transactionId": 2257,
                "transactionAmount": 90.0,
                "transactionDate": "2023-01-01"
            },
            {
                "transactionId": 2258,
                "transactionAmount": 120.0,
                "transactionDate": "2023-02-04"
            },
            {
                "transactionId": 2259,
                "transactionAmount": 165.0,
                "transactionDate": "2023-02-05"
            },
            {
                "transactionId": 2272,
                "transactionAmount": 120.0,
                "transactionDate": "2022-10-12"
            }
        ]
    },
    {
        "customerId": 2252,
        "customerName": "Jayan",
        "transcations": [
            {
                "transactionId": 2260,
                "transactionAmount": 113.0,
                "transactionDate": "2022-12-05"
            },
            {
                "transactionId": 2261,
                "transactionAmount": 80.0,
                "transactionDate": "2022-12-27"
            },
            {
                "transactionId": 2262,
                "transactionAmount": 102.0,
                "transactionDate": "2023-01-04"
            },
            {
                "transactionId": 2263,
                "transactionAmount": 210.0,
                "transactionDate": "2023-01-01"
            },
            {
                "transactionId": 2264,
                "transactionAmount": 130.0,
                "transactionDate": "2023-02-27"
            },
            {
                "transactionId": 2265,
                "transactionAmount": 88.0,
                "transactionDate": "2023-04-15"
            },
            {
                "transactionId": 2273,
                "transactionAmount": 120.0,
                "transactionDate": "2022-10-12"
            }
        ]
    },
    {
        "customerId": 2253,
        "customerName": "Chid",
        "transcations": [
            {
                "transactionId": 2266,
                "transactionAmount": 102.0,
                "transactionDate": "2022-12-05"
            },
            {
                "transactionId": 2267,
                "transactionAmount": 84.0,
                "transactionDate": "2022-12-27"
            },
            {
                "transactionId": 2268,
                "transactionAmount": 200.0,
                "transactionDate": "2023-01-04"
            },
            {
                "transactionId": 2269,
                "transactionAmount": 103.0,
                "transactionDate": "2023-01-01"
            },
            {
                "transactionId": 2270,
                "transactionAmount": 500.0,
                "transactionDate": "2023-02-27"
            },
            {
                "transactionId": 2271,
                "transactionAmount": 585.0,
                "transactionDate": "2023-02-20"
            },
            {
                "transactionId": 2274,
                "transactionAmount": 120.0,
                "transactionDate": "2022-10-12"
            }
        ]
    }
]
```

#Fetch customer rewards point based on customer id

curl --location 'localhost:8081/reward-service/customer/2251/reward'

```
{
    "customerId": 2251,
    "customerName": "Jaykumar",
    "firstPreviosMonthReward": 270,
    "secondPreviosMonthReward": 210,
    "thirdPreviosMonthReward": 125,
    "totalReward": 605
}
```


curl --location 'localhost:8081/reward-service/customer/2252/reward'

```
{
    "customerId": 2252,
    "customerName": "Jayan",
    "firstPreviosMonthReward": 0,
    "secondPreviosMonthReward": 354,
    "thirdPreviosMonthReward": 76,
    "totalReward": 430
}
```