# CS 320: Software Testing, Automation, and Quality Assurance

## Project Overview

This repository contains coursework completed for CS 320: Software Testing, Automation, and Quality Assurance at Southern New Hampshire University (SNHU).

The projects demonstrate my ability to develop Java applications, implement automated unit tests using JUnit 5, validate software requirements, and evaluate software quality through code coverage analysis.

Throughout this course, I developed three Java services: Contact Service, Task Service, and Appointment Service. Each service was designed around specific functional requirements and supported by automated tests that verify expected behavior, enforce input constraints, and identify potential defects.

The repository also includes my Project Two Summary and Reflections Report, which evaluates the testing strategies used throughout the course.

## Project Artifacts

### Project One: Contact Service

The Contact Service provides an in-memory system for managing contact information. It supports adding, updating, and deleting contacts while enforcing requirements for unique identifiers, field lengths, and valid input values.

**Portfolio files:**
- `Contact.java`
- `ContactService.java`
- `ContactTest.java`
- `ContactServiceTest.java`

These files demonstrate object-oriented programming, requirements-based testing, input validation, and automated unit testing using JUnit 5.

### Project Two: Summary and Reflections Report

Project Two documents my approach to software testing and quality assurance across the Contact, Task, and Appointment Services. The report examines the effectiveness of my testing strategies, the importance of maintainable test code, and the role of software testing in reducing defects and technical debt. It also reflects on professional responsibility, developer bias, and discipline throughout the software development lifecycle.

## Testing and Code Coverage

I used JUnit 5 to develop automated tests and JaCoCo to measure code coverage. Tests covered valid and invalid inputs, boundary conditions, null values, duplicate identifiers, exception handling, and service operations.

The final JaCoCo report confirmed the following results across all six production Java classes:

| Coverage Metric | Result |
| --- | --- |
| Instruction Coverage | 100% (509/509) |
| Branch Coverage | 100% (70/70) |
| Line Coverage | 100% (154/154) |
| Production Classes Tested | 6 |

These results exceeded the Project One requirement of at least 80% code coverage. Although 100% coverage does not guarantee that software is free of defects, it demonstrates that the automated tests exercised all measured executable lines and branches.

---

# Reflection

## How can I ensure that my code, program, or software is functional and secure?

Ensuring that software is functional and secure begins with understanding its requirements and translating those requirements into measurable test cases. Throughout CS 320, I focused on validating both expected behavior and how the application responds to invalid input. For example, the Contact Service required unique identifiers, specific character limits, and non-null fields. I implemented these constraints directly in the application and created JUnit tests to verify that valid inputs were accepted while invalid inputs produced the appropriate exceptions.

I also learned the importance of testing boundary conditions rather than relying exclusively on typical use cases. Testing values at and beyond their permitted limits helped verify that validation rules behaved correctly. Additionally, preventing changes to unique identifiers and protecting mutable appointment dates demonstrated how careful implementation decisions can preserve data integrity.

From a security perspective, input validation and controlled access to object state help reduce opportunities for unexpected behavior. However, functional testing alone does not establish that an application is secure. In a production environment, I would also incorporate static code analysis, dependency vulnerability scanning, security testing, and automated checks within CI/CD pipelines.

My experience using Jenkins, GitHub Actions, and Azure DevOps has reinforced the value of integrating quality checks throughout development rather than treating testing as a final activity before deployment. Ultimately, I believe reliable software requires continuous validation, effective monitoring, and a combination of automated testing and engineering judgment.

## How do I interpret user needs and incorporate them into a program?

I approach user requirements by identifying the underlying problem, understanding the expected behavior, and translating those expectations into clear technical requirements and acceptance criteria.

One important lesson reinforced by this course is that requirements must be specific enough to implement and test. For example, the Contact Service requirements explicitly defined maximum field lengths, required values, and restrictions on updating contact identifiers. These details informed both the class design and the JUnit test cases.

Rather than implementing functionality based on assumptions, I believe developers should clarify ambiguous requirements and consider how users might interact with the system under both normal and unexpected conditions. This includes identifying edge cases, validating input, and ensuring that error handling is predictable.

In my professional experience, effective communication with stakeholders and collaboration across engineering teams have been essential to delivering software that meets business expectations. I typically break larger requirements into smaller, testable components and use automated tests to confirm that each component behaves as intended.

This approach reduces misunderstandings, makes development more manageable, and helps ensure that the final application addresses actual user needs rather than simply satisfying a technical implementation.

## How do I approach designing software?

My approach to software design emphasizes simplicity, maintainability, clear responsibilities, and testability. I begin by reviewing the requirements, identifying the primary components, and determining how those components should interact.

In CS 320, I applied object-oriented design principles by separating the data models from the services responsible for managing them. For example, the `Contact` class manages contact information and validates its fields, while `ContactService` handles operations such as adding, updating, and deleting contacts.

This separation makes the code easier to understand, maintain, and test. It also allows individual components to be verified independently without requiring a complete application environment.

I prefer designing software in small, manageable components with clearly defined responsibilities. I also consider error handling, input validation, and automated testing early in the design process rather than introducing them after implementation.

My professional experience developing distributed systems, APIs, and cloud-based applications has shown me that maintainability becomes increasingly important as applications grow. Poorly defined responsibilities and tightly coupled components can make debugging, testing, and future enhancements significantly more difficult.

This course reinforced my belief that software quality begins with good design. By combining clear requirements, modular architecture, and automated testing, I can build applications that are easier to extend, troubleshoot, and maintain.

---

## Technologies Used

- Java
- JUnit 5
- Gradle
- JaCoCo
- IntelliJ IDEA
- Git and GitHub

## Key Takeaways

CS 320 reinforced the importance of treating software testing as an integral part of development rather than a separate activity.

The most valuable lessons from this course were the importance of requirements-based testing, validating boundary conditions, writing maintainable automated tests, and using measurable coverage results to evaluate testing effectiveness.

The projects also reinforced the need to balance automation with critical thinking. While automated tests improve consistency and efficiency, software engineers must still evaluate requirements, identify potential risks, and recognize that passing tests do not necessarily guarantee software quality.

These principles will continue to guide my approach to developing reliable, maintainable, and secure software.

## AI Usage Acknowledgment

ChatGPT, developed by OpenAI, was used to assist with organizing and refining the documentation and reflections for this portfolio. The project implementations, testing activities, and reported JaCoCo coverage results are described as part of the CS 320 coursework.

## Author

**Ogheneogaga F. Agi**  
Bachelor of Science in Computer Science  
Southern New Hampshire University (SNHU)
