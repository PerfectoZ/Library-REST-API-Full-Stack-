import { Shape } from "./shape";
import { Circle } from "./circle";

let myShape = new Shape(10, 15);
console.log(myShape.getinfo());

let myCircle = new Circle(5, 10, 20);
console.log(myCircle.getinfo());

let theShapes: Shape[] = [];
theShapes.push(myShape);
theShapes.push(myCircle);

for(let tempShape of theShapes) {
    console.log(tempShape.getinfo());
}