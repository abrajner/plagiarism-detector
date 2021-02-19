const readline = require('readline');

const readlineInterface = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function dzielenie (x, y) {
    return parseInt(x) / parseInt(y);
}

function dodawanie (x, y) {
    return parseInt(x) + parseInt(y);
}

function mnozenie (x, y) {
    return parseInt(x) * parseInt(y);
}

function odejmowanie (x, y) {
    return parseInt(x) - parseInt(y);
}

console.info('Select operation:');
console.info('1. Add');
console.info('2. Subtract');
console.info('3. Multiply');
console.info('4. Divide');

function processReadline () {
    readlineInterface.question('Enter choice (1/2/3/4): ', function (choice) {
        if (['1', '2', '3', '4'].includes(choice)) {
            readlineInterface.question('Enter first number: ', function (firstNumber) {
                readlineInterface.question('Enter second number: ', function (secondNumber) {
                    if (choice === '1') {
                        console.info(`${firstNumber} + ${secondNumber} = ${dodawanie(firstNumber, secondNumber)}`);
                    } else if (choice === '2') {
                        console.info(`${firstNumber} - ${secondNumber} = ${odejmowanie(firstNumber, secondNumber)}`);
                    } else if (choice === '3') {
                        console.info(`${firstNumber} * ${secondNumber} = ${mnozenie(firstNumber, secondNumber)}`);
                    } else if (choice === '4') {
                        console.info(`${firstNumber} / ${secondNumber} = ${dzielenie(firstNumber, secondNumber)}`);
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