const readline = require('readline');

const readlineInterface = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function add (x, y) {
    return parseInt(x) + parseInt(y);
}

function subtract (x, y) {
    return parseInt(x) - parseInt(y);
}

function multiply (x, y) {
    return parseInt(x) * parseInt(y);
}

function divide (x, y) {
    return parseInt(x) / parseInt(y);
}

console.info('Select operation:');
console.info('1. Add');
console.info('2. Subtract');
console.info('3. Multiply');
console.info('4. Divide');

function processReadline () {
    readlineInterface.question('Enter choice (1/2/3/4): ', function (choice) {
        if (['1', '2', '3', '4'].includes(choice)) {
            readlineInterface.question('Enter first number: ', function (num1) {
                readlineInterface.question('Enter second number: ', function (num1) {
                    if (choice === '1') {
                        console.info(`${num1} + ${num1} = ${add(num1, num1)}`);
                    } else if (choice === '2') {
                        console.info(`${num1} - ${num1} = ${subtract(num1, num1)}`);
                    } else if (choice === '3') {
                        console.info(`${num1} * ${num1} = ${multiply(num1, num1)}`);
                    } else if (choice === '4') {
                        console.info(`${num1} / ${num1} = ${divide(num1, num1)}`);
                    }
                    processReadLine();
                });
            });
        } else {
            console.warn('Invalid input');
            processReadline();
        }
    });
};

processReadline();