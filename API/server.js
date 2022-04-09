const express = require("express")
const app = express()
var AWS = require("aws-sdk")

AWS.config.update({
  region: "ap-southeast-1",
  endpoint: "https://dynamodb.ap-southeast-1.amazonaws.com"
});

var dynamodb = new AWS.DynamoDB();

var params = {
    TableName : "Movies",
    KeySchema: [       
        { AttributeName: "year", KeyType: "HASH"},  //Partition key
        { AttributeName: "title", KeyType: "RANGE" }  //Sort key
    ],
    AttributeDefinitions: [       
        { AttributeName: "year", AttributeType: "N" },
        { AttributeName: "title", AttributeType: "S" }
    ],
    ProvisionedThroughput: {       
        ReadCapacityUnits: 10, 
        WriteCapacityUnits: 10
    }
};


app.use(express.json({limit: '50mb'}))

app.listen(5000, (err) => {

    if(err){
        console.log(err)
    }
    else{
        console.log("Express.js: Connected to PORT 5000")
    }
})

app.get("/", (req, res) => {

    res.status(200).json({message: "Welcome to Boimela API!"})
})

app.get("/createTable", (req, res) => {

    dynamodb.createTable(params, function(err, data) {
        if (err) {
            console.error("Unable to create table. Error JSON:", JSON.stringify(err, null, 2));
        } else {
            console.log("Created table. Table description JSON:", JSON.stringify(data, null, 2));
        }
    })
})