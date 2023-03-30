for(let i=0; i<10; i++) {
    console.log(i);
}

let rev : number[] = [1,2,3,4.5,4];
for(let i=0; i<rev.length; i++) {
    console.log(Math.floor(rev[i]));
}

for(let x of rev) {
    console.log(x);
}

const arr : string[] = ['a','b','c'];
arr.push('d');
console.log(arr.join('-'));

for (const element of arr) {
    console.log(`${element}`);
}