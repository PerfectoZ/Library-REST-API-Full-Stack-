"use strict";
var User = /** @class */ (function () {
    function User(name, age) {
        this.name = name ? name : 'John';
        this.age = age ? age : 25;
    }
    Object.defineProperty(User.prototype, "Name", {
        get: function () {
            return this.name;
        },
        set: function (name) {
            this.name = name;
        },
        enumerable: false,
        configurable: true
    });
    Object.defineProperty(User.prototype, "Age", {
        get: function () {
            return this.age;
        },
        set: function (age) {
            this.age = age;
        },
        enumerable: false,
        configurable: true
    });
    User.prototype.sayHello = function () {
        return "Hello, I am ".concat(this.name, " and I am ").concat(this.age, " years old. Bye");
    };
    return User;
}());
var user = new User();
console.log(user.Name);
user.Name = 'Mandeep';
user.Age = 25;
console.log(user.sayHello());
