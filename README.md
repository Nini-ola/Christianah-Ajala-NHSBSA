## NHS Jobs Search Automation
This project automates testing of the NHS Jobs website's search functionality using Java, Selenium, Cucumber, and TestNG. It verifies that jobseekers can search for jobs using their preferences (role, location, distance) and receive sorted results based on the most recent posting date.

## Tech Stack
- Java 21
- Maven
- Selenium WebDriver
- Cucumber (BDD)
- TestNG
- WebDriverManager

## Setup Instructions
1. Clone the repository
2. Run `mvn clean install` to install dependencies
3. Use `mvn test` or run individual features/test classes from your IDE
4. Ensure Chrome and Firefox are installed (ChromeDriver and FirefoxDriver managed via WebDriverManager need the binaries of the browsers to run)

## Test Coverage
User Story: 
As a jobseeker on the NHS Jobs website,  
I want to search for a job with my preferences,  
So that I can get recently posted job results.

Acceptance Criteria & Covered Scenarios:
- Search with only job title
- Search with only location
- Search with both job title and location then sort by newest date posted
- Search with only job title and sort by newest date posted
- Search with only location and sort by newest date posted
- Search with no data for job title and location then sort by newest date posted
- No-match search

## Assumptions
- Jobs are posted on weekdays only.
- If there are no jobs posted today, the most recent available job should appear first (even if posted days ago).
- When only one job is returned, it is assumed to be the newest available, even if it wasn't posted today.
- Search results may use singular or plural ("1 job found" vs "5 jobs found") — test regex accounts for this.
- The test expects at least one job to appear when valid role/location values are provided, unless deliberately testing for no matches.
- “Newest first” means results are sorted by posting date in descending order.
- Jobs have certain key words attached to them therefore search results for job types may not give job listings with the exact role requested
- Every search with location defaults to within 5 miles of the given location

## Known Issues/Limitations
- Test reliability may depend on current job postings on the live NHS site.
- Edge cases (e.g. searching at 1am) may yield older job posts, which the test handles by allowing fallback to previous weekdays.

## Project Structure
- `src/test/java` – Step definitions, page objects, utilities
- `src/test/resources/features` – Cucumber feature files
- `testng.xml` – TestNG suite runner
- `README.md` – This file



