"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var shape_1 = require("./shape");
var circle_1 = require("./circle");
var myShape = new shape_1.Shape(10, 15);
console.log(myShape.getinfo());
var myCircle = new circle_1.Circle(5, 10, 20);
console.log(myCircle.getinfo());
var theShapes = [];
theShapes.push(myShape);
theShapes.push(myCircle);
for (var _i = 0, theShapes_1 = theShapes; _i < theShapes_1.length; _i++) {
    var tempShape = theShapes_1[_i];
    console.log(tempShape.getinfo());
}
