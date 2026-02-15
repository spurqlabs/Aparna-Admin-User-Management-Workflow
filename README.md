OrangeHRM Admin User Management Automation

**Java + Playwright + Cucumber + Maven**

## Overview

This project automates the **Admin → User Management module** of the OrangeHRM application using a **BDD-based automation framework**.

The framework is designed with:

* Page Object Model (POM)
* JSON-driven test data & locators
* Explicit waits
* Logging & reporting
* Screenshot capture on failure
* Maven execution support

It validates the complete **end-to-end admin user lifecycle**:

* Login
* Create user
* Search user
* Delete user
* Invalid search validation

---

# Tech Stack

| Component     | Technology             | Version |
| ------------- | ---------------------- | ------- |
| Language      | Java                   | 21      |
| UI Automation | Playwright Java        | 1.58.0  |
| BDD           | Cucumber               | 7.14.0  |
| Build Tool    | Maven                  | 3.x     |
| Test Runner   | TestNG                 | 7.12.0  |
| Reporting     | Allure + Cucumber HTML | 2.24.0  |
| Logging       | Log4j2                 | 2.25.3  |
| JSON Parsing  | Gson                   | 2.13.2  |

---

# Framework Architecture

```
orangehrm-playwright-java
│
├── src
│   ├── main
│   │   └── java
│   │       └── com.orangehrm.automation
│   │           ├── base            → Playwright setup & browser factory
│   │           ├── utils           → Waits, Actions, Readers, Screenshot, etc.
│   │           └── pages           → Page Object Model classes
│   │
│   └── test
│       ├── java
│       │   └── com.orangehrm.automation
│       │       ├── steps           → Step Definitions
│       │       └── runner          → Cucumber Test Runner
│       │
│       └── resources
│           ├── features            → Cucumber feature files
│           ├── locators            → locators.json
│           ├── testdata            → JSON test data
│           └── config              → config.properties
│
├── pom.xml                         → Maven dependencies & plugins
└── README.md                       → Project documentation
```

---

# Key Framework Components

## 1. Base Layer

**PlaywrightFactory**

* Initializes browser, context, and page.
* Manages lifecycle for tests.

---

## 2. Page Object Model (POM)

Located in:

```
src/main/java/.../pages
```

Responsibilities:

* UI interactions only
* No test logic
* Uses **locators from JSON**
* Uses **WaitUtils & ActionUtils**

Example:

```
AdminPage.java
```

---

## 3. Utilities

Located in:

```
src/main/java/.../utils
```

### Included utilities:

* **WaitUtils** → explicit waits
* **ActionUtils** → click, fill, select wrappers
* **LocatorReader** → reads locators.json
* **DataReader** → reads test data JSON
* **ScreenshotUtil** → captures screenshots on failure

---

## 4. Test Data Management

Stored in:

```
src/test/resources/testdata
```

Files:

* `adminUser.json`
* `loginData.json`

Advantages:

* No hard-coded values in steps/pages
* Easy data-driven testing
* Supports Scenario Outline expansion

---

## 5. Locator Management

All UI locators stored in:

```
src/test/resources/locators/locators.json
```

Benefits:

* Centralized maintenance
* Zero hard-coded XPath in code
* Improves scalability

---

## 6. Cucumber BDD Layer

### Feature Files

Location:

```
src/test/resources/features
```

Defines:

* Scenarios
* Tags (`@smoke`, `@regression`)
* Scenario Outline support

---

### Step Definitions

Location:

```
src/test/java/.../steps
```

Responsibilities:

* Connect feature steps → page methods
* Assertions
* Logging
* Exception handling

---

## 7. Hooks

Implemented using:

```
@Before / @After
```

Functions:

* Browser setup & teardown
* Screenshot capture on failure
* Logging test lifecycle

---

# Reporting

## Allure Report

Generate:

```
mvn test
allure serve target/allure-results
```

Includes:

* Step-level execution
* Screenshots on failure
* Logs
* Timeline view

---

## Cucumber HTML Report

Generated in:

```
target/cucumber-reports.html
```

---

# Logging

Implemented using **Log4j2**.

Logs include:

* Step execution info
* Errors & exceptions
* Debug traces

---

# Test Scenarios Covered

### Admin User Management Flow

1. Login with valid credentials
2. Navigate to Admin → User Management
3. Create new user with dynamic username
4. Search created user
5. Delete created user
6. Invalid search → verify “No Records Found”

---

# Running the Project

## Prerequisites

* Java 21 installed
* Maven installed
* Internet connection for Playwright browsers

---

## Execute Tests

```
mvn clean test
```

---

## Run Specific Tags

```
mvn test -Dcucumber.filter.tags="@smoke"
```

---

# Parallel Execution

Can be enabled using:

* TestNG configuration
* Maven Surefire plugin

(Currently configurable in framework.)

---

# CI/CD Ready

Framework supports integration with:

* GitHub Actions
* Jenkins

By running:

```
mvn clean test
```

---

# Design Principles Followed

* BDD architecture
* Page Object Model
* Data-driven testing
* Centralized locators
* Explicit waits only
* Clean separation of concerns
* Scalable & maintainable structure

---
