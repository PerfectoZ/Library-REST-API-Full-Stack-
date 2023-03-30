"use strict";
for (var i = 0; i < 10; i++) {
    console.log(i);
}
var rev = [1, 2, 3, 4.5, 4];
for (var i = 0; i < rev.length; i++) {
    console.log(Math.floor(rev[i]));
}
for (var _i = 0, rev_1 = rev; _i < rev_1.length; _i++) {
    var x_1 = rev_1[_i];
    console.log(x_1);
}
var arr = ['a', 'b', 'c'];
arr.push('d');
console.log(arr.join('-'));
for (var _a = 0, arr_1 = arr; _a < arr_1.length; _a++) {
    var element = arr_1[_a];
    console.log("".concat(element));
}
