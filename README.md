# CCValidator

💳 Java CLI tool to generate and validate credit card numbers using the Luhn algorithm.

## Features

- Supports Visa, MasterCard, Amex, Discover
- Generates valid test card numbers
- Validates card numbers via Luhn algorithm
- No dependencies, pure Java

## How to Use

### Compile

```sh
javac -d out src/main/java/com/wearl/ccvalidator/CCValidator.java
```

### Run

```sh
cd D:\CC-Generater-In-Java\CCValidator\src\main\java
javac com/wearl/ccvalidator/CCValidator.java
java -cp . com.wearl.ccvalidator.CCValidator generate visa
java -cp out com.wearl.ccvalidator.CCValidator validate 4111111111111111
```

## Project Structure

```
CCValidator/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── wearl/
│                   └── ccvalidator/
│                       └── CCValidator.java
├── README.md
```

## License

MIT License

## Author
[Saurabh Kushwaha](https://www.linkedin.com/in/saurabh884095/)
