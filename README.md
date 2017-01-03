# mooc-cybersec-project
Cyber Security Base Project 1, duplicated from the template.

# Issues
Five of ten issues replcated from https://www.owasp.org/index.php/Top_10_2013-Top_10.

## #1 Sensitive Data Exposure
Usernames, addresses and passwords are all kept in plaintext. A breach would endanger
all information about users easily.

1. Create a user or login successfully
2. Look at address bar parameters (user=[username]&password=[password])
3. Password is in plain text so it is also saved in plain text

**Fix: Hashing and salting passwords.**

## #2 Insecure Direct Object References
Username and password are sent as parameters in a GET-request, allowing an easy way to brute force.

1. Create a user or login successfully
2. Look at address bar parameters (user=[username]&password=[password])
3. Using information from issue #4, start bruteforcing passwords for known users.

**Fix: More sophisticated authentication e.g. (org.springframework.security.core.Authentication) would make this approach impossible.**

## #3 Security Misconfiguration
Stack trace is visible when trying to create a user with same username, displaying unwanted information.

1. Create a user with name "USER" and password "PASSWORD"
2. Try to create user with same information again
3. Error page with unwanted information is displayed

```There was an unexpected error (type=Internal Server Error, status=500).
could not execute statement; SQL [n/a]; constraint ["UK_GEX1LMAQPG0IR5G1F5EFTYAA1_INDEX_E ON PUBLIC.ACCOUNT(USERNAME) VALUES ('USER', 9)"; SQL statement: insert into account (id, address, password, username) values (null, ?, ?, ?) [23505-193]]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement```

**Fix: Create error pages for possible exceptions.**

## #3 Missing Function Level Access Control
There is a list of users on the site which is accessible without any credentials.

1. Navigate to http://localhost:8080/userlist
2. See a list of all possible users

**Fix : Implement credential check when accessing sensitive pages.**

## #5 Cross-Site Scripting (XSS)
None of the data that application receives is escaped in any way. If the service would
be integrated into another services, this could cause massive problems. In this application, it makes in impossible to log
in as some accounts with special characters or log in as wrong user in some.

1. Create an account with name 'account' and some password
2. Create an account with name 'account&more' with some password
3. You should be thrown back into login page, even though the account exists

If the accounts are created with the same password, logging in with 'account&more' will actually log in as 'account', releaving personal information.

**Fix: escape input data.**
