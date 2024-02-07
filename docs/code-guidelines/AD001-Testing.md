# Testing Rules

| Date       | Status   | Decided By     | Description     |
|------------|----------|----------------|-----------------|
| 07/02/2024 | Accepted | - Nick Bauters | Initial version |

## Testing

Testing should be an integral part of the coding experience on this project.
Even on time constraints, tests should never be skipped, and are a valid reason for delaying/pushing a production ready increment.

This chapter describes the testing rules that should be followed when working on this project.

### Unit tests

The entire logical codebase should be covered by unit tests.
We prefer UseCase based testing over component based testing.

Classes that are free from being tested are:
- DTO's without additional logic
- Entities without additional logic
- Repositories without additional logic

All other changes, or implementations where we add logic, should be covered by unit tests.

### integration tests

[To be documented later, this should be a part of the CI/CD pipeline and should be run on every push to master/main]

## When should tests run

### Unit tests

1. After every change, the test suite should be run locally
2. After every push, the test suite should be run on the CI
3. With every pull request, the test suite should be run on the CI