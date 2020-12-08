# Git Changelog Maven plugin changelog
Changelog of Git Changelog Maven plugin.
## Unreleased
### GitHub [#3](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/pull/3) 2: Converted to a multi-module project. Added generated OB data model
[2d45a0a3748c4e9](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/commit/2d45a0a3748c4e9) Matt Wills *2020-11-19 13:06:49*
2: Convert to multi-module project (#3)

- Add OB model objects to forgerock-sba-ob-model
- Add ability to generate OB model objects using maven profile
Issue: https://github.com/SecureBankingAcceleratorToolkit/SecureBankingAcceleratorToolkit/issues/2
### GitHub [#4](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/pull/4) Added initial controllers and integration tests
[c41cf79572181e6](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/commit/c41cf79572181e6) Matt Wills *2020-11-20 17:21:18*
2: Add initial controllers and integration tests (#4)

- Add v3.1.5 of Domestic Payment controllers
- Add SpringBoot tests using an embedded mongo
- Add Test Data Factories and test-jar generation
- Update jackson/joda libraries
- Add converter methods to reduce number of assertions
- Rename module to securebanking-openbanking-aspsp-simulator

Issue: https://github.com/SecureBankingAcceleratorToolkit/SecureBankingAcceleratorToolkit/issues/2
### GitHub [#5](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/pull/5) 2: Add CHANGELOG.md generation to pom.xml
[245861e14329568](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/commit/245861e14329568) Matt Wills *2020-12-08 10:22:15*
5: Swagger specification endpoint (#9)

- Add endpoint to retrieve full swagger specification
 - Add documentation to README.md detailing how to enable/disable API endpoints.

Issue: SecureBankingAcceleratorToolkit/SecureBankingAcceleratorToolkit#5
[cb5ff36119a07df](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/commit/cb5ff36119a07df) Matt Wills *2020-12-07 10:47:35*
5: Discovery blacklist functionality (#8)

- Reject requests (with a 404) to any API endpoint that has been explicitly disabled in the config

Issue: SecureBankingAcceleratorToolkit/SecureBankingAcceleratorToolkit#5
[dc28150f705fe9e](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/commit/dc28150f705fe9e) Matt Wills *2020-12-02 09:59:26*
5: Load all API endpoints from config (#7)

5: Supported endpoints via Discovery API

- Load all available Read/Write API endpoints from application.yml
- Filter supported APIs according to customer's config (if applicable)

Issue: SecureBankingAcceleratorToolkit/SecureBankingAcceleratorToolkit#5
[84d81622e49af84](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/commit/84d81622e49af84) Matt Wills *2020-11-23 14:23:52*
2: Add CHANGELOG.md generation to pom.xml (#5)

- Include first CHANGELOG.md
- Minor addition to README.md

Issue: https://github.com/SecureBankingAcceleratorToolkit/SecureBankingAcceleratorToolkit/issues/2
### GitHub [#6](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/pull/6) Added self link in responses
[eb13c6640053956](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/commit/eb13c6640053956) Matt Wills *2020-11-26 15:11:09*
5: Add self link in responses (#6)

- Use Spring HATEOAS to generate a self link for a resource

Issue: https://github.com/SecureBankingAcceleratorToolkit/SecureBankingAcceleratorToolkit/issues/5
### GitHub [#7](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/pull/7) Supported endpoints via Discovery API
[dc28150f705fe9e](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/commit/dc28150f705fe9e) Matt Wills *2020-12-02 09:59:26*
5: Load all API endpoints from config (#7)

5: Supported endpoints via Discovery API

- Load all available Read/Write API endpoints from application.yml
- Filter supported APIs according to customer's config (if applicable)

Issue: SecureBankingAcceleratorToolkit/SecureBankingAcceleratorToolkit#5
### GitHub [#8](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/pull/8) Discovery blacklist functionality
[cb5ff36119a07df](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/commit/cb5ff36119a07df) Matt Wills *2020-12-07 10:47:35*
5: Discovery blacklist functionality (#8)

- Reject requests (with a 404) to any API endpoint that has been explicitly disabled in the config

Issue: SecureBankingAcceleratorToolkit/SecureBankingAcceleratorToolkit#5
### GitHub [#9](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/pull/9) Swagger specification endpoint and README update
[245861e14329568](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/commit/245861e14329568) Matt Wills *2020-12-08 10:22:15*
5: Swagger specification endpoint (#9)

- Add endpoint to retrieve full swagger specification
 - Add documentation to README.md detailing how to enable/disable API endpoints.

Issue: SecureBankingAcceleratorToolkit/SecureBankingAcceleratorToolkit#5
[b4d0e28645d0542](https://github.com/SecureBankingAcceleratorToolkit/securebanking-openbanking-aspsp/commit/b4d0e28645d0542) Matt Wills *2020-11-19 13:05:05*
2: Initial SpringBoot project

- Add SpringBoot capability and initial test to verify the start up
- Add FR and OB domain models
- Add mongo documents and repositories
- Add OB model to/from FR domain converters
- Add services and common utilities

**Issue:** https://github.com/SecureBankingAcceleratorToolkit/SecureBankingAcceleratorToolkit/issues/2
