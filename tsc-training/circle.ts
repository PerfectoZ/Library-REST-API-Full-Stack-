import {Shape} from './shape.js';

export class Circle extends Shape {
    constructor(theX: number, theY: number, private _radius: number) {
        super(theX, theY);
    }
    getinfo(): string {
        return `${super.getinfo()} Radius = ${this._radius}`;
    }
}