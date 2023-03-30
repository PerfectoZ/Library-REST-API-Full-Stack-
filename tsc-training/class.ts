class User {
    private name: string;
    private age: number;
    
    constructor();
    constructor(name: string, age: number);
    constructor(name?: string, age?: number) {
        this.name = name ? name : 'John';
        this.age = age ? age : 25;
    }

    get Name(): string {
        return this.name;
    }

    get Age(): number {
        return this.age;
    }

    set Name(name: string) {
        this.name = name;
    }

    set Age(age: number) {
        this.age = age;
    }

    public sayHello(): string {
        return `Hello, I am ${this.name} and I am ${this.age} years old. Bye`;
    }
}

const user = new User();
console.log(user.Name);
user.Name ='Mandeep';
user.Age=25;
console.log(user.sayHello());