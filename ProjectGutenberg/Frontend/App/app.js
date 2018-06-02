var express = require('express');
var path = require('path');

var app = express();
var index = "./Views/index.html";

app.use(express.static("public"));

app.listen(7777, function () {
    console.log("Server started, listening on: " + 7777);
});