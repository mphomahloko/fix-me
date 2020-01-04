# Fix-Me

A stock exchange simulation using trading algorithms, networking and socket implementations.

The aim of this project is to develop multi-threaded network applications with asynchronous sockets and the java executor framework.

## Installation

### Prerequisites

   - [Java](https://www.java.com/) (Core Programming Language - JDK 8 >)
   
   - [Maven](https://maven.apache.org/) (Dependency Management)
   
#### macOS

 - Installing with [Homebrew](https://brew.sh/)
> In the terminal run the following: 

```shell
$ brew update
$ brew cask install java
$ brew install maven
```
#### Windows

 - Installing with [Chocolatey](https://chocolatey.org/)
> In a Powershell run the following:

```PowerShell
$ choco install jdk8
$ choco install maven
```

### Clone

> Clone this repo to your local machine using:

```
$ git clone https://github.com/Mahloko/fix-me.git
```

### Setup

#### macOS

- You will need three separate terminals

> First Terminal : Router

```shell
$ sh runRouter
```

> Second Terminal : Broker

```shell
$ sh runBroker
```

> Second Terminal : Market

```shell
$ sh runMarket
```

#### Windows

- You will need three separate shells

> First Shell : Router

```Powershell
$ mvn clean package
$ java -jar router/target/router.jar
```

> Second Shell : Broker

```Powershell
$ java -jar broker/target/broker.jar
```

> Second Shell : Market

```Powershell
$ java -jar market/target/market.jar firstMarketStock
```

## Sample
![Screenshot](./screenshot.png)
---

## Authors

- **[Mpho Mahloko](https://github.com/Mahloko)**
- **[Mohambe Sambo]()**
- **[Valerie Gongora](https://github.com/ValerieGM)**
- **[Chanel Letinić](https://github.com/CLetinic)**
- **[Piet Ragolane]()**

---
