"use strict";

export const PI = 3.1416;

function f1(a, b) {
    return a + b;
}

const f2 = function (a, b) {
    return a - b;
}
console.log(f2(5, 3));
const f3 = (a, b) => { return a * b };
export const f4 = (a) => a ** 3;
// fetch('http://localhost:8080/api/alumnos')
//     .then(res => res.json())
//     .then(data => console.log(data))
//     .catch(err => console.log(err));
// fetch('https://jsonplaceholder.typicode.com/todos/1')
//     .then(response => response.json())
//     .then(json => console.log(json))
//     .catch(err => console.log(err));
export { f1, f2, f3 };